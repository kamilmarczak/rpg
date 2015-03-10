package com.rpg.game.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;

public class DataManager  {


	
	public DataManager() {
	
	}
	
	private FileHandle file = Gdx.files.local("bin/data.rdks");
	
	public Data newData(Data data){
		
		data.setBulets(100);
		data.setCashAmount(0);
		data.setPlayerHp(100);
		return data;
	}
	
	
	public void save(Data data) {
		Json json = new Json();
		json.setOutputType(OutputType.json);
		//file.writeString(Base64Coder.encodeString(json.toJson(data)), false);
		file.writeString(json.toJson(data), false);
	}


	public Data load(Data data) {

		Json json = new Json();
		//data = json.fromJson(Data.class, Base64Coder.decodeString(file.readString()));
		if (file != null && file.exists()) {
				//if(data != null)data =null;
		data = json.fromJson(Data.class, file.readString());
		}else {
			newData(data);
			save(data);
		}
		return data;
		
	}

	

	

}
