package com.rpg.game.state;

import static com.rpg.game.handler.B2DVars.PPM;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.badlogic.gdx.utils.Array;
import com.rpg.game.AdultGame;
import com.rpg.game.entities.Coins;
import com.rpg.game.entities.Collectibles;
import com.rpg.game.entities.Door;
import com.rpg.game.entities.Enemy;
import com.rpg.game.entities.HUD;
import com.rpg.game.entities.Player;
import com.rpg.game.entities.Player2;
import com.rpg.game.entities.SmallEnemy;
import com.rpg.game.entities.Teleport;
import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.BodyMover;
import com.rpg.game.handler.EnemyDirection;
import com.rpg.game.handler.GameMaps;
import com.rpg.game.handler.GameStateManager;
import com.rpg.game.handler.MyContactListener;
import com.rpg.game.handler.MyTimer;

public class Play extends GameState {

	// private IsometricTiledMapRenderer isoTiledrenderer;
	private boolean debug = false;
	private Box2DDebugRenderer b2dRenderer;
	private MyContactListener cl;
	public static World world;
	//public static Player player;
	public static Player2 player2;
	private Body body;
	private Array<Teleport> teleports;
	private Array<Collectibles> collectibles;
	private Array<Door> doors;
	private static Array<Enemy> enemy;
	private EnemyDirection ed;
	private HUD hud;
	private BodyMover bm;
	private GameMaps gameMap;
	private MyTimer timerPlayer;
	private static boolean playerIsAttacking = false;
	// movment
	private static float playerPositionX;
	private static float playerPositionY;
	public static float getPlayerPositionX() {return playerPositionX;}
	public static float getPlayerPositionY() {return playerPositionY;}
	public static float getLastClickX() {return lastClickX;}
	public static float getLastClickY() {return lastClickY;}
//	public static Player getPlayer() {return player;}
	public static Player2 getPlayer() {return player2;}
	public static boolean isMoving() {return isMoving;}
	public static Array<Enemy> getEnemy() {return enemy;}
	private static float lastClickX;
	private static float lastClickY;
	public static boolean isMoving = false;
	private static int numerAnimacii = 100;
	
	
	public Play(GameStateManager gsm) {

		super(gsm);
		ed = new EnemyDirection();
		collectibles = new Array<Collectibles>();

		// set up box2d
		world = new World(new Vector2(0, 0), true); // gravity here
		cl = new MyContactListener();
		world.setContactListener(cl);
		b2dRenderer = new Box2DDebugRenderer();

		// create Map
		gameMap = new GameMaps();
		gameMap.createMap();
		cam.setBounds(0, gameMap.getWidthInTiles() * GameMaps.getTileSize(), 0,
				gameMap.getHeightInTiles() * GameMaps.getTileSize());

		// create player
		createPlayer();
		

		
		lastClickX = player2.getBody().getPosition().x;
		lastClickY = player2.getBody().getPosition().y;
		
		// create portal
		creatPortal();

		createEnemy(20);

		// set up b2dcamera
		b2dCam.setToOrtho(false, AdultGame.G_WIDTH / PPM, AdultGame.G_HEIGHT/ PPM);
		b2dCam.setBounds(0,
				(gameMap.getWidthInTiles() * GameMaps.getTileSize()) / PPM, 0,
				(gameMap.getHeightInTiles() * GameMaps.getTileSize()) / PPM);
		// Set up HUD
		hud = new HUD(player2);
		
		
		
		
		
	}

	public static World getWorld() {
		return world;
	}
	private void createCoin(float posX,float  posY) {
	
		Collectibles colle = new Collectibles(new Coins(world, posX, posY).getBody(), "coins");
		collectibles.add(colle);
		colle.getBody().setUserData(colle);
	}
	private void createEnemy(int iletenmy) {
		enemy = new Array<Enemy>();

		for (int i = 0; i < iletenmy; i++) {

			Random random = new Random();
			SmallEnemy sm = new SmallEnemy(300, 300);
		
			float x = random.nextFloat();
			float y = random.nextFloat();
			sm.getBody().setLinearVelocity(x, y);
			enemy.add(sm);
			sm.getBody().setUserData(sm);
			

		}

	}

	public void handleInput() {

		playerPositionX = player2.getBody().getPosition().x;
		playerPositionY = player2.getBody().getPosition().y;

		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			player2.getBody().setLinearVelocity(0, 0);
			lastClickX = cam.position.x / PPM - (cam.viewportWidth / 2 / PPM)
					+ Gdx.input.getX() / PPM;
			lastClickY = cam.position.y / PPM - (cam.viewportHeight / 2 / PPM)
					+ (cam.viewportHeight / PPM) - Gdx.input.getY() / PPM;
			isMoving = true;

			animationChecker();
		}

