package com.rpg.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.rpg.game.AdultGame;
import com.rpg.game.handler.B2DSprite;

public class Teleport extends B2DSprite {

	public Teleport(Body body, String a) {
		super(body);

/*			Texture tex = AdultGame.res.getTexture("portal");
			TextureRegion[] sprites = TextureRegion.split(tex, 64, 64)[0];
			animation.setFrames(sprites, 1 / 12f);
			
			width = sprites[0].getRegionWidth();
			height = sprites[0].getRegionHeight();
		System.out.println(	body.getUserData());
		*/
		
			
			
			if(a.equals("back")){
				Texture tex = AdultGame.res.getTexture("portal");
				TextureRegion[] sprites = TextureRegion.split(tex, 64, 64)[0];
				getAnimation().setFrames(sprites, 1 / 12f);
				
				setWidth(sprites[0].getRegionWidth());
				setHeight(sprites[0].getRegionHeight());
				}else if(a.equals("forward")) {
					Texture tex = AdultGame.res.getTexture("portal2");
					TextureRegion[] sprites = TextureRegion.split(tex, 64, 64)[0];
					getAnimation().setFrames(sprites, 1 / 12f);
					
					setWidth(sprites[0].getRegionWidth());
					setHeight(sprites[0].getRegionHeight());
					
				}else{}
		
		
		
	}

}
