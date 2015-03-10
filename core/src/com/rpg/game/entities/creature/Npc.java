package com.rpg.game.entities.creature;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.rpg.game.handler.B2DVars;

public class Npc extends Creature{
	
	
	private static String bodyTAG = "npc";
	private static String sensorTAG = "npcSensor";
	private static short categoryBit =B2DVars.BIT_NPC;
	private static int maskBits = B2DVars.BIT_PLAYER;
	
	static boolean isSensor = false;

	private  int animationRow=0;
	private int type = 4;
	private String textureName = "npc";

	
	public int getAnimationRow() {return animationRow;}
	public void setAnimationRow(int animationRow) {this.animationRow = animationRow;}


	public Npc(float x, float y,World world,Player player) {
		super(x, y, bodyTAG, sensorTAG, categoryBit, maskBits, isSensor,world);
		sprite.playAnimation(animationRow, textureName);
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public void update(float dt) {
		
		
	}

	@Override
	public void render(SpriteBatch sb) {
		sprite.render(sb);
		
	}

	@Override
	public void attack() {
	
		
	}

}
