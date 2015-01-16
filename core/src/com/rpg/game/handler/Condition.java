package com.rpg.game.handler;



public class Condition {
	private static boolean playerIsAttacking = false;
	private static float lastClickX;
	private static float lastClickY;
	private static float playerPositionX;
	private static float playerPositionY;
	private static boolean isMoving = false;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static boolean isMoving() {return isMoving;}
	public static void setMoving(boolean isMoving) {Condition.isMoving = isMoving;}
	public static void setPlayerPositionX(float playerPositionX) {Condition.playerPositionX = playerPositionX;}
	public static void setPlayerPositionY(float playerPositionY) {Condition.playerPositionY = playerPositionY;	}
	public static float getPlayerPositionX() {return playerPositionX;}
	public static float getPlayerPositionY() {return playerPositionY;}
	public static void setLastClickX(float lastClickX) {Condition.lastClickX = lastClickX;}
	public static void setLastClickY(float lastClickY) {Condition.lastClickY = lastClickY;}
	public static float getLastClickX() {return lastClickX;}
	public static float getLastClickY() {return lastClickY;}
	public static boolean isPlayerIsAttacking() {return playerIsAttacking;}
	public static void setPlayerIsAttacking(boolean playerIsAttacking) {Condition.playerIsAttacking = playerIsAttacking;}
	
	
	
	
}
