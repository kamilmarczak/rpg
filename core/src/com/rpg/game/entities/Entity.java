package com.rpg.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity  {
	
	protected float x,y;

	
	public Entity(float x, float y) {
		this.x=x;
		this.y=y;
	}
	
	public abstract void update(float dt);
	public abstract void render( SpriteBatch sb);

	

}
