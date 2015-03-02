package com.rpg.game.handler.actions;

import com.rpg.game.entities.creature.Creature;
import com.rpg.game.handler.ContendHolder;
import com.rpg.game.state.Play;

public class Death {
	private Creature creatureToKill;




	public Death() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public void update(){
		hpChecker();
		
		
	}
	private void hpChecker(){
		
		if(	Play.getMyInputHandler().getTagHolder()!=null&& Play.getMyInputHandler().getTagHolder().getHealth()<=0){
			Play.getMyInputHandler().getTagHolder().getHealthBar().getSkinAtlas().dispose();
			creatureToKill= Play.getMyInputHandler().getTagHolder();
			ContendHolder.getENEMIES().removeValue(Play.getMyInputHandler().getTagHolder(), false);
			Play.getMyInputHandler().getTagHolder().setInCombat(false);

			creatureToKill.getTarget().getBody().setActive(false);
			creatureToKill.getBody().setActive(false);

			
			
			if(!Play.getWorld().isLocked()){
				Play.getMyInputHandler().getTagHolder().drop();
			Play.getMyInputHandler().getTagHolder().setInCombat(false);
			Play.getCl().getFallow().removeValue(Play.getMyInputHandler().getTagHolder().getBody(), false);
			Play.getWorld().destroyBody(Play.getMyInputHandler().getTagHolder().getBody());
			Play.getWorld().destroyBody(Play.getMyInputHandler().getTagHolder().getTarget().getBody());
			Play.getMyInputHandler().getTagHolder().setTarget(null);
			Play.getMyInputHandler().setTagHolder(null);
			}
			

		}
		
		
		
		
	}
	
	

}
