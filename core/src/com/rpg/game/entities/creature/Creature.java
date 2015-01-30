package com.rpg.game.entities.creature;


import static com.rpg.game.handler.B2DVars.PPM;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.rpg.game.AdultGame;
import com.rpg.game.entities.B2DSprite;
import com.rpg.game.entities.Entity2;
import com.rpg.game.entities.HealthBar;
import com.rpg.game.pathfinding.Mover;

public abstract class Creature extends Entity2 implements Mover{
	
	protected int health;
	protected Body body;
	protected B2DSprite sprite;
	private BodyDef bdef;
	private FixtureDef fdef;
	//private PolygonShape polygonShape;
	private CircleShape sensroShape, circle;
	public int getHitPoint() {return hitPoint;}
	public void setHitPoint(int hitPoint) {this.hitPoint = hitPoint;}
	private int animationRow =0,hitPoint,enemyHitPower=0;
	protected HealthBar healthBar;
	private boolean isFighting= false;


	 
	 
	 
	public boolean isFighting() {
		return isFighting;
	}
	public void setFighting(boolean isFighting) {
		this.isFighting = isFighting;
	}
	public Creature(float x, float y, String bodyTAG, String sensorTAG,short categoryBit,int maskBits,boolean isSensor) {
		super(x, y);
		health = 10;

		bodyCreator(x, y, bodyTAG, sensorTAG, categoryBit, maskBits, isSensor);
	}

	
	public void bodyCreator(float x,float y, String bodyTAG,String sensorTAG, short categoryBit ,int maskBits, boolean isSensor){
		// Def initializing
		bdef = new BodyDef();
		fdef = new FixtureDef();
		//polygonShape = new PolygonShape();
		circle= new CircleShape();
		// BodyDef
		bdef.position.set(x/ PPM, y / PPM);
		bdef.type = BodyType.DynamicBody;
		bdef.fixedRotation = true;
	    body = world.createBody(bdef);

		// fixtureDef
	//	shapeEnemy.setAsBox(5 / PPM, 5 / PPM, new Vector2(0 / PPM, 0 / PPM),0); // //
		circle.setRadius(10/PPM);
		fdef.shape = circle;
		//fdef.shape = shapeEnemy;
		fdef.isSensor = isSensor;
		fdef.filter.categoryBits= categoryBit;
		fdef.filter.maskBits=  (short) maskBits ;
		fdef.restitution=0;
		body.createFixture(fdef).setUserData(bodyTAG);
		
		// Player's sensor
		circle.setRadius(100/PPM);
		fdef.shape = sensroShape;
		fdef.filter.categoryBits= categoryBit;
		fdef.isSensor = true;
		//body.createFixture(fdef).setUserData(sensorTAG);
		//shapeEnemy.dispose();
	//	sensroShape.dispose();
		sprite= new B2DSprite(body);

		
	}
	
	
	public  void playAnimation(int animationRow, String textureName) {

		this.setAnimationRow(animationRow);

		Texture tex = AdultGame.res.getTexture(textureName);
		TextureRegion[] sprites = new TextureRegion[9];

		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = new TextureRegion(tex, i * 64, animationRow * 64, 64,
					64);
		}

		sprite.getAnimation().setFrames(sprites, 1 / 12f);

		sprite.setWidth(sprites[0].getRegionWidth());
		sprite.setHeight(sprites[0].getRegionHeight());
	}


	public Body getBody() {
		return body;
	}
	public int getEnemyHitPower() {
		return enemyHitPower;
	}


	public void setEnemyHitPower(int enemyHitPower) {this.enemyHitPower = enemyHitPower;}


	public int getAnimationRow() {return animationRow;}


	public void setAnimationRow(int animationRow) {this.animationRow = animationRow;}
	
	
}
