package useless;

public class Useless {
	//old smallenemy
	/*package com.rpg.game.entities;

	import com.badlogic.gdx.graphics.g2d.SpriteBatch;
	import com.rpg.game.handler.B2DVars;
	import com.rpg.game.handler.EnemyMover;


	public class SmallEnemy extends Entity {





		
		private String textureName="enemySmall";
		private String bodyTAG = "enemy";
		boolean isSensor = false;
		private String sensorTAG="sensorEnemySmall";
		
		
		private int type=3;
		
		private EnemyMover em;
		private SmallEnemy smallEnemy;
		public int getType() {return type;}
		
	//path


		public SmallEnemy(int i, int j,int type) {
			super(type);
			em = new EnemyMover();
		enemycreator(i, j,bodyTAG,sensorTAG, B2DVars.BIT_ENEMY, B2DVars.BIT_ENEMY| B2DVars.BIT_PLAYER ,isSensor);
		setEnemyHitPower(1);
		playAnimation(getAnimationRow(),textureName);

		
		}


		@Override
		public void render(SpriteBatch sb) {

			super.render(sb);
			
			sb.begin();
			int pozX=(int)(getBody().getPosition().x * B2DVars.PPM - sprite.getWidth() / 2);
			int pozY=(int) (getBody().getPosition().y * B2DVars.PPM - sprite.getHeight() / 2);
			hp.draw(sb,pozX,pozY ,sprite.getWidth(),sprite.getHeight(),hitPoint);
			sb.end();
		}


		
		

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		super.update(dt);
		//em.pathStarter((SmallEnemy)getBody().getUserData());
	}

	public EnemyMover getEm() {
		return em;
	}






	}
	*/
	
	
	
	
	
	
	
	
	
	
	//old player
	/*package com.rpg.game.entities;

	import com.badlogic.gdx.Gdx;
	import com.badlogic.gdx.Input;
	import com.badlogic.gdx.physics.box2d.Body;
	import com.badlogic.gdx.utils.Array;
	import com.rpg.game.handler.B2DVars;
	import com.rpg.game.handler.BodyMover;
	import com.rpg.game.handler.Condition;
	import com.rpg.game.handler.EnemyContainer;
	import com.rpg.game.handler.MyTimer;

	import com.rpg.game.state.Play;

	public class Player extends Entity  {
		private static int COINS = 0;
		private String textureName = "player";
		private String bodyTAG = "player";
		private String sensorTAG = "playerSensor";
		private static int playerHP=100;
		private static MyTimer mt=new MyTimer(1);
		private static BodyMover bm;
		private static int type =3;

		private Array<Coin> coinsArray=new Array<Coin>();

		
		
		
		
		
		
		
		public Array<Coin> getCoinsArray() {return coinsArray;	}
		public void setCoinsArray(Array<Coin> coinsArray) {this.coinsArray = coinsArray;}
		public float getPlayerPositionX() {return getBody().getPosition().x;}
		public float getPlayerPositionY() {return getBody().getPosition().y;}
		public static int getPlayerHP() {	return playerHP;}
		public static void setPlayerHP(int playerHP) {	Player.playerHP = playerHP;}
		public static int getCOINS() {return COINS;}
		public static void setCOINS(int COINs) {COINS = COINs;}
		public static void setTimerForAtt() {	mt.start();}
		boolean isSensor = false;
//		private EnemyContainer enemyContainer = new EnemyContainer();;



		public Player(float i, float j) {
			super(type );
			
		
			enemycreator(i, j,bodyTAG,sensorTAG ,
					B2DVars.BIT_PLAYER,
					B2DVars.BIT_ENEMY |
					B2DVars.BIT_DOOR |
					B2DVars.BIT_PORTAL_FORWARD |
					B2DVars.BIT_PORTAL_BACK |
					B2DVars.COLLECTA,false);
			
			playAnimation(getAnimationRow(), textureName);
		

		}


		
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		super.update(dt);
		
		damage() ;
		
	}
			public  void damage() {
					
			Array<Body> bodiesDmg = Play.getCl().getDamege();
				Array<Body> fallowDmg = Play.getCl().getFallow();
				
				
				//Enemy Fallow player
					for(int j =0; j<fallowDmg.size; j++){
							Body f = fallowDmg.get(j);
								bm= new BodyMover(((Entity) f.getUserData()).getBody().getPosition().x,
				  ((Entity) f.getUserData()).getBody().getPosition().y,
				  getPlayerPositionX(), getPlayerPositionY(), 1);
					((Entity) f.getUserData()).getBody().setLinearVelocity((float)bm.getMovementX(),(float)bm.getMovementY());
		
	}
					//Enemy attack player
			for (int i = 0; i < bodiesDmg.size; i++) {
			Body b = bodiesDmg.get(i);

			((Entity) b.getUserData()).attack();

	//Player attack
				if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
						Condition.setPlayerIsAttacking(true);
							setTimerForAtt();

								((Entity) b.getUserData()).setHitPoint(((Entity) b.getUserData()).getHitPoint() - 0.5f);
									ishidead((Entity) b.getUserData());
			}
		}		
	}

	public void ishidead(Entity entity){
		
		if (entity.getHitPoint() <= 0) {
		
		float posX=entity.getBody().getPosition().x;
		float posY=entity.getBody().getPosition().y;
		
			createCoin(posX,posY,B2DVars.COIN);
			entity.getHp().getSkinAtlas().dispose();
			EnemyContainer .GETSMALLENEMY().removeValue(entity,false);
			Play.getWorld().destroyBody(entity.getBody());
			
		}
		
	}

	public void createCoin(float posX, float posY, int money){

		Coin coin = new Coin(posX, posY, money);
		
		coinsArray.add(coin);
		coin.getBody().setUserData(coin);
		
		}
	}
	*/
	//old entity
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//sprawdzanie czy  woda czy 
/*			if(GameMaps.coliLayer.getCell((int)(playerPositionX+cam.position.x / PPM - (cam.viewportWidth / 2 / PPM)),(int)((playerPositionY)+(cam.position.y / PPM - (cam.viewportHeight / 2 / PPM)))).getTile().getProperties().containsKey("trawa")){
		//System.out.println(cam.position.x / PPM - (cam.viewportWidth / 2 / PPM));
		System.out.println(cam.position.y / PPM - (cam.viewportHeight / 2 / PPM));

		System.out.println("tak");
	}else {
		System.out.println("nieeee");
	}*/
	
	
	//old control
	/*
	public void handleInput() {

		playerPositionX = player.getBody().getPosition().x;
		playerPositionY = player.getBody().getPosition().y;

		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			player.getBody().setLinearVelocity(0, 0);
			lastClickX = cam.position.x / PPM - (cam.viewportWidth / 2 / PPM)+ Gdx.input.getX() / PPM;
			lastClickY = cam.position.y / PPM - (cam.viewportHeight / 2 / PPM)+ (cam.viewportHeight / PPM) - Gdx.input.getY() / PPM;
			isMoving = true;
			animationChecker();

		}

		if (isMoving) {
			// if (lastClickX != player.getBody().getPosition().x || lastClickY
			// != player.getBody().getPosition().y ) {

			// Get the vector between the player and the target
			float pathX = lastClickX - playerPositionX;
			float pathY = lastClickY - playerPositionY;

			// set 1

			// Calculate the unit vector of the path
			double distance = Math.sqrt(pathX * pathX + pathY * pathY);
			double directionX = pathX / distance;
			double directionY = pathY / distance;

			// Calculate the actual walk amount
			double movementX = directionX * speed;
			double movementY = directionY * speed;

			player.getBody().setLinearVelocity((float) movementX,
					(float) movementY);
			if ((int) (player.getBody().getPosition().x * 10) == (int) (lastClickX * 10)
					&& (int) (player.getBody().getPosition().y * 10) == (int) (lastClickY * 10)) {
				isMoving = false;
			}

		} else {
			player.getBody().setLinearVelocity(0, 0);
			aniChecker(4);
		}

	}*/

	
	
