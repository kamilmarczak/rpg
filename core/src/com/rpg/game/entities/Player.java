package com.rpg.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.rpg.game.AdultGame;
import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.MyTimer;
import com.rpg.game.state.Play;

public class Player extends B2DSprite {


	public static int getCOINS() {
		return COINS;
	}
	public static void setCOINS(int COINs) {
		COINS = COINs;
	}

	private int currentAction = 1;
	private Texture tex;
	private static float playerPositionX;
	private static float playerPositionY;
	private static float lastClickX;
	private static float lastClickY;
	private static boolean isMoving;
	private static double speed = 2;
	private static MyTimer timerPlayer;

	public static int getPlayerHp() {
		return playerHp;
	}
	public static void setPlayerHp(int f) {
		Player.playerHp = f;
	}

	private static int playerHp=100;;

	
	// money
	private static int COINS=0;
	
	
	public Player(Body body) {
		super(body);
		timerPlayer = new MyTimer(1);
	}
public void attack(SmallEnemy smallEnemy){
	
//	smallEnemy.setHitPoint(smallEnemy.getHitPoint() - 0.5f);

	smallEnemy.setHitPoint(smallEnemy.getHitPoint() - 0.5f);
}



	public void playAnimation(int j) {

		Texture tex = AdultGame.res.getTexture("player");
		TextureRegion[] sprites = new TextureRegion[9];

		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = new TextureRegion(tex, i * 64, j * 64, 64, 64);
		}

		animation.setFrames(sprites, 1 / 12f);
		width = sprites[0].getRegionWidth();
		height = sprites[0].getRegionHeight();

	}

	public static void playerControl() {

		playerPositionX = Play.getPlayerPositionX();
		playerPositionY = Play.getPlayerPositionY();

		lastClickX = Play.getLastClickX();
		lastClickY = Play.getLastClickY();
		isMoving = Play.isMoving();

		if (isMoving) {
			
			Play.setPlayerIsAttacking(false);

			// Get the vector between the player and the target
			float pathX = lastClickX - playerPositionX;
			float pathY = lastClickY - playerPositionY;

			// set 1

			// Calculate the unit vector of the path
			double distance = Math.sqrt(pathX * pathX + pathY * pathY);
			double directionX = pathX / distance;
			double directionY = pathY / distance;

			// Calculate the actual walk amount
			double movementX = directionX * speed;
			double movementY = directionY * speed;

			Play.getPlayer().getBody().setLinearVelocity((float) movementX, (float) movementY);
			if ((int) (Play.getPlayer().getBody().getPosition().x * 10) == (int) (lastClickX * 10)
					&& (int) (Play.getPlayer().getBody().getPosition().y * 10) == (int) (lastClickY * 10)) {

				Play.setMoving(false);
			}

		}  else {
			if(!isMoving ){

			Play.getPlayer().getBody().setLinearVelocity(0, 0);
			Play.aniChecker(4);
			
			}
	
			
			
		}

	}


}
