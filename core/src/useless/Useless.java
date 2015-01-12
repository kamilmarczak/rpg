package useless;

public class Useless {
	//sprawdzanie czy  woda czy 
/*			if(GameMaps.coliLayer.getCell((int)(playerPositionX+cam.position.x / PPM - (cam.viewportWidth / 2 / PPM)),(int)((playerPositionY)+(cam.position.y / PPM - (cam.viewportHeight / 2 / PPM)))).getTile().getProperties().containsKey("trawa")){
		//System.out.println(cam.position.x / PPM - (cam.viewportWidth / 2 / PPM));
		System.out.println(cam.position.y / PPM - (cam.viewportHeight / 2 / PPM));

		System.out.println("tak");
	}else {
		System.out.println("nieeee");
	}*/
	
	
	//old control
	/*
	public void handleInput() {

		playerPositionX = player.getBody().getPosition().x;
		playerPositionY = player.getBody().getPosition().y;

		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			player.getBody().setLinearVelocity(0, 0);
			lastClickX = cam.position.x / PPM - (cam.viewportWidth / 2 / PPM)+ Gdx.input.getX() / PPM;
			lastClickY = cam.position.y / PPM - (cam.viewportHeight / 2 / PPM)+ (cam.viewportHeight / PPM) - Gdx.input.getY() / PPM;
			isMoving = true;
			animationChecker();

		}

		if (isMoving) {
			// if (lastClickX != player.getBody().getPosition().x || lastClickY
			// != player.getBody().getPosition().y ) {

			// Get the vector between the player and the target
			float pathX = lastClickX - playerPositionX;
			float pathY = lastClickY - playerPositionY;

			// set 1

			// Calculate the unit vector of the path
			double distance = Math.sqrt(pathX * pathX + pathY * pathY);
			double directionX = pathX / distance;
			double directionY = pathY / distance;

			// Calculate the actual walk amount
			double movementX = directionX * speed;
			double movementY = directionY * speed;

			player.getBody().setLinearVelocity((float) movementX,
					(float) movementY);
			if ((int) (player.getBody().getPosition().x * 10) == (int) (lastClickX * 10)
					&& (int) (player.getBody().getPosition().y * 10) == (int) (lastClickY * 10)) {
				isMoving = false;
			}

		} else {
			player.getBody().setLinearVelocity(0, 0);
			aniChecker(4);
		}

	}*/

	
	
	////////////////////////////////////////////////////////////////////
	// for creating  bodys from layers
	
	
	
	

	
	//TODO wylaczona metoda  createLayer
	//TiledMapTileLayer layer;
	//layer = (TiledMapTileLayer) tileMap.getLayers().get("Grass");
	//createLayer(layer, B2DVars.BIT_GRASS);
	// layer=(TiledMapTileLayer)tileMap.getLayers().get("Grass") ;
	// createLayer(layer, B2DVars.BIT_GRASS);
	// layer=(TiledMapTileLayer)tileMap.getLayers().get("Grass") ;
	// createLayer(layer, B2DVars.BIT_GRASS);



//TODO disabled metod  create layer

/*	private void createLayer(TiledMapTileLayer layer, short bits) {

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
			bdef.position.set((col + .5f) * tileSize / PPM, (row + .5f)
					* tileSize / PPM);

			Vector2[] v = new Vector2[5];
			v[0] = new Vector2(-tileSize / 2 / PPM, -tileSize / 2 / PPM);
			v[1] = new Vector2(-tileSize / 2 / PPM, tileSize / 2 / PPM);
			v[2] = new Vector2(tileSize / 2 / PPM, tileSize / 2 / PPM);
			v[3] = new Vector2(tileSize / 2 / PPM, -tileSize / 2 / PPM);
			v[4] = new Vector2(-tileSize / 2 / PPM, -tileSize / 2 / PPM);
			cs.createChain(v);

			fdef.friction = 0;
			fdef.shape = cs;

			fdef.filter.categoryBits = bits;
			fdef.filter.maskBits = -1;
			fdef.isSensor = false;
			world.createBody(bdef).createFixture(fdef);
			cs.dispose();

		}
	}

}
*/

	
	
	
	
	
	
	
	
	
	
	
	/////////////////////////////////////////////////////////////////
	//player is not going shortes way
	
	   
	 

    
/*		    //set 2
	   
	   //nie zawsze idzie po linii priostej
	    
	   
	    
 double angle = Math.atan2(pathY, pathX) * 180 / Math.PI;
	    
	    
	    double movementX = Math.cos(angle * Math.PI/180) * speed;
	    double movementY = Math.sin(angle * Math.PI/180) * speed;
	    
	    
	    player.getBody().setLinearVelocity((int)movementX,(int)movementY);
		   if((int)(player.getBody().getPosition().x*10)==(int)(lastClickX*10) &&(int)(player.getBody().getPosition().y*10)==(int)(lastClickY*10)){ isMoving=false;}
	  
	 
	   
	   */
	

	
	////////////////////////////////////////////////////////////////
	//Player WSAD movement
	/*
	

	if (MyInput.isPressed(MyInput.UP)) {
		player.playAnimation(0);
		player.getBody().setLinearVelocity(player.getBody().getLinearVelocity().x, .5f);
		listaPrzycisków.add(1);
		
	}
	if (MyInput.isPressed(MyInput.DOWN)) {
		player.playAnimation(2);
		player.getBody().setLinearVelocity(player.getBody().getLinearVelocity().x, -.5f);
		listaPrzycisków.add(1);
	}
	
	if (MyInput.isPressed(MyInput.LEFT))  {
		player.playAnimation(1);
		player.getBody().setLinearVelocity(-.5f,player.getBody().getLinearVelocity().y);
		listaPrzycisków.add(1);
	}		
	if (MyInput.isPressed(MyInput.RIGHT)) {

		player.playAnimation(3);
		player.getBody().setLinearVelocity(.5f,	player.getBody().getLinearVelocity().y);
		listaPrzycisków.add(1);
	}

	
	

	if (MyInput.isReleased(MyInput.UP)) {
		listaPrzycisków.remove(listaPrzycisków.size()-1);
		player.getBody().setLinearVelocity(player.getBody().getLinearVelocity().x, 0);
		player.playAnimation(4);
	}
	if (MyInput.isReleased(MyInput.DOWN)) {
		listaPrzycisków.remove(listaPrzycisków.size()-1);
		player.getBody().setLinearVelocity(player.getBody().getLinearVelocity().x, 0);
		player.playAnimation(4);
	}
	if (MyInput.isReleased(MyInput.LEFT)) {
		listaPrzycisków.remove(listaPrzycisków.size()-1);
		player.getBody().setLinearVelocity(0,player.getBody().getLinearVelocity().y);
		player.playAnimation(4);
	}
	if (MyInput.isReleased(MyInput.RIGHT)) {
		listaPrzycisków.remove(listaPrzycisków.size()-1);
		player.getBody().setLinearVelocity(0,player.getBody().getLinearVelocity().y);
		player.playAnimation(4);
	}


	*/

}
