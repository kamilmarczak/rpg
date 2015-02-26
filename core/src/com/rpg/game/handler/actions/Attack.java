package com.rpg.game.handler.actions;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.rpg.game.entities.creature.Creature;
import com.rpg.game.entities.creature.Player;
import com.rpg.game.handler.Condition;
import com.rpg.game.handler.MyTimer;
import com.rpg.game.state.Play;

public class Attack {
	private MyTimer  timer;

	
	public Attack() {
		atackAvalibleChek();
		buttonsSetter();
		timer= new MyTimer(1);
	}
		
	public void update(){
		
		if(timer.hasCompleted()){
			Player.setAttackingMelee(false);
			Player.setAttackingRange(false);
		}
		
	}
	
	
	public void atackAvalibleChek(){	
		
		Array<Body> attack =Play.getCl().getFallow();	
		
		
		  for(int j =0; j<attack.size; j++)
		  {
	
			if( (((Creature) attack.get(j).getUserData())).getBody().getPosition().dst2(Play.getPlayer().getBody().getPosition())<.6f){
				
				((Creature) attack.get(j).getUserData()).setInCombat(true);
				
				
				
			}else {
				if(((Creature) attack.get(j).getUserData())!=null){
				((Creature) attack.get(j).getUserData()).setInCombat(false);
			}}
			 
			  
			  
		  }
			  
	
	}
	
	
	private void buttonsSetter(){

		Play.getHud().getButonKnife().addListener(new ClickListener(){
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				meleeAtack();

				
			}
		});
		
		Play.getHud().getButtonGun().addListener(new ClickListener(){
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				Player.setAttackingRange(true);
				rangeAtack();
				timer.start();

				
			}
		});
		
		
		
		
		
	}
	private void rangeAtack(){
		if(	Play.getMyInputHandler().getTagHolder()!=null){

			
			//set agro
			Play.getMyInputHandler().getTagHolder().setTargetRandom(false);
			//face
			Play.getPlayer().getTarget().getBody().setTransform(
					Play.getMyInputHandler().getTagHolder().getPosition().x,
					Play.getMyInputHandler().getTagHolder().getPosition().y, 0);
			//atack
			Play.getMyInputHandler().getTagHolder().setHealth(Play.getMyInputHandler().getTagHolder().getHealth()-Play.getPlayer().getEnemyHitPower());
		
		
		}
	}
	
	private void meleeAtack(){
		Player.setAttackingMelee(true);
		// when is targeted and  dyst from player to target is  less than  <6
		if(	Play.getMyInputHandler().getTagHolder()!=null&& Play.getMyInputHandler().getTagHolder().getPosition().dst2(Play.getPlayer().getPosition())<.6f){
			Play.getPlayer().setInRange(true);
			//set player to face enemy
			Play.getPlayer().getTarget().getBody()
			.setTransform(Play.getMyInputHandler().getTagHolder().getPosition().x,
					Play.getMyInputHandler().getTagHolder().getPosition().y, 0);
			Play.getMyInputHandler().getTagHolder().setHealth(Play.getMyInputHandler().getTagHolder().getHealth()-Play.getPlayer().getEnemyHitPower());
			timer.start();

			
			
			
			
		}else if(Play.getMyInputHandler().getTagHolder()!=null&& Play.getMyInputHandler().getTagHolder().getPosition().dst2(Play.getPlayer().getPosition())>.6f){
			Play.getPlayer().setInRange(false);
			
			Condition.setLastClickX(Play.getMyInputHandler().getTagHolder().getPosition().x);
			Condition.setLastClickY(Play.getMyInputHandler().getTagHolder().getPosition().y);
			
		}
		
	}
	
	
	


}
