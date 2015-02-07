package com.rpg.game.handler;

import com.badlogic.gdx.utils.Array;
import com.rpg.game.entities.Entity;
import com.rpg.game.entities.creature.Creature;

public class EnemyContainer {
	
	
	private static  Array<Creature> SMALLENEMY = new Array<Creature>();
	private static   Array<Creature> TARGETS = new Array<Creature>();

	
	public static  Array<Creature> getTARGETS() {
		return TARGETS;
	}

	public static Array<Creature> GETSMALLENEMY() {return SMALLENEMY;}
	public void SETSMALLENEMY(Array<Creature> smallEnemy) {EnemyContainer.SMALLENEMY = smallEnemy;}

	
}
