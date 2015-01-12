package com.rpg.game.handler;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;
import com.rpg.game.entities.Enemy;
import com.rpg.game.entities.SmallEnemy;

public class MyContactListener implements ContactListener {

	//private int numFootContacts;
	private Array<Body> bodiesToRemove;
	private Array<Body> damage;
	private Array<Body> coins;
	private boolean isPlayerTeleportingForward;
	private boolean isPlayerTeleportingBack;
	private boolean isPlayerEnterinHouse;
	private boolean isPlayerExitingHouse;
	private boolean isPlayerInRange;
	
	public MyContactListener(){
		super();
		damage= new Array<Body>();
		coins= new Array<Body>();
	}



	public void beginContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		//System.out.println(fa.getUserData()+ ","+ fb.getUserData()) ;
		
		if(fa == null || fb == null) return;
		if(fa.getUserData() != null && fa.getUserData().equals("portalForward")) {
			isPlayerTeleportingForward=true;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("portalForward")) {
			isPlayerTeleportingForward=true;
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(fa == null || fb == null) return;
		if(fa.getUserData() != null && fa.getUserData().equals("portalBack")) {
			isPlayerTeleportingBack=true;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("portalBack")) {
			isPlayerTeleportingBack=true;
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(fa == null || fb == null) return;
		if(fa.getUserData() != null && fa.getUserData().equals("doorEnter")) {
			isPlayerEnterinHouse=true;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("doorEnter")) {
			isPlayerEnterinHouse=true;
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(fa.getUserData() != null && fa.getUserData().equals("doorExit")) {
			isPlayerExitingHouse=true;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("doorExit")) {
			isPlayerExitingHouse=true;
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

if(fa.getUserData() != null && fa.getUserData().equals("enemy")) {
	damage.add(fa.getBody());
	isPlayerInRange= true;
	((SmallEnemy)fa.getBody().getUserData()).setFighting(true);
}
if(fb.getUserData() != null && fb.getUserData().equals("enemy")) {
	damage.add(fb.getBody());
	isPlayerInRange= true;
	((SmallEnemy)fb.getBody().getUserData()).setFighting(true);

}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if(fa.getUserData() != null && fa.getUserData().equals("coin")) {
coins.add(fa.getBody());
}
if(fb.getUserData() != null && fb.getUserData().equals("coin")) {
coins.add(fb.getBody());
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		
		
		/*	
		if(fa.getUserData() != null && fa.getUserData().equals("sensor")) {
			numFootContacts++;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("sensor")) {
			numFootContacts++;
		}
		
	if(fa.getUserData() != null && fa.getUserData().equals("crystal")) {
			bodiesToRemove.add(fa.getBody());
		}
		if(fb.getUserData() != null && fb.getUserData().equals("crystal")) {
			bodiesToRemove.add(fb.getBody());
		}
		
		if(fa.getUserData() != null && fa.getUserData().equals("spike")) {
			playerDead = true;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("spike")) {
			playerDead = true;
		}*/
		
	}




	public void endContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		if(fa == null || fb == null) return;
		
		if(fa.getUserData() != null && fa.getUserData().equals("portalForward")) {
			isPlayerTeleportingForward=false;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("portalForward")) {
			isPlayerTeleportingForward=false;
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(fa.getUserData() != null && fa.getUserData().equals("portalBack")) {
			isPlayerTeleportingBack=false;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("portalBack")) {
			isPlayerTeleportingBack=false;
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(fa.getUserData() != null && fa.getUserData().equals("doorEnter")) {
			isPlayerEnterinHouse=false;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("doorEnter")) {
			isPlayerEnterinHouse=false;
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(fa.getUserData() != null && fa.getUserData().equals("doorExit")) {
			isPlayerExitingHouse=false;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("doorExit")) {
			isPlayerExitingHouse=false;
		}
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(fa.getUserData() != null && fa.getUserData().equals("enemy")) {
			((SmallEnemy)fa.getBody().getUserData()).setFighting(false);
			damage.removeValue(fa.getBody(), true);
			isPlayerInRange= false;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("enemy")) {
			((SmallEnemy)fb.getBody().getUserData()).setFighting(false);
			damage.removeValue(fb.getBody(), true);
			isPlayerInRange= false;
		}
		///////////////////////////////////////////////////////////////////////////////////////

		
		
		

	}
	
		
//	public boolean playerCanJump() { return numFootContacts > 0; }
	public Array<Body> getBodies() { return bodiesToRemove; }
	public Array<Body> getDamege() { return damage; }
	public Array<Body> getCoins() {return coins;}
	public boolean isPlayerTeleportingForward() { return isPlayerTeleportingForward; }
	public boolean isPlayerTeleportingBack() { return isPlayerTeleportingBack; }
	public boolean isPlayerEnterinHouse() { return isPlayerEnterinHouse; }
	public boolean isPlayerExitingHouse() { return isPlayerExitingHouse; }

	public boolean isPlayerInRange() {return isPlayerInRange;}



	
	
	public void preSolve(Contact contact, Manifold oldManifold) {}

	public void postSolve(Contact contact, ContactImpulse impulse) {}

}