	////////////////////////////////////////////////////////////////////
	// for creating  bodys from layers
	
	
	
	

	
	//TODO wylaczona metoda  createLayer
	//TiledMapTileLayer layer;
	//layer = (TiledMapTileLayer) tileMap.getLayers().get("Grass");
	//createLayer(layer, B2DVars.BIT_GRASS);
	// layer=(TiledMapTileLayer)tileMap.getLayers().get("Grass") ;
	// createLayer(layer, B2DVars.BIT_GRASS);
	// layer=(TiledMapTileLayer)tileMap.getLayers().get("Grass") ;
	// createLayer(layer, B2DVars.BIT_GRASS);



//TODO disabled metod  create layer

/*	private void createLayer(TiledMapTileLayer layer, short bits) {

	BodyDef bdef = new BodyDef();
	FixtureDef fdef = new FixtureDef();

	// go through all cells in the layer
	for (int row = 0; row < layer.getHeight(); row++) {
		for (int col = 0; col < layer.getWidth(); col++) {
			// get cell
			Cell cell = layer.getCell(col, row);
			// check if cell exists
			if (cell == null)
				continue;
			if (cell.getTile() == null)
				continue;

			// create body and fixture form cell

			ChainShape cs = new ChainShape();
			bdef.type = BodyType.StaticBody;
			bdef.position.set((col + .5f) * tileSize / PPM, (row + .5f)
					* tileSize / PPM);

			Vector2[] v = new Vector2[5];
			v[0] = new Vector2(-tileSize / 2 / PPM, -tileSize / 2 / PPM);
			v[1] = new Vector2(-tileSize / 2 / PPM, tileSize / 2 / PPM);
			v[2] = new Vector2(tileSize / 2 / PPM, tileSize / 2 / PPM);
			v[3] = new Vector2(tileSize / 2 / PPM, -tileSize / 2 / PPM);
			v[4] = new Vector2(-tileSize / 2 / PPM, -tileSize / 2 / PPM);
			cs.createChain(v);

			fdef.friction = 0;
			fdef.shape = cs;

			fdef.filter.categoryBits = bits;
			fdef.filter.maskBits = -1;
			fdef.isSensor = false;
			world.createBody(bdef).createFixture(fdef);
			cs.dispose();

		}
	}

}
*/

	
	
	
	
	
	
	
	
	
	
	
	/////////////////////////////////////////////////////////////////
	//player is not going shortes way
	
	   
	 

    
/*		    //set 2
	   
	   //nie zawsze idzie po linii priostej
	    
	   
	    
 double angle = Math.atan2(pathY, pathX) * 180 / Math.PI;
	    
	    
	    double movementX = Math.cos(angle * Math.PI/180) * speed;
	    double movementY = Math.sin(angle * Math.PI/180) * speed;
	    
	    
	    player.getBody().setLinearVelocity((int)movementX,(int)movementY);
		   if((int)(player.getBody().getPosition().x*10)==(int)(lastClickX*10) &&(int)(player.getBody().getPosition().y*10)==(int)(lastClickY*10)){ isMoving=false;}
	  
	 
	   
	   */
	

	
	////////////////////////////////////////////////////////////////
	//Player WSAD movement
	/*
	

	if (MyInput.isPressed(MyInput.UP)) {
		player.playAnimation(0);
		player.getBody().setLinearVelocity(player.getBody().getLinearVelocity().x, .5f);
		listaPrzyciskw.add(1);
		
	}
	if (MyInput.isPressed(MyInput.DOWN)) {
		player.playAnimation(2);
		player.getBody().setLinearVelocity(player.getBody().getLinearVelocity().x, -.5f);
		listaPrzyciskw.add(1);
	}
	
	if (MyInput.isPressed(MyInput.LEFT))  {
		player.playAnimation(1);
		player.getBody().setLinearVelocity(-.5f,player.getBody().getLinearVelocity().y);
		listaPrzyciskw.add(1);
	}		
	if (MyInput.isPressed(MyInput.RIGHT)) {

		player.playAnimation(3);
		player.getBody().setLinearVelocity(.5f,	player.getBody().getLinearVelocity().y);
		listaPrzyciskw.add(1);
	}

	
	

	if (MyInput.isReleased(MyInput.UP)) {
		listaPrzyciskw.remove(listaPrzyciskw.size()-1);
		player.getBody().setLinearVelocity(player.getBody().getLinearVelocity().x, 0);
		player.playAnimation(4);
	}
	if (MyInput.isReleased(MyInput.DOWN)) {
		listaPrzyciskw.remove(listaPrzyciskw.size()-1);
		player.getBody().setLinearVelocity(player.getBody().getLinearVelocity().x, 0);
		player.playAnimation(4);
	}
	if (MyInput.isReleased(MyInput.LEFT)) {
		listaPrzyciskw.remove(listaPrzyciskw.size()-1);
		player.getBody().setLinearVelocity(0,player.getBody().getLinearVelocity().y);
		player.playAnimation(4);
	}
	if (MyInput.isReleased(MyInput.RIGHT)) {
		listaPrzyciskw.remove(listaPrzyciskw.size()-1);
		player.getBody().setLinearVelocity(0,player.getBody().getLinearVelocity().y);
		player.playAnimation(4);
	}


	*/

}
