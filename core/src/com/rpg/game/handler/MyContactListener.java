package com.rpg.game.handler;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;
import com.rpg.game.entities.Bullets;
import com.rpg.game.entities.creature.Creature;
import com.rpg.game.entities.creature.Player;
import com.rpg.game.handler.steering.Target;
import com.rpg.game.state.Play;

public class MyContactListener implements ContactListener {

	// private int numFootContacts;
	private Array<Body> damage;
	private Array<Body> fallow;
	private Array<Body> buletsToRemoves;
	private boolean isPlayerTeleportingForward;
	private boolean isPlayerTeleportingBack;
	private boolean isPlayerEnterinHouse;
	private boolean isPlayerExitingHouse;
	private boolean isPlayerInRange;
	private Player player;

	

	public MyContactListener(Player player) {
		super();
		this.player= player;
		damage = new Array<Body>();
		buletsToRemoves = new Array<Body>();
		fallow = new Array<Body>();
		
	}

	public Array<Body> getBuletsToRemoves() {
		return buletsToRemoves;
	}

	public void beginContact(Contact contact) {
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
	//System.out.println(fa.getUserData()+ ","+ fb.getUserData()) ;

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

			//((SmallCoyote) fa.getBody().getUserData()).setFighting(true);
		}
		if (fb.getUserData().equals("enemy")
				&& fa.getUserData().equals("player")) {

			damage.add(fb.getBody());

			//((SmallCoyote) fb.getBody().getUserData()).setFighting(true);

		}
		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		if (fa.getUserData().equals("sensorEnemy")&& fb.getUserData().equals("player")) {
			fallow.add(fa.getBody());
		}
		if (fb.getUserData().equals("sensorEnemy")&& fa.getUserData().equals("player")) {
			fallow.add(fb.getBody());
			
		}
		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (fa.getUserData().equals("sensorEnemy")&& fb.getUserData().equals("enemy")) {
		}
		
		if (fa.getUserData().equals("target")&& fb.getUserData().equals("target")) {
			((Target)fa.getBody().getUserData()).setCollision(true);
			//((Target)fb.getBody().getUserData()).setCollision(true);
			
		}
		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		if (fa.getUserData().equals("enemy")&& fb.getUserData().equals("enemy")) {
			((Creature)fa.getBody().getUserData()).setCollisionEnemys(true);
			((Creature)fb.getBody().getUserData()).setCollisionEnemys(true);

		}
			
		
		if (fa.getUserData().equals("player")&& fb.getUserData().equals("roof")||
				fb.getUserData().equals("player")&& fa.getUserData().equals("roof")) {
			Play.setVisibleRoof(false);

		}
		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		if (fa.getUserData().equals("enemy")&& fb.getUserData().equals("roof")) {
			((Creature)fa.getBody().getUserData()).setInContacWithRoof(true);
		}
		if (fb.getUserData().equals("enemy")&& fa.getUserData().equals("roof")) {
			((Creature)fb.getBody().getUserData()).setInContacWithRoof(true);
		}
		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (fa.getUserData().equals("enemy")&& fb.getUserData().equals("bullet")) {
			((Creature)fa.getBody().getUserData()).setHealth(((Creature)fa.getBody().getUserData()).getHealth()-player.getHitPower());
			((Creature)fa.getBody().getUserData()).setTargetRandom(false);
			buletsToRemoves.add(fb.getBody());
		
		}
			
		if (fb.getUserData().equals("enemy")&& fa.getUserData().equals("bullet")) {
			((Creature)fb.getBody().getUserData()).setHealth(((Creature)fb.getBody().getUserData()).getHealth()-player.getHitPower());
			((Creature)fb.getBody().getUserData()).setTargetRandom(false);
			buletsToRemoves.add(fa.getBody());
		}
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (fa.getUserData().equals("wall")&& fb.getUserData().equals("bullet")) {
			
			buletsToRemoves.add(fb.getBody());
		
		}
			
		if (fb.getUserData().equals("wall")&& fa.getUserData().equals("bullet")) {
			
			buletsToRemoves.add(fa.getBody());
		}
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
		//	((SmallCoyote) fa.getBody().getUserData()).setFighting(false);
			damage.removeValue(fa.getBody(), true);
			isPlayerInRange = false;

		}
		if (fb.getUserData().equals("enemy")
				&& fa.getUserData().equals("player")) {
			//((SmallCoyote) fb.getBody().getUserData()).setFighting(false);

			damage.removeValue(fb.getBody(), true);
			isPlayerInRange = false;
		}

		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if (fa.getUserData().equals("sensorEnemy")&& fb.getUserData().equals("player")) {
			
			((Creature)fa.getBody().getUserData()).setTargetRandom(true);
			fallow.removeValue(fa.getBody(), true);

		}
		if (fb.getUserData().equals("sensorEnemy")&& fa.getUserData().equals("player")) {
			((Creature)fb.getBody().getUserData()).setTargetRandom(true);
			fallow.removeValue(fb.getBody(), true);
			
			
		}
		// /////////////////////////////////////////////////////////////////////////////////////
		if (fa.getUserData().equals("target")&& fb.getUserData().equals("target")) {
			
			((Target)fa.getBody().getUserData()).setCollision(false);


		}
		if (fa.getUserData().equals("enemy")&& fb.getUserData().equals("enemy")) {
			((Creature)fa.getBody().getUserData()).setCollisionEnemys(false);
			((Creature)fb.getBody().getUserData()).setCollisionEnemys(false);
			



		}
		if (fa.getUserData().equals("player")&& fb.getUserData().equals("roof")||
				fb.getUserData().equals("player")&& fa.getUserData().equals("roof")) {
	Play.setVisibleRoof(true);
		

		}
		if (fa.getUserData().equals("enemy")&& fb.getUserData().equals("roof")) {
			((Creature)fa.getBody().getUserData()).setInContacWithRoof(false);
		}
		if (fb.getUserData().equals("enemy")&& fa.getUserData().equals("roof")) {
			((Creature)fb.getBody().getUserData()).setInContacWithRoof(false);
		}

	}



	public Array<Body> getFallow() {
		return fallow;
	}

	// public boolean playerCanJump() { return numFootContacts > 0; }


	public Array<Body> getDamege() {
		return damage;
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
		//Fixture fa = contact.getFixtureA();
		//Fixture fb = contact.getFixtureB();


	}

	public void postSolve(Contact contact, ContactImpulse impulse) {
	}

}
