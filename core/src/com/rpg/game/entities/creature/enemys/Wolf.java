package com.rpg.game.entities.creature.enemys;

import com.badlogic.gdx.physics.box2d.World;
import com.rpg.game.data.DataManager;
import com.rpg.game.entities.creature.Enemy;
import com.rpg.game.entities.creature.Player;
import com.rpg.game.handler.GameMaps;

public class Wolf extends Enemy {
	private DataManager dataManager;
	private static String teksture="enemySmall";

	public Wolf(float x, float y, World world, Player player,
			DataManager dataManager, GameMaps map) {
		super(x, y, world, player, dataManager, map, teksture);
		this.dataManager = dataManager;

	}

	@Override
	public void removeOne() {
		dataManager.getData().setWolfOnMap(dataManager.getData().getWolfOnMap() - 1);
	}

	@Override
	public void killedForQuest(int i) {
		dataManager.getData().setWolfKilledForQuest(dataManager.getData().getWolfKilledForQuest() + 1);
	}

}
