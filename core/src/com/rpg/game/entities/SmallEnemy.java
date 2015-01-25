package com.rpg.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rpg.game.handler.B2DVars;


public class SmallEnemy extends Entity {





	
	private String textureName="enemySmall";
	private String bodyTAG = "enemy";
	boolean isSensor = false;
	private String sensorTAG="sensorEnemySmall";
	private int type=3;
	public int getType() {return type;}
	
//path


	public SmallEnemy(int i, int j,int type) {
		super(type);

	enemycreator(i, j,bodyTAG,sensorTAG, B2DVars.BIT_ENEMY, B2DVars.BIT_ENEMY| B2DVars.BIT_PLAYER ,isSensor);
	setEnemyHitPower(1);
	playAnimation(getAnimationRow(),textureName);

	
	}


	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		super.render(sb);
		sb.begin();
		int pozX=(int)(getBody().getPosition().x * B2DVars.PPM - sprite.width / 2);
		int pozY=(int) (getBody().getPosition().y * B2DVars.PPM - sprite.height / 2);
		hp.draw(sb,pozX,pozY ,sprite.width,sprite.height,hitPoint);
		sb.end();
	}









}
