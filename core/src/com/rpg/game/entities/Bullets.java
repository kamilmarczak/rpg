package com.rpg.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.rpg.game.data.Data;
import com.rpg.game.handler.B2DSprite;
import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.BodyCreator;
import com.rpg.game.handler.MyTimer;
import com.rpg.game.handler.steering.BodyMover;
import com.rpg.game.state.Play;

public class Bullets extends Entity {

	private String bodyTAG="bullet";
	private String sensorTAG="sensorBullet";
	private short categoryBit= B2DVars.BIT_BULET;

	private static int maskBits = 
			B2DVars.BIT_ENEMY|
			B2DVars.BIT_PLAYER|
			B2DVars.BIT_ROOF;
	private boolean isSensor= true;
	private Body body;
	private MyTimer bulletTime;
	private BodyMover bm;
	private int bulletSpeed= 20;
	private String textureName = "bullet";
	private B2DSprite sprite;
	private World world;
	private Data data;
	
	

	


	


	public Bullets(float startX, float startY, float targetX,float TargetY,World world,Data data) {
		super(startX, startY);
	this.world= world;
		body =new BodyCreator(startX, startY, bodyTAG, sensorTAG, categoryBit, maskBits, isSensor,world).getBody();
	 this.data= data;
		sprite= new B2DSprite(body);
		sprite.imageDraw(textureName);
		bm= new BodyMover(startX, startY, targetX, TargetY, bulletSpeed);
		body.setLinearVelocity(bm.getMovementX(), bm.getMovementY());
		body.setUserData(this);
		bulletTime= new MyTimer(2);
		bulletTime.start();
		data.getBulestsList().add(this);
	
	}

	
	
	
	
	@Override
	public void update(float dt) {
	
	sprite.update(dt);
		
	if(bulletTime.hasCompleted()){
		destroyBullet();
		}
		
	}
	
	
	

	@Override
	public void render(SpriteBatch sb) {
		sprite.render(sb);

	}
	

	public void  destroyBullet(){
		world.destroyBody(body);
		data.getBulestsList().removeValue(this, false);	
		
	}

	public void setBodyTAG(String bodyTAG) {
		this.bodyTAG = bodyTAG;
	}

	

}
