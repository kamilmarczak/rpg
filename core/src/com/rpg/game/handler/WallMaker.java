package com.rpg.game.handler;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.rpg.game.state.Play;
import static com.rpg.game.handler.B2DVars.PPM;


public class WallMaker {
	
/*	
		private void createLayer(TiledMapTileLayer layer, short bits) {

	BodyDef bdef = new BodyDef();
	FixtureDef fdef = new FixtureDef();

	// go through all cells in the layer
	for (int row = 0; row < layer.getHeight(); row++) {
		for (int col = 0; col < layer.getWidth(); col++) {
			// get cell
			Cell cell = layer.getCell(col, row);
			// check if cell exists
			if (cell == null)
				continue;
			if (cell.getTile() == null)
				continue;

			// create body and fixture form cell

			ChainShape cs = new ChainShape();
			bdef.type = BodyType.StaticBody;
			bdef.position.set((col + .5f) * Play.tileSize / PPM, (row + .5f)* Play.tileSize / PPM);

			Vector2[] v = new Vector2[5];
			v[0] = new Vector2(-Play.tileSize / 2 / PPM, -Play.tileSize / 2 / PPM);
			v[1] = new Vector2(-Play.tileSize / 2 / PPM, Play.tileSize / 2 / PPM);
			v[2] = new Vector2(Play.tileSize / 2 / PPM, Play.tileSize / 2 / PPM);
			v[3] = new Vector2(Play.tileSize / 2 / PPM, -Play.tileSize / 2 / PPM);
			v[4] = new Vector2(-Play.tileSize / 2 / PPM, -Play.tileSize / 2 / PPM);
			cs.createChain(v);

			fdef.friction = 0;
			fdef.shape = cs;

			fdef.filter.categoryBits = bits;
			fdef.filter.maskBits = -1;
			fdef.isSensor = false;
			Play.world.createBody(bdef).createFixture(fdef);
			cs.dispose();

		}
	}

}
*/

}
