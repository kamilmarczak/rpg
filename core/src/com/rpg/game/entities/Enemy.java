package com.rpg.game.entities;

import static com.rpg.game.handler.B2DVars.PPM;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.rpg.game.AdultGame;
import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.MyTimer;
import com.rpg.game.state.Play;

public class Enemy{


	private World world = null;
	private BodyDef bdef;
	private FixtureDef fdef;
	private PolygonShape shapeEnemy;
	private CircleShape sensroShape;
	//private  Body body;
	private boolean isFighting= false;
	private int animationRow =0;
	protected float hitPoint= 1;
	protected HealthBar hp;
	private MyTimer timer;
	private int enemyHitPower=1;

	private String textureName;
	private Body body;
	protected B2DSprite sprite;
	



	public String getEnemyTextureName() {
		return textureName;
	}


	public Enemy() {
		
		world=Play.getWorld();
		hp= new HealthBar();
		timer= new MyTimer(1);
		timer.start();
		
	}
	

	public void enemycreator(int posX,int posY, String bodyTAG,short categoryBit ,int maskBits){
		



		// Def initializing
		bdef = new BodyDef();
		fdef = new FixtureDef();
		shapeEnemy = new PolygonShape();
		sensroShape= new CircleShape();
		
		// BodyDef
		bdef.position.set(posX / PPM, posY / PPM);
		bdef.type = BodyType.DynamicBody;
		bdef.fixedRotation = true;
	    body = world.createBody(bdef);

		// fixtureDef
		shapeEnemy.setAsBox(15 / PPM, 25 / PPM, new Vector2(0 / PPM, 0 / PPM),0); // //
		fdef.shape = shapeEnemy;
		fdef.filter.categoryBits= categoryBit;
		fdef.filter.maskBits=  (short) maskBits ;

		body.createFixture(fdef).setUserData(bodyTAG);
		
		// Player's sensor
		sensroShape.setRadius(200/PPM);
		fdef.shape = sensroShape;
		fdef.filter.categoryBits= categoryBit;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("sensor");
		shapeEnemy.dispose();
		sensroShape.dispose();
		sprite= new B2DSprite(body);

		
	}



	public void attack()
	{

		if(Player.getPlayerHP()>6 && timer.hasCompleted()){
			
				Player.setPlayerHP(Player.getPlayerHP()- enemyHitPower);
				timer.start();
				

		}
	}
	
	public void playAnimation(int animationRow, String textureName) {
		
		this.textureName=textureName;
		this.animationRow = animationRow;

		Texture tex = AdultGame.res.getTexture(textureName);
		TextureRegion[] sprites = new TextureRegion[9];

		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = new TextureRegion(tex, i * 64, animationRow * 64, 64,
					64);
		}

		sprite.animation.setFrames(sprites, 1 / 12f);

		sprite.width = sprites[0].getRegionWidth();
		sprite.height = sprites[0].getRegionHeight();


	}
	

	

	public Body getBody() {return body;}
	public float getHitPoint() {	return hitPoint;}
	public HealthBar getHp() {return hp;}
	public void setHitPoint(float hitPoint) {this.hitPoint = hitPoint;	}
	public int getAnimationRow() {return animationRow;}
	public void setAnimationRow(int animationRow) {	this.animationRow = animationRow;}
	public boolean isFighting() {return isFighting;}
	public void setFighting(boolean isFighting) {this.isFighting = isFighting;}
	public void setEnemyHitPower(int enemyHitPower) {this.enemyHitPower = enemyHitPower;}


	public void update(float dt) {
		sprite.update(dt);
		
	}
	public void render(SpriteBatch sb) {
		sprite.render(sb);
	
		
	}

}
