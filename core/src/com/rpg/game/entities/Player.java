package com.rpg.game.entities;

import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.BodyMover;
import com.rpg.game.handler.MyTimer;
import com.rpg.game.state.Play;

public class Player extends Entity  {
	private static int COINS = 0;
	private String textureName = "player";
	private String bodyTAG = "player";
	private static int playerHP=100;
	private BodyMover bm;
	private MyTimer mt;
	private  float playerPositionX;
	private  float playerPositionY;
	

	public float getPlayerPositionX() {return playerPositionX;}
	public void setPlayerPositionX(float playerPositionX) {this.playerPositionX = playerPositionX;}
	public float getPlayerPositionY() {return playerPositionY;}
	public void setPlayerPositionY(float playerPositionY) {	this.playerPositionY = playerPositionY;}
	public static int getPlayerHP() {	return playerHP;}
	public static void setPlayerHP(int playerHP) {	Player.playerHP = playerHP;}
	public static int getCOINS() {return COINS;}
	public static void setCOINS(int COINs) {COINS = COINs;}





	public Player(int i, int j) {
	mt= new MyTimer(1);
		
	//	enemycreator(i, j,bodyTAG, B2DVars.BIT_PLAYER, B2DVars.BIT_DOOR | B2DVars.BIT_ENEMY);
		enemycreator(i, j,bodyTAG, 
				B2DVars.BIT_PLAYER,
				B2DVars.BIT_ENEMY |
				B2DVars.BIT_DOOR |
				B2DVars.BIT_PORTAL_FORWARD |
				B2DVars.BIT_PORTAL_BACK |
				B2DVars.COLLECTA|
				B2DVars.BIT_DOOR);
		
		playAnimation(getAnimationRow(), textureName);

	getBody().setLinearVelocity(1, 1);

	}




	@Override
	public void update(float dt) {
		super.update(dt);
		playerControl();
	}

	public void playerControl() {
		
	
		playerPositionX = getBody().getPosition().x;
		playerPositionY = getBody().getPosition().y;
	
		bm = new BodyMover(playerPositionX, playerPositionY, Play.getLastClickX(), Play.getLastClickY(), 2);
		
		//Play.setMoving(true);
	
		
		if(Play.isMoving()){
		getBody().setLinearVelocity((float)bm.getMovementX(),(float)bm.getMovementY());
		}
		
		
	
		
			if ((int) (getBody().getPosition().x * 10) == (int) (Play.getLastClickX() * 10)
					&& (int) (getBody().getPosition().y * 10) == (int) (Play.getLastClickY() * 10)) {
			
				Play.setMoving(false);
			} else {
			
			if(!Play.isMoving()){
	
			getBody().setLinearVelocity(0, 0);
			Play.aniChecker(4);
}
			}
			
			
	}




}


