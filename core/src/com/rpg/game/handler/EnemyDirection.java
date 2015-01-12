package com.rpg.game.handler;

import com.rpg.game.state.Play;

public class EnemyDirection {
	private int numerAnimacii = 100;


	public void directionChecker(int i) {

		float firstX = Play.getSmalEnemy().get(i).getBody().getLinearVelocity().x * 10;
		float firstY = Play.getSmalEnemy().get(i).getBody().getLinearVelocity().y * 10;
		boolean isFighting = Play.getSmalEnemy().get(i).isFighting();
	//	System.out.println("isFa   "+isFighting);

		if ((int) firstX > 0 && (int) firstY > 0 && !isFighting) {
			anienemChecker(5, i);

		}

		if ((int) firstX > 0 && (int) firstY < 0&& !isFighting) {
			anienemChecker(8, i);

		}

		if ((int) firstX < 0 && (int) firstY < 0&& !isFighting) {
			anienemChecker(7, i);

		}

		if ((int) firstX < 0 && (int) firstY > 0&& !isFighting) {
			anienemChecker(6, i);

		}

		if ((int) firstX > 0 && (int) firstY == 0&& !isFighting) {
			anienemChecker(3, i);

		}

		if ((int) firstX < 0 && (int) firstY == 0&& !isFighting) {
			anienemChecker(1, i);

		}

		if ((int) firstX == 0 && (int) firstY > 0&& !isFighting) {
			anienemChecker(0, i);

		}

		if ((int) firstX == 0 && (int) firstY < 0&& !isFighting) {
			anienemChecker(2, i);

		}

		if ((int) firstX == 0 && (int) firstY == 0&& !isFighting) {
			anienemChecker(4, i);

		}
		if (isFighting) {
			anienemChecker(9, i);

		}

	}

	private void anienemChecker(int j, int i) {

		if(Play.getSmalEnemy().get(i).getAnimationRow()!=j){
			Play.getSmalEnemy().get(i).playAnimation(j);
		}
			
			
		
		

	}

}
