package com.rpg.game.handler;


import java.util.Stack;

import com.rpg.game.AdultGame;
import com.rpg.game.state.GameState;
import com.rpg.game.state.Play;
import com.rpg.game.state.PlayMap2;
import com.rpg.game.state.Wormhole;


public class GameStateManager {
	
	private AdultGame game;
	
	private Stack<GameState> gameStates;
	
	public static final int MENU =  83435392;
	public static final int WORMHOLE =  75435392;
	public static final int PLAY =  388231654;
	public static final int PLAYMAP2 = 831212412;
	public static final int HOUSE = 231232412;
	public static final int LEVEL_SELECT = -9237632;
	
	public GameStateManager(AdultGame game) {
		this.game = game;
		gameStates = new Stack<GameState>();
		pushState(PLAY);
	}
	
	public void update(float dt) {
		gameStates.peek().update(dt);
	}
	
	public void render() {
		gameStates.peek().render();
	}
	
	public AdultGame game() { return game; }
	
	private GameState getState(int state) {
		if(state == WORMHOLE) return new Wormhole(this);
		if(state == PLAY) return new Play(this);
		if(state == PLAYMAP2) return new PlayMap2(this);
	//	if(state == LEVEL_SELECT) return new LevelSelect(this);
	//	if(state == HOUSE) return new House(this);
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
