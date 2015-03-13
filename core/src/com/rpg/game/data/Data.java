package com.rpg.game.data;

import com.badlogic.gdx.utils.Array;
import com.rpg.game.entities.Bullets;
import com.rpg.game.entities.Coin;
import com.rpg.game.entities.creature.Creature;

public class Data {
	
	
	private  int playerHp;
	private  int bulletsInInventory;
	private  int cashAmount;
	private float playerX,playerY;
	private transient Array<Creature> Player = new Array<Creature>();
	private transient Array<Creature> ENEMIES = new Array<Creature>();
	private  transient Array<Coin> Coins = new Array<Coin>();
	private  transient Array<Bullets> bulestsList = new Array<Bullets>();

	
	
	
	
	
	
	
	
	
	
	
	
	public void setPlayer(Array<Creature> player) {
		Player = player;
	}
	///////get and set
	public  int  getPlayerHp() {return playerHp;}
	public void setPlayerHp(int playerHp) {this.playerHp = playerHp;}
	public int getBulets() {return bulletsInInventory;}
	public void setBulets(int bulets) {this.bulletsInInventory = bulets;}

	////////
	public  void subtractCashAmount(int cashAmount) {this.cashAmount -= cashAmount;}
	public  void addCashAmount(int cashAmount) {if(this.cashAmount>=1000000000)return;this.cashAmount += cashAmount;}
	public  int getCashAmount() {return cashAmount;}
	public  void setCashAmount(int cashAmount) {	this.cashAmount = cashAmount;}
	public  Array<Creature> getENEMIES() {return ENEMIES;	}
	public  Array<Coin> getCoins() {return Coins;}
	public   Array<Bullets> getBulestsList() {return bulestsList;}
	
	public Array<Creature> getPlayer() {
		return Player;

	}
	public float getPlayerX() {
		return playerX;
	}
	public void setPlayerX(float playerX) {
		this.playerX = playerX;
	}
	public float getPlayerY() {
		return playerY;
	}
	public void setPlayerY(float playerY) {
		this.playerY = playerY;
	}


}
