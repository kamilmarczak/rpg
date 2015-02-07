package com.rpg.game.handler.steering;

import static com.rpg.game.handler.B2DVars.PPM;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.rpg.game.entities.creature.Creature;
import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.EnemyContainer;
import com.rpg.game.handler.GameMaps;
import com.rpg.game.handler.MyTimer;
import com.rpg.game.pathfinding.AStarPathFinder;
import com.rpg.game.pathfinding.Path;
import com.rpg.game.pathfinding.PathFinder;

public class EnemyMover {

	private GameMaps map = new GameMaps();
	
	private PathFinder finder;
	private Path path;
	private int iterator = 0, iterator2 = 0;
	private BodyMover bm;
	private boolean usItDone = true;
	private Array<Circle> enemyTrace = new Array<Circle>();

	
	

	public EnemyMover() {

		finder = new AStarPathFinder(map, 100, true);
	
		

	}

	public void pathStarter(Creature creature) {
		
		
		
		
		
		if(creature.getTarget().isCollision()){
		//	System.out.println("kolizja");
			usItDone=true;
			iterator = 0;
			iterator2 = 0;
			
			
		}
		if(creature.isCollisionEnemys()){
			
			creature.getTarget().getBody().setTransform(creature.getBody().getPosition().x,creature.getBody().getPosition().y,0);
			
			
		}

			


	
		
		

			if(usItDone){

				
				int enemyNextX = randInt((int)creature.getBody().getPosition().x-10,(int) creature.getBody().getPosition().x+10);
				int enemyNextY =  randInt((int)creature.getBody().getPosition().y-10,(int) creature.getBody().getPosition().y+10);
				iterator=0;
	
				
				if(enemyNextY/B2DVars.MTT<=0||enemyNextX/B2DVars.MTT<=0||enemyNextX/B2DVars.MTT>map.getHeightInTiles() ||enemyNextY/B2DVars.MTT>map.getWidthInTiles()){
				Gdx.app.debug("EnemyMover", "out of bounds");
					
				}else {
			
				
				
				path = finder.findPath(creature, 
						(int) (creature.getBody().getPosition().x / B2DVars.MTT),
						(int) (creature.getBody().getPosition().y / B2DVars.MTT),
						(int) (enemyNextX / B2DVars.MTT),
						(int) (enemyNextY / B2DVars.MTT));	
				usItDone=false;
			}
			}
				
				

				if (path != null) {//if path is not null

					if (path.getLength() != iterator) {
						
							creature.getTarget().getBody().setTransform(
									path.getStep(iterator).getX() * B2DVars.MTT + B2DVars.MTT/2,
									path.getStep(iterator).getY() * B2DVars.MTT + B2DVars.MTT/2,
									0);

							bm = new BodyMover(
									creature.getBody().getPosition().x,
									creature.getBody().getPosition().y,
									creature.getTarget().getPosition().x,
									creature.getTarget().getPosition().y,1);
						
						creature.getBody().setLinearVelocity(bm.getMovementX(),bm.getMovementY());
						
					
						

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
					bm = new BodyMover(creature.getBody().getPosition().x, creature
							.getBody().getPosition().y, creature.getBody()
							.getPosition().x, creature.getBody().getPosition().y,1);
					creature.getBody().setLinearVelocity(bm.getMovementX(),
							bm.getMovementY());
					usItDone=true;
					
				}

			

	

	

	}
	
	

	
	public BodyMover getBm() {
		return bm;
	}

	public void setBm(BodyMover bm) {
		this.bm = bm;
	}

	public int getIterator() {
		return iterator;
	}

	public void setIterator(int iterator) {
		this.iterator = iterator;
	}

	public void setUsItDone(boolean usItDone) {
		this.usItDone = usItDone;
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
