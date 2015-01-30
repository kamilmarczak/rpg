package com.rpg.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.rpg.game.state.Play;

public abstract class Entity2  {
	
	protected float x,y;
	protected World world =Play.getWorld();
	
	public Entity2(float x, float y) {
		this.x=x;
		this.y=y;
	}
	
	public abstract void update(float dt);
	public abstract void render( SpriteBatch sb);
	

}
