package com.rpg.game.handler;

import static com.rpg.game.handler.B2DVars.PPM;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.utils.Array;
import com.rpg.game.pathfinding.AStarPathFinder;
import com.rpg.game.pathfinding.Path;
import com.rpg.game.pathfinding.PathFinder;
import com.rpg.game.state.Play;

public class PlayerControler {
	
	private static int numerAnimacii =100 ;
	private BodyMover bm;
	private MyTimer mt;
	private int  iterator=0,iterator2=0;
	private float playersX,playersY,lastClickX,lastClickY ;
	//pathfinding 
	private static GameMaps map = new GameMaps();
	private PathFinder finder;
	private Path path;
	private float lastFindX ;
	private float lastFindY ;
	private static Array<Circle> trace= new Array<Circle>();

	

	
	public PlayerControler() {
		 mt = new MyTimer(1);
			//path
			finder = new AStarPathFinder(map, 100, true);
		
	}

	public void startControl() {

		playersX =Condition.getPlayerPositionX();
		playersY=Condition.getPlayerPositionY();
		 lastClickX=Condition.getLastClickX();
		 lastClickY=Condition.getLastClickY();

					if ((lastFindX != lastClickX) || (lastFindY != lastClickY)) {
						lastFindX = lastClickX;
						lastFindY = lastClickY;
						iterator=0;
						iterator2=0;
						 trace.clear();
						 path =finder.findPath(Play.getPlayer(),
								 (int)(Play.getPlayer().getBody().getPosition().x/B2DVars.MTT),
								 (int) (Play.getPlayer().getBody().getPosition().y/B2DVars.MTT), 
								 (int)(lastClickX/B2DVars.MTT),
								 (int)(lastClickY/B2DVars.MTT));
					}


		 
			if(path!=null){
			 if(path.getLength()!=iterator){
			 bm = new BodyMover(Condition.getPlayerPositionX(), Condition.getPlayerPositionY(),path.getStep(iterator).getX()*B2DVars.MTT+B2DVars.MTT/2, path.getStep(iterator).getY()*B2DVars.MTT+B2DVars.MTT/2, 2);
			if(bm.atDestynation()){iterator++;}
			}
		

			
			 if(path.getLength()>iterator2){
				 Circle ciclce = new Circle((path.getX(iterator2)*B2DVars.MTT+B2DVars.MTT/2)*PPM, (path.getY(iterator2)*B2DVars.MTT+B2DVars.MTT/2 )*PPM, 32f);
					trace.add(ciclce);
					iterator2++;}
					}else {
						bm = new BodyMover(Condition.getPlayerPositionX(), Condition.getPlayerPositionY(),Condition.getPlayerPositionX(), Condition.getPlayerPositionY(), 5);
						
					}


	
	
		
		//is moving
		if(Condition.isMoving()){
			animationChecker();
			//not att
			if(!Condition.isPlayerIsAttacking()){
			//body mover
				Play.player.getBody().setLinearVelocity(bm.getMovementX(),bm.getMovementY());
			
				//atack
			 }else {
				//Play.player.getBody().setLinearVelocity((float)bm.getMovementX(),(float)bm.getMovementY());
				aniChecker(9);
				if(mt.hasCompleted()){
					Condition.setPlayerIsAttacking(false);
				}
				
			 
			}

			
		}
		//is not moving
			if(!Condition.isMoving()){
				
				//if not moving and not attacking
				if(!Condition.isPlayerIsAttacking()){
					
					Play.player.getBody().setLinearVelocity(0, 0);
					aniChecker(4);
			}//if is not moving and attacing
				else{
				
					Play.player.getBody().setLinearVelocity(0, 0);
					Condition.setPlayerIsAttacking(true);
					aniChecker(9);
				
				
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


		// checking direction to draw sprite in the correct way
		private void animationChecker() {
			if(Condition.isPlayerIsAttacking()) return;
			
			if (lastClickX > playersX
					&& (int)lastClickY > playersY)
				aniChecker(5);
			
			if (lastClickX > playersX
					&&  lastClickY < playersY)
				aniChecker(8);

			if (lastClickX <playersX
					&&  lastClickY > playersY)
				aniChecker(6);

			if (lastClickX< playersX
					&&  lastClickY < playersY)
				aniChecker(7);

			if (lastClickX < playersX
					&& lastClickY == playersY)
				aniChecker(1);

			if (lastClickX > playersX
					&& lastClickY == playersY)
				aniChecker(3);

			if (lastClickX == playersX
					&& lastClickY < playersY)
				aniChecker(2);

			if (lastClickX == playersX
					&& lastClickY > playersY)
				aniChecker(0);
			
		}

		// dont start animation over and over again
		public static void aniChecker(int i) {
			if (numerAnimacii  != i) {
				Play.player.playAnimation(i,Play.player.getEnemyTextureName());
				numerAnimacii = i;
				
				
			}
		}
}