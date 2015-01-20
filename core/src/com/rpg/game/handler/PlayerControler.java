package com.rpg.game.handler;

import static com.rpg.game.handler.B2DVars.PPM;

import java.util.ArrayList;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.rpg.game.entities.Door;
import com.rpg.game.pathfinding.AStarPathFinder;
import com.rpg.game.pathfinding.Mover;
import com.rpg.game.pathfinding.Path;
import com.rpg.game.pathfinding.PathFinder;
import com.rpg.game.state.Play;
import com.sun.xml.internal.fastinfoset.algorithm.BuiltInEncodingAlgorithm.WordListener;

public class PlayerControler {
	
	private static int numerAnimacii =100 ;
	private BodyMover bm;
	private MyTimer mt;
	private int  iterator=0;
	private float playersX,playersY,lastClickX,lastClickY ;
	//pathfinding 
	private static GameMaps map = new GameMaps();
	private PathFinder finder;
	private Path path;
	//private ArrayList<Node> nodesCheck;
	private float lastFindX = -1;
	
	private float lastFindY = -1;
	private int iterator2 =0 ;
	
	private Array<Body> trace= new Array<Body>();
	
	
	
	
	
	
	
	
	
	public PlayerControler() {
		 mt = new MyTimer(1);
		
		
	}
	
	
	public void startControl() {
		
		//path
		finder = new AStarPathFinder(map, 1500, true);
		
		
		playersX =Condition.getPlayerPositionX();
		playersY=Condition.getPlayerPositionY();
		
		 lastClickX=Condition.getLastClickX();
		 lastClickY=Condition.getLastClickY();
		 
		
		// path =finder.findPath(Play.getPlayer(), (int)1,(int) playersY, (int)lastClickX,(int) lastClickY);
		 
		
		// System.out.println("LAst clk "+lastClickX+"   "+lastClickY+"Player body pos"+Condition.getPlayerPositionX()+ "  " +Condition.getPlayerPositionY());
		 
		 
		 

		
					if ((lastFindX != lastClickX) || (lastFindY != lastClickY)) {
						lastFindX = lastClickX;
						lastFindY = lastClickY;
						
						 path =finder.findPath(Play.getPlayer(), (int)(Play.getPlayer().getBody().getPosition().x/0.64),
								 (int) (Play.getPlayer().getBody().getPosition().y/0.64f+0.32f), (int)(lastClickX/0.64f+0.32f),(int)( lastClickY/0.64f+0.32f));
					
					
					}

		 
		 
		 
		 if(path!=null){
			 
			//	for(int a =0; a<path.getLength();a++){
			 if(path.getLength()!=iterator){
					 bm = new BodyMover(Condition.getPlayerPositionX(), Condition.getPlayerPositionY(),path.getStep(iterator).getX()*0.64f+0.32f, path.getStep(iterator).getY()*0.64f+0.32f, 2);
						if( path.getLength()!=iterator-1){
						if(bm.atDestynation())
							 iterator++;
						System.out.println("test2");
						}
				}else {
					iterator=0;
					iterator2=0;
					path=null;
					
					for(int a=0;a<trace.size;a++){
						Body bodzioy =trace.get(a);
							Play.world.destroyBody(bodzioy);
							trace.removeValue(bodzioy, false);
						}

				}
			 
			 
			 
		 }
		 
		 
		 

		
		 
		 try {
			 if(path.getLength()!=iterator2 ){
				
				BodyDef bdef = new BodyDef();
				FixtureDef	fdef = new FixtureDef();
				CircleShape	shapeEnemy = new CircleShape();
				
				bdef.position.set(path.getX(iterator2)*0.64f+0.32f, path.getY(iterator2)*0.64f+0.32f);
				  
			//	bdef.position.set(path.getX(a)*0.64f+0.32f, path.getY(a)*0.64f+0.32f);
				bdef.type = BodyType.StaticBody;
			

				shapeEnemy.setRadius(0.32f);
				fdef.shape = shapeEnemy;
				Body  body = Play.getWorld().createBody(bdef);
				body.createFixture(fdef);	
				iterator2++;
				trace.add(body);
			 }
			
		} catch (Exception e) {
			//System.out.println("blad z dlugoscia sciezki");
		//	e.printStackTrace();
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