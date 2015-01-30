package com.rpg.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.rpg.game.AdultGame;

public class Door extends B2DSprite {

	public Door(Body body, String a) {
		super(body);
	//	if (a.equals("door")) {
	
			Texture text = AdultGame.res.getTexture("door");
			TextureRegion[] sprites = TextureRegion.split(text, 64, 64)[0];
			getAnimation().setFrames(sprites, 1 / 12f);
			setWidth(sprites[0].getRegionWidth());
			setHeight(sprites[0].getRegionHeight());
		
			
	//}

	}

}
