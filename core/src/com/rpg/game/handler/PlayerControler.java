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
	private float lastFindX = -1;
	private float lastFindY = -1;
	private static Array<Circle> trace= new Array<Circle>();
	private boolean firstclick= true;
	

	
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
						 trace.clear();
						 path =finder.findPath(Play.getPlayer(),
								 (int)(Play.getPlayer().getBody().getPosition().x/0.64f),
								 (int) (Play.getPlayer().getBody().getPosition().y/0.64f), 
								 (int)(lastClickX/0.64f),
								 (int)(lastClickY/0.64f));
						 
				
							 
					

					
					}


		 
					if(path!=null){
		
			 if(path.getLength()!=iterator){
				 
					 bm = new BodyMover(Condition.getPlayerPositionX(), Condition.getPlayerPositionY(),path.getStep(iterator).getX()*0.64f+0.32f, path.getStep(iterator).getY()*0.64f+0.32f, 2);
				
						if(bm.atDestynation())iterator++;

					}
		

			
			 if(path.getLength()>iterator2){
				 Circle ciclce = new Circle((path.getX(iterator2)*0.64f+0.32f)*PPM, (path.getY(iterator2)*0.64f+0.32f )*PPM, 32f);
					trace.add(ciclce);
					iterator2++;
			 }else {
				 iterator2=0;
				
			}
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
					&& (int)lastClickY > playersY && !bm.atDestynation())
				aniChecker(5);
			
			if (lastClickX > playersX
					&&  lastClickY < playersY&& !bm.atDestynation())
				aniChecker(8);

			if (lastClickX <playersX
					&&  lastClickY > playersY&& !bm.atDestynation())
				aniChecker(6);

			if (lastClickX< playersX
					&&  lastClickY < playersY&& !bm.atDestynation())
				aniChecker(7);

			if (lastClickX < playersX
					&& lastClickY == playersY&& !bm.atDestynation())
				aniChecker(1);

			if (lastClickX > playersX
					&& lastClickY == playersY&& !bm.atDestynation())
				aniChecker(3);

			if (lastClickX == playersX
					&& lastClickY < playersY&& !bm.atDestynation())
				aniChecker(2);

			if (lastClickX == playersX
					&& lastClickY > playersY&& !bm.atDestynation())
				aniChecker(0);
			if(bm.atDestynation()){
				aniChecker(4);
			}
		}

		// dont start animation over and over again
		public static void aniChecker(int i) {
			if (numerAnimacii  != i) {
				Play.player.playAnimation(i,Play.player.getEnemyTextureName());
				numerAnimacii = i;
				
				
			}
		}
}