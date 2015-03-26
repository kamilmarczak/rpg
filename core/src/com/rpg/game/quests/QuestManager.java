package com.rpg.game.quests;

import com.badlogic.gdx.physics.box2d.World;
import com.rpg.game.data.DataManager;
import com.rpg.game.entities.creature.Player;
import com.rpg.game.entities.creature.Enemy;
import com.rpg.game.handler.GameMaps;

public class QuestManager {

	private DataManager dataManager;
	private String questStatus, questDialog, questName;
	private Quests quests;

	public QuestManager(DataManager dataManager,World world,Player player ,GameMaps  gameMap) {
		this.dataManager = dataManager;
		quests= new Quests(dataManager);

	}

	public void quest(String[] questList) {

		for (int i = 0; i < questList.length; i++) {
			if(dataManager.getData().isQuestdDone()==true){dataManager.getData().getQuests().put(questList[i], "done");}


			if (dataManager.getData().getQuests().get(questList[i]) == null) {
				dataManager.getData().getQuests().put(questList[i], "start");
				questStatus = dataManager.getData().getQuests().get(questList[i]);
				questName = questList[i];
				break;
			} else if (dataManager.getData().getQuests().get(questList[i]).equals("inProgress")) {
				questStatus = dataManager.getData().getQuests().get(questList[i]);
				questName = questList[i];
				break;
			}else if (dataManager.getData().getQuests().get(questList[i]).equals("done")) {
				questStatus = dataManager.getData().getQuests().get(questList[i]);
				questName = questList[i];
				dataManager.getData().setQuestdDone(false);
				break;
				
			}else {
				questStatus= dataManager.getData().getQuests().get(questList[i]);
			}

		}

		switch (questStatus) {

		case "start":
			questDialog = quests.getDialog(questName, questStatus);
			dataManager.getData().getQuests().put(questName, "inProgress");

			break;
		case "inProgress":
			questDialog = quests.getDialog(questName, questStatus);
			//dataManager.getData().getQuests().put(questName, "done");
			break;
		case "done":
			questDialog = quests.getDialog(questName, questStatus);
			dataManager.getData().getQuests().put(questName, "finished");
			break;
		case "finished":
			questDialog = "Thank you for all your help";
			break;

		default:
			break;

		}


	}
	
	
	
	
	
	
	
	
	

	
	

	public String getQuestStatus() {
		return questStatus;
	}

	public String getQuestName() {
		return questName;
	}

	public String getQuestDialog() {
		return questDialog;
	}

}
