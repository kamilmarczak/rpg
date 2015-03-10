package com.rpg.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.rpg.game.AdultGame;
import com.rpg.game.data.Data;
import com.rpg.game.handler.B2DSprite;
import com.rpg.game.handler.GameStateManager;

public class Menu extends GameState  {
		private B2DSprite sprite;
		private Stage stage;
		private TextButton buttonNewGame,buttonContinue, buttonQuit;
		private TextButtonStyle textButtonStyle;
		private BitmapFont fontBlack;
		private Skin skin;
		private TextureAtlas buttonAtlas;
		private Table table;
		private Group group;
		private Data data;

	public Menu(GameStateManager gsm) {
		super(gsm);
		data= new Data();
	//stage
		stage= new Stage(new ScreenViewport(hudCam));
		Gdx.input.setInputProcessor(stage);
		//background
		sprite= new B2DSprite(hudCam);
		sprite.backgroundDraw("mainmenu");
	//	font
				fontBlack = new BitmapFont(Gdx.files.internal("fontBlack/black.fnt"),false);
				fontBlack.scale(-.65f);
				
		        skin = new Skin();
		        buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/ButtonsMenu"+ ".pack"));
		        skin.addRegions(buttonAtlas);
		        textButtonStyle = new TextButtonStyle();
		        textButtonStyle.font = fontBlack;
		        textButtonStyle.up = skin.getDrawable("buttonUP");
		        textButtonStyle.down = skin.getDrawable("buttonDOWN");
		        buttonCreator();
		        menuLayout();
		        buttonSetter();
				

		        
		        
		        

	}

	private void buttonCreator(){
        buttonNewGame = new TextButton("NEW GAME", textButtonStyle);
        buttonContinue=new TextButton("Continue", textButtonStyle);
        buttonQuit=new TextButton("Quit", textButtonStyle);
		
		
		
		
		
		
	}
	private void menuLayout(){
        table= new Table();
        table.setWidth(AdultGame.G_WIDTH);
	   table.setHeight(AdultGame.G_HEIGHT);
        table.align(Align.top);
		table.padTop(AdultGame.G_HEIGHT/12);
		table.add(buttonNewGame).pad(1).expandX().size(buttonNewGame.getWidth(), buttonNewGame.getHeight());
   		table.row();
		table.add(buttonContinue).pad(1).expandX().size(buttonContinue.getWidth(), buttonContinue.getHeight());
		table.row();
		table.add(buttonQuit).pad(20).right().bottom().expand().size(buttonQuit.getWidth(), buttonQuit.getHeight());
		stage.addActor(table);	
		
	}
	
	
	
	private void buttonSetter(){
		buttonNewGame.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				gsm.setState(GameStateManager.PLAY);
				
			
			}
		});
		
		
		buttonContinue.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
			
			
			}
			
		});
		
		buttonQuit.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				Gdx.app.exit();
			}
		});
		
        

		
	}
	
	
	
	
	
	
	@Override
	public void handleInput() {
		
		

	
		
	}

	
	
	@Override
	public void update(float dt) {
		handleInput();
		hudCam.update();
		table.setWidth(hudCam.viewportWidth);
		table.setHeight(hudCam.viewportHeight);
		table.padTop(AdultGame.G_HEIGHT/12);
		 stage.getViewport().update(AdultGame.G_WIDTH, AdultGame.G_HEIGHT, true);
		 

		
		
	}

	@Override
	public void render() {

		sprite.renderBackground(sb);
		stage.draw();

		
	
		
	}

	@Override
	public void dispose() {
	
		
	}

}
