package com.rpg.game.entities.creature;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rpg.game.entities.HealthBar;
import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.EnemyMover;

public class SmallCoyote extends Creature {
	
	
	private String textureName="enemySmall";
	private static String bodyTAG = "enemy";
	static boolean isSensor = false;
	private static String sensorTAG="sensorEnemySmall";
	private int type=3;
	private EnemyMover em;

	public SmallCoyote(float x, float y) {
		super(x, y, bodyTAG, sensorTAG, B2DVars.BIT_ENEMY,  B2DVars.BIT_ENEMY| B2DVars.BIT_PLAYER, isSensor);
		healthBar= new HealthBar();
		setEnemyHitPower(1);
		playAnimation(getAnimationRow(),textureName);
		setHitPoint(100);
		em = new EnemyMover();
		

	}




	@Override
	public void render(SpriteBatch sb) {

		sprite.render(sb);
		sb.begin();
		int pozX=(int)(body.getPosition().x * B2DVars.PPM - sprite.getWidth() / 2);
		int pozY=(int) (body.getPosition().y * B2DVars.PPM - sprite.getHeight() / 2);
		healthBar.draw(sb,pozX,pozY ,sprite.getWidth(),sprite.getHeight(),getHitPoint());
		sb.end();

		
	}


	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return type;
	}




	@Override
	public void update(float dt) {
	sprite.update(dt);
	em.pathStarter((Creature)body.getUserData());
		
	}

}
