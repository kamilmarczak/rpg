package com.rpg.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.rpg.game.AdultGame;
import com.rpg.game.handler.B2DVars;


public class SmallEnemy extends Enemy {

	private String textureName="enemySmall";
	private String bodyTAG = "enemy";

	

	public SmallEnemy(int i, int j) {
	enemycreator(i, j,bodyTAG,B2DVars.BIT_ENEMY,B2DVars.BIT_PLAYER);
	setEnemyHitPower(1);
	playAnimation(getAnimationRow(),textureName);
	
	
	}










}
