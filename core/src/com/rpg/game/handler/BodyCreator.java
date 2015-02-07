package com.rpg.game.handler;

import static com.rpg.game.handler.B2DVars.PPM;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.rpg.game.state.Play;

public class BodyCreator {
	
	protected Body body;
	private BodyDef bdef=new BodyDef();;
	private FixtureDef fdef = new FixtureDef();
	private CircleShape sensroShape= new CircleShape(), circle= new CircleShape();;
	private PolygonShape polygon = new PolygonShape();
	private World world =Play.getWorld(); 
	private  String targetTag="target";




	public Body getBody() {
		return body;
	}
		

	final float centerX = 0;
    final float top = -.3f;
    final float bottom = .1f;
    final float left = -.15f;
    final float right = .15f;

    final Vector2[] vertices = {
            new Vector2(centerX, top).rotate(180),
            new Vector2(right, bottom).rotate(180),
            new Vector2(left, bottom).rotate(180)
    };
public BodyCreator(float x,float y, String bodyTAG,String sensorTAG, short categoryBit ,int maskBits, boolean isSensor){

	
		// BodyDef
		bdef.position.set(x*B2DVars.MTT, y*B2DVars.MTT );
		bdef.type = BodyType.DynamicBody;
		bdef.fixedRotation = false;
	    body = world.createBody(bdef);

		// fixtureDef
		//circle.setRadius(16/PPM);

		polygon.set(vertices);
		fdef.shape = polygon;
		fdef.isSensor = isSensor;
		fdef.filter.categoryBits= categoryBit;
		fdef.filter.maskBits=  (short) maskBits ;
	
		fdef.density=1;
		fdef.friction=0;
		body.createFixture(fdef).setUserData(bodyTAG);
		
		// Player's sensor
		circle.setRadius(100/PPM);
		fdef.shape = circle;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData(sensorTAG);
		circle.dispose();
		sensroShape.dispose();
	
			}

public BodyCreator(float x,float y) {
	
	bdef.active=true;
	bdef.position.set(x*B2DVars.MTT, y*B2DVars.MTT );
	bdef.type = BodyType.DynamicBody;
	bdef.fixedRotation = true;
	

    body = world.createBody(bdef);
    circle.setRadius(5/PPM);
	fdef.shape = circle;
	fdef.filter.categoryBits= B2DVars.BIT_TARGET;
	fdef.filter.maskBits= B2DVars.BIT_TARGET;
	body.createFixture(fdef).setUserData(targetTag);
}



}

