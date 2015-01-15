package com.rpg.game.entities;

import sun.rmi.runtime.Log;

import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.BodyMover;
import com.rpg.game.handler.MyContactListener;
import com.rpg.game.handler.MyTimer;
import com.rpg.game.state.Play;

public class Player extends Entity  {
	private static int COINS = 0;
	private String textureName = "player";
	private String bodyTAG = "player";
	private static int playerHP=100;
	private BodyMover bm;
	private static MyTimer mt=new MyTimer(1);
	private  float playerPositionX;
	private  float playerPositionY;
	private MyContactListener mcl= new MyContactListener();
	

	public float getPlayerPositionX() {return playerPositionX;}
	public void setPlayerPositionX(float playerPositionX) {this.playerPositionX = playerPositionX;}
	public float getPlayerPositionY() {return playerPositionY;}
	public void setPlayerPositionY(float playerPositionY) {	this.playerPositionY = playerPositionY;}
	public static int getPlayerHP() {	return playerHP;}
	public static void setPlayerHP(int playerHP) {	Player.playerHP = playerHP;}
	public static int getCOINS() {return COINS;}
	public static void setCOINS(int COINs) {COINS = COINs;}
	public static void setTimerForAtt() {	mt.start();}






	public Player(int i, int j) {

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
	
		//is moving
		if(Play.isMoving()){
			//not att
			if(!Play.isPlayerIsAttacking()){
			//body mover
			getBody().setLinearVelocity((float)bm.getMovementX(),(float)bm.getMovementY());
			
	//atack
			 }else {
				 
				getBody().setLinearVelocity((float)bm.getMovementX(),(float)bm.getMovementY());
				Play.aniChecker(9);
				if(mt.hasCompleted()){
					Play.setPlayerIsAttacking(false);
				}
				
			 
			}//is at dest
			if ((int) (getBody().getPosition().x * 10) == (int) (Play.getLastClickX() * 10)&& (int) (getBody().getPosition().y * 10) == (int) (Play.getLastClickY() * 10)) {
				Play.setMoving(false);
				
			}
			
		}
		//is not moving
			if(!Play.isMoving()){
			
				//if not moving and not attacking
				if(!Play.isPlayerIsAttacking()){
			getBody().setLinearVelocity(0, 0);
			Play.aniChecker(4);
			}//if is not moving and attacing
				else{
				
				getBody().setLinearVelocity(0, 0);
				Play.setPlayerIsAttacking(true);
				Play.aniChecker(9);
				System.out.println("player is att");
				
				if(mt.hasCompleted()){
				
					Play.setPlayerIsAttacking(false);
					
					System.out.println(" finished attacing");
					
				}
				}
				
				
			}
			
			
			
	}

}


