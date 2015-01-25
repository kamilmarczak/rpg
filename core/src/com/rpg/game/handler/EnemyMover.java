package com.rpg.game.handler;

import static com.rpg.game.handler.B2DVars.PPM;

import java.util.Random;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.Array;
import com.rpg.game.entities.Entity;
import com.rpg.game.pathfinding.AStarPathFinder;
import com.rpg.game.pathfinding.Path;
import com.rpg.game.pathfinding.PathFinder;
import com.rpg.game.state.Play;

public class EnemyMover {

	private GameMaps map = new GameMaps();
	private MyTimer mt;
	private PathFinder finder;
	private Path path;
	private int iterator = 0, iterator2 = 0;

	private BodyMover bm;
	private boolean usItDone = true;
	private Entity entity;
	private Array<Circle> enemyTrace = new Array<Circle>();

	public EnemyMover() {

		finder = new AStarPathFinder(map, 100, true);
		mt = new MyTimer(1);

	}

	public void pathStarter() {

			if(usItDone){

				entity = EnemyContainer.GETSMALLENEMY().first();
				int enemyNextX = randInt((int)entity.getBody().getPosition().x-2,(int) entity.getBody().getPosition().x+5);
				int enemyNextY =  randInt((int)entity.getBody().getPosition().y-2,(int) entity.getBody().getPosition().y+5);
				System.out.println(enemyNextX/0.64+.32+" -  "+enemyNextY/0.64+.32+"        "+enemyNextX/0.64+" - "+enemyNextY/0.64);
	
				
				if(enemyNextY/0.64<=0||enemyNextX/0.64<=0||enemyNextX/0.64>map.getHeightInTiles() ||enemyNextY/0.64>map.getWidthInTiles()){
					//	if(enemyNextY/0.64+.32<=0||enemyNextX/0.64+.32<=0||enemyNextX/0.64+.32>map.getHeightInTiles() ||enemyNextY/0.64+.32>map.getWidthInTiles()){
				System.out.println( "za ma³e random");
					
				}else {
			
				
				
				path = finder.findPath(entity, 
						(int) (entity.getBody().getPosition().x / 0.64f),
						(int) (entity.getBody().getPosition().y / 0.64f),
						(int) (enemyNextX / 0.64f),
						(int) (enemyNextY / 0.64f));	
				usItDone=false;
			}
			}
				
				

				if (path != null) {//if path is not null

					if (path.getLength() != iterator) {
								
						bm = new BodyMover(
								entity.getBody().getPosition().x,
								entity.getBody().getPosition().y,
								path.getStep(iterator).getX() * 0.64f + 0.32f,
								path.getStep(iterator).getY() * 0.64f + 0.32f,5);
						
						entity.getBody().setLinearVelocity(bm.getMovementX(),bm.getMovementY());
						
						if (bm.atDestynation()) {
							iterator++;iterator2=0;}
						

					}else {
						usItDone=true;
						iterator=0;
					}
					
					 if(path.getLength()>iterator2){
						 Circle ciclce = new Circle((path.getX(iterator2)*0.64f+0.32f)*PPM, (path.getY(iterator2)*0.64f+0.32f )*PPM, 32f);
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
