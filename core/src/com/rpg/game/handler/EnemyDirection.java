package com.rpg.game.handler;



public class EnemyDirection {




	public void directionChecker(int i) {
	

	float firstX = EnemyContainer.GETSMALLENEMY().get(i).getBody().getLinearVelocity().x * 10;
		float firstY = EnemyContainer.GETSMALLENEMY().get(i).getBody().getLinearVelocity().y * 10;
		boolean isFighting = EnemyContainer.GETSMALLENEMY().get(i).isFighting();


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

		if(EnemyContainer.GETSMALLENEMY().get(i).getAnimationRow()!=j){
			EnemyContainer.GETSMALLENEMY().get(i).playAnimation(j, EnemyContainer.GETSMALLENEMY().get(i).getEnemyTextureName());
			
		}
			
			
		
		

	}

}
