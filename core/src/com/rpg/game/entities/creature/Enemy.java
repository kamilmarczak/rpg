package com.rpg.game.entities.creature;

import java.util.Random;

import com.badlogic.gdx.ai.steer.behaviors.Face;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.rpg.game.data.Data;
import com.rpg.game.data.DataManager;
import com.rpg.game.entities.Coin;
import com.rpg.game.entities.HealthBar;
import com.rpg.game.entities.Mark;
import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.GameMaps;
import com.rpg.game.handler.MyTimer;
import com.rpg.game.handler.actions.Death;
import com.rpg.game.handler.steering.EnemyMover;
import com.rpg.game.state.Play;

public class Enemy extends Creature {
	
	
	
	
	private Player player;
	private int smallCoyoteHp = 100;
	private String textureName;
	private static String bodyTAG = "enemy";
	static boolean isSensor = false;
	private static String sensorTAG = "sensorEnemy";
	private boolean stopMovment = true;
	private MyTimer atackTimer;
	private int currentAnimationRow;
	private boolean visible = true;
	private int type = 3;
	private EnemyMover em;
	private int animationRow = 0;
	private Coin  coins;
	private World world;
	private DataManager dataManager;
	private static short categoryBit = B2DVars.BIT_ENEMY;
	private static int maskBits = B2DVars.BIT_ENEMY | B2DVars.BIT_PLAYER
			| B2DVars.BIT_BULET | B2DVars.BIT_ROOF;

	
	
	
	
	public Enemy(float x, float y,World world,Player player,DataManager dataManager,GameMaps map, String textureName) {
		super(x, y, bodyTAG, sensorTAG, categoryBit, maskBits, isSensor,world);
		this.dataManager= dataManager;
		this.textureName=textureName;
		sprite.playAnimation(animationRow, textureName);
		healthBar = new HealthBar();
		mark = new Mark(this.getBody());
		atackTimer = new MyTimer(2);
 		setHitPower(1);
		setHealth(smallCoyoteHp);
		death= new Death(this,world,dataManager);
		
		em = new EnemyMover(map, dataManager);

		final Face<Vector2> faceSB = new Face<Vector2>(this, this.getTarget()) //
				.setTimeToTarget(0.0003f) //
				.setAlignTolerance(0.01f) //
				.setDecelerationRadius(MathUtils.degreesToRadians * 60);
		this.setMaxAngularAcceleration(100000);
		this.setMaxAngularSpeed(10);
		this.setMaxLinearAcceleration(10);
		this.setMaxLinearSpeed(10);
		this.setIndependentFacing(true);
		this.setTagged(false);
		this.setSteeringBehavior(faceSB);
		this.player=player;
		this.world= world;

	}

	public EnemyMover getEm() {
		return em;
	}

	@Override
	public void render(SpriteBatch sb) {

		sprite.render(sb);
		sb.begin();
		int pozX = (int) (body.getPosition().x * B2DVars.PPM - sprite
				.getWidth() / 2);
		int pozY = (int) (body.getPosition().y * B2DVars.PPM - sprite
				.getHeight() / 2);

		healthBar.draw(sb, pozX, pozY, sprite.getWidth(), sprite.getHeight(),
				getHealth() / 100);
		sb.end();

		if (tagged) {

			mark.render(sb);

		}

	}

	@Override
	public int getType() {
		return type;
	}

	public void setAnimationRow(int animationRow) {
		this.animationRow = animationRow;
	}

	@Override
	public void update(float dt) {
		// if((Creature)body.getUserData()==null)return;
		// if(body==null)return;
		if (animationRow != currentAnimationRow) {
			sprite.playAnimation(animationRow, textureName);

			currentAnimationRow = animationRow;
		}
		steering(dt);
		mark.update(dt);
		sprite.update(dt);

		attack();
		death.update();
		
	}
	
	private void steering(float dt){
		
		if (steeringBehavior != null) {

			// Calculate steering acceleration
			steeringBehavior.calculateSteering(steeringOutput);

			/*
			 * Here you might want to add a motor control layer filtering
			 * steering accelerations.
			 * 
			 * For instance, a car in a driving game has physical constraints on
			 * its movement: it cannot turn while stationary; the faster it
			 * moves, the slower it can turn (without going into a skid); it can
			 * brake much more quickly than it can accelerate; and it only moves
			 * in the direction it is facing (ignoring power slides).
			 */

			// Apply steering acceleration
			applySteering(steeringOutput, dt);

			visibilityCheck();
		}

		if (isTargetRandom()) {

			getBody().setAngularDamping(0);
			stopMovment = true;
			em.pathStarter(
					(Creature) body.getUserData(),
					randInt((int) this.getBody().getPosition().x - 10,
							(int) this.getBody().getPosition().x + 10),
					randInt((int) this.getBody().getPosition().y - 10,
							(int) this.getBody().getPosition().y + 10));

		} else {
			// NOT RANDOM set on player position

			if (((Creature) body.getUserData()).getBody().getPosition().dst2(player.getPosition()) < .6f) {
				((Creature) body.getUserData()).getBody().setAngularDamping(.2f);
				
				
				em.pathStarter((Creature) body.getUserData(), getBody().getPosition().x, getBody().getPosition().y);

			} else {
				if (em.getPath() != null) {

					em.pathStarter((Creature) body.getUserData(), player.getBody().getPosition().x, player.getBody().getPosition().y);
				} else {

					em.pathStarter((Creature) body.getUserData(), player.getPosition().x, player
							.getPosition().y);
				}
			}

			if (stopMovment) {

				em.setReset(true);
				stopMovment = false;
			} else {
				em.setReset(false);
			}

		}
		
	}

	private void visibilityCheck() {

		if (Play.isVisibleRoof() && isInContacWithRoof()) {

			this.setVisible(false);
			setAnimationRow(8);
			healthBar.setVisible(false);
		} else {
			this.setVisible(true);
		}

	}

	public static int randInt(int min, int max) {

		Random rand = new Random();

		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	@Override
	public void attack() {

		if (inCombat && atackTimer.hasCompleted()) {
			// face
			getTarget().getBody().setTransform(
					player.getPlayerPositionX(),
					player.getPlayerPositionY(), 0);

			setAnimationRow(2);
			
			dataManager.getData().setPlayerHp(dataManager.getData().getPlayerHp()-getHitPower());

			
			
			
			atackTimer.start();
		} else if (!inCombat && isVisible()) {
			setAnimationRow(0);
		}

	}

@Override
public void drop() {

	
	coins= 	new Coin(body.getPosition().x/B2DVars.MTT, body.getPosition().y/B2DVars.MTT, world, player,dataManager);
	dataManager.getData().getCoins().add(coins);
}



	
	
	
	
	
	
	
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
