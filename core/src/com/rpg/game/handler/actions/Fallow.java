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
			

	//	Array<Body> bodiesDmg = Play.getCl().getDamege();
		Array<Body> fallowDmg =Play.getCl().getFallow();	
		


		
		  //Enemy Fallow player 
		  for(int j =0; j<fallowDmg.size; j++){
			// ((Creature) fallowDmg.get(j).getUserData()).getTarget().getBody().setTransform(Play.getPlayer().getPosition(),0);
			 ((Creature) fallowDmg.get(j).getUserData()).setTargetRandom(false);

			 
			  
			  
		  }
			  
			  
/*			  
		  { Body f =fallowDmg.get(j); bm= new BodyMover(((Entity)
		  f.getUserData()).getBody().getPosition().x, ((Entity)
		  f.getUserData()).getBody().getPosition().y, getPlayerPositionX(),
		  getPlayerPositionY(), 1); ((Entity)
		  f.getUserData()).getBody().setLinearVelocity
		  
		 */
		
		// Enemy attack player
/*		for (int i = 0; i < bodiesDmg.size; i++) {
			Body b = bodiesDmg.get(i);

			((Entity) b.getUserData()).attack();

			// Player attack
			if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
				Condition.setPlayerIsAttacking(true);
				setTimerForAtt();

				((Entity) b.getUserData()).setHitPoint(((Entity) b
						.getUserData()).getHitPoint() - 0.5f);
				ishidead((Entity) b.getUserData());
			}
		}*/

	}
}
