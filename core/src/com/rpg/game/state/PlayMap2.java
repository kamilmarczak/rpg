package com.rpg.game.state;

import static com.rpg.game.handler.B2DVars.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.rpg.game.AdultGame;
import com.rpg.game.entities.Player;
import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.GameStateManager;
import com.rpg.game.handler.MyContactListener;

public class PlayMap2 extends GameState{
	
	
	private TiledMap tileMap;
	private OrthogonalTiledMapRenderer tmr;
	private Integer tileSize;
	private Integer tileMapWidth;
	private Integer tileMapHeight;
	private Player player;
	private Body body;
	private World world;
	private MyContactListener cl;
	private float lastClickX;
	private float lastClickY;

	public PlayMap2(GameStateManager gsm) {
		super(gsm);
		
		 	//create world
		
		 world = new World(new Vector2(0, 0), true);
			cl = new MyContactListener();
			world.setContactListener(cl);
			
			 createMap();
			 cam.setBounds(0, tileMapWidth * tileSize, 0, tileMapHeight * tileSize);
			 
			 
			 
			//create Player
			createPlayer();
			lastClickX=player.getBody().getPosition().x;
		lastClickY=player.getBody().getPosition().y;
		
			
			
			
	}

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float dt) {
		world.step(AdultGame.STEP, 6, 2);
		player.update(dt);
		
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.setPosition(player.getPosition().x * PPM, player.getPosition().y* PPM);
		cam.update();
		tmr.setView(cam);
		tmr.render();
		
	
		
		drawPlayer();
		
	}

	@Override
	public void dispose() {
		sb.dispose();
		tileMap.dispose();
	
		
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
		body.createFixture(fdef).setUserData("Player1");

		// Player's sensor
		shape.setAsBox(35 / PPM, 35 / PPM, new Vector2(0 / PPM, 0 / PPM), 0);
		fdef.shape = shape;
		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
		// fdef.filter.maskBits= B2DVars.
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("sensor1");

		// create Player
		player = new Player(body);
		player.playAnimation(4);
		body.setUserData("player1");
		shape.dispose();
	}
	public void drawPlayer() {
	
		sb.setProjectionMatrix(cam.combined);
		player.render(sb);

	}

}
