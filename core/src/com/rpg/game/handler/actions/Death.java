package com.rpg.game.handler.actions;

import com.badlogic.gdx.physics.box2d.World;
import com.rpg.game.data.Data;
import com.rpg.game.entities.creature.Creature;
import com.rpg.game.state.Play;

public class Death {
	private Creature creatureToKill;
private Creature creature;
private World world;
private Data data;



	public Death(Creature creature,World  world, Data data ) {
	this.creature= creature;
	this.world= world;
	this.data = data;

	}
	
	
	
	public void update(){
		hpChecker(creature);
		
		
	}
	private void hpChecker(Creature creature){
		
		if(	creature.getHealth()<=0){
			creature.getHealthBar().getSkinAtlas().dispose();
			creature.setInCombat(false);
			data.getENEMIES().removeValue(creature, false);

			creature.drop();
			creature.setInCombat(false);
			Play.getCl().getFallow().removeValue(creature.getBody(), false);
			world.destroyBody(creature.getBody());
			world.destroyBody(creature.getTarget().getBody());
			creature.setTarget(null);
			if(creature==Play.getMyInputHandler().getTagHolder()){
				Play.getMyInputHandler().setTagHolder(null);
				
			}

			

		}
		
		
		
		
		
		
		
		
/*		if(	Play.getMyInputHandler().getTagHolder()!=null&& Play.getMyInputHandler().getTagHolder().getHealth()<=0){
			Play.getMyInputHandler().getTagHolder().getHealthBar().getSkinAtlas().dispose();
			Play.getMyInputHandler().getTagHolder().setInCombat(false);
			ContendHolder.getENEMIES().removeValue(Play.getMyInputHandler().getTagHolder(), false);

			//	creatureToKill= Play.getMyInputHandler().getTagHolder();
		//	creatureToKill.getTarget().getBody().setActive(false);
		//	creatureToKill.getBody().setActive(false);

			
			
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
		*/
		
		
		
	}
	
	

}
