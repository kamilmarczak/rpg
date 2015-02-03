package com.rpg.game.handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;
import com.rpg.game.entities.creature.Creature;
import com.rpg.game.entities.creature.SmallCoyote;
import com.rpg.game.handler.steering.Target;

public class MyContactListener implements ContactListener {

	// private int numFootContacts;
	private Array<Body> bodiesToRemove;
	private Array<Body> damage;
	private Array<Body> fallow;
	private Array<Body> coins;
	private boolean isPlayerTeleportingForward;
	private boolean isPlayerTeleportingBack;
	private boolean isPlayerEnterinHouse;
	private boolean isPlayerExitingHouse;
	private boolean isPlayerInRange;

	

	public MyContactListener() {
		super();
		damage = new Array<Body>();
		coins = new Array<Body>();
		fallow = new Array<Body>();
	}

	public void beginContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
	System.out.println(fa.getUserData()+ ","+ fb.getUserData()) ;

		if (fa == null || fb == null)
			return;
		if (fa.getUserData() != null
				&& fa.getUserData().equals("portalForward")
				&& fb.getUserData().equals("player")) {
			isPlayerTeleportingForward = true;
		}
		if (fb.getUserData() != null
				&& fb.getUserData().equals("portalForward")
				&& fa.getUserData().equals("player")) {
			isPlayerTeleportingForward = true;
		}
		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (fa == null || fb == null)
			return;
		if (fa.getUserData() != null && fa.getUserData().equals("portalBack")
				&& fb.getUserData().equals("player")) {
			isPlayerTeleportingBack = true;
		}
		if (fb.getUserData() != null && fb.getUserData().equals("portalBack")
				&& fa.getUserData().equals("player")) {
			isPlayerTeleportingBack = true;
		}
		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (fa == null || fb == null)
			return;
		if (fa.getUserData() != null && fa.getUserData().equals("doorEnter")
				&& fb.getUserData().equals("player")) {
			isPlayerEnterinHouse = true;
		}
		if (fb.getUserData() != null && fb.getUserData().equals("doorEnter")
				&& fa.getUserData().equals("player")) {
			isPlayerEnterinHouse = true;
		}
		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (fa.getUserData() != null && fa.getUserData().equals("doorExit")
				&& fb.getUserData().equals("player")) {
			isPlayerExitingHouse = true;
		}
		if (fb.getUserData() != null && fb.getUserData().equals("doorExit")
				&& fa.getUserData().equals("player")) {
			isPlayerExitingHouse = true;
		}
		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		if (fa.getUserData().equals("enemy")
				&& fb.getUserData().equals("player")) {
			damage.add(fa.getBody());

			((SmallCoyote) fa.getBody().getUserData()).setFighting(true);
		}
		if (fb.getUserData().equals("enemy")
				&& fa.getUserData().equals("player")) {

			damage.add(fb.getBody());

			((SmallCoyote) fb.getBody().getUserData()).setFighting(true);

		}
		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (fa.getUserData() != null && fa.getUserData().equals("coin")
				&& fb.getUserData().equals("player")) {
			coins.add(fa.getBody());
		}
		if (fb.getUserData() != null && fb.getUserData().equals("coin")
				&& fa.getUserData().equals("player")) {
			coins.add(fb.getBody());
		}
		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		if (fa.getUserData().equals("enemy")
				&& fb.getUserData().equals("playerSensor")) {
			fallow.add(fa.getBody());
		}
		if (fb.getUserData().equals("enemy")
				&& fa.getUserData().equals("playerSensor")) {
			fallow.add(fb.getBody());
			
		}
		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (fa.getUserData().equals("sensorEnemy")&& fb.getUserData().equals("enemy")) {



		}
		
		
		if (fa.getUserData().equals("target")&& fb.getUserData().equals("target")) {
			((Target)fa.getBody().getUserData()).setCollision(true);
			((Target)fb.getBody().getUserData()).setCollision(true);
			


		}
		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		if (fa.getUserData().equals("enemy")&& fb.getUserData().equals("enemy")) {
	Gdx.app.debug("MyCollision", "Enemycollide");
		}
		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	}

	public void endContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();

		if (fa == null || fb == null)
			return;

		if (fa.getUserData() != null
				&& fa.getUserData().equals("portalForward")) {
			isPlayerTeleportingForward = false;
		}
		if (fb.getUserData() != null
				&& fb.getUserData().equals("portalForward")) {
			isPlayerTeleportingForward = false;
		}
		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (fa.getUserData() != null && fa.getUserData().equals("portalBack")) {
			isPlayerTeleportingBack = false;
		}
		if (fb.getUserData() != null && fb.getUserData().equals("portalBack")) {
			isPlayerTeleportingBack = false;
		}
		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (fa.getUserData() != null && fa.getUserData().equals("doorEnter")) {
			isPlayerEnterinHouse = false;
		}
		if (fb.getUserData() != null && fb.getUserData().equals("doorEnter")) {
			isPlayerEnterinHouse = false;
		}
		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (fa.getUserData() != null && fa.getUserData().equals("doorExit")) {
			isPlayerExitingHouse = false;
		}
		if (fb.getUserData() != null && fb.getUserData().equals("doorExit")) {
			isPlayerExitingHouse = false;
		}

		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (fa.getUserData().equals("enemy")
				&& fb.getUserData().equals("player")) {
			((SmallCoyote) fa.getBody().getUserData()).setFighting(false);
			damage.removeValue(fa.getBody(), true);
			isPlayerInRange = false;

		}
		if (fb.getUserData().equals("enemy")
				&& fa.getUserData().equals("player")) {
			((SmallCoyote) fb.getBody().getUserData()).setFighting(false);

			damage.removeValue(fb.getBody(), true);
			isPlayerInRange = false;
		}

		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (fa.getUserData().equals("enemy")
				&& fb.getUserData().equals("playerSensor")) {
			fallow.removeValue(fa.getBody(), true);

		}
		if (fb.getUserData().equals("enemy")
				&& fa.getUserData().equals("playerSensor")) {
			fallow.removeValue(fb.getBody(), true);
		}
		// /////////////////////////////////////////////////////////////////////////////////////
		if (fa.getUserData().equals("target")&& fb.getUserData().equals("target")) {
			((Target)fa.getBody().getUserData()).setCollision(false);
			((Target)fb.getBody().getUserData()).setCollision(false);
			
			


		}
		if (fa.getUserData().equals("enemy")&& fb.getUserData().equals("enemy")) {


		}

	}



	public Array<Body> getFallow() {
		return fallow;
	}

	// public boolean playerCanJump() { return numFootContacts > 0; }
	public Array<Body> getBodies() {
		return bodiesToRemove;
	}

	public Array<Body> getDamege() {
		return damage;
	}

	public Array<Body> getCoins() {
		return coins;
	}

	public boolean isPlayerTeleportingForward() {
		return isPlayerTeleportingForward;
	}

	public boolean isPlayerTeleportingBack() {
		return isPlayerTeleportingBack;
	}

	public boolean isPlayerEnterinHouse() {
		return isPlayerEnterinHouse;
	}

	public boolean isPlayerExitingHouse() {
		return isPlayerExitingHouse;
	}

	public boolean isPlayerInRange() {
		return isPlayerInRange;
	}

	public void preSolve(Contact contact, Manifold oldManifold) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();


	}

	public void postSolve(Contact contact, ContactImpulse impulse) {
	}

}
