package com.rpg.game.state;

import static com.rpg.game.handler.B2DVars.PPM;

import java.util.Random;

import sun.awt.windows.WLightweightFramePeer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.steer.behaviors.CollisionAvoidance;
import com.badlogic.gdx.ai.steer.behaviors.PrioritySteering;
import com.badlogic.gdx.ai.steer.behaviors.Wander;
import com.badlogic.gdx.ai.steer.limiters.LinearAccelerationLimiter;
import com.badlogic.gdx.ai.tests.steer.box2d.Box2dRadiusProximity;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.rpg.game.AdultGame;
import com.rpg.game.entities.Door;
import com.rpg.game.entities.HUD;
import com.rpg.game.entities.Teleport;
import com.rpg.game.entities.creature.Creature;
import com.rpg.game.entities.creature.Player;
import com.rpg.game.entities.creature.SmallCoyote;
import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.Condition;
import com.rpg.game.handler.EnemyContainer;
import com.rpg.game.handler.GameMaps;
import com.rpg.game.handler.GameStateManager;
import com.rpg.game.handler.MyContactListener;
import com.rpg.game.handler.MyTimer;
import com.rpg.game.handler.input.Targeting;
import com.rpg.game.handler.steering.PlayerControler;




public class Play extends GameState {

	
	private static boolean debug = false ;
	private Box2DDebugRenderer b2dRenderer;
	private static MyContactListener cl;
	public static World world;
	public static Player player;
	private Array<Teleport> teleports;
	private ShapeRenderer shapeRenderer ;
	private Array<Door> doors;
	private Targeting targeting;

	
	


	private HUD hud;

	private GameMaps gameMap;
	private int enemyIerator=10;
	private MyTimer myTimerSmallEnemy= new MyTimer(1);
	// pathfinding

		
	//private GameMaps gameMaps = new GameMaps();

	
	
	
	// movment
	public static Player getPlayer() {return player;}
	public static World getWorld() {return world;}
	public static MyContactListener getCl() {return cl;}
	

	public Play(GameStateManager gsm) {
		super(gsm);
		shapeRenderer=new ShapeRenderer();
		//enemyContainer= new EnemyContainer();
		// set up box2d
		world = new World(new Vector2(0, 0), true); // gravity here
		cl = new MyContactListener();
		world.setContactListener(cl);
		b2dRenderer = new Box2DDebugRenderer();
		// create Map
		gameMap = new GameMaps();
		//gameMap.createMap();
		cam.setBounds(0, gameMap.getWidthInTiles() * GameMaps.getTileSize(), 0,	gameMap.getHeightInTiles() * GameMaps.getTileSize());
		// create player
		createPlayer();
	
		// create portal
		creatPortal();
		// set up b2dcamera
		b2dCam.setToOrtho(false, AdultGame.G_WIDTH / PPM, AdultGame.G_HEIGHT/ PPM);
		b2dCam.setBounds(0,(gameMap.getWidthInTiles() * GameMaps.getTileSize()) / PPM,
						 0,(gameMap.getHeightInTiles() * GameMaps.getTileSize()) / PPM);
		// Set up HUD
		hud = new HUD(player);
targeting= new Targeting();
	}

	
	
	
	
	private void createPlayer() {
		
		player = new Player(2, 2);
		
	}
	
	
	
	private void createEnemy(int iletenmy) {

		if(iletenmy>EnemyContainer.GETSMALLENEMY().size)
		{
			int x =randInt(0, 60);
			int y =randInt(0,60);
			
			/*non overlapping spawn*/
			// tempSpawn = new Vector2(x*B2DVars.MTT, y*B2DVars.MTT);
			
/*			for(int i=0; i<EnemyContainer.GETSMALLENEMY().size;i++){
				if(EnemyContainer.GETSMALLENEMY().get(i).getBody().getPosition().dst2(tempSpawn)<.4f){
					
					 x =randInt(0, 60);
					 y =randInt(0,60);
						 tempSpawn = new Vector2(x*B2DVars.MTT, y*B2DVars.MTT);
						i=0;
				}
				
			}*/
			

			if(myTimerSmallEnemy.hasCompleted()){
				myTimerSmallEnemy.start();
			SmallCoyote smalCoy = new SmallCoyote(x,y );
			EnemyContainer.GETSMALLENEMY().add(smalCoy);
			smalCoy.getBody().setUserData(smalCoy);


			}
			
			
			
		}

	}
	
	





	
	
	
	
	
	

