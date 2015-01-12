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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.rpg.game.AdultGame;
import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.MyTimer;
import com.rpg.game.state.Play;

public class Enemy extends B2DSprite{


	private World world = null;
	private BodyDef bdef;
	private FixtureDef fdef;
	private PolygonShape shapeEnemy;
	private CircleShape sensroShape;
	private  Body body;
	private boolean isFighting= false;
	private int animationRow =0;
	private float hitPoint= 1;
	private HealthBar hp;
	private MyTimer timer;
	private int enemyHitPower=1;


	



	public Enemy() {
		super();

		world=Play.getWorld();
		playAnimation(animationRow);
		hp= new HealthBar();
		timer= new MyTimer(1);
		timer.start();
	}
	

	public void enemycreator(int posX,int posY){

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
	
	
		fdef.filter.categoryBits= B2DVars.BIT_ENEMY;
		fdef.filter.maskBits= B2DVars.BIT_PLAYER;
		//fdef.filter.maskBits= B2DVars.BIT_PLAYER |B2DVars.BIT_ENEMY;
		body.createFixture(fdef).setUserData("enemy");

		// Player's sensor
		sensroShape.setRadius(200/PPM);
		fdef.shape = sensroShape;
		fdef.filter.categoryBits= B2DVars.BIT_ENEMY;
		 fdef.filter.maskBits= B2DVars.BIT_PLAYER;
		 fdef.density= 0.1f;
		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("sensorEnemy");
		shapeEnemy.dispose();
		sensroShape.dispose();
		
	}
	public void attack(Player player)
	{

		if(Player.getPlayerHp()>6 && timer.hasCompleted()){
			
				Player.setPlayerHp(Player.getPlayerHp()- enemyHitPower);
				timer.start();
				

		}
	}
	public void playAnimation(int animationRow) {
		
		this.animationRow = animationRow;

		Texture tex = AdultGame.res.getTexture("enemySmall");
		TextureRegion[] sprites = new TextureRegion[9];

		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = new TextureRegion(tex, i * 64, animationRow * 64, 64,
					64);
		}

		animation.setFrames(sprites, 1 / 12f);

		width = sprites[0].getRegionWidth();
		height = sprites[0].getRegionHeight();


	}
	//hp bar
	@Override
	public void render(SpriteBatch sb) {
		// TODO Auto-generated method stub
		super.render(sb);
		
		sb.begin();
		int pozX=(int)(getBody().getPosition().x * B2DVars.PPM - width / 2);
		int pozY=(int) (getBody().getPosition().y * B2DVars.PPM - height / 2);
		hp.draw(sb,pozX,pozY ,width,height,hitPoint);
		sb.end();

		
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

}
