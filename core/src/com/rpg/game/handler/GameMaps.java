package com.rpg.game.handler;



import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.rpg.game.entities.creature.Creature;
import com.rpg.game.pathfinding.Mover;
import com.rpg.game.pathfinding.TileBasedMap;




public class GameMaps implements TileBasedMap {
	private static TiledMap tileMap;
	
	private int[][] terrain ;
	private boolean[][] visited ;
	private int[][] units;
	
	

	private static String resTyp = "terrain";
	private static Array<Rectangle>  bounds;
	private static  int level = 1;
	private static float tileSize;
	private OrthogonalTiledMapRenderer tmr;
	private int tileMapWidth;
	private int tileMapHeight;
	public static TiledMapTileLayer mapLayer;
	public static final int GRASS = 1;
	public static final int WATER = 0;
	
	

	public static Array<Rectangle> getBounds() {return bounds;}
	public static void setResTyp(String resTyp) {GameMaps.resTyp = resTyp;}
	public static void setLevel(int level) {GameMaps.level = level;}
	public static  int getLevel() {return GameMaps.level;}
	public OrthogonalTiledMapRenderer getTmr() {return tmr;}
	public static float getTileSize() {return tileSize;}
	public static TiledMap getTileMap() {return tileMap;}
	
	
	
	public GameMaps(){
		GameMaps.bounds = new Array<Rectangle>();
		createMap(resTyp, level);
		mapLayer= (TiledMapTileLayer) tileMap.getLayers().get("Ground");
		terrain = new int[tileMapWidth][tileMapHeight];
		visited = new boolean[tileMapWidth][tileMapHeight];
		units = new int[tileMapWidth][tileMapHeight];
		allCelsinLayer();
		
		
	}
	
	
	 
	public void allCelsinLayer(){
		
		for(int col =0; col <mapLayer.getHeight();col++){
			for(int row=0; row<mapLayer.getWidth();row++){
				//get cell
				Cell cell = mapLayer.getCell(col, row);
				if (cell == null)continue;
				if (cell.getTile() == null)continue;
				if(cell.getTile().getProperties().containsKey("walkable")){
				terrain[col][row]=GRASS;
				  bounds.add(new Rectangle(col * tileSize, row * tileSize, tileSize, tileSize));
			
				
			}		
			}
		}
	
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
	
	public void clearVisited() {
		for (int x=0;x<getWidthInTiles();x++) {
			for (int y=0;y<getHeightInTiles();y++) {
				visited[x][y] = false;
			}
		}
	}
	public int getTerrain(int x, int y) {
		return terrain[x][y];
	}
	@Override
	public int getWidthInTiles() {return tileMapWidth;}
	@Override
	public int getHeightInTiles() {return tileMapHeight;}

	@Override
	public void pathFinderVisited(int x, int y) {
		visited[x][y] = true;
	}
	public boolean visited(int x, int y) {
		return visited[x][y];
	}
	
	@Override
	public boolean blocked(Mover mover, int x, int y) {
		
		int unit = ((Creature) mover).getType();
		if (unit == B2DVars.PLAYER) {
			return terrain[x][y] != GRASS;	
			
		}
		if (unit == B2DVars.SMALLENEMY) {
			return terrain[x][y] != GRASS;	
		}
		
		
		
		
		
		
		//TODO
		//not sure if true of fallse
		return true;
		}

	@Override
	public float getCost(Mover mover, int sx, int sy, int tx, int ty) {return 1;}

	public int getUnit(int x, int y) {
		return units[x][y];
	}

	public void setUnit(int x, int y, int unit) {
		units[x][y] = unit;
	}

}
