/*package com.rpg.game.entities;

import static com.rpg.game.handler.B2DVars.PPM;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.SteeringBehavior;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.rpg.game.AdultGame;
import com.rpg.game.handler.MyTimer;
import com.rpg.game.pathfinding.Mover;
import com.rpg.game.state.Play;

public class Entity3 implements Mover, Steerable<Vector2>  {


	private World world = Play.getWorld();
	private BodyDef bdef;
	private FixtureDef fdef;
	private PolygonShape shapeEnemy;
	private CircleShape sensroShape, circle;
	//private  Body body;
	private boolean isFighting= false;
	private int animationRow =0;
	protected float hitPoint= 1,f=1,g=1;
	protected HealthBar hp;
	private MyTimer timer;
	private int enemyHitPower=1;

	private String textureName;
	private Body body;
	protected B2DSprite sprite;
	private int type;
//steering
	float boundingRadius;
	boolean tagged;
	
	//TextureRegion region;
	float maxLinearSpeed;
	float maxLinearAcceleration;
	float maxAngularSpeed;
	float maxAngularAcceleration;
	boolean independentFacing;
	protected SteeringBehavior<Vector2> steeringBehavior;
	private static final SteeringAcceleration<Vector2> steeringOutput = new SteeringAcceleration<Vector2>(new Vector2());


	public String getEnemyTextureName() {
		return textureName;
	}


	public Entity3(int type) {
		this.type = type;
		//world=Play.getWorld();
		hp= new HealthBar();
		timer= new MyTimer(1);
		timer.start();
		
	}


	public void enemycreator(float f,float g, String bodyTAG,String sensorTAG, short categoryBit ,int maskBits, boolean isSensor){



		// Def initializing
		bdef = new BodyDef();
		fdef = new FixtureDef();
		shapeEnemy = new PolygonShape();
		sensroShape= new CircleShape();
		circle= new CircleShape();
		
		// BodyDef
		bdef.position.set(f/ PPM, g / PPM);
		
		bdef.type = BodyType.DynamicBody;
		bdef.fixedRotation = true;
		
	    body = world.createBody(bdef);

		// fixtureDef
	//	shapeEnemy.setAsBox(5 / PPM, 5 / PPM, new Vector2(0 / PPM, 0 / PPM),0); // //
		circle.setRadius(10/PPM);
		fdef.shape = circle;
		//fdef.shape = shapeEnemy;
		fdef.isSensor = isSensor;
		fdef.filter.categoryBits= categoryBit;
		fdef.filter.maskBits=  (short) maskBits ;
		fdef.restitution=0;
		body.createFixture(fdef).setUserData(bodyTAG);
		
		// Player's sensor
		sensroShape.setRadius(100/PPM);
		fdef.shape = sensroShape;
		fdef.filter.categoryBits= categoryBit;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData(sensorTAG);
		shapeEnemy.dispose();
		sensroShape.dispose();
		sprite= new B2DSprite(body);

		
	}



	public void attack()
	{

		if(Player.getPlayerHP()>6 && timer.hasCompleted()){
			
				Player.setPlayerHP(Player.getPlayerHP()- enemyHitPower);
				timer.start();
				

		}
	}
	
	public TextureRegion getRegion () {
		return region;
	}

	public void setRegion (TextureRegion region) {
		this.region = region;
	}
	
	
	public void playAnimation(int animationRow, String textureName) {
		
		this.textureName=textureName;
		this.animationRow = animationRow;

		Texture tex = AdultGame.res.getTexture(textureName);
		TextureRegion[] sprites = new TextureRegion[9];

		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = new TextureRegion(tex, i * 64, animationRow * 64, 64,
					64);
		}

		sprite.getAnimation().setFrames(sprites, 1 / 12f);

		sprite.setWidth(sprites[0].getRegionWidth());
		sprite.setHeight(sprites[0].getRegionHeight());
	}
	

	

	public Body getBody() {return body;}
	public float getHitPoint() {	return hitPoint;}
	public HealthBar getHp() {return hp;}
	public void setHitPoint(float hitPoint) {this.hitPoint = hitPoint;	}
	public int getAnimationRow() {return animationRow;}
	public void setAnimationRow(int animationRow) {	this.animationRow = animationRow;}
	public boolean isFighting() {return isFighting;}
	public void setFighting(boolean isFighting) {this.isFighting = isFighting;}
	public void setEnemyHitPower(int enemyHitPower) {this.enemyHitPower = enemyHitPower;}


	public void update(float dt) {
		sprite.update(dt);
		if (steeringBehavior != null) {
			
			// Calculate steering acceleration
			steeringBehavior.calculateSteering(steeringOutput);

			
			 * Here you might want to add a motor control layer filtering steering accelerations.
			 * 
			 * For instance, a car in a driving game has physical constraints on its movement: it cannot turn while stationary; the
			 * faster it moves, the slower it can turn (without going into a skid); it can brake much more quickly than it can
			 * accelerate; and it only moves in the direction it is facing (ignoring power slides).
			 
			
			// Apply steering acceleration
			applySteering(steeringOutput, dt);
		}
		
		
		
		
	}
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
	
	
	public void render(SpriteBatch sb) {
		sprite.render(sb);
	
		
	}
	public boolean isIndependentFacing () {
		return independentFacing;
	}
	public void setIndependentFacing (boolean independentFacing) {
		this.independentFacing = independentFacing;
	}


	@Override
	public int getType() {
		return type;
	}


	@Override
	public float getMaxLinearSpeed() {
		
		return maxLinearSpeed;
	}


	@Override
	public void setMaxLinearSpeed(float maxLinearSpeed) {
		this.maxLinearSpeed = maxLinearSpeed;
		
	}


	@Override
	public float getMaxLinearAcceleration() {
		return maxLinearAcceleration;
	}


	@Override
	public void setMaxLinearAcceleration(float maxLinearAcceleration) {
		this.maxLinearAcceleration = maxLinearAcceleration;
		
	}


	@Override
	public float getMaxAngularSpeed() {
		return maxAngularSpeed;
	}


	@Override
	public void setMaxAngularSpeed(float maxAngularSpeed) {
		this.maxAngularSpeed = maxAngularSpeed;
		
	}


	@Override
	public float getMaxAngularAcceleration() {
		return maxAngularAcceleration;
	}


	@Override
	public void setMaxAngularAcceleration(float maxAngularAcceleration) {
		this.maxAngularAcceleration = maxAngularAcceleration;
		
	}


	@Override
	public Vector2 getPosition() {
		
		 return body.getPosition();
	}


	@Override
	public float getOrientation() {
		return body.getAngle();
	}


	@Override
	public Vector2 getLinearVelocity() {
		return body.getLinearVelocity();
	}


	@Override
	public float getAngularVelocity() {
		return body.getAngularVelocity();
	}


	@Override
	public float getBoundingRadius() {
		return boundingRadius;
	}


	@Override
	public boolean isTagged() {
		return tagged;
	}


	@Override
	public void setTagged(boolean tagged) {
		this.tagged = tagged;
		
	}


	@Override
	public Vector2 newVector() {
		return new Vector2();
	}


	@Override
	public float vectorToAngle(Vector2 vector) {
		return (float)Math.atan2(-vector.x, vector.y);
	}


	@Override
	public Vector2 angleToVector(Vector2 outVector, float angle) {
		outVector.x = -(float)Math.sin(angle);
		outVector.y = (float)Math.cos(angle);
		return outVector;
	}
	public SteeringBehavior<Vector2> getSteeringBehavior () {
		return steeringBehavior;
	}
	public void setSteeringBehavior (SteeringBehavior<Vector2> steeringBehavior) {
		this.steeringBehavior = steeringBehavior;
	}




}
*/