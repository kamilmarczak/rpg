package com.rpg.game.handler.input;

import static com.rpg.game.handler.B2DVars.PPM;
import javafx.scene.input.KeyCode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.rpg.game.data.Data;
import com.rpg.game.data.DataManager;
import com.rpg.game.entities.creature.Creature;
import com.rpg.game.entities.creature.Player;
import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.Condition;
import com.rpg.game.handler.GameStateManager;

public class MyInputHandler implements InputProcessor {
	
	private Vector2  targetTemp;
	private Creature tagHolder;

//	private Creature tagHolder2 =null;
	private boolean wasTagged= false;
	private Camera cam;
	private Data data;
	private GameStateManager gsm;
	private DataManager dataManager;
	
	
	public MyInputHandler() {

	
	}
	public void handleInput(Camera cam,Player player,Data data,GameStateManager gsm,DataManager dataManager) {
		this.cam=cam;
		this.data=data;
		this.gsm=gsm;
		this.dataManager=dataManager;
		update(player);

	}
	
	private void update(Player player){
		if (tagHolder!=null && player.getBody().getPosition().dst2(tagHolder.getPosition())>100) {
			tagHolder.setTagged(false);
			tagHolder=null;		
		}
		
	}
	
	public Creature getTagHolder() {return tagHolder;}
	public void setTagHolder(Creature tagHolder) {this.tagHolder = tagHolder;}
	
	
	@Override
	public boolean keyDown(int keycode) {
        if (keycode == Keys.BACK) {
        	
        	gsm.pushState(GameStateManager.MENU);
    		dataManager.save(data);
    	
        }
		return false;
	}
	
	
	
	@Override
	public boolean keyUp(int keycode) {

		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		

				
				targetTemp= new Vector2(cam.position.x/PPM-(cam.viewportWidth/2/PPM*B2DVars.getZOOM())+Gdx.input.getX()*B2DVars.getZOOM()/PPM,
						cam.position.y/PPM-(cam.viewportHeight/2/PPM*B2DVars.getZOOM())+(cam.viewportHeight*B2DVars.getZOOM()/PPM)-Gdx.input.getY()*B2DVars.getZOOM()/PPM);
				
				
				//check all enemys
				for(int i=0; i<data.getENEMIES().size;i++){
					//if you cliced on enemy position
					if(data.getENEMIES().get(i).getBody().getPosition().dst2(targetTemp)<.9f){

						if(data.getENEMIES().get(i).isTagged()){wasTagged=true;}else {wasTagged=false;}
						if(tagHolder!=null)tagHolder.setTagged(false);
						tagHolder= data.getENEMIES().get(i);
					
					}else {
						//if you cliced on  not enemys position
						Player.setMoving(true);
					}
			}/// when enemy  is out of range
				
				
				//if enemy is null
				if(tagHolder==null){
				
						 Player.setMoving(true);
						 //when it not null is taged and was not taged before
			}else if (tagHolder!=null&& !tagHolder.isTagged() &&!wasTagged) {
					tagHolder.setTagged(true);
					Player.setMoving(false);
					 
				
			}

			
				Condition.setLastClickX  (cam.position.x/PPM-(cam.viewportWidth/2/PPM*B2DVars.getZOOM())+Gdx.input.getX()*B2DVars.getZOOM()/PPM );
				Condition.setLastClickY (cam.position.y/PPM-(cam.viewportHeight/2/PPM*B2DVars.getZOOM())+(cam.viewportHeight*B2DVars.getZOOM()/PPM)-Gdx.input.getY()*B2DVars.getZOOM()/PPM);

		

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
		B2DVars.setZOOM(B2DVars.getZOOM()+amount /25f);

		return false;
	}
	
	
}
