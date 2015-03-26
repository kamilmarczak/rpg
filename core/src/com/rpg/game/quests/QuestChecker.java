package com.rpg.game.quests;

import com.rpg.game.data.DataManager;
import com.rpg.game.entities.creature.Creature;

public class QuestChecker {
	

	public QuestChecker(int type, DataManager dataManager, Creature creature) {
		if(dataManager.getData().getObjectiveType()==type){
			dataManager.getData().setObjectiveprogress(dataManager.getData().getObjectiveprogress()+1);
			creature.killedForQuest(1);
			if(dataManager.getData().getObjectiveprogress()>=dataManager.getData().getObjectiveCount()){
				dataManager.getData().setQuestdDone(true);
				
			}
			
			
			
		}
	}

}
