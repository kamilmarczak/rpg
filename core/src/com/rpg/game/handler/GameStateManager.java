package com.rpg.game.handler;


import java.util.Stack;

import com.rpg.game.AdultGame;
import com.rpg.game.state.GameState;
import com.rpg.game.state.Menu;
import com.rpg.game.state.Options;
import com.rpg.game.state.Play;


public class GameStateManager {
	
	private AdultGame game;
	
	private Stack<GameState> gameStates;
	
	public static final int MENU =  83435392;
	public static final int PLAY =  388231654;
	public static final int OPTIONS = 831212412;
	public static final int HOUSE = 231232412;
	public static final int LEVEL_SELECT = -9237632;
	public static final int WORMHOLE =  75435392;
	
	public GameStateManager(AdultGame game) {
		this.game = game;
		gameStates = new Stack<GameState>();
		pushState(MENU);
	}
	
	public void update(float dt) {
		gameStates.peek().update(dt);
	}
	
	public void render() {
		gameStates.peek().render();
	}
	
	public AdultGame game() { return game; }
	
	private GameState getState(int state) {
		if(state == PLAY) return new Play(this);
		if(state == MENU) return new Menu(this);
		if(state == OPTIONS) return new Options(this);
		return null;
	}
	
	public void setState(int state) {
		popState();
		pushState(state);
	}
	
	public void pushState(int state) {
		gameStates.push(getState(state));
	}
	
	public void popState() {
		GameState g = gameStates.pop();
		g.dispose();
	}
	
}
