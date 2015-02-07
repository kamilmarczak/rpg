package com.rpg.game.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.rpg.game.entities.creature.Creature;
import com.rpg.game.handler.Animation;
import com.rpg.game.handler.B2DSprite;
import com.rpg.game.state.Play;

public class Mark extends B2DSprite {

	private  int animationRow=0;
	private String textureName = "mark";
	private Animation animation;
	private int width;
	private int height;
	private Body targetsBody;
	private Creature  creature;
	
	
	
	
	
	public Mark(Body body) {
		super(body);
		this.targetsBody=body;
		this.creature= ((Creature)body.getUserData());
		
		playAnimation(animationRow, textureName);
		animation = new Animation();
	
	
	}
	
	
	
	
	
	
	@Override
	public void update(float dt) {
		super.update(dt);
	
		
	}




	

 
}