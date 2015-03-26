package com.rpg.game.state;

import static com.rpg.game.handler.B2DVars.PPM;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.rpg.game.AdultGame;
import com.rpg.game.data.DataManager;
import com.rpg.game.enemyManager.EnemyManager;
import com.rpg.game.entities.Bullets;
import com.rpg.game.entities.Door;
import com.rpg.game.entities.HUD;
import com.rpg.game.entities.Teleport;
import com.rpg.game.entities.creature.Npc;
import com.rpg.game.entities.creature.Player;
import com.rpg.game.entities.creature.Enemy;
import com.rpg.game.entities.creature.npc.NpcManager;
import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.BuildingHandler;
import com.rpg.game.handler.GameMaps;
import com.rpg.game.handler.GameStateManager;
import com.rpg.game.handler.MyContactListener;
import com.rpg.game.handler.MyTimer;
import com.rpg.game.handler.actions.Attack;
import com.rpg.game.handler.actions.Fallow;
import com.rpg.game.handler.input.MyInputHandler;
import com.rpg.game.handler.steering.PlayerControler;
import com.rpg.game.quests.QuestManager;





public class Play extends GameState  {

	
	private static boolean debug = false ;
	private Box2DDebugRenderer b2dRenderer;
	private static MyContactListener cl;
	public  World world;
	public  Player player;
	private Array<Teleport> teleports;
	private ShapeRenderer shapeRenderer ;
	private Array<Door> doors;
	private static MyInputHandler myImputHandler;
	private Fallow fallow;
	private Attack attack;
	private Stage stage;

	//private Death death;
	private BuildingHandler roofRemover;
	private static boolean  visibleRoof= true;
	private DataManager dataManager;
	private QuestManager questManager;
	private NpcManager npcManager;
	private EnemyManager enemyManager;

	
	////




	public static boolean isVisibleRoof() {return visibleRoof;}
	public static void setVisibleRoof(boolean visibleRoof) {Play.visibleRoof = visibleRoof;	}
	public static HUD getHud() {return hud;}



	private static HUD hud;

	private GameMaps gameMap;
	//private int enemyIerator=0;
	private MyTimer myTimerSmallEnemy= new MyTimer(1);
	// pathfinding

		
	//private GameMaps gameMaps = new GameMaps();

	
	
	
	// movment
	public  Player getPlayer() {return player;}
	public  World getWorld() {return world;}
	public static MyContactListener getCl() {return cl;}
	

	public Play(GameStateManager gsm) {
		super(gsm);

		world = new World(new Vector2(0, 0), true); // gravity here
		
		shapeRenderer=new ShapeRenderer();
		//enemyContainer= new EnemyContainer();
		// set up box2d

		b2dRenderer = new Box2DDebugRenderer();
		// create Map
		gameMap = new GameMaps();
		//gameMap.createMap();
		cam.setBounds(0, gameMap.getWidthInTiles() * GameMaps.getTileSize(), 0,	gameMap.getHeightInTiles() * GameMaps.getTileSize());
		
		dataManager= new DataManager();
		//dataMenager.newData(data);
	//	dataMenager.save(data);
	//	dataMenager.load(data);
		
		dataManager.load();
		// create player
		createPlayer();
		questManager= new QuestManager(dataManager, world, player, gameMap);
		

	
		creatPortal();
		cl = new MyContactListener(player);
		world.setContactListener(cl);
		
		
		
		// set up b2dcamera
	//	b2dCam.setToOrtho(false, AdultGame.G_WIDTH / PPM, AdultGame.G_HEIGHT/ PPM);
/*		b2dCam.setBounds(0,(gameMap.getWidthInTiles() * GameMaps.getTileSize()) / PPM,
						 0,(gameMap.getHeightInTiles() * GameMaps.getTileSize()) / PPM);*/

		
		myImputHandler= new MyInputHandler();
		// Set up HUD
		stage = new Stage(new ScreenViewport(hudCam));
		hud = new HUD(player,stage, dataManager);
		attack= new Attack(world, player, dataManager);
		InputMultiplexer im = new InputMultiplexer(stage,myImputHandler);
		Gdx.input.setInputProcessor(im);
		fallow= new Fallow();

		roofRemover= new BuildingHandler(world);
		npcManager= new NpcManager(world, player,questManager, dataManager, stage);
		enemyManager= new EnemyManager(dataManager, world, gameMap, player);
		
	
	}

	

	
	private void createPlayer() {
		
		player = new Player(dataManager.getData().getPlayerX(), dataManager.getData().getPlayerY(), world, dataManager, gameMap);
		dataManager.getData().getPlayer().add(player);
		
	}

	

	

