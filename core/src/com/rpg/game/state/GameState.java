package com.rpg.game.state;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rpg.game.AdultGame;
import com.rpg.game.handler.BoundedCamera;
import com.rpg.game.handler.GameStateManager;

public abstract class GameState {

	protected GameStateManager gsm;
	protected AdultGame game;
	protected SpriteBatch sb;
	protected BoundedCamera cam;
	protected OrthographicCamera hudCam;
	protected BoundedCamera b2dCam;
	protected Camera staticCamera;

	protected GameState(GameStateManager gsm) {
		this.gsm = gsm;
		game = gsm.game();
		sb = game.getSpriteBatch();
		cam = game.getCamera();
		hudCam = game.getHUDCamera();
		b2dCam= game.getB2dCam();
		staticCamera= game.getStaticCamera();
	}

	public abstract void handleInput();

	public abstract void update(float dt);

	public abstract void render();

	public abstract void dispose();

}
