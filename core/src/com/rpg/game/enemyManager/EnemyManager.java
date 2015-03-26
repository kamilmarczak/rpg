package com.rpg.game.enemyManager;

import com.badlogic.gdx.physics.box2d.World;
import com.rpg.game.data.DataManager;
import com.rpg.game.entities.creature.Player;
import com.rpg.game.entities.creature.Enemy;
import com.rpg.game.entities.creature.enemys.Wolf;
import com.rpg.game.handler.GameMaps;

public class EnemyManager {

	private DataManager dataManager;
	private World world;
    private GameMaps gameMap;
	private Player player;
	private Enemy smallCoy;
	
	
	public EnemyManager(DataManager dataManager,World world, GameMaps gameMap, Player player) {
		this.dataManager=dataManager;
	    this.world= world;
		this.gameMap=gameMap;
	    this.player=player; 

	   
	  
	    
	}
	
	public void spawn(){
		


			if((dataManager.getData().getWolfNeededForQuest()-dataManager.getData().getWolfKilledForQuest())>dataManager.getData().getWolfOnMap()){
				Enemy smalCoy = new Wolf(10,10, world,player, dataManager, gameMap );
				dataManager.getData().getENEMIES().add(smalCoy);
				smalCoy.getBody().setUserData(smalCoy);
				dataManager.getData().setWolfOnMap(dataManager.getData().getWolfOnMap()+1);
				

				
				
			

		}







		
		
	
	}
	
	

}