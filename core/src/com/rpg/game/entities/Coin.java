package com.rpg.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.rpg.game.handler.B2DSprite;
import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.BodyCreator;
import com.rpg.game.handler.ContendHolder;
import com.rpg.game.state.Play;

public class Coin extends Entity {
	private Body body;
	private String textureName="coins";
	private String bodyTAG = "coin";
	private boolean isSensor = true;
	private String sensorTAG="sensorCoin";
	private  B2DSprite sprite;
	private int animationRow=0;
	
	
	private static short categoryBit =B2DVars.BIT_COLLECTA;
	private static int maskBits = 
			B2DVars.BIT_PLAYER;
	

	public Coin(float x, float y) {
		super(x, y);
		body= new BodyCreator( x,  y, bodyTAG , sensorTAG,  categoryBit,  maskBits,  isSensor).getBody();
		sprite= new B2DSprite(body);
		sprite.playAnimation(animationRow, textureName);
	}
	
	
	@Override
	public void update(float dt) {
		sprite.update(dt);
		checkDistToPlayer();
		
	}
	@Override
	public void render(SpriteBatch sb) {
		sprite.render(sb);
		
	}
	private void checkDistToPlayer(){
		if(Play.getPlayer().getPosition().dst2(body.getPosition())<.6f){
			ContendHolder.getCoins().removeValue(this, false);
			if(!Play.getWorld().isLocked()){
			Play.getWorld().destroyBody(body);
			}
			ContendHolder.addCashAmount(1);
			
			
		}
		
		
		
	}

	



}
