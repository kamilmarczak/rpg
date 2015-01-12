package com.rpg.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Timer;
import com.rpg.game.AdultGame;
import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.MyTimer;

public class SmallEnemy extends B2DSprite {
	private int animationRow;
	Body body;
	private HealthBar hp;
	private MyTimer timer;
	private boolean isFighting= false;

	
	

	protected AdultGame gameAdu;
	private float hitPoint= 1;
	

	public float getHitPoint() {	return hitPoint;}
	public HealthBar getHp() {return hp;}
	public void setHitPoint(float hitPoint) {this.hitPoint = hitPoint;	}



	public SmallEnemy(Body body) {
		super(body);
		this.body =body;
		playAnimation(animationRow);
		hp= new HealthBar();
		timer= new MyTimer(1);
		timer.start();

		
	}
	


	public void playAnimation(int animationRow) {
		
		this.animationRow= animationRow;

		Texture tex = AdultGame.res.getTexture("enemySmall");
		TextureRegion[] sprites = new TextureRegion[9];

		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = new TextureRegion(tex, i * 64, animationRow * 64, 64,
					64);
		}

		animation.setFrames(sprites, 1 / 12f);

		width = sprites[0].getRegionWidth();
		height = sprites[0].getRegionHeight();


	}
	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		super.render(sb);
		
		sb.begin();
		int pozX=(int)(body.getPosition().x * B2DVars.PPM - width / 2);
		int pozY=(int) (body.getPosition().y * B2DVars.PPM - height / 2);
		hp.draw(sb,pozX,pozY ,width,height,hitPoint);
		sb.end();

		
	}
	public void attack(Player player)
	{
	

	
		if(Player.getPlayerHp()>6 && timer.hasCompleted()){
			
				Player.setPlayerHp(Player.getPlayerHp()-1);
				timer.start();
				

		}
	}
	

	public void setFighting(boolean isFighting) {
		this.isFighting = isFighting;
	}
	public boolean isFighting() {
		return isFighting;
	}
	public int getAnimationRow() {
		return animationRow;
	}

	public void setAnimationRow(int animationRow) {
		this.animationRow = animationRow;
	}
	public Body getBody() {
		return body;
	}

}
