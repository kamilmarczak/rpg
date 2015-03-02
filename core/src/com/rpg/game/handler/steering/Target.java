package com.rpg.game.handler.steering;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.rpg.game.entities.Entity;
import com.rpg.game.handler.BodyCreator;

public class Target extends Entity implements Steerable<Vector2>  {
	

	//limiter 
	protected  boolean independentFacing;
	float maxLinearSpeed;
	float maxLinearAcceleration;
	float maxAngularSpeed;
	float maxAngularAcceleration;
	protected SteeringBehavior<Vector2> steeringBehavior;
	protected  final SteeringAcceleration<Vector2> steeringOutput = new SteeringAcceleration<Vector2>(new Vector2());
	public boolean isIndependentFacing () {return independentFacing;}
	public void setIndependentFacing (boolean independentFacing) {this.independentFacing = independentFacing;}
	public SteeringBehavior<Vector2> getSteeringBehavior () {return steeringBehavior;}
	public void setSteeringBehavior (SteeringBehavior<Vector2> steeringBehavior) {this.steeringBehavior = steeringBehavior;}
	float boundingRadius;
	boolean tagged;
	private boolean collision= false;
	////////
	
	private Body body;


	
	
	
	///

	public Target(float x, float y) {
		super(x, y);
		body= new BodyCreator(x, y).getBody();
		body.setUserData(Target.this);
		//EnemyContainer.getTARGETS().add(this);
	
	}
	
	
	
	
	
	
	
	
	
	

	public Body getBody() {
		return body;
	}
		//_________________________________________________________________________
		//Getter and setters for steering
		@Override
		public Vector2 getPosition () { return body.getPosition();}
		@Override
		public float getOrientation () {return body.getAngle();}
		@Override
		public Vector2 getLinearVelocity () {return body.getLinearVelocity();}
		@Override
		public float getAngularVelocity () {return body.getAngularVelocity();}
		@Override
		public float getBoundingRadius () {return boundingRadius;}
		@Override
		public boolean isTagged () {return tagged;}
		@Override
		public void setTagged (boolean tagged) {this.tagged = tagged;}
		@Override
		public Vector2 newVector () {return new Vector2();}
		@Override
		public float vectorToAngle (Vector2 vector) {return (float)Math.atan2(-vector.x, vector.y);}
		@Override
		public Vector2 angleToVector (Vector2 outVector, float angle) {
			outVector.x = -(float)Math.sin(angle);
			outVector.y = (float)Math.cos(angle);
			return outVector;}
		//
		// Limiter implementation
		//

		@Override
		public float getMaxLinearSpeed () { return maxLinearSpeed;}
		@Override
		public void setMaxLinearSpeed (float maxLinearSpeed) {this.maxLinearSpeed = maxLinearSpeed;	}
		@Override
		public float getMaxLinearAcceleration () {return maxLinearAcceleration;}
		@Override
		public void setMaxLinearAcceleration (float maxLinearAcceleration) {this.maxLinearAcceleration = maxLinearAcceleration;}
		@Override
		public float getMaxAngularSpeed () {	return maxAngularSpeed;}
		@Override
		public void setMaxAngularSpeed (float maxAngularSpeed) {this.maxAngularSpeed = maxAngularSpeed;}
		@Override
		public float getMaxAngularAcceleration () {return maxAngularAcceleration;}
		@Override
		public void setMaxAngularAcceleration (float maxAngularAcceleration) {this.maxAngularAcceleration = maxAngularAcceleration;	}
		@Override
		public void update(float dt) {

			
		}
		@Override
		public void render(SpriteBatch sb) {

			
		}
		public void setCollision(boolean collision) {
			this.collision=collision;
			
			
		}
		public boolean isCollision() {	return collision;	}





}
