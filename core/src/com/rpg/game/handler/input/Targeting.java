package com.rpg.game.handler.input;

import static com.rpg.game.handler.B2DVars.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.rpg.game.entities.creature.Creature;
import com.rpg.game.handler.Condition;
import com.rpg.game.handler.EnemyContainer;
import com.rpg.game.state.Play;

public class Targeting {
	
	private Vector2 tempSpawn, targetTemp;
	private Creature tagHolder;

//	private Creature tagHolder2 =null;
	private boolean targetIsnotEnemy= false,wasTagged= false,justPresed= false;
	
	
	public Targeting() {
	
	}
	public void handleInput(Camera cam) {

		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			if(justPresed){
				justPresed=false;
				targetTemp= new Vector2(cam.position.x/PPM-(cam.viewportWidth/2/PPM)+Gdx.input.getX()/PPM,
						cam.position.y/PPM-(cam.viewportHeight/2/PPM)+(cam.viewportHeight/PPM)-Gdx.input.getY()/PPM);
				
				
				
				
				//check all enemys
				for(int i=0; i<EnemyContainer.GETSMALLENEMY().size;i++){
					//if you cliced on enemy position
					if(EnemyContainer.GETSMALLENEMY().get(i).getBody().getPosition().dst2(targetTemp)<.06f){

						if(EnemyContainer.GETSMALLENEMY().get(i).isTagged()){wasTagged=true;}else {wasTagged=false;}
						if(tagHolder!=null)tagHolder.setTagged(false);
						tagHolder= EnemyContainer.GETSMALLENEMY().get(i);
					
					}else {
						//if you cliced on  not enemys position
						Condition.setMoving(true);
					}
			}// when enemy  is out of range
				while (tagHolder!=null && Play.getPlayer().getBody().getPosition().dst(tagHolder.getPosition())>3) {
					tagHolder.setTagged(false);
					tagHolder=null;		
				}
				
				//if enemy is null
				if(tagHolder==null){
					System.out.println("target null");
						 Condition.setMoving(true);
						 //when it not null is taged and was not taged before
			}else if (tagHolder!=null&& !tagHolder.isTagged() &&!wasTagged) {
					tagHolder.setTagged(true);
					 Condition.setMoving(false);
					 
				
			}
				Condition.setLastClickX  (cam.position.x/PPM-(cam.viewportWidth/2/PPM)+Gdx.input.getX()/PPM );
				Condition.setLastClickY (cam.position.y/PPM-(cam.viewportHeight/2/PPM)+(cam.viewportHeight/PPM)-Gdx.input.getY()/PPM);
			

				}

		}else {
			justPresed=true;
		}
		if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
			 float x= (cam.position.x/PPM-(cam.viewportWidth/2/PPM)+Gdx.input.getX()/PPM );
		float y=(cam.position.y/PPM-(cam.viewportHeight/2/PPM)+(cam.viewportHeight/PPM)-Gdx.input.getY()/PPM);
			 Play.player.getTarget().getBody().setTransform(x, y,  Play.player.getTarget().getBody().getAngle());
		}
		
		

		
		

	}
	
	
}
