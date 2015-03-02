package com.rpg.game.handler;

import com.badlogic.gdx.utils.Array;
import com.rpg.game.entities.Coin;
import com.rpg.game.entities.Entity;
import com.rpg.game.entities.creature.Creature;

public class ContendHolder {
	
	private static int cashAmount=0;
	private static  Array<Creature> ENEMIES = new Array<Creature>();
	private static  Array<Coin> Coins = new Array<Coin>();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void subtractCashAmount(int cashAmount) {
		ContendHolder.cashAmount -= cashAmount;
	}
	public static void addCashAmount(int cashAmount) {
		ContendHolder.cashAmount += cashAmount;
	}
	
	public static int getCashAmount() {
		return cashAmount;
	}
	public static void setCashAmount(int cashAmount) {
		ContendHolder.cashAmount = cashAmount;
	}
	
	public static Array<Creature> getENEMIES() {return ENEMIES;	}
	//public static void setENEMIES(Array<Creature> eNEMIES) {	ENEMIES = eNEMIES;}
	public static Array<Coin> getCoins() {return Coins;}




	
}
