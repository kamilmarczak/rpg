package com.rpg.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.BodyMover;
import com.rpg.game.state.Play;

public class Player extends Enemy  {
	private static int COINS = 0;
	private String textureName = "player";
	private static int playerHP=100;
	
	
	
	private  float playerPositionX;
	private  float playerPositionY;

	private BodyMover bm;
	
	public static int getPlayerHP() {
		return playerHP;
	}

	public static void setPlayerHP(int playerHP) {
		Player.playerHP = playerHP;
	}




	private String bodyTAG = "enemy";


	public Player(int i, int j) {
	
		
		enemycreator(i, j,bodyTAG, B2DVars.BIT_PLAYER, B2DVars.BIT_DOOR , B2DVars.BIT_ENEMY);
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
		System.out.println(Play.getLastClickX());
		bm = new BodyMover(playerPositionX, playerPositionY, Play.getLastClickX(), Play.getLastClickY(), 2);
		
		//Play.setMoving(true);
	
		
		if(Play.isMoving()){
		getBody().setLinearVelocity((float)bm.getMovementX(),(float)bm.getMovementY());
		}
		
		
		System.out.println( Play.isMoving() );
		
		
			if ((int) (getBody().getPosition().x * 10) == (int) (Play.getLastClickX() * 10)
					&& (int) (getBody().getPosition().y * 10) == (int) (Play.getLastClickY() * 10)) {
			
				Play.setMoving(false);
			} else {
			
			if(!Play.isMoving()){

			getBody().setLinearVelocity(0, 0);
			Play.aniChecker(4);
			
			}}
			
			
	}

	public static int getCOINS() {return COINS;}
	public static void setCOINS(int COINs) {COINS = COINs;}


}


