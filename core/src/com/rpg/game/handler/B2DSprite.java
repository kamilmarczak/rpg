package com.rpg.game.handler;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.rpg.game.AdultGame;
import com.rpg.game.entities.creature.Creature;

public class B2DSprite  {
	
	protected Body body;
	private Animation animation;
	private float width;
	private float height;
	private int animationRow=0;

	
	public B2DSprite(Body body) {
	
		this.body = body;
		setAnimation(new Animation());
	
			}
	


	public B2DSprite(Creature creatur) {
		this.body = creatur.getBody();
	}



	public void setAnimation(TextureRegion reg, float delay) {	
		setAnimation(new TextureRegion[] { reg }, delay);}
	
	public void setAnimation(TextureRegion[] reg, float delay) {

		animation.setFrames(reg, delay);
		setWidth(reg[0].getRegionWidth());
		setHeight(reg[0].getRegionHeight());
	}
	
	public void update(float dt) {

		getAnimation().update(dt);
	}
	
	public void render(SpriteBatch sb) {
	
		int pozX=(int)(getPosition().x * B2DVars.PPM - getWidth() / 2);
		int pozY=(int) (getPosition().y * B2DVars.PPM - getHeight() / 2);
		float w = animation.getFrame().getRegionWidth();
		float h = animation.getFrame().getRegionHeight();
		float ox = w / 2f;
		float oy = h / 2f;
		
		sb.begin();
		sb.draw(getAnimation().getFrame(),pozX ,pozY,	ox, oy, //
				w, h, //
				1, 1, //
				body.getAngle() * MathUtils.radiansToDegrees); // 
		
		
		sb.end();
	

	
	}
	
	public  void playAnimation(int animationRow, String textureName) {

		setAnimationRow(animationRow);

		Texture tex = AdultGame.res.getTexture(textureName);
		TextureRegion[] sprites = new TextureRegion[10];

		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = new TextureRegion(tex, i * 128, animationRow * 128, 128,128);
		}

		getAnimation().setFrames(sprites, 1 / 12f);

		setWidth(sprites[0].getRegionWidth());
		setHeight(sprites[0].getRegionHeight());
	}

	
	
	
	
	
	

	
	public int getAnimationRow() {return animationRow;}
	public void setAnimationRow(int animationRow) {this.animationRow = animationRow;}
	public Body getBody() { return body; }
	public Vector2 getPosition() { return getBody().getPosition(); }
	public float getWidth() { return width; }
	public float getHeight() { return height; }
	public Animation getAnimation() {return animation;	}
	public void setAnimation(Animation animation) {this.animation = animation;}
	public void setWidth(float width) {this.width = width;}
	public void setHeight(float height) {this.height = height;}
}