	public static int randInt(int min, int max) {


	    Random rand = new Random();


	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	
	
	

	


	public void update(float dt) {
		world.step(AdultGame.STEP, 6, 2);
		handleInput();
		teleportingLogic();		
		player.update(dt);
//		coinColector();
		
		
		
		for (int i = 0; i < teleports.size; i++) {teleports.get(i).update(dt);}
		//for (int i = 0; i < player.getCoinsArray().size; i++) {	player.getCoinsArray().get(i).update(dt);}
		for (int i = 0; i < doors.size; i++) {doors.get(i).update(dt);}
		for (int i = 0; i <EnemyContainer.GETSMALLENEMY().size; i++) {
		//	ed.directionChecker(i);
			//System.out.println(EnemyContainer.GETSMALLENEMY().get(i).getBody().getPosition());
			EnemyContainer.GETSMALLENEMY().get(i).update(dt);}
	
		//create enemy
		createEnemy(enemyIerator);
	}
	
	



	public void render() {
	
		// camera follow player
		cam.setPosition(player.getPlayerPositionX()* PPM, player.getPlayerPositionY()* PPM);
		cam.update();

		// draw tile
		if(!debug){
		gameMap.getTmr().setView(cam);
		gameMap.getTmr().render();
		}
		
		if (debug) {
			
			b2dCam.setPosition(cam.position.x/ PPM- (b2dCam.viewportWidth / PPM - (b2dCam.viewportWidth / PPM)),
					cam.position.y / PPM - (b2dCam.viewportHeight / PPM)+ (b2dCam.viewportHeight / PPM));
			
			b2dCam.setBounds(0,(gameMap.getWidthInTiles() * GameMaps.getTileSize()) / PPM,
					0, (gameMap.getHeightInTiles() * GameMaps.getTileSize())/ PPM);
			
			b2dCam.update();
			b2dRenderer.setDrawVelocities(true);
			b2dRenderer.setDrawContacts(true);
			b2dRenderer.setDrawInactiveBodies(true);
			b2dRenderer.render(world, b2dCam.combined);
			
			
			shapeRenderer.setProjectionMatrix(cam.combined);
			shapeRenderer.setAutoShapeType(true);
			//cam.update();
			shapeRenderer.begin();
			for (int j = 0; j < GameMaps.getBounds().size; j++) {
				
				shapeRenderer.rect(GameMaps.getBounds().get(j).getX(),GameMaps.getBounds().get(j).getY(),GameMaps.getBounds().get(j).getWidth(),GameMaps.getBounds().get(j).getHeight())	;
			}
			for (int j = 0; j < PlayerControler.getTrace().size; j++) {
				
				shapeRenderer.setColor(255,255,255,1);
				shapeRenderer.circle(PlayerControler.getTrace().get(j).x, PlayerControler.getTrace().get(j).y, PlayerControler.getTrace().get(j).radius-1);
				shapeRenderer.setColor(255,255,255,1);
			}

			
			shapeRenderer.end();
			
			
		}

		// draw player
		sb.setProjectionMatrix(cam.combined);
		player.render(sb);
		
		// draw portal
		for (int i = 0; i < teleports.size; i++) {teleports.get(i).render(sb);}
		for (int j = 0; j < doors.size; j++) {doors.get(j).render(sb);}
		//for (int i = 0; i <player.getCoinsArray().size; i++) {player.getCoinsArray().get(i).render(sb);}
		
		
		if(!debug){

		
		for (int j = 0; j < EnemyContainer.GETSMALLENEMY().size; j++) {EnemyContainer.GETSMALLENEMY().get(j).render(sb);}
		}

		//draw HUD
		sb.setProjectionMatrix(hudCam.combined);
		hud.render(sb);
		


		}


	
//TODO move this shit form here
	
	
/*	private void coinColector(){
		Array<Body> coinList = cl.getCoins();
		for (int i = 0; i < coinList.size; i++) {
			Body bo = coinList.get(i);
			//moving coins
			bm= new BodyMover(bo.getPosition().x, bo.getPosition().y, 8000, 8000, 10);
				((Coin)bo.getUserData()).getBody().setLinearVelocity((float) bm.getMovementX(), (float) bm.getMovementY());
			//	coinsArray.removeValue((Coin) bo.getUserData(), true);
				//	world.destroyBody(bo);
					
		}
		///counting coins
		if(coinList.size>0){
			Player.setCOINS(Player.getCOINS()+1);
			coinList.size--;
			
		}
		
	}*/
	

	public void dispose() {
		GameMaps.getTileMap().dispose();
	

	}


	
	
	

	private void creatPortal() {

		teleports = new Array<Teleport>();
		doors = new Array<Door>();
		MapLayer ml = GameMaps.getTileMap().getLayers().get("Teleport");
		if (ml == null)
			return;

		for (MapObject mo : ml.getObjects()) {

			String a = mo.getProperties().get("type", String.class);
			if (mo instanceof EllipseMapObject) {

				BodyDef cdef = new BodyDef();
				cdef.type = BodyType.StaticBody;
				float x = Float.parseFloat((mo.getProperties().get("x")
						.toString())) / PPM;
				float y = Float.parseFloat((mo.getProperties().get("y")
						.toString())) / PPM;
				cdef.position.set(x + 32 / PPM, y + 32 / PPM);
				Body body = world.createBody(cdef);
				FixtureDef cfdef = new FixtureDef();
				CircleShape cshape = new CircleShape();
				cshape.setRadius(32 / PPM);
				cfdef.shape = cshape;
				cfdef.isSensor = true;
				cfdef.filter.maskBits = B2DVars.BIT_PLAYER;

				if (a.equals("forward")) {
					cfdef.filter.categoryBits = B2DVars.BIT_PORTAL_FORWARD;
					body.createFixture(cfdef).setUserData("portalForward");
				} else if (a.equals("back")) {
					cfdef.filter.categoryBits = B2DVars.BIT_PORTAL_BACK;
					body.createFixture(cfdef).setUserData("portalBack");
				}

				Teleport t = new Teleport(body, a);
				body.setUserData(t);
				teleports.add(t);
				cshape.dispose();

			}
			if (mo instanceof RectangleMapObject) {
				Rectangle r = ((RectangleMapObject) mo).getRectangle();

				BodyDef cdef = new BodyDef();
				cdef.type = BodyType.StaticBody;
				float x = r.x / PPM;
				float y = r.y / PPM;
				
				float width = r.width /10;
				float height = r.height/10 ;
				cdef.position.set(x + 32 / PPM, y + 32 / PPM);
				

				Body body = world.createBody(cdef);
				FixtureDef cfdef = new FixtureDef();
				PolygonShape pshape = new PolygonShape();
				pshape.setAsBox(width / PPM, height / PPM);
				cfdef.shape = pshape;
			//	cfdef.isSensor = true;
				
				if (a.equals("doorEnter")) {
					cfdef.filter.categoryBits = B2DVars.BIT_DOOR;
					body.createFixture(cfdef).setUserData("doorEnter");
				} else if (a.equals("doorExit")) {
					cfdef.filter.categoryBits = B2DVars.BIT_DOOR;
					body.createFixture(cfdef).setUserData("doorExit");

				}

				Door d = new Door(body, a);
				body.setUserData(d);
				doors.add(d);
				pshape.dispose();

			}

		}
	}

	private void teleportingLogic() {
		// loading level
		if (cl.isPlayerTeleportingForward()) {

			GameMaps.setLevel(GameMaps.getLevel() + 1);
			gsm.setState(GameStateManager.PLAY);

		} else if (cl.isPlayerTeleportingBack()) {
			GameMaps.setLevel(GameMaps.getLevel() - 1);
			gsm.setState(GameStateManager.PLAY);

		} else if (cl.isPlayerEnterinHouse()) {
			
			GameMaps.setResTyp("house1");
			gsm.setState(GameStateManager.PLAY);
		} else if (cl.isPlayerExitingHouse()) {

			GameMaps.setResTyp("terrain");
			gsm.setState(GameStateManager.PLAY);
		}
	}
	@Override
	public void handleInput() {
	targeting.handleInput(cam);
		if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
			if(debug== true){
				debug= false;
			}else {
				debug= true;
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
