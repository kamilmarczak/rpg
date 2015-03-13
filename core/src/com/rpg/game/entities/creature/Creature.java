package com.rpg.game.entities.creature;


import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.rpg.game.entities.Coin;
import com.rpg.game.entities.Entity;
import com.rpg.game.entities.HealthBar;
import com.rpg.game.entities.Mark;
import com.rpg.game.handler.B2DSprite;
import com.rpg.game.handler.BodyCreator;
import com.rpg.game.handler.actions.Death;
import com.rpg.game.handler.steering.Target;
import com.rpg.game.pathfinding.Mover;


public abstract class Creature extends Entity implements Mover, Steerable<Vector2>{
	
	
	//limiter  steering
	protected  boolean independentFacing;
	float maxLinearSpeed;
	float maxLinearAcceleration;
	float maxAngularSpeed;
	float maxAngularAcceleration;
	protected SteeringBehavior<Vector2> steeringBehavior;
	
	protected final SteeringAcceleration<Vector2> steeringOutput = new SteeringAcceleration<Vector2>(new Vector2());
	
	float boundingRadius= 1;
	/////////
	protected HealthBar healthBar;
	protected Mark mark;
	protected BodyCreator bodyCreator; 
	protected  B2DSprite sprite;
	protected Body body;
	private float health;
	protected Death death;
	private int enemyHitPower=0;
	private Target target;
	private boolean targetRandom= true;
	protected boolean inCombat=false;
	private static  boolean isMoving = false;
	private static  boolean isAttackingMelee = false;
	private static  boolean isAttackingRange = false;
	private static  boolean inRange = false;
	private boolean inContacWithRoof= false;
	boolean tagged ,collide=false;
	private World world;


	
	
	
	
	public Creature(float x,float y, String bodyTAG,String sensorTAG, short categoryBit ,int maskBits, boolean isSensor,World world) {
		super(x, y);
		this.world= world;

		body= new BodyCreator( x,  y, bodyTAG , sensorTAG,  categoryBit,  maskBits,  isSensor,world).getBody();
		createTarget(x, y);
		sprite= new B2DSprite(body);
		
		
	}
	
	
	
	
	
	
	
	
	///////////
	protected void applySteering (SteeringAcceleration<Vector2> steering, float deltaTime) {
		boolean anyAccelerations = false;
		// Update position and linear velocity.
		if (!steeringOutput.linear.isZero()) {
			Vector2 force = steeringOutput.linear.scl(deltaTime);
			body.applyForceToCenter(force, true);
			anyAccelerations = true;
		}
		// Update orientation and angular velocity
		if (isIndependentFacing()) {
			if (steeringOutput.angular != 0) {
				body.applyTorque(steeringOutput.angular * deltaTime, true);
				anyAccelerations = true;
			}
		}
		else {
			// If we haven't got any velocity, then we can do nothing.
			Vector2 linVel = getLinearVelocity();
			if (!linVel.isZero(MathUtils.FLOAT_ROUNDING_ERROR)) {
				float newOrientation = vectorToAngle(linVel);
				body.setAngularVelocity((newOrientation - getAngularVelocity()) * deltaTime); // this is superfluous if independentFacing is always true
				body.setTransform(body.getPosition(), newOrientation);
			}
		}

		if (anyAccelerations) {
			// body.activate();

			// TODO:
			// Looks like truncating speeds here after applying forces doesn't work as expected.
			// We should likely cap speeds form inside an InternalTickCallback, see
			// http://www.bulletphysics.org/mediawiki-1.5.8/index.php/Simulation_Tick_Callbacks

			// Cap the linear speed
			Vector2 velocity = body.getLinearVelocity();
		
			float currentSpeedSquare = velocity.len2();
			float maxLinearSpeed = getMaxLinearSpeed();
	
			
			if (currentSpeedSquare > maxLinearSpeed * maxLinearSpeed) {
				body.setLinearVelocity(velocity.scl(maxLinearSpeed / (float)Math.sqrt(currentSpeedSquare)));
			}

			// Cap the angular speed
			float maxAngVelocity = getMaxAngularSpeed();
		
			if (body.getAngularVelocity() > maxAngVelocity) {
				body.setAngularVelocity(maxAngVelocity);
				
			}
		}
	}
///////////////////////////////////////////////////

	
	
	
	public boolean isCollisionEnemys() {
		return collide;
	}
	public void setCollisionEnemys(boolean collide) {
		this.collide = collide;
	}
	
	
	public void drop() {

	
	}


	/////getters and setters
	
	
	public boolean isInContacWithRoof() {return inContacWithRoof;}
	public HealthBar getHealthBar() {return healthBar;}
	public void setInContacWithRoof(boolean inContacWithRoof) {this.inContacWithRoof = inContacWithRoof;}
	public boolean isInRange() {return inRange;}
	public void setInRange(boolean inRange) {Creature.inRange = inRange;}
	public boolean isAttackingMelee() {return isAttackingMelee;}
	public static void setAttackingMelee(boolean isAttackingMelee) {Creature.isAttackingMelee = isAttackingMelee;}
	public boolean isAttackingRange() {return isAttackingRange;}
	public static void setAttackingRange(boolean isAttackingRange) {	Creature.isAttackingRange = isAttackingRange;	}
	public boolean isMoving() {return isMoving;}
	public static void setMoving(boolean isMoving) {Creature.isMoving = isMoving;}
	public boolean isInCombat() {return inCombat;}
	public void setInCombat(boolean inCombat) {this.inCombat = inCombat;}
	
	public Target getTarget() {	return target;}
	public void setTarget(Target target) {this.target = target;}
	private void createTarget(float x, float y){target= new Target(x, y,world);}
	public abstract void attack();
	public boolean isTargetRandom() {return targetRandom;}
	public void setTargetRandom(boolean targetRandom) {this.targetRandom = targetRandom;}
	public int getHitPower() {return enemyHitPower;}
	public void setHitPower(int enemyHitPower) {this.enemyHitPower = enemyHitPower;}
	public Body getBody() {return body;}
	public float getHealth() {return health;}
	public void setHealth(float health) {	if(health<0){health=0;}this.health = health;}
	
	
	
	
	//_________________________________________________________________________
	//Getter and setters for steering
	//stearing
	public boolean isIndependentFacing () {return independentFacing;}
	public void setIndependentFacing (boolean independentFacing) {this.independentFacing = independentFacing;}
	public SteeringBehavior<Vector2> getSteeringBehavior () {return steeringBehavior;}
	public void setSteeringBehavior (SteeringBehavior<Vector2> steeringBehavior) {this.steeringBehavior = steeringBehavior;}
	///
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
	}