		Player.playerControl();

	}

	

	public void update(float dt) {

		handleInput();

		world.step(AdultGame.STEP, 6, 2);
		damage();

		player2.update(dt);

		for (int i = 0; i < teleports.size; i++) {
			teleports.get(i).update(dt);
		}
		
		for (int i = 0; i < collectibles.size; i++) {
			collectibles.get(i).update(dt);
		}
		for (int i = 0; i < doors.size; i++) {
			doors.get(i).update(dt);
		}

		// if(Enemy.getSmallEnemys()!=null){
		for (int i = 0; i < enemy.size; i++) {
			ed.directionChecker(i);

			enemy.get(i).update(dt);
		}
		// }

		teleportingLogic();
	
		
	}


	public void render() {
	
		// camera follow player
		cam.setPosition(player2.getPosition().x * PPM, player2.getPosition().y
				* PPM);
		cam.update();
		coinColector();

		// draw tile
		gameMap.getTmr().setView(cam);
		gameMap.getTmr().render();

		// draw player
		drawPlayer();
		// draw portal
		for (int i = 0; i < teleports.size; i++) {
			teleports.get(i).render(sb);
		}
		for (int j = 0; j < doors.size; j++) {
			doors.get(j).render(sb);

		}
		
		for (int j= 0; j < collectibles.size; j++) {
			collectibles.get(j).render(sb);
		}
		
		
		for (int j = 0; j < enemy.size; j++) {

			enemy.get(j).render(sb);

		}

		//draw HUD
		// draw hud
		
	
		sb.setProjectionMatrix(hudCam.combined);
	
		hud.render(sb);
		
		if (debug) {

			b2dCam.setPosition(
					cam.position.x
							/ PPM
							- (b2dCam.viewportWidth / PPM - (b2dCam.viewportWidth / PPM)),
					cam.position.y / PPM - (b2dCam.viewportHeight / PPM)
							+ (b2dCam.viewportHeight / PPM));

			b2dCam.setBounds(0,
					(gameMap.getWidthInTiles() * GameMaps.getTileSize()) / PPM,
					0, (gameMap.getHeightInTiles() * GameMaps.getTileSize())
							/ PPM);

			b2dCam.update();
			b2dRenderer.render(world, b2dCam.combined);

		}

	}

	public void drawPlayer() {

		sb.setProjectionMatrix(cam.combined);
		//player.render(sb);
		player2.render(sb);

	}
	private void damage() {
		// damage
		Array<Body> bodiesDmg = cl.getDamege();
		float posX, posY;
		
		for (int i = 0; i < bodiesDmg.size; i++) {
			Body b = bodiesDmg.get(i);
			
			bm= new BodyMover(((SmallEnemy) b.getUserData()).getPosition().x, ((SmallEnemy) b.getUserData()).getPosition().y,playerPositionX, playerPositionY, 1);
			((SmallEnemy) b.getUserData()).getBody().setLinearVelocity((float)bm.getMovementX(),(float)bm.getMovementX());
		//	((SmallEnemy) b.getUserData()).attack();
		
	
			if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			playerIsAttacking= true;
				if (((SmallEnemy) b.getUserData()) != null) {
					//player.attack((SmallEnemy) b.getUserData());
				//	player.attack((SmallEnemy) b.getUserData());
				//System.out.println(((Enemy)b.getUserData()).getBody());	
					
					//((SmallEnemy) b.getUserData()).setHitPoint(((SmallEnemy) b.getUserData()).getHitPoint() - 0.5f);
					
					if (((SmallEnemy) b.getUserData()).getHitPoint() <= 0) {
						posX=((SmallEnemy) b.getUserData()).getBody().getPosition().x;
						posY=((SmallEnemy) b.getUserData()).getBody().getPosition().y;
						createCoin(posX, posY);
						
						
						((SmallEnemy) b.getUserData()).getHp().getSkinAtlas().dispose();
						enemy.removeValue((SmallEnemy) b.getUserData(),false);
						world.destroyBody(b);
					}
				}
			}
		}		
	}

	
	public static void setPlayerIsAttacking(boolean playerIsAttacking) {
		Play.playerIsAttacking = playerIsAttacking;
	}
	public static boolean isPlayerIsAttacking() {
		return playerIsAttacking;
	}
	private void coinColector(){
		Array<Body> coinList = cl.getCoins();
		for (int i = 0; i < coinList.size; i++) {
			Body bo = coinList.get(i);
			//	collectibles.removeValue((Collectibles)bo.getUserData(), true);
			//	world.destroyBody(bo);
				
			//moving coins
			bm= new BodyMover(bo.getPosition().x, bo.getPosition().y, 8000, 8000, 10);
				((Collectibles)bo.getUserData()).getBody().setLinearVelocity((float) bm.getMovementX(), (float) bm.getMovementY());
				

		}
		///counting coins
		if(coinList.size>0){
			Player.setCOINS(Player.getCOINS()+1);
			coinList.size--;
		}
		
	}
	

	public void dispose() {
		GameMaps.tileMap.dispose();

	}
	
	public static void setMoving(boolean isMoving) {
		Play.isMoving = isMoving;
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
		shape.setAsBox(15 / PPM, 25 / PPM, new Vector2(0 / PPM, 0 / PPM), 0); // //
		fdef.shape = shape;
		fdef.restitution = 0;
		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
		// fdef.filter.maskBits= B2DVars.
		body.createFixture(fdef).setUserData("Player");

		// Player's sensor
		shape.setAsBox(48 / PPM, 68 / PPM, new Vector2(0 / PPM, 0 / PPM), 0);
		fdef.shape = shape;
		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
		fdef.filter.maskBits = B2DVars.BIT_DOOR | B2DVars.BIT_ENEMY;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("sensor");

		// create Player

		//player = new Player(body);
		player2 = new Player2(650, 650);
		player2.playAnimation(4,player2.getEnemyTextureName());
		body.setUserData("player");
		shape.dispose();
	}

	private void creatPortal() {

		teleports = new Array<Teleport>();
		doors = new Array<Door>();
		MapLayer ml = gameMap.getTileMap().getLayers().get("Teleport");
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
				// float x = (float) mo.getProperties().get("x") / PPM;
				// float y = (float) mo.getProperties().get("y") / PPM;

				Rectangle r = ((RectangleMapObject) mo).getRectangle();

				BodyDef cdef = new BodyDef();
				cdef.type = BodyType.StaticBody;
				float x = r.x / PPM;
				float y = r.y / PPM;
				float width = r.width / PPM;
				float height = r.height / PPM;
				cdef.position.set(x + 32 / PPM, y + 32 / PPM);
				cdef.angle = 45;
				// System.out.println((float)mo.getProperties().get("rotation")
				// );
				Body body = world.createBody(cdef);
				FixtureDef cfdef = new FixtureDef();
				PolygonShape pshape = new PolygonShape();
				pshape.setAsBox(width / PPM, height / PPM);
				cfdef.shape = pshape;
				cfdef.isSensor = true;
				if (a.equals("doorEnter")) {

					body.createFixture(cfdef).setUserData("doorEnter");
				} else if (a.equals("doorExit")) {
					body.createFixture(cfdef).setUserData("doorExit");

				}

				Door d = new Door(body, a);
				body.setUserData(d);
				doors.add(d);
				pshape.dispose();

			}

		}
	}

	// checking direction to draw sprite in the correct way
	private void animationChecker() {
		if ((int) lastClickX > (int) playerPositionX
				&& (int) lastClickY > (int) playerPositionY && !playerIsAttacking)
			aniChecker(5);

		if ((int) lastClickX > (int) playerPositionX
				&& (int) lastClickY < (int) playerPositionY&& !playerIsAttacking)
			aniChecker(8);

		if ((int) lastClickX < (int) playerPositionX
				&& (int) lastClickY > (int) playerPositionY&& !playerIsAttacking)
			aniChecker(6);

		if ((int) lastClickX < (int) playerPositionX
				&& (int) lastClickY < (int) playerPositionY&& !playerIsAttacking)
			aniChecker(7);

		if ((int) lastClickX < (int) playerPositionX
				&& (int) lastClickY == (int) playerPositionY&& !playerIsAttacking)
			aniChecker(1);

		if ((int) lastClickX > (int) playerPositionX
				&& (int) lastClickY == (int) playerPositionY&& !playerIsAttacking)
			aniChecker(3);

		if ((int) lastClickX == (int) playerPositionX
				&& (int) lastClickY < (int) playerPositionY&& !playerIsAttacking)
			aniChecker(2);

		if ((int) lastClickX == (int) playerPositionX
				&& (int) lastClickY > (int) playerPositionY&& !playerIsAttacking)
			aniChecker(0);
/*		if (playerIsAttacking ){
			aniChecker(9);
			
			
			
	
				timerPlayer= new MyTimer(2);
				timerPlayer.start();
				
				if(timerPlayer.hasCompleted()) {
					Play.setPlayerIsAttacking(false);
					
				}
				

			
		}*/
	}

	// dont start animation over and over again
	public static void aniChecker(int i) {
		if (numerAnimacii != i) {
			player2.playAnimation(i,player2.getEnemyTextureName());
			numerAnimacii = i;
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

			// GameMaps.setLevel(level--);
			gsm.setState(GameStateManager.PLAY);
		} else if (cl.isPlayerEnterinHouse()) {

			GameMaps.setResTyp("house1");
			// gameMap.createMap("house1",level);

			gsm.setState(GameStateManager.PLAY);
		} else if (cl.isPlayerExitingHouse()) {

			GameMaps.setResTyp("terrain");
			gsm.setState(GameStateManager.PLAY);
		}
	}

}
