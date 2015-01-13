package com.rpg.game.entities;

import com.rpg.game.handler.B2DVars;

public class Player2 extends Enemy {
	private static int COINS = 0;
	private String textureName = "player";
	
	private String bodyTAG = "Player";

	public Player2(int i, int j) {
		enemycreator(i, j,bodyTAG, B2DVars.BIT_PLAYER, B2DVars.BIT_DOOR | B2DVars.BIT_ENEMY);
		playAnimation(getAnimationRow(), textureName);

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static int getCOINS() {return COINS;}
	public static void setCOINS(int COINs) {COINS = COINs;}

}
