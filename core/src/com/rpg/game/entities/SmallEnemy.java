/*package com.rpg.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.EnemyMover;


public class SmallEnemy extends Entity {





	
	private String textureName="enemySmall";
	private String bodyTAG = "enemy";
	boolean isSensor = false;
	private String sensorTAG="sensorEnemySmall";
	
	
	private int type=3;
	
	private EnemyMover em;
	private SmallEnemy smallEnemy;
	public int getType() {return type;}
	
//path


	public SmallEnemy(int i, int j,int type) {
		super(type);
		em = new EnemyMover();
	enemycreator(i, j,bodyTAG,sensorTAG, B2DVars.BIT_ENEMY, B2DVars.BIT_ENEMY| B2DVars.BIT_PLAYER ,isSensor);
	setEnemyHitPower(1);
	playAnimation(getAnimationRow(),textureName);

	
	}


	@Override
	public void render(SpriteBatch sb) {

		super.render(sb);
		
		sb.begin();
		int pozX=(int)(getBody().getPosition().x * B2DVars.PPM - sprite.getWidth() / 2);
		int pozY=(int) (getBody().getPosition().y * B2DVars.PPM - sprite.getHeight() / 2);
		hp.draw(sb,pozX,pozY ,sprite.getWidth(),sprite.getHeight(),hitPoint);
		sb.end();
	}


	
	

@Override
public void update(float dt) {
	// TODO Auto-generated method stub
	super.update(dt);
	//em.pathStarter((SmallEnemy)getBody().getUserData());
}

public EnemyMover getEm() {
	return em;
}






}
*/