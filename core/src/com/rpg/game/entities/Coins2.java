package com.rpg.game.entities;

import com.rpg.game.handler.B2DVars;
import static com.rpg.game.handler.B2DVars.PPM;

public class Coins2 extends Entity {

	private String textureName="coins";
	private String bodyTAG = "coin";
	private boolean isSensor = true;
	
	public Coins2(int x, int y) {
	
		enemycreator(x*PPM, y*PPM,bodyTAG,B2DVars.BIT_ENEMY, B2DVars.BIT_ENEMY| B2DVars.BIT_PLAYER,  isSensor );
		playAnimation(getAnimationRow(),textureName);
		
		
	}

}
