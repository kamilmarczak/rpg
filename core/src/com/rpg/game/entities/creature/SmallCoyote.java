package com.rpg.game.entities.creature;

import java.util.Random;

import com.badlogic.gdx.ai.steer.behaviors.Face;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.rpg.game.entities.HealthBar;
import com.rpg.game.entities.Mark;
import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.steering.EnemyMover;
import com.rpg.game.state.Play;

public class SmallCoyote extends Creature {
	
	
	private int smallCoyoteHp=100;
	private String textureName="enemySmall";
	private static String bodyTAG = "enemy";
	static boolean isSensor = false;
	private static String sensorTAG="sensorEnemy";
	private boolean stopMovment=true;

	
	
	
	
	private int type=3;
	private EnemyMover em;
	private int animationRow=0;





	private static short categoryBit =B2DVars.BIT_ENEMY;
	
	private static int maskBits = 
			B2DVars.BIT_ENEMY|
			B2DVars.BIT_PLAYER;






	public SmallCoyote(float x, float y) {
		super(x, y, bodyTAG, sensorTAG, categoryBit, maskBits, isSensor);
		sprite.playAnimation(animationRow, textureName);
		healthBar= new HealthBar();
		mark= new Mark(this.getBody());
	
		setEnemyHitPower(1);
		
		setHealth(smallCoyoteHp);
		em = new EnemyMover();
		
		final Face<Vector2> faceSB = new Face<Vector2>(this, this.getTarget()) //
				.setTimeToTarget(0.001f) //
				.setAlignTolerance(0.001f) //
				.setDecelerationRadius(MathUtils.degreesToRadians * 360);
				
				
		this.setMaxAngularAcceleration(1000000);
		this.setMaxAngularSpeed(35);
		this.setMaxLinearAcceleration(10);
		this.setMaxLinearSpeed(10);
	
		this.setIndependentFacing(true);
		this.setTagged(false);
		this.setSteeringBehavior(faceSB);
		

	}

	
	
	
	

	
	
	
	
	
	
	
	public EnemyMover getEm() {
		return em;
	}

	@Override
	public void render(SpriteBatch sb) {
		
	sprite.render(sb);
		sb.begin();
		int pozX=(int)(body.getPosition().x * B2DVars.PPM - sprite.getWidth() / 2);
		int pozY=(int) (body.getPosition().y * B2DVars.PPM - sprite.getHeight() / 2);
		healthBar.draw(sb,pozX,pozY ,sprite.getWidth(),sprite.getHeight(),getHealth()/100);
		sb.end();
		
		if(tagged){
		
			mark.render(sb);
			
		}
		

		
	}

	@Override
	public int getType() {return type;}

	@Override
	public void update(float dt) {
		
		
			mark.update(dt);
	sprite.update(dt);
	if (steeringBehavior != null) {
		
		// Calculate steering acceleration
		steeringBehavior.calculateSteering(steeringOutput);

		
		/*
		 * Here you might want to add a motor control layer filtering steering accelerations.
		 * 
		 * For instance, a car in a driving game has physical constraints on its movement: it cannot turn while stationary; the
		 * faster it moves, the slower it can turn (without going into a skid); it can brake much more quickly than it can
		 * accelerate; and it only moves in the direction it is facing (ignoring power slides).
		 */
		 
		
		// Apply steering acceleration
		applySteering(steeringOutput, dt);
	}
	
	if(isTargetRandom()){
	em.pathStarter((Creature)body.getUserData(), 
			randInt((int)this.getBody().getPosition().x-10,(int) this.getBody().getPosition().x+10),
			randInt((int)this.getBody().getPosition().y-10,(int) this.getBody().getPosition().y+10));
	stopMovment=true;

	}else {
		
		em.pathStarter((Creature)body.getUserData(), 
				Play.getPlayer().getBody().getPosition().x,
				Play.getPlayer().getBody().getPosition().y);
		if(stopMovment){
			em.setReset(true);
			stopMovment=false;
		}else {
			em.setReset(false);
		}
		
		
	}
	}
	

	
	
	public static int randInt(int min, int max) {


	    Random rand = new Random();


	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	
	
	
	
	


}
