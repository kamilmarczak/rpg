package com.rpg.game.handler.steering;

public class BodyMover {
	double movementX=0;
	double movementY=0;
	double distance ;
	
	
	public BodyMover(float startPozX,float startPozY,float destX,float destY,int speed) {
	
		
		
		float pathX = destX - startPozX;
		float pathY = destY - startPozY;
		 distance = Math.sqrt(pathX * pathX + pathY * pathY);
		double directionX = pathX / distance;
		double directionY = pathY / distance;
	
		
		 movementX = directionX * speed;
		 movementY = directionY * speed;

		
		 atDestynation();
	}


	public boolean atDestynation(){
	
	if(distance<0.2)
	{
		movementY=0;
		movementX=0;
	return true;
	}else {
		
		return false;
	}
}

public float getMovementX() {return  (float) movementX;}
public float getMovementY() {return  (float) movementY;}

}
