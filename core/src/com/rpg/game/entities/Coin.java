package com.rpg.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.rpg.game.data.Data;
import com.rpg.game.entities.creature.Player;
import com.rpg.game.handler.B2DSprite;
import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.BodyCreator;

public class Coin extends Entity {
	private Body body;
	private String textureName="coins";
	private String bodyTAG = "coin";
	private boolean isSensor = true;
	private String sensorTAG="sensorCoin";
	private  B2DSprite sprite;
	private int animationRow=0;
	private World world;
	private Player player;
	private Data data;

	
	
	private static short categoryBit =B2DVars.BIT_COLLECTA;
	private static int maskBits = 
			B2DVars.BIT_PLAYER;
	

	public Coin(float x, float y,World world,Player player,Data data) {
		super(x, y);
		this.world=world;
		this.player=player;
		this.data= data;
	
		
		body= new BodyCreator( x,  y, bodyTAG , sensorTAG,  categoryBit,  maskBits,  isSensor, world).getBody();
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
		if(player.getPosition().dst2(body.getPosition())<.6f){
			data.getCoins().removeValue(this, false);
			world.destroyBody(body);
	
			data.addCashAmount(1);
			
			
		}
		
		
		
	}

	



}
