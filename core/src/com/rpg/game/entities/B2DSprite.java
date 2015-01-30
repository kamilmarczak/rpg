package com.rpg.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.rpg.game.handler.Animation;
import com.rpg.game.handler.B2DVars;

public class B2DSprite  {
	
	protected Body body;
	private Animation animation;
	private float width;
	private float height;

	
	public B2DSprite(Body body) {
	
		this.body = body;
		setAnimation(new Animation());
	
			}
	


	public void setAnimation(TextureRegion reg, float delay) {

		setAnimation(new TextureRegion[] { reg }, delay);
	}
	
	public void setAnimation(TextureRegion[] reg, float delay) {

		animation.setFrames(reg, delay);
		setWidth(reg[0].getRegionWidth());
		setHeight(reg[0].getRegionHeight());
	}
	
	public void update(float dt) {

		getAnimation().update(dt);
	}
	
	public void render(SpriteBatch sb) {
		Vector2 pos = body.getPosition();
		int pozX=(int)(getPosition().x * B2DVars.PPM - getWidth() / 2);
		int pozY=(int) (getPosition().y * B2DVars.PPM - getHeight() / 2);
		float w = getAnimation().getFrame().getRegionWidth();
		float h = getAnimation().getFrame().getRegionHeight();
		float ox = w / 2f;
		float oy = h / 2f;
		
		sb.begin();
		sb.draw(getAnimation().getFrame(),pozX ,pozY,	ox, oy, //
				w, h, //
				1, 1, //
				body.getAngle() * MathUtils.radiansToDegrees); // 
		
		
		sb.end();
	

	
	}
	

	
	
	
	
	
	
	
	
	public Body getBody() { return body; }
	public Vector2 getPosition() { return getBody().getPosition(); }
	
	
	public float getWidth() { return width; }
	public float getHeight() { return height; }



	public Animation getAnimation() {
		return animation;
	}



	public void setAnimation(Animation animation) {
		this.animation = animation;
	}



	public void setWidth(float width) {
		this.width = width;
	}



	public void setHeight(float height) {
		this.height = height;
	}
}