package com.rpg.game.quests;

import com.rpg.game.data.DataManager;

public class Quests {
	private  String questName,questStatus,questDialog;
	private int objectiveType;
	private int objectiveCount;
	private DataManager dataManager;




	
	public Quests(DataManager dataManager) {
		this.dataManager= dataManager;

	}


	public String getDialog(String questName, String questStatus){
		this.questName=questName;
		this.questStatus=questStatus;
		
		switch (questName) {
		
		case "QuestOne":
			questDialog=questOne(questStatus);
			dataManager.getData().setObjectiveCount(10);
			dataManager.getData().setObjectiveType(3);
			
			
			break;
		case "QuestTwo":
			questDialog=questTwo(questStatus);
			dataManager.getData().setObjectiveCount(9);
			dataManager.getData().setObjectiveType(4);
			
			break;
		}
		return questDialog;
		
		
	}
	
	
	private String questOne(String questStatus){
		switch (questStatus) {
		case "start":
			questDialog="hey i need help can you kill 10 wolfs for me";
	
		
			break;
		case "inProgress":
			questDialog="you need to kill more of them please";

			break;
		case "done":
			questDialog="good job here is your reward";


			break;
		case "finished":
			questDialog="hello old friend, what's up";

			break;
		}

		return questDialog;
			
		
	}
	
	
	
	
	
	
	
	
	
	
	private String questTwo(String questStatus){
		switch (questStatus) {
		case "start":
			questDialog=" 22  hey i need help can you kill 10 wolfs for me";

			
			break;
		case "inProgress":
			questDialog="22 you need to kill more of them please";

			break;
		case "done":
			questDialog="22 good job here is your reward";


			break;
		case "finished":
			questDialog="22 hello old friend, what's up";

			break;
		}

		return questDialog;
			
		
	}


	public int getObjectiveType() {
		return objectiveType;
	}


	public void setObjectiveType(int objectiveType) {
		this.objectiveType = objectiveType;
	}


	public int getObjectiveCount() {
		return objectiveCount;
	}


	public void setObjectiveCount(int objectiveCount) {
		this.objectiveCount = objectiveCount;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	

}
