package com.rpg.game.data;


import java.util.HashMap;

import com.badlogic.gdx.utils.Array;
import com.rpg.game.entities.Bullets;
import com.rpg.game.entities.Coin;
import com.rpg.game.entities.creature.Creature;
import com.rpg.game.entities.creature.Npc;

public class Data {
	
	
	private  int playerHp;
	private  int bulletsInInventory;
	private  int cashAmount;
	private float playerX,playerY;
	private boolean allowDiagMovement;
	private int objectiveType;
	private int objectiveCount;
	private int objectiveprogress;
	private boolean questdDone;
	
	
	

	
	
	
	

	private  HashMap<String,String> Quests = new HashMap<String,String>();
	
	
	
	private transient Array<Creature> Player = new Array<Creature>();
	private transient Array<Creature> ENEMIES = new Array<Creature>();
	private  transient Array<Coin> Coins = new Array<Coin>();
	private  transient Array<Bullets> bulestsList = new Array<Bullets>();
	private  transient Array<Npc> npc = new Array<Npc>();

	
	
	
	//
	private transient int wolfOnMap;
	private int wolfNeededForQuest;
	private int wolfKilledForQuest;
	
	
	
	
	public void setPlayer(Array<Creature> player) {Player = player;}
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
	public Array<Creature> getPlayer() {return Player;}
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
	public boolean isAllowDiagMovement() {
		return allowDiagMovement;
	}
	public void setAllowDiagMovement(boolean allowDiagMovement) {
		this.allowDiagMovement = allowDiagMovement;
	}
	public HashMap<String, String> getQuests() {
		return Quests;
	}
	public void setQuests(HashMap<String, String> quests) {
		Quests = quests;
	}
	public Array<Npc> getNpc() {
		return npc;
	}
	public int getObjectiveType() {
		return objectiveType;
	}
	public int getObjectiveCount() {
		return objectiveCount;
	}
	public void setObjectiveType(int objectiveType) {
		this.objectiveType = objectiveType;
	}
	public void setObjectiveCount(int objectiveCount) {
		this.objectiveCount = objectiveCount;
	}
	public int getObjectiveprogress() {
		return objectiveprogress;
	}
	public void setObjectiveprogress(int objectiveprogress) {
		this.objectiveprogress = objectiveprogress;
	}
	public boolean isQuestdDone() {
		return questdDone;
	}
	public void setQuestdDone(boolean questdDone) {
		this.questdDone = questdDone;
	}
	public int getWolfOnMap() {
		return wolfOnMap;
	}
	public void setWolfOnMap(int wolfOnMap) {
		this.wolfOnMap = wolfOnMap;
	}
	public int getWolfNeededForQuest() {
		return wolfNeededForQuest;
	}
	public void setWolfNeededForQuest(int wolfNeededForQuest) {
		this.wolfNeededForQuest = wolfNeededForQuest;
	}
	public int getWolfKilledForQuest() {
		return wolfKilledForQuest;
	}
	public void setWolfKilledForQuest(int wolfKilledForQuest) {
		this.wolfKilledForQuest = wolfKilledForQuest;
	}


}
