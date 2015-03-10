package com.rpg.game.handler.actions;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.rpg.game.data.Data;
import com.rpg.game.entities.Bullets;
import com.rpg.game.entities.creature.Creature;
import com.rpg.game.entities.creature.Player;
import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.Condition;
import com.rpg.game.handler.MyTimer;
import com.rpg.game.handler.steering.Target;
import com.rpg.game.state.Play;

public class Attack {
	private World world;
	private Player player;
	private MyTimer  timer;
	private Data data;

	
	public Attack(World world,Player player,Data data) {
		this.world=world;
		this.player= player;
		this.data= data;
		atackAvalibleChek(player);
		buttonsSetter();
		timer= new MyTimer(1);
	}
		
	public void update(){
		
		if(timer.hasCompleted()){
			Player.setAttackingMelee(false);
			Player.setAttackingRange(false);
		}
		
		
	}
	
	
	public void atackAvalibleChek(Player player){	
		
		Array<Body> attack =Play.getCl().getFallow();	
		
		
		  for(int j =0; j<attack.size; j++)
		  {
			//  if(attack.get(j).getUserData()!=null&& !attack.get(j).getUserData().getClass().equals(Target.class))
			  
			if( (((Creature) attack.get(j).getUserData())).getBody().getPosition().dst2(player.getBody().getPosition())<.6f){
				
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
				rangeAtack(world);
				timer.start();

				
			}
		});

	}
	private void rangeAtack(World world){
		if(	Play.getMyInputHandler().getTagHolder()!=null){
			//new Bullets(Play.getPlayer().getBody().getPosition().x/B2DVars.MTT, Play.getPlayer().getBody().getPosition().y/B2DVars.MTT, new Vector2(1, 1));

			
			//set agro
		//	Play.getMyInputHandler().getTagHolder().setTargetRandom(false);
			
			
			
			
			
/*			
		Play.getPlayer().getTarget().getBody().setTransform(
					Play.getMyInputHandler().getTagHolder().getPosition().x/B2DVars.MTT,
					Play.getMyInputHandler().getTagHolder().getPosition().y/B2DVars.MTT, 0);*/
			
		// shoot
			new Bullets(
					
					
					player.getBody().getPosition().x/B2DVars.MTT+player.getBody().getWorldVector(new Vector2(0.30f,0.55f)).x/B2DVars.MTT, 
					player.getBody().getPosition().y/B2DVars.MTT+player.getBody().getWorldVector(new Vector2(0.30f,0.55f)).y/B2DVars.MTT,
					Play.getMyInputHandler().getTagHolder().getBody().getPosition().x/B2DVars.MTT,
					Play.getMyInputHandler().getTagHolder().getBody().getPosition().y/B2DVars.MTT, world, data);



		
		}

	}
	
	
	
	
	private void meleeAtack(){
		Player.setAttackingMelee(true);
		// when is targeted and  dyst from player to target is  less than  <6
		if(	Play.getMyInputHandler().getTagHolder()!=null&& Play.getMyInputHandler().getTagHolder().getPosition().dst2(player.getPosition())<.6f){
			player.setInRange(true);
			//set player to face enemy
			player.getTarget().getBody()
			.setTransform(Play.getMyInputHandler().getTagHolder().getPosition().x,
					Play.getMyInputHandler().getTagHolder().getPosition().y, 0);
			Play.getMyInputHandler().getTagHolder().setHealth(Play.getMyInputHandler().getTagHolder().getHealth()-player.getHitPower());
			timer.start();

			
			
			
			
		}else if(Play.getMyInputHandler().getTagHolder()!=null&& Play.getMyInputHandler().getTagHolder().getPosition().dst2(player.getPosition())>.6f){
			player.setInRange(false);
			
			
			Condition.setLastClickX(Play.getMyInputHandler().getTagHolder().getPosition().x);
			Condition.setLastClickY(Play.getMyInputHandler().getTagHolder().getPosition().y);
			
		}
		
	}
	
	
	


}
