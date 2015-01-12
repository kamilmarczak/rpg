package com.rpg.game.entities;

import static com.rpg.game.handler.B2DVars.PPM;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.rpg.game.handler.B2DVars;

public class Enemy{

	private float posX = 0;
	private float posY = 0;
	private World world = null;
	private BodyDef bdef;
	private FixtureDef fdef;
	private PolygonShape shapeEnemy;
	private CircleShape sensroShape;
	private Body body;

	public Enemy(Body body) {
		this.body =body;

	
		
	}



	public Enemy(World world, int posX, int posY) {
		this.world= world;
		this.posX= posX;
		this.posY= posY;
		 enemycreator();
	
	
		
	}
	

	public void enemycreator(){

		
		// Def initializing
		bdef = new BodyDef();
		fdef = new FixtureDef();
		shapeEnemy = new PolygonShape();
		sensroShape= new CircleShape();
		
		// BodyDef
		bdef.position.set(posX / PPM, posY / PPM);
		bdef.type = BodyType.DynamicBody;
		bdef.fixedRotation = true;
		

		body = world.createBody(bdef);

		// fixtureDef
		shapeEnemy.setAsBox(15 / PPM, 25 / PPM, new Vector2(0 / PPM, 0 / PPM),0); // //
		fdef.shape = shapeEnemy;
	
	
		fdef.filter.categoryBits= B2DVars.BIT_ENEMY;
		fdef.filter.maskBits= B2DVars.BIT_PLAYER;
		//fdef.filter.maskBits= B2DVars.BIT_PLAYER |B2DVars.BIT_ENEMY;
		body.createFixture(fdef).setUserData("enemy");

		// Player's sensor
		sensroShape.setRadius(200/PPM);
		fdef.shape = sensroShape;
		fdef.filter.categoryBits= B2DVars.BIT_ENEMY;
		 fdef.filter.maskBits= B2DVars.BIT_PLAYER;
		 fdef.density= 0.1f;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("sensorEnemy");
		shapeEnemy.dispose();
		sensroShape.dispose();
		
	}



	public Body getBody() {
		return body;
	}




}
