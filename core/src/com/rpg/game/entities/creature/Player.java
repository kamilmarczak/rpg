package com.rpg.game.entities.creature;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.BodyMover;
import com.rpg.game.handler.MyTimer;
import com.rpg.game.state.Play;

public class Player extends Creature {
	private String textureName = "player";
	private static String bodyTAG = "player";
	private static String sensorTAG = "playerSensor";
	private static int playerHP = 100;
	private int type = 3;

	public Player(float x, float y) {
		super(x, y, bodyTAG, sensorTAG, B2DVars.BIT_PLAYER, B2DVars.BIT_ENEMY
				| B2DVars.BIT_DOOR | B2DVars.BIT_PORTAL_FORWARD
				| B2DVars.BIT_PORTAL_BACK | B2DVars.COLLECTA, false);
		playAnimation(getAnimationRow(), textureName);

	}

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
		damage();
	
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
	public static int getPlayerHP() {	return playerHP;}
	public static void setPlayerHP(int playerHP) {	Player.playerHP = playerHP;}
	public static void setTimerForAtt() {	mt.start();}
	boolean isSensor = false;

	public String getEnemyTextureName() {

		return textureName;
	}

}
