package com.rpg.game.handler;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class MyInputProcessor extends InputAdapter {

	
	public boolean keyDown(int k) {
		if(k == Keys.W) MyInput.setKey(MyInput.UP, true);
		if(k == Keys.S) MyInput.setKey(MyInput.DOWN, true); 
		if(k == Keys.A) MyInput.setKey(MyInput.LEFT, true);
		if(k == Keys.D) MyInput.setKey(MyInput.RIGHT, true); 
		MyInput.down = true;
		return true;
	}
	
	public boolean keyUp(int k) {
		if(k == Keys.W) MyInput.setKey(MyInput.UP, false);
		if(k == Keys.S) MyInput.setKey(MyInput.DOWN, false); 
		if(k == Keys.A) MyInput.setKey(MyInput.LEFT, false);
		if(k == Keys.D) MyInput.setKey(MyInput.RIGHT, false); 
		MyInput.down = false;
		return true;
	}
	


/*		
		public boolean mouseMoved(int x, int y) {
			MyInput.x = x;
			MyInput.y = y;
			return true;
		public boolean touchDragged(int x, int y, int pointer) {
			MyInput.x = x;
			MyInput.y = y;
			MyInput.down = true;
			return true;
	
		
		public boolean touchDown(int x, int y, int pointer, int button) {
			MyInput.x = x;
			MyInput.y = y;
			MyInput.down = true;
			return true;
		
		
		public boolean touchUp(int x, int y, int pointer, int button) {
			MyInput.x = x;
			MyInput.y = y;
			MyInput.down = false;
			return true;
		}
		*/
	}
