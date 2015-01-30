/*package com.rpg.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rpg.game.handler.B2DVars;
import static com.rpg.game.handler.B2DVars.PPM;

public class Coin extends Entity2 {

	private String textureName="coins";
	private String bodyTAG = "coin";
	private boolean isSensor = true;
	private String sensorTAG="sensorCoin";
	
	public Coin(float x, float y,int type) {
		super(type);
	
		enemycreator(x*PPM, y*PPM,bodyTAG,sensorTAG, B2DVars.BIT_ENEMY, B2DVars.BIT_ENEMY| B2DVars.BIT_PLAYER,  isSensor );
		playAnimation(getAnimationRow(),textureName);

	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		
	}

}
*/