package com.rpg.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.rpg.game.AdultGame;

public class Collectibles extends B2DSprite {
	private String typeColl;

	public Collectibles(Body body,String typeColl) {
		super(body);
		this.body = body;
		this.typeColl= typeColl;
		playAnimation(typeColl);
	}

	
	
	public void playAnimation(String typeColl) {
		this.typeColl = typeColl;

		Texture tex = AdultGame.res.getTexture(typeColl);
	
		TextureRegion[] sprites = TextureRegion.split(tex, 64, 64)[0];

		animation.setFrames(sprites, 1 / 12f);
		width = sprites[0].getRegionWidth();
		height = sprites[0].getRegionHeight();

	}

	
	
	

}
