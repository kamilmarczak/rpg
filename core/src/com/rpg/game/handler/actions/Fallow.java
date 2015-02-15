package com.rpg.game.handler.actions;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.rpg.game.entities.Entity;
import com.rpg.game.entities.creature.Creature;
import com.rpg.game.handler.MyContactListener;
import com.rpg.game.state.Play;

public class Fallow {

	
	
public Fallow() {
	
}
	public void proximityCheck() {
			


		Array<Body> fallow =Play.getCl().getFallow();	
		


		
		  //Enemy Fallow player 
		  for(int j =0; j<fallow.size; j++){
	
			 ((Creature) fallow.get(j).getUserData()).setTargetRandom(false);
			  
		  }
			  

	}
}
