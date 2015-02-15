package com.rpg.game.handler.input;

import static com.rpg.game.handler.B2DVars.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.rpg.game.entities.creature.Creature;
import com.rpg.game.handler.Condition;
import com.rpg.game.handler.EnemyContainer;
import com.rpg.game.state.Play;

public class MyInputHandler implements InputProcessor {
	
	private Vector2 tempSpawn, targetTemp;
	private Creature tagHolder;

//	private Creature tagHolder2 =null;
	private boolean targetIsnotEnemy= false,wasTagged= false,justPresed= false;
	private Camera cam;
	
	
	public MyInputHandler() {

	
	}
	public void handleInput(Camera cam) {
		this.cam=cam;
		update();

	}
	
	private void update(){
		if (tagHolder!=null && Play.getPlayer().getBody().getPosition().dst2(tagHolder.getPosition())>16) {
			tagHolder.setTagged(false);
			tagHolder=null;		
		}
		
	}
	
	public Creature getTagHolder() {
		return tagHolder;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		


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
			}/// when enemy  is out of range
				
				
				//if enemy is null
				if(tagHolder==null){
				
						 Condition.setMoving(true);
						 //when it not null is taged and was not taged before
			}else if (tagHolder!=null&& !tagHolder.isTagged() &&!wasTagged) {
					tagHolder.setTagged(true);
					 Condition.setMoving(false);
					 
				
			}
				Condition.setLastClickX  (cam.position.x/PPM-(cam.viewportWidth/2/PPM)+Gdx.input.getX()/PPM );
				Condition.setLastClickY (cam.position.y/PPM-(cam.viewportHeight/2/PPM)+(cam.viewportHeight/PPM)-Gdx.input.getY()/PPM);
			

				//}

	/*	}else {
			justPresed=true;
		}*/
		if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
			 float x= (cam.position.x/PPM-(cam.viewportWidth/2/PPM)+Gdx.input.getX()/PPM );
		float y=(cam.position.y/PPM-(cam.viewportHeight/2/PPM)+(cam.viewportHeight/PPM)-Gdx.input.getY()/PPM);
			 Play.player.getTarget().getBody().setTransform(x, y,  Play.player.getTarget().getBody().getAngle());
		}
		
		
		
		

		return false;
		
		
		
		
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
