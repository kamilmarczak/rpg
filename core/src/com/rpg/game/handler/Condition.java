package com.rpg.game.handler;

import com.rpg.game.state.Play;



public class Condition {
	private static boolean playerIsAttacking = false;
	private static float lastClickX;
	private static float lastClickY;

	private static boolean isMoving = false;
	private static boolean triger= true;
	

	
	public static boolean isMoving() {return isMoving;}
	public static void setMoving(boolean isMoving) {Condition.isMoving = isMoving;}
	public static void setLastClickX(float lastClickX) {Condition.lastClickX = lastClickX;}
	public static void setLastClickY(float lastClickY) {Condition.lastClickY = lastClickY;}
	public static float getLastClickX() {return lastClickX;}
	public static float getLastClickY() {return lastClickY;}
	public static boolean isPlayerIsAttacking() {return playerIsAttacking;}
	public static void setPlayerIsAttacking(boolean playerIsAttacking) {Condition.playerIsAttacking = playerIsAttacking;}
	public static boolean isTriger() {
		return triger;
	}
	public static void setTriger(boolean triger) {
		Condition.triger = triger;
	}
	
	
	
	
}
