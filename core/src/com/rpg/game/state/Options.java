package com.rpg.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.rpg.game.AdultGame;
import com.rpg.game.handler.B2DSprite;
import com.rpg.game.handler.GameStateManager;

public class Options extends GameState{
	
	private B2DSprite sprite;
	private Stage stage;
	private TextButton buttonSetPathfinding,buttonOptions, ByttonBack;
	private TextButtonStyle textButtonStyle;
	private BitmapFont fontBlack, font;
	private Skin skin;
	private TextureAtlas buttonAtlas;
	private Table table;
	private Image background;
	private Texture texture;
	private Label label;
	
	

	public Options(GameStateManager gsm) {
		super(gsm);
		
		stage= new Stage(new ScreenViewport(staticCamera));
		
		Gdx.input.setInputProcessor(stage);
		texture= AdultGame.res.getTexture("mainmenu");
		background = new Image(texture);
		background.setBounds(0, 0, staticCamera.viewportWidth, staticCamera.viewportHeight);
		stage.addActor(background);
		buttonCreator();
		lableCreator();
		menuLayout();
		buttonSetter();

	}
	
	
	private void lableCreator() {
		font = new BitmapFont();
		LabelStyle style = new LabelStyle(font,  Color.valueOf("FF4500"));
		label= new Label("sss", style);
		
	}


	private void buttonCreator(){
		//font
		fontBlack = new BitmapFont(Gdx.files.internal("fontBlack/black.fnt"),false);
		fontBlack.scale(-.65f);
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/ButtonsMenu"+ ".pack"));
        skin.addRegions(buttonAtlas);
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = fontBlack;
        textButtonStyle.up = skin.getDrawable("buttonUP");
        textButtonStyle.down = skin.getDrawable("buttonDOWN");
        //////////////////
        buttonSetPathfinding = new TextButton("pathfinding OFF", textButtonStyle);
        buttonOptions=new TextButton("opt one ", textButtonStyle);
        ByttonBack=new TextButton("Back", textButtonStyle);
	}
	
	
	
	private void menuLayout(){
        table= new Table();
        table.setWidth(AdultGame.G_WIDTH);
	   table.setHeight(AdultGame.G_HEIGHT);
        table.align(Align.top);
		table.padTop(AdultGame.G_HEIGHT/12);
		table.add(buttonSetPathfinding).pad(1).expandX().size(buttonSetPathfinding.getWidth(), buttonSetPathfinding.getHeight());
	//	table.add(label).expandX();
   		table.row();
		table.add(buttonOptions).pad(1).expandX().size(buttonOptions.getWidth(), buttonOptions.getHeight());
		table.row();
		table.add(ByttonBack).pad(20).right().bottom().expand().size(ByttonBack.getWidth(), ByttonBack.getHeight());
		table.debug();
		stage.addActor(table);	
		
	}
	
	
	private void buttonSetter(){
		buttonSetPathfinding.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				if(buttonSetPathfinding.getText().toString().equals("pathfinding OFF")){
				buttonSetPathfinding.setText("pathfinding  ON");
				buttonSetPathfinding.setColor(.9f, .9f, .9f, .8f);
				}else {
					buttonSetPathfinding.setText("pathfinding OFF");
					buttonSetPathfinding.setColor(1, 1, 1, 1);
				}

				
				
				
			
			}
		});
		
		
		buttonOptions.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
			
			
			
			}
			
		});
		
		ByttonBack.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				gsm.setState(GameStateManager.MENU);
			}
		});

		
	}
	
	
	

	@Override
	public void handleInput() {
	
/*		if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
			
			
			WindowStyle wstyle = new WindowStyle(new BitmapFont(), new Color(1,1,1,1), null);
			Window windows = new  Window("sdasda", wstyle);
			windows.padTop(65);
			stage.addActor(windows);
		}*/
		
	}

	@Override
	public void update(float dt) {
		handleInput();
		staticCamera.update();
		table.setWidth(staticCamera.viewportWidth);
		table.setHeight(staticCamera.viewportHeight);
		table.padTop(AdultGame.G_HEIGHT/12);
		background.setBounds(0, 0, staticCamera.viewportWidth, staticCamera.viewportHeight);
		 stage.getViewport().update(AdultGame.G_WIDTH, AdultGame.G_HEIGHT, true);
		 
		
	}

	@Override
	public void render() {
	stage.draw();
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
