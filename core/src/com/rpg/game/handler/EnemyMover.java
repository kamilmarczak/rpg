package com.rpg.game.handler;

import static com.rpg.game.handler.B2DVars.PPM;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.Array;
import com.rpg.game.entities.Entity;
import com.rpg.game.pathfinding.AStarPathFinder;
import com.rpg.game.pathfinding.Path;
import com.rpg.game.pathfinding.PathFinder;

public class EnemyMover {

	private GameMaps map = new GameMaps();
	private MyTimer mt;
	private PathFinder finder;
	private Path path;
	private int iterator = 0, iterator2 = 0;

	private BodyMover bm;
	private boolean usItDone = true;

	private Array<Circle> enemyTrace = new Array<Circle>();

	public EnemyMover() {

		finder = new AStarPathFinder(map, 100, true);
		mt = new MyTimer(1);

	}

	public void pathStarter(Entity entity) {

			if(usItDone){

				
				int enemyNextX = randInt((int)entity.getBody().getPosition().x-5,(int) entity.getBody().getPosition().x+5);
				int enemyNextY =  randInt((int)entity.getBody().getPosition().y-5,(int) entity.getBody().getPosition().y+5);
				
	
				
				if(enemyNextY/B2DVars.MTT<=0||enemyNextX/B2DVars.MTT<=0||enemyNextX/B2DVars.MTT>map.getHeightInTiles() ||enemyNextY/B2DVars.MTT>map.getWidthInTiles()){
				Gdx.app.debug("EnemyMover", "out of bounds");
					
				}else {
			
				
				
				path = finder.findPath(entity, 
						(int) (entity.getBody().getPosition().x / B2DVars.MTT),
						(int) (entity.getBody().getPosition().y / B2DVars.MTT),
						(int) (enemyNextX / B2DVars.MTT),
						(int) (enemyNextY / B2DVars.MTT));	
				usItDone=false;
			}
			}
				
				

				if (path != null) {//if path is not null

					if (path.getLength() != iterator) {
								
						bm = new BodyMover(
								entity.getBody().getPosition().x,
								entity.getBody().getPosition().y,
								path.getStep(iterator).getX() * B2DVars.MTT + B2DVars.MTT/2,
								path.getStep(iterator).getY() * B2DVars.MTT + B2DVars.MTT/2,2);
						
						entity.getBody().setLinearVelocity(bm.getMovementX(),bm.getMovementY());
						
						if (bm.atDestynation()) {
							iterator++;iterator2=0;}
						

					}else {
						usItDone=true;
						iterator=0;
					}
					
					 if(path.getLength()>iterator2){
						 Circle ciclce = new Circle((path.getX(iterator2)*B2DVars.MTT+B2DVars.MTT/2)*PPM, (path.getY(iterator2)*B2DVars.MTT+B2DVars.MTT/2 )*PPM, 32f);
							enemyTrace .add(ciclce);
							iterator2++;}
				
						

				} else {//if there is no path
					bm = new BodyMover(entity.getBody().getPosition().x, entity
							.getBody().getPosition().y, entity.getBody()
							.getPosition().x, entity.getBody().getPosition().y,1);
					entity.getBody().setLinearVelocity(bm.getMovementX(),
							bm.getMovementY());
					usItDone=true;
					
				}

			

	

	

	}
	
	public Array<Circle> getEnemyTrace() {
		return enemyTrace;
	}

	public static int randInt(int min, int max) {


	    Random rand = new Random();


	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	
	
	
	
}
