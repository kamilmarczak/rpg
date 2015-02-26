package com.rpg.game;

import static com.rpg.game.handler.B2DVars.PPM;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rpg.game.handler.BoundedCamera;
import com.rpg.game.handler.Content;
import com.rpg.game.handler.GameStateManager;
import com.rpg.game.handler.MyInput;
import com.rpg.game.handler.MyInputProcessor;
import com.rpg.game.state.Play;

public class AdultGame implements ApplicationListener {
	public static final String TITLE = "I LOVE JAVA";
	public static int G_WIDTH = 800;
	public static int G_HEIGHT = 600;
	
	public static final float STEP = 1 / 60f;
	private SpriteBatch sb;
	private BoundedCamera cam;
	private OrthographicCamera hudCam;
	public static Content res;
	public BoundedCamera b2dCam;
	
	

	private Viewport viewport;

	private GameStateManager gsm;
	
	public void create() {



		Gdx.input.setInputProcessor(new MyInputProcessor());
		res = new Content();
		res.loadTexture("img/template.png", "player");
		res.loadTexture("img/portal.png", "portal");
		res.loadTexture("img/portal2.png", "portal2");
		res.loadTexture("img/door.png", "door");
		res.loadTexture("img/npc2.png", "enemySmall");
		res.loadTexture("img/coin.png", "coins");
		res.loadTexture("img/hud.png", "hud");
		res.loadTexture("img/target.png", "mark");
		res.loadTexture("img/npc1.png", "npc");
	
	
		cam = new BoundedCamera();
		b2dCam = new BoundedCamera();
		
	//	cam.setToOrtho(false, G_WIDTH, G_HEIGHT);
		viewport = new ScreenViewport(cam);
		
		hudCam = new OrthographicCamera();;
		
		sb = new SpriteBatch();
		gsm = new GameStateManager(this);
		b2dCam.setToOrtho(false, AdultGame.G_WIDTH / PPM, AdultGame.G_HEIGHT/ PPM);

	}

	public void render() {
	
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		Gdx.graphics.setTitle(TITLE + " -- FPS: " + Gdx.graphics.getFramesPerSecond());
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render();
		MyInput.update();

	}

	@Override
	public void resize(int width, int height) {
		G_WIDTH= width;
		G_HEIGHT=height;
		
		viewport.update(width, height, false);
		b2dCam.setToOrtho(false, width / PPM, height/ PPM);


		
	//	hudCam.setToOrtho(false, width, height);
		

	

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		

	}

	@Override
	public void dispose() {
	
		res.removeAll();

	}
	public SpriteBatch getSpriteBatch(){return sb;}
	public BoundedCamera getCamera(){return cam;}
	public OrthographicCamera getHUDCamera(){return hudCam;}

	public BoundedCamera getB2dCam() {
		return b2dCam;
	}


}
