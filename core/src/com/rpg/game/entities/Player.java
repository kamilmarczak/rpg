package com.rpg.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.BodyMover;
import com.rpg.game.handler.Condition;
import com.rpg.game.handler.MyTimer;
import com.rpg.game.handler.PlayerControler;
import com.rpg.game.state.Play;

public class Player extends Entity  {
	private static int COINS = 0;
	private String textureName = "player";
	private String bodyTAG = "player";
	private static int playerHP=100;
	private static MyTimer mt=new MyTimer(1);
	private static BodyMover bm;

	private Array<Coin> coinsArray=new Array<Coin>();

	
	
	
	
	
	
	
	public Array<Coin> getCoinsArray() {return coinsArray;	}
	public void setCoinsArray(Array<Coin> coinsArray) {this.coinsArray = coinsArray;}
	public float getPlayerPositionX() {return getBody().getPosition().x;}
	public float getPlayerPositionY() {return getBody().getPosition().y;}
	public static int getPlayerHP() {	return playerHP;}
	public static void setPlayerHP(int playerHP) {	Player.playerHP = playerHP;}
	public static int getCOINS() {return COINS;}
	public static void setCOINS(int COINs) {COINS = COINs;}
	public static void setTimerForAtt() {	mt.start();}
	boolean isSensor = false;
	private PlayerControler pc= new PlayerControler();


	public Player(int i, int j) {
		enemycreator(i, j,bodyTAG, 
				B2DVars.BIT_PLAYER,
				B2DVars.BIT_ENEMY |
				B2DVars.BIT_DOOR |
				B2DVars.BIT_PORTAL_FORWARD |
				B2DVars.BIT_PORTAL_BACK |
				B2DVars.COLLECTA,false);
		
		playAnimation(getAnimationRow(), textureName);
	

	}

	
@Override
public void update(float dt) {
	// TODO Auto-generated method stub
	super.update(dt);
	pc.startControl();
	damage() ;
	
}
public  void damage() {
// damage
Array<Body> bodiesDmg = Play.getCl().getDamege();



for (int i = 0; i < bodiesDmg.size; i++) {

Body b = bodiesDmg.get(i);
//Enemy Fallow player
		bm= new BodyMover(((Entity) b.getUserData()).getBody().getPosition().x,
						  ((Entity) b.getUserData()).getBody().getPosition().y,
						  getPlayerPositionX(), getPlayerPositionY(), 1);
		((Entity) b.getUserData()).getBody().setLinearVelocity((float)bm.getMovementX(),(float)bm.getMovementY());
			//Enemy Attack
		((Entity) b.getUserData()).attack();

//Player attack
if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
	Condition.setPlayerIsAttacking(true);
	setTimerForAtt();

		((Entity) b.getUserData()).setHitPoint(((Entity) b.getUserData()).getHitPoint() - 0.5f);
		ishidead((Entity) b.getUserData());
		}
	}		
}

public void ishidead(Entity entity){
	
	if (entity.getHitPoint() <= 0) {
	
	float posX=entity.getBody().getPosition().x;
	float posY=entity.getBody().getPosition().y;
	
		createCoin(posX,posY);
		entity.getHp().getSkinAtlas().dispose();
		Play.getEnemy().removeValue(entity,false);
		Play.getWorld().destroyBody(entity.getBody());
		
	}
	
}

public void createCoin(float posX, float posY){

	Coin coin = new Coin(posX, posY);
	coinsArray.add(coin);
	coin.getBody().setUserData(coin);
	
	}
}
