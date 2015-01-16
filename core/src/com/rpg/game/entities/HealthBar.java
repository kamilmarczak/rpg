package com.rpg.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class HealthBar extends Actor {

    private NinePatchDrawable loadingBarBackground;

    private NinePatchDrawable loadingBar;
    private TextureAtlas skinAtlas;

    public HealthBar() {
    	skinAtlas = new TextureAtlas(Gdx.files.internal("data/uiskin.atlas"));
        
        NinePatch loadingBarBackgroundPatch = new NinePatch(skinAtlas.findRegion("default-round"), 5, 5, 4, 4);
        NinePatch loadingBarPatch = new NinePatch(skinAtlas.findRegion("default-round-down"), 5, 5, 4, 4);
        loadingBar = new NinePatchDrawable(loadingBarPatch);
        loadingBarBackground = new NinePatchDrawable(loadingBarBackgroundPatch);
        
  
    }

    public TextureAtlas getSkinAtlas() {
		return skinAtlas;
	}

	public void draw(Batch batch,int x,int y, Float width ,Float height, float hitPoint) {
        

        loadingBarBackground.draw(batch, x, y+height, width , height/10 );
        loadingBar.draw(batch, x, y+height, hitPoint * width , height /10);
    }

	public void playerHpDraw(SpriteBatch sb, int hitPoint, int x, int y) {
		 loadingBarBackground.draw(sb, x, y, 100 , 20 );
        loadingBar.draw(sb, x, y, hitPoint  , 20);
		
	}


    
}