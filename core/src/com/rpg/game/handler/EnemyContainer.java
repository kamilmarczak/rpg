package com.rpg.game.handler;

import com.badlogic.gdx.utils.Array;
import com.rpg.game.entities.Entity2;

public class EnemyContainer {
	
	
	private static  Array<Entity2> SMALLENEMY = new Array<Entity2>();

	public static Array<Entity2> GETSMALLENEMY() {
		return SMALLENEMY;
	}

	public void SETSMALLENEMY(Array<Entity2> smallEnemy) {
		EnemyContainer.SMALLENEMY = smallEnemy;
	}

	
}
