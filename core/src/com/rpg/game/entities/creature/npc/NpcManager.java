package com.rpg.game.entities.creature.npc;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.rpg.game.data.DataManager;
import com.rpg.game.entities.creature.Player;
import com.rpg.game.quests.QuestManager;

public class NpcManager {
	private RegularNpc Jon;
	
	
	
	public NpcManager(World world, Player player, QuestManager questManager,DataManager dataManager,Stage stage) {
		Jon= new RegularNpc(2, 2, world, player,questManager, stage);
		dataManager.getData().getNpc().add(Jon);
	
	}
	
	
	

	public void update(float dt) {
		Jon.update(dt);

		
	}

	public void render(SpriteBatch sb) {
		Jon.render(sb);
		
	}

}