	public static int randInt(int min, int max) {


	    Random rand = new Random();


	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	

	

	


	public void update(float dt) {
		world.step(AdultGame.STEP, 6, 2);
	
	//	death.update();

	
		handleInput();
		teleportingLogic();		
		player.update(dt);
		fallow.proximityCheck();
		attack.atackAvalibleChek(player);
//		coinColector();
		hud.update();
		npcManager.update(dt);
		attack.update();
		
		
		for (int i = 0; i < getCl().getBuletsToRemoves().size; i++) {
			if(((Bullets)getCl().getBuletsToRemoves().get(i).getUserData())!=null){
			((Bullets)getCl().getBuletsToRemoves().get(i).getUserData()).destroyBullet();
			}
			getCl().getBuletsToRemoves().removeIndex(i);
			//world.destroyBody(getCl().getBuletsToRemoves().get(i));
		}
		
		
		
		
		for (int i = 0; i < dataManager.getData().getBulestsList().size; i++) {dataManager.getData().getBulestsList().get(i).update(dt);}
		for (int i = 0; i < teleports.size; i++) {teleports.get(i).update(dt);}
		for (int i = 0; i < doors.size; i++) {doors.get(i).update(dt);}
		for (int i = 0; i <dataManager.getData().getENEMIES().size; i++) {
			dataManager.getData().getENEMIES().get(i).update(dt);}
	
		//create enemy
	//	createEnemy(enemyIerator);
		
		  
		for (int i = 0; i <dataManager.getData().getCoins().size; i++) {
			dataManager.getData().getCoins().get(i).update(dt);}
		
		
		enemyManager.spawn();
	//	createEnemy(3);
	
	}
	  
	


	
	
	
	public void render() {
	
		// camera follow player
		cam.setPosition(player.getPlayerPositionX()* PPM, player.getPlayerPositionY()* PPM);
		cam.zoom=B2DVars.getZOOM();
		cam.update();
		hudCam.update();

		// draw tile
		if(!debug){
		gameMap.getTmr().setView(cam);

		gameMap.getTmr().getMap().getLayers().get("roof").setVisible(visibleRoof);
		gameMap.getTmr().render();
		}
		
		if (debug) {
			b2dCam.zoom=B2DVars.getZOOM();		
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

//npc		
		npcManager.render(sb);
		// draw portal
		for (int i = 0; i < teleports.size; i++) {teleports.get(i).render(sb);}
		for (int j = 0; j < doors.size; j++) {doors.get(j).render(sb);}
		//for (int i = 0; i <player.getCoinsArray().size; i++) {player.getCoinsArray().get(i).render(sb);}
		
		
		if(!debug){

		
		for (int j = 0; j < dataManager.getData().getENEMIES().size; j++) {dataManager.getData().getENEMIES().get(j).render(sb);}
		}

		//draw HUD
		sb.setProjectionMatrix(hudCam.combined);
		hud.render(sb);
		
		stage.draw();
		
//draw bulets
		sb.setProjectionMatrix(cam.combined);
		for (int i = 0; i < dataManager.getData().getBulestsList().size; i++) {dataManager.getData().getBulestsList().get(i).render(sb);}
		// draw player
		sb.setProjectionMatrix(cam.combined);
		player.render(sb);
//draw coins
		for (int i = 0; i <dataManager.getData().getCoins().size; i++) {
			dataManager.getData().getCoins().get(i).render(sb);}
		}


	

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
	
		myImputHandler.handleInput(cam, player,  gsm, dataManager);
	
	if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
		
			if(debug== true){
				debug= false;
			}else {
				debug= true;
			}
		}
	if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
		
		dataManager.save();
		gsm.pushState(GameStateManager.MENU);
		
	}

	
		
	}
	

	
	public static MyInputHandler getMyInputHandler() {
		return myImputHandler;
	}

	


}
