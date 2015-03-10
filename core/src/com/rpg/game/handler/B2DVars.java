package com.rpg.game.handler;


public class B2DVars {
	
	public static void setZOOM(float zOOM) {
		if(zOOM<.2)return;
		if(zOOM>2)return;
		ZOOM = zOOM;
	}

	//pixel ratio
	public static final float PPM = 100;
	public static final float MTT = 0.64f;
	private static float ZOOM = 1;
	public static float INTERFACESCALE = 2;
	

	
	public static float getZOOM() {
		return ZOOM;
	}

	//category bits
	public static final short BIT_PLAYER =2;
	public static final short BIT_DOOR = 4;
	public static final short BIT_PORTAL_FORWARD = 8;
	public static final short BIT_PORTAL_BACK = 16;
	public static final short BIT_ENEMY = 32;
	public static final short BIT_COLLECTA = 64;
	public static final short BIT_PLAYER_SENSOR =128;
	public static final short BIT_TARGET =256;
	public static final short BIT_NPC =512;
	public static final short BIT_ROOF =1024;
	public static final short BIT_BULET =2048;
	
	//Enemy type
	public static int SMALLENEMY =1,COIN =2, PLAYER =3;
	
}
