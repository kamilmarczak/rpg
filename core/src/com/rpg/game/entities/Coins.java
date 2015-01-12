package com.rpg.game.entities;

import static com.rpg.game.handler.B2DVars.PPM;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.rpg.game.handler.B2DVars;

public class Coins {

	private BodyDef bdef;
	private FixtureDef fdef;
	private CircleShape shape;
	private Body body;

	
public Coins(Body body){
	this.body= body;
}


	public Coins(World world, float posX, float posY) {
		

		
		// Def initializing
		bdef = new BodyDef();
		fdef = new FixtureDef();

		shape= new CircleShape();
		
		// BodyDef
		bdef.position.set(posX , posY );
		bdef.type = BodyType.DynamicBody;
		bdef.fixedRotation = true;
		body = world.createBody(bdef);

		// fixtureDef
		shape.setRadius(20/PPM); // //
		fdef.shape = shape;
		fdef.isSensor = true;
		
		fdef.filter.categoryBits= B2DVars.COLLECTA;
		fdef.filter.maskBits= B2DVars.BIT_PLAYER;
		body.createFixture(fdef).setUserData("coin");

		// Player's sensor
		shape.setRadius(50/PPM);
		fdef.shape = shape;
		fdef.filter.categoryBits= B2DVars.COLLECTA;
		 fdef.filter.maskBits= B2DVars.BIT_PLAYER;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("sensorCoin");
		shape.dispose();

	
		
	}





	public Body getBody() {
		return body;
	}
}
