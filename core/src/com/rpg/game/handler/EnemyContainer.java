package com.rpg.game.handler;

import com.badlogic.gdx.utils.Array;
import com.rpg.game.entities.Entity;
import com.rpg.game.entities.creature.Creature;

public class EnemyContainer {
	
	
	private static  Array<Entity> SMALLENEMY = new Array<Entity>();
	private static   Array<Entity> TARGETS = new Array<Entity>();

	
	public static  Array<Entity> getTARGETS() {
		return TARGETS;
	}

	public static Array<Entity> GETSMALLENEMY() {return SMALLENEMY;}
	public void SETSMALLENEMY(Array<Entity> smallEnemy) {EnemyContainer.SMALLENEMY = smallEnemy;}

	
}
