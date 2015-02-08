package com.rpg.game.handler.steering;

import static com.rpg.game.handler.B2DVars.PPM;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.Array;
import com.rpg.game.entities.creature.Creature;
import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.Condition;
import com.rpg.game.handler.GameMaps;
import com.rpg.game.handler.MyTimer;
import com.rpg.game.pathfinding.AStarPathFinder;
import com.rpg.game.pathfinding.Path;
import com.rpg.game.pathfinding.PathFinder;


public class PlayerControler {

	private BodyMover bm;
	private MyTimer mt;
	private int  playerXforMap,playerYforMap,iterator=0,iterator2=0,lastClickX,lastClickY;

	//pathfinding 
	private static GameMaps map = new GameMaps();
	private PathFinder finder;
	private Path path;
	private float lastFindX,lastFindY,playerX,playerY ;
	
	private static Array<Circle> trace= new Array<Circle>();

	

	
	public PlayerControler() {
		 mt = new MyTimer(1);
			//path
			finder = new AStarPathFinder(map, 100, true);
		
	}

	public void startControl(Creature creature) {

		playerXforMap =(int) (creature.getPosition().x/B2DVars.MTT);
		playerYforMap=(int) (creature.getPosition().y/B2DVars.MTT);
		
		playerX = creature.getPosition().x;
		playerY= creature.getPosition().y;
		
		 lastClickX=(int) (Condition.getLastClickX()/B2DVars.MTT);
		 lastClickY=(int) (Condition.getLastClickY()/B2DVars.MTT);

					if ((lastFindX != lastClickX) || (lastFindY != lastClickY)) {
						lastFindX = lastClickX;
						lastFindY = lastClickY;
						iterator=0;
						iterator2=0;
						 trace.clear();
						 path =finder.findPath(creature, playerXforMap, playerYforMap,  lastClickX, lastClickY);
					}

			if(path!=null){
			 if(path.getLength()!=iterator){
				 
				 
				 creature.getTarget().getBody().setTransform(
						 path.getStep(iterator).getX()*B2DVars.MTT+B2DVars.MTT/2,
						 path.getStep(iterator).getY()*B2DVars.MTT+B2DVars.MTT/2,
						 creature.getTarget().getBody().getAngle());
			
				 
			 bm = new BodyMover(playerX, playerY,
					 creature.getTarget().getBody().getPosition().x,
					 creature.getTarget().getBody().getPosition().y,2);

			 		
			if(bm.atDestynation())
			{iterator++;} 
			 }

			 if(path.getLength()>iterator2){
				 Circle ciclce = new Circle((path.getX(iterator2)*B2DVars.MTT+B2DVars.MTT/2)*PPM, 
						 					(path.getY(iterator2)*B2DVars.MTT+B2DVars.MTT/2 )*PPM, 32f);
					trace.add(ciclce);
					iterator2++;}
					}else {
						bm = new BodyMover(playerX,playerY,playerX, playerY, 5);
						
					}

		//is moving
		if(Condition.isMoving()){
		//	animationChecker();
			//not att
			if(!Condition.isPlayerIsAttacking()){
			//body mover
				
				creature.getBody().setLinearVelocity(bm.getMovementX(),bm.getMovementY());
				//aniChecker(0);
				//atack
			 }else {

			//	aniChecker(9);
				if(mt.hasCompleted()){
					Condition.setPlayerIsAttacking(false);
				}
			}	
		}
		//is not moving
			if(!Condition.isMoving()){
				
				//if not moving and not attacking
				if(!Condition.isPlayerIsAttacking()){
				
					creature.getBody().setLinearVelocity(0, 0);
					//aniChecker(4);
			}//if is not moving and attacing
				else{
				
					creature.getBody().setLinearVelocity(0, 0);
					Condition.setPlayerIsAttacking(true);
					//aniChecker(9);
				
				
				if(mt.hasCompleted()){
					Condition.setPlayerIsAttacking(false);

					}
				}	
			}
			//	
			
	}
	

		public static Array<Circle> getTrace() {
		return trace;
	}



/*		// dont start animation over and over again
		public static void aniChecker(int i) {
			if (numerAnimacii  != i) {
		
				Play.player.playAnimation(i,Play.player.getEnemyTextureName());
				numerAnimacii = i;
				
				
			}
		}*/
}