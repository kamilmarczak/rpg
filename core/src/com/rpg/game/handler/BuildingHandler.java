package com.rpg.game.handler;

import static com.rpg.game.handler.B2DVars.PPM;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.rpg.game.state.Play;

public class BuildingHandler {
	
	public BuildingHandler(World world) { 
		creatRoof(world);
		creatWalls(world);
	
	}
	
	
	
	
	
	
	
	private void creatRoof(World world) {

		//teleports = new Array<Teleport>();
	//	doors = new Array<Door>();
		MapLayer ml = GameMaps.getTileMap().getLayers().get("roofobj");
		if (ml == null)
			return;

		for (MapObject mo : ml.getObjects()) {

			if (mo instanceof RectangleMapObject) {

				BodyDef cdef = new BodyDef();
				cdef.type = BodyType.StaticBody;
		
				
				float x =((RectangleMapObject) mo).getRectangle().getX()/PPM;
				float y =((RectangleMapObject) mo).getRectangle().getY()/PPM;
				float width = ((RectangleMapObject) mo).getRectangle().width/PPM/2;
				float height = ((RectangleMapObject) mo).getRectangle().height/PPM/2;
				cdef.position.set(x +width , y +height );
				Body body = world.createBody(cdef);
				FixtureDef cfdef = new FixtureDef();
				PolygonShape pshape = new PolygonShape();
				pshape.setAsBox(width, height);
				cfdef.shape = pshape;
				cfdef.isSensor = true;
				cfdef.filter.categoryBits = B2DVars.BIT_ROOF;
				cfdef.filter.maskBits = B2DVars.BIT_PLAYER|B2DVars.BIT_ENEMY;
				body.createFixture(cfdef).setUserData("roof");
				pshape.dispose();

			}


		}
	}
	
	private void creatWalls(World world) {

		//teleports = new Array<Teleport>();
	//	doors = new Array<Door>();
		MapLayer ml = GameMaps.getTileMap().getLayers().get("wallsobj");
		if (ml == null)
			return;

		for (MapObject mo : ml.getObjects()) {

			if (mo instanceof RectangleMapObject) {

				BodyDef cdef = new BodyDef();
				cdef.type = BodyType.StaticBody;
		
				
				float x =((RectangleMapObject) mo).getRectangle().getX()/PPM;
				float y =((RectangleMapObject) mo).getRectangle().getY()/PPM;
				float width = ((RectangleMapObject) mo).getRectangle().width/PPM/2;
				float height = ((RectangleMapObject) mo).getRectangle().height/PPM/2;
				cdef.position.set(x +width , y +height );
				Body body = world.createBody(cdef);
				FixtureDef cfdef = new FixtureDef();
				PolygonShape pshape = new PolygonShape();
				pshape.setAsBox(width, height);
				cfdef.shape = pshape;
				cfdef.isSensor = true;
				cfdef.filter.categoryBits = B2DVars.BIT_ROOF;
				cfdef.filter.maskBits =B2DVars.BIT_BULET;
				body.createFixture(cfdef).setUserData("wall");
				pshape.dispose();

			}


		}
	}


}
