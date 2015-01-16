package com.rpg.game.handler;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;
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
		if(fa.getUserData() != null && fa.getUserData().equals("portalForward")&& fb.getUserData().equals("player")) {
			isPlayerTeleportingForward=true;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("portalForward")&& fa.getUserData().equals("player")) {
			isPlayerTeleportingForward=true;
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(fa == null || fb == null) return;
		if(fa.getUserData() != null && fa.getUserData().equals("portalBack")&& fb.getUserData().equals("player")) {
			isPlayerTeleportingBack=true;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("portalBack")&& fa.getUserData().equals("player")) {
			isPlayerTeleportingBack=true;
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(fa == null || fb == null) return;
		if(fa.getUserData() != null && fa.getUserData().equals("doorEnter")&& fb.getUserData().equals("player")) {
			isPlayerEnterinHouse=true;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("doorEnter")&& fa.getUserData().equals("player")) {
			isPlayerEnterinHouse=true;
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(fa.getUserData() != null && fa.getUserData().equals("doorExit")&& fb.getUserData().equals("player")) {
			isPlayerExitingHouse=true;
		}
		if(fb.getUserData() != null && fb.getUserData().equals("doorExit")&& fa.getUserData().equals("player")) {
			isPlayerExitingHouse=true;
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

if( fa.getUserData().equals("enemy") &&fb.getUserData().equals("player")) {
	damage.add(fa.getBody());
	isPlayerInRange= true;
	((SmallEnemy)fa.getBody().getUserData()).setFighting(true);
}
if(fb.getUserData().equals("enemy") &&fa.getUserData().equals("player")) {
	
	damage.add(fb.getBody());
	isPlayerInRange= true;
	((SmallEnemy)fb.getBody().getUserData()).setFighting(true);

}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
if(fa.getUserData() != null && fa.getUserData().equals("coin")&&fb.getUserData().equals("player")) {
coins.add(fa.getBody());
}
if(fb.getUserData() != null && fb.getUserData().equals("coin")&&fa.getUserData().equals("player")) {
coins.add(fb.getBody());
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		

		
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
		if(fa.getUserData().equals("enemy")&&fb.getUserData().equals("player")) {
			((SmallEnemy)fa.getBody().getUserData()).setFighting(false);
			damage.removeValue(fa.getBody(), true);
			isPlayerInRange= false;
			
		}
		if(fb.getUserData().equals("enemy")&&fa.getUserData().equals("player")) {
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
