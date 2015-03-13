package com.rpg.game.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;
import com.rpg.game.handler.B2DVars;

public class DataManager  {

private Data data;
	
	public DataManager(Data data) {
		this.data=data;
	
	}
	
	private FileHandle file = Gdx.files.local("bin/data.rdks");
	
	public Data newData(Data data){
		
		data.setBulets(1200);
		data.setCashAmount(0);
		data.setPlayerHp(100);
		data.setPlayerX(2);
		data.setPlayerY(2);
		data.setAllowDiagMovement(true);
		return data;
	}
	
	
	public void save(Data data) {
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
			System.out.println("file exist");
				//if(data != null)data =null;
		data = json.fromJson(Data.class, file.readString());
		
		
		}else {
		
			newData(data);
			save(data);
		}
		return data;
		
	}

	

	

}
