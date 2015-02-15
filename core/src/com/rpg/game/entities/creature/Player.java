package com.rpg.game.entities.creature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.steer.behaviors.Face;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.MyTimer;
import com.rpg.game.handler.steering.BodyMover;
import com.rpg.game.handler.steering.EnemyMover;
import com.rpg.game.handler.steering.PlayerControler;
import com.rpg.game.state.Play;

public class Player extends Creature {
	
	private static String bodyTAG = "player";
	private static String sensorTAG = "playerSensor";
	private static short categoryBit =B2DVars.BIT_PLAYER;
	private static int maskBits = 
			B2DVars.BIT_ENEMY
			| B2DVars.BIT_DOOR 
			| B2DVars.BIT_PORTAL_FORWARD
			| B2DVars.BIT_PORTAL_BACK 
			| B2DVars.BIT_COLLECTA;
	static boolean isSensor = false;

	private  int animationRow=0;
	private int type = 3;
	private String textureName = "npc";
//	private String textureName = "player";
	private int playerHp=100;
	private MyTimer atackTimer;
	
	
	private PlayerControler pc=new PlayerControler();;




	//
	

	public int getAnimationRow() {return animationRow;}
	public void setAnimationRow(int animationRow) {this.animationRow = animationRow;}

	
	public Player(float x, float y) {
		super(x, y, bodyTAG, sensorTAG, categoryBit, maskBits, isSensor);
		sprite.playAnimation(animationRow, textureName);
		setHealth(playerHp);
		setEnemyHitPower(10);
		atackTimer= new MyTimer(1);
		
		this.setMaxAngularAcceleration(1000000);
		this.setMaxAngularSpeed(35);
		this.setIndependentFacing(true);
		this.setTagged(false);
		this.setSteeringBehavior(faceSB);
		
	
	}

	final Face<Vector2> faceSB = new Face<Vector2>(this, this.getTarget()) 
			.setTimeToTarget(0.001f) 
			.setAlignTolerance(0.001f) 
			.setDecelerationRadius(MathUtils.degreesToRadians * 360);

	
	@Override
	public void render(SpriteBatch sb) {
		sprite.render(sb);
		

	}

	@Override
	public int getType() {

		return type;
	}

	@Override
	public void update(float dt) {
		//damage();
		attack();
	
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
		pc.startControl(this);
		
	}
	



	

	public void damage() {

		Array<Body> bodiesDmg = Play.getCl().getDamege();
		Array<Body> fallowDmg = Play.getCl().getFallow();

		/*
		 * //Enemy Fallow player for(int j =0; j<fallowDmg.size; j++){ Body f =
		 * fallowDmg.get(j); bm= new BodyMover(((Entity)
		 * f.getUserData()).getBody().getPosition().x, ((Entity)
		 * f.getUserData()).getBody().getPosition().y, getPlayerPositionX(),
		 * getPlayerPositionY(), 1); ((Entity)
		 * f.getUserData()).getBody().setLinearVelocity
		 * ((float)bm.getMovementX(),(float)bm.getMovementY());
		 * 
		 * }
		 */
		// Enemy attack player
/*		for (int i = 0; i < bodiesDmg.size; i++) {
			Body b = bodiesDmg.get(i);

			((Entity) b.getUserData()).attack();

			// Player attack
			if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
				Condition.setPlayerIsAttacking(true);
				setTimerForAtt();

				((Entity) b.getUserData()).setHitPoint(((Entity) b
						.getUserData()).getHitPoint() - 0.5f);
				ishidead((Entity) b.getUserData());
			}
		}*/

	}

/*	public void ishidead(Entity entity) {

		if (entity.getHitPoint() <= 0) {

			float posX = entity.getBody().getPosition().x;
			float posY = entity.getBody().getPosition().y;

			createCoin(posX, posY, B2DVars.COIN);
			entity.getHp().getSkinAtlas().dispose();
			EnemyContainer.GETSMALLENEMY().removeValue(entity, false);
			Play.getWorld().destroyBody(entity.getBody());

		}

	}*/

/*	public void createCoin(float posX, float posY, int money) {

		Coin coin = new Coin(posX, posY, money);

		coinsArray.add(coin);
		coin.getBody().setUserData(coin);

	}*/
	
	
	
	
	private static MyTimer mt=new MyTimer(1);
	private static BodyMover bm;
	
/*	private static int COINS = 0;
	private Array<Coin> coinsArray=new Array<Coin>();
	public Array<Coin> getCoinsArray() {return coinsArray;	}
	public void setCoinsArray(Array<Coin> coinsArray) {this.coinsArray = coinsArray;}
	public static int getCOINS() {return COINS;}
	public static void setCOINS(int COINs) {COINS = COINs;}*/
	
	
	public float getPlayerPositionX() {return body.getPosition().x;}
	public float getPlayerPositionY() {return body.getPosition().y;}
	public static void setTimerForAtt() {	mt.start();}
	
	
	
	@Override
	public void attack() {
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
		if(Play.getMyInputHandler().getTagHolder()!=null&& atackTimer.hasCompleted()){
		if(Play.getMyInputHandler().getTagHolder().getPosition().dst2(Play.getPlayer().getPosition())>1){
		Play.getMyInputHandler().getTagHolder().setHealth(Play.getMyInputHandler().getTagHolder().getHealth()-getEnemyHitPower() );
		atackTimer.start();
		
		
				}
			}
		}
		
	}
	
	
	
	
	
	


}
