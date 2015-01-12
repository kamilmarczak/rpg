package com.rpg.game.handler;

public class BodyMover {
	double movementX=0;
	double movementY=0;
	
	
	public BodyMover(float startPozX,float startPozY,float destX,float destY,int speed) {
	
		
		
		float pathX = destX - startPozX;
		float pathY = destY - startPozY;
		double distance = Math.sqrt(pathX * pathX + pathY * pathY);
		double directionX = pathX / distance;
		double directionY = pathY / distance;
		 movementX = directionX * speed;
		 movementY = directionY * speed;

		
		
	}


	public double getMovementX() {
		return movementX;
	}


	public double getMovementY() {
		return movementY;
	}

}
