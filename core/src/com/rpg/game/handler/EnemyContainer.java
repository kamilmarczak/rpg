package com.rpg.game.handler;

import com.badlogic.gdx.utils.Array;
import com.rpg.game.entities.Entity;

public class EnemyContainer {
	
	
	private static  Array<Entity> SMALLENEMY = new Array<Entity>();

	public static Array<Entity> GETSMALLENEMY() {
		return SMALLENEMY;
	}

	public void SETSMALLENEMY(Array<Entity> smallEnemy) {
		EnemyContainer.SMALLENEMY = smallEnemy;
	}

	
}
