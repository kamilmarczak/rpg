package com.rpg.game.handler;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.rpg.game.pathfinding.Mover;
import com.rpg.game.pathfinding.TileBasedMap;
public class GameMaps implements TileBasedMap {
	public static TiledMap tileMap;


	private static String resTyp = "terrain";
	public static void setResTyp(String resTyp) {
		GameMaps.resTyp = resTyp;
	}

	private static  int level = 1;
	private static float tileSize;
	public static void setLevel(int level) {GameMaps.level = level;}
	
	public static  int getLevel() {return GameMaps.level;}

	private OrthogonalTiledMapRenderer tmr;
	private int tileMapWidth;
	private int tileMapHeight;
	public static TiledMapTileLayer coliLayer;
	
	public void createMap() {


		// Load Tiled Map
		tileMap = new TmxMapLoader().load("terrain/" + resTyp + level + ".tmx");
	
		tmr = new OrthogonalTiledMapRenderer(tileMap);
		tileSize = tileMap.getProperties().get("tilewidth", Integer.class);
		tileMapWidth = tileMap.getProperties().get("width", Integer.class);
		tileMapHeight = tileMap.getProperties().get("height", Integer.class);
		
		coliLayer= (TiledMapTileLayer) tileMap.getLayers().get("Ground");
		

	//	System.out.println(coliLayer.getCell((int)(coliLayer.getTileWidth()/PPM), (int)(coliLayer.getTileHeight()/PPM)).getTile().getProperties().containsKey("grass"));
		
	}
	 
	
	
	public void createMap(String resTyp,Integer level) {
		GameMaps.resTyp = resTyp;
		GameMaps.level= level;

		// Load Tiled Map
		tileMap = new TmxMapLoader().load("terrain/" + resTyp + level + ".tmx");
		tmr = new OrthogonalTiledMapRenderer(tileMap);
		tileSize = tileMap.getProperties().get("tilewidth", Integer.class);
		tileMapWidth = tileMap.getProperties().get("width", Integer.class);
		tileMapHeight = tileMap.getProperties().get("height", Integer.class);
		
		
	}
	

	
	public OrthogonalTiledMapRenderer getTmr() {return tmr;}
	public static float getTileSize() {return tileSize;}
	public TiledMap getTileMap() {return tileMap;}
	
	//public int getTileMapWidth() {return tileMapWidth;}
	//public int getTileMapHeight() {return tileMapHeight;}



	@Override
	public int getWidthInTiles() {
		// TODO Auto-generated method stub
		return tileMapWidth;
	}

	@Override
	public int getHeightInTiles() {
		// TODO Auto-generated method stub
		return tileMapHeight;
	}

	@Override
	public void pathFinderVisited(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean blocked(Mover mover, int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float getCost(Mover mover, int sx, int sy, int tx, int ty) {
		// TODO Auto-generated method stub
		return 0;
	}

}
