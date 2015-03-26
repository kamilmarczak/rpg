package com.rpg.game.data;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;
import com.rpg.game.handler.B2DVars;

public class DataManager  {

private Data data;
	
	public DataManager() {
	data= load();
	
	}
	
	private FileHandle file = Gdx.files.local("bin/data.rdks");
	
	public Data newData( ){
		data= new Data();
		data.setBulets(1200);
		data.setCashAmount(0);
		data.setPlayerHp(100);
		data.setPlayerX(2);
		data.setPlayerY(2);
		data.setAllowDiagMovement(true);
		data.setObjectiveCount(0);
		data.setObjectiveType(0);
		data.setWolfNeededForQuest(10);
		questInit();
		
		
		return data;
	}
	
	private void  questInit(){
		data.getQuests().put("QuestOne", null);
		data.getQuests().put("QuestTwo", null);
		data.getQuests().put("QuestThree", null);
		data.getQuests().put("QuestFour", null);
		
	}



	public void save() {
		Json json = new Json();
		json.setOutputType(OutputType.json);
		
		if(data.getPlayer().size>0){
		data .setPlayerX(data.getPlayer().first().getBody().getPosition().x/B2DVars.MTT);
		data.setPlayerY(data.getPlayer().first().getBody().getPosition().y/B2DVars.MTT);
		}

		
		
		//file.writeString(Base64Coder.encodeString(json.toJson(data)), false);
		
		file.writeString(json.toJson(data), false);
	}


	public Data load() {

		Json json = new Json();
		//data = json.fromJson(Data.class, Base64Coder.decodeString(file.readString()));
		if (file != null && file.exists()) {
				if(data != null)data =null;
		data = json.fromJson(Data.class, file.readString());
		
		
		}else {
		
			newData();
			save();
		}
		return data;
		
	}

	public Data getData() {
		return data;
	}

	

}
