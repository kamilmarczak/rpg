package com.rpg.game.state;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.rpg.game.entities.Teleport;
import com.rpg.game.entities.creature.Player;
import com.rpg.game.handler.GameStateManager;
import com.rpg.game.handler.MyContactListener;


public class House extends GameState {
	
	//for all
	
	private TiledMap tileMap;
	private OrthogonalTiledMapRenderer tmr;
	private Integer tileSize;
	private Integer tileMapWidth;
	private Integer tileMapHeight;
	private Player player;
	private Body body;
	private World world;
	private MyContactListener cl;
	
	
	private Array<Teleport> teleports;
	///movment
	private float lastClickX;
	private float lastClickY;
	private float playerPositionX;
	private float playerPositionY;
	private int numerAnimacii=100;
	private boolean isMoving = false;
	private double speed=2;
	
	
	
	
	

	public House(GameStateManager gsm) {
		super(gsm);
		//create Map
		// createMap();
		 	//create world
		 cam.setBounds(0, tileMapWidth * tileSize, 0, tileMapHeight * tileSize);
		 world = new World(new Vector2(0, 0), true);
			cl = new MyContactListener();
			world.setContactListener(cl);
			
			
			//create Player
			//createPlayer();
			lastClickX=player.getBody().getPosition().x;
			lastClickY=player.getBody().getPosition().y;
			//create Portal
			//creatPortal();
		 
		 
	}






	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		
	}






	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}






	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}






	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
	/*
	
	public void handleInput() {
		playerPositionX=player.getBody().getPosition().x;
		playerPositionY=player.getBody().getPosition().y;

		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
		{
			player.getBody().setLinearVelocity(0,0);
			 lastClickX = cam.position.x/PPM-(cam.viewportWidth/2/PPM)+Gdx.input.getX()/PPM ;
			 lastClickY =cam.position.y/PPM-(cam.viewportHeight/2/PPM)+(cam.viewportHeight/PPM)-Gdx.input.getY()/PPM;
			 isMoving=true;
			 animationChecker();

		}
			
		if (isMoving) {
		//	if (lastClickX != player.getBody().getPosition().x || lastClickY != player.getBody().getPosition().y ) {
				
				
			
		    // Get the vector between the player and the target
		    float pathX = lastClickX - playerPositionX;
		    float pathY = lastClickY - playerPositionY;


		   //set 1
		    
		    
		    // Calculate the unit vector of the path
		    double distance = Math.sqrt(pathX * pathX + pathY * pathY);
		    double directionX = pathX / distance;
		    double directionY = pathY / distance;
	
		    // Calculate the actual walk amount
		   double movementX = directionX * speed;
		   double movementY = directionY * speed;

	

		   player.getBody().setLinearVelocity((float)movementX,(float)movementY);
		   if((int)(player.getBody().getPosition().x*10)==(int)(lastClickX*10) &&
				   (int)(player.getBody().getPosition().y*10)==(int)(lastClickY*10)){ isMoving=false;}
		    

		   
		   
		}else{
			  player.getBody().setLinearVelocity(0,0);
			  aniChecker(4);
}

	}


	public void update(float dt) {
		handleInput();
		world.step(AdultGame.STEP, 6, 2);
		player.update(dt);
		for(int i=0; i< teleports.size; i++){teleports.get(i).update(dt);}
		
		
		//player exit
		

	}


	public void render() {
		cam.setPosition(player.getPosition().x * PPM, player.getPosition().y* PPM);
		cam.update();
		tmr.setView(cam);
		tmr.render();
		drawPlayer();
		for(int i=0; i< teleports.size; i++){teleports.get(i).render(sb);}
		
		
		

	}


	public void dispose() {
		sb.dispose();
		// TODO Auto-generated method stub

	}
	private void createMap() {
		// Load Tiled Map
	
		tileMap = new TmxMapLoader().load("terrain/terrain2.tmx");
		tmr = new OrthogonalTiledMapRenderer(tileMap);
		tileSize = tileMap.getProperties().get("tilewidth", Integer.class);
		tileMapWidth = tileMap.getProperties().get("width", Integer.class);
		tileMapHeight = tileMap.getProperties().get("height", Integer.class);

	}
	private void createPlayer() {

		// Def initializing
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();

		// BodyDef
		bdef.position.set(400 / PPM, 400 / PPM);
		bdef.type = BodyType.DynamicBody;
		bdef.fixedRotation = true;
		body = world.createBody(bdef);

		// fixtureDef
		shape.setAsBox(15 / PPM, 25 / PPM, new Vector2(0 / PPM, 0 / PPM), 0);   ////
		fdef.shape = shape;
		fdef.restitution = 0;
		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
		// fdef.filter.maskBits= B2DVars.
		body.createFixture(fdef).setUserData("Player");

		// Player's sensor
		shape.setAsBox(35 / PPM, 35 / PPM, new Vector2(0 / PPM, 0 / PPM), 0);
		fdef.shape = shape;
		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
		// fdef.filter.maskBits= B2DVars.
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("sensor");

		// create Player

		player = new Player(body);
		player.playAnimation(4);
		body.setUserData("player");
		shape.dispose();
	}

	//checking  direction to draw sprite  in the correct way
		private void animationChecker() {
		if( (int)lastClickX >(int)playerPositionX&&(int)lastClickY >(int)playerPositionY)
			  aniChecker(5);
			
			if( (int)lastClickX >(int)playerPositionX&&(int)lastClickY <(int)playerPositionY)
				  aniChecker(8);
		
			if( (int)lastClickX <(int)playerPositionX&&(int)lastClickY >(int)playerPositionY)
				  aniChecker(6);
				
			if( (int)lastClickX <(int)playerPositionX&&(int)lastClickY <(int)playerPositionY)
				  aniChecker(7);
				

			if( (int)lastClickX <(int)playerPositionX&&(int)lastClickY ==(int)playerPositionY)
				  aniChecker(1);
			
			if( (int)lastClickX >(int)playerPositionX&&(int)lastClickY ==(int)playerPositionY)
				  aniChecker(3);
		
			if( (int)lastClickX ==(int)playerPositionX&&(int)lastClickY <(int)playerPositionY)
				  aniChecker(2);
				
			if( (int)lastClickX ==(int)playerPositionX&&(int)lastClickY >(int)playerPositionY)
				  aniChecker(0);

		}

	 //dont start  animation over and over again
		private void aniChecker(int i) {
			if(numerAnimacii!=i){
				  player.playAnimation(i);
				  numerAnimacii=i;
				  }
		}

	
	
	
	
	

	public void drawPlayer() {
		sb.setProjectionMatrix(cam.combined);
		player.render(sb);

	}
	private void creatPortal() {
		
		// create list of portals
		teleports = new Array<Teleport>();
		
		// get all crystals in "crystals" layer,
		// create bodies for each, and add them
		// to the crystals list
		MapLayer ml = tileMap.getLayers().get("Teleport");
		if(ml == null) return;
		
		for(MapObject mo : ml.getObjects()) {
			BodyDef cdef = new BodyDef();
			cdef.type = BodyType.StaticBody;
			float x = (float) mo.getProperties().get("x") / PPM;
			float y = (float) mo.getProperties().get("y") / PPM;
			cdef.position.set(x+32/PPM, y+32/PPM);
			Body body = world.createBody(cdef);
			FixtureDef cfdef = new FixtureDef();
			CircleShape cshape = new CircleShape();
			cshape.setRadius(32 / PPM);
			cfdef.shape = cshape;
			cfdef.isSensor = true;
			cfdef.filter.categoryBits = B2DVars.BIT_PORTAL;
			cfdef.filter.maskBits = B2DVars.BIT_PLAYER;
			body.createFixture(cfdef).setUserData("portal");
			Teleport t = new Teleport(body);
			body.setUserData(t);
			teleports.add(t);
			cshape.dispose();
		}
	}
	
	

}
*/