package com.rpg.game.handler;

import com.rpg.game.state.Play;

public class PlayerControler {
	private static int numerAnimacii =100;
	private BodyMover bm;
	private MyTimer mt;
	private float playersX,playersY,lastClickX,lastClickY;

	
	public PlayerControler() {
		 mt = new MyTimer(1);
	}
	
	
	public void playerControl() {
		
		playersX=(int)Condition.getPlayerPositionX();
		playersY=(int)Condition.getPlayerPositionY();
		 lastClickX=(int)Condition.getLastClickX();
		 lastClickY=(int)Condition.getLastClickY();
	
		bm = new BodyMover(Condition.getPlayerPositionX(), Condition.getPlayerPositionY(), Condition.getLastClickX(), Condition.getLastClickY(), 2);
		
		//is moving
		if(Condition.isMoving()){
			//not att
			if(!Condition.isPlayerIsAttacking()){
			//body mover
				
				Play.player.getBody().setLinearVelocity((float)bm.getMovementX(),(float)bm.getMovementY());
			
	//atack
			 }else {
				 
				 Play.player.getBody().setLinearVelocity((float)bm.getMovementX(),(float)bm.getMovementY());
				aniChecker(9);
				if(mt.hasCompleted()){
					Condition.setPlayerIsAttacking(false);
				}
				
			 
			}//is at dest
			if ((int) (Play.player.getBody().getPosition().x * 100) == (int) (Condition.getLastClickX() * 10)&&
				(int) (Play.player.getBody().getPosition().y * 100) == (int) (Condition.getLastClickY() * 10)) {
				Condition.setMoving(false);
				
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
			
			animationChecker();
			
	}
	

		// checking direction to draw sprite in the correct way
		private void animationChecker() {
			if(Condition.isPlayerIsAttacking()) return;
			
			if (lastClickX > playersX
					&& (int)lastClickY > playersY )
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
