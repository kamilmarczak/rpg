package com.rpg.game.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
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
import com.rpg.game.data.Data;
import com.rpg.game.data.DataManager;
import com.rpg.game.handler.B2DSprite;
import com.rpg.game.handler.GameStateManager;

public class Options extends GameState{
	

	private Stage stage;
	private TextButton buttonDiagMovement,buttonReset, buttonBack,buttonOK,buttonCancel;
	private TextButtonStyle textButtonStyle;
	private BitmapFont fontBlack, font;
	private Skin skin;
	private TextureAtlas buttonAtlas;
	private Table table;
	private Image background;
	private Texture texture;
	private DataManager dataManager;
	private Dialog dialog;
	private WindowStyle windowStyle;
	private LabelStyle labelStyle;
	private GameStateManager gsm;
	

	public Options(GameStateManager gsm) {
		super(gsm);
		this.gsm=gsm;
		stage= new Stage(new ScreenViewport(staticCamera));
		Gdx.input.setInputProcessor(stage);
		texture= AdultGame.res.getTexture("mainmenu");
		background = new Image(texture);
		background.setBounds(0, 0, staticCamera.viewportWidth, staticCamera.viewportHeight);
		stage.addActor(background);

		dataManager= new DataManager();
       dataManager.load();
		buttonCreator();
		menuLayout();
		buttonSetter();

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
        
        if(dataManager.getData().isAllowDiagMovement()){
        buttonDiagMovement = new TextButton("DiagMovement ON ", textButtonStyle);
        }else {
        	 buttonDiagMovement = new TextButton("DiagMovement OFF", textButtonStyle);}
        
        buttonReset=new TextButton("Reset Game", textButtonStyle);
        buttonBack=new TextButton("Back", textButtonStyle);
        buttonOK= new TextButton("OK", textButtonStyle);
        buttonCancel= new TextButton("Cancel", textButtonStyle);
	}
	
	
	
	private void menuLayout(){
        table= new Table();
        table.setWidth(AdultGame.G_WIDTH);
	   table.setHeight(AdultGame.G_HEIGHT);
        table.align(Align.top);
		table.padTop(AdultGame.G_HEIGHT/12);
		table.add(buttonDiagMovement).pad(1).expandX().size(buttonDiagMovement.getWidth(), buttonDiagMovement.getHeight());
	//	table.add(label).expandX();
   		table.row();
		table.add(buttonReset).pad(1).expandX().size(buttonReset.getWidth(), buttonReset.getHeight());
		table.row();
		table.add(buttonBack).pad(20).right().bottom().expand().size(buttonBack.getWidth(), buttonBack.getHeight());
	//	table.debug();
		stage.addActor(table);	
		
	}
	
	
	private void buttonSetter(){
		buttonDiagMovement.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);

				if(buttonDiagMovement.getText().toString().equals("DiagMovement OFF")){
				buttonDiagMovement.setText("DiagMovement  ON");
				dataManager.getData().setAllowDiagMovement(true);
				}else {
					buttonDiagMovement.setText("DiagMovement OFF");
					dataManager.getData().setAllowDiagMovement(false);
				}

				
				
				
			
			}
		});
		
		
		buttonReset.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				
				skin= new Skin(buttonAtlas);
				windowStyle= new WindowStyle();
				windowStyle.background = skin.getDrawable("buttonUP");
				windowStyle.titleFont= new BitmapFont();
				
				dialog =new Dialog("", windowStyle);
				labelStyle= new  LabelStyle();
				BitmapFont  font = new BitmapFont();
				font.setScale(4);
				labelStyle.font= font;
			//	dialog.align(Align.center);
				dialog.text(new Label("Delete all saved data?", labelStyle));
			
			
				buttonCancel.setSize(buttonCancel.getWidth(),buttonCancel.getHeight());
				buttonOK.setSize(buttonOK.getWidth(),buttonOK.getHeight());
				
			dialog.button(buttonOK);
			dialog.button(buttonCancel);

			dialog.pack();
			dialog.setPosition(stage.getWidth()/2f-dialog.getWidth()/2, stage.getHeight()/2f-dialog.getHeight()/2);
		//	dialog.debug();
			stage.addActor(dialog);

			
			
			
			
			}
			
		});
		
		buttonBack.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				dataManager.save();
				gsm.setState(GameStateManager.MENU);
			}
		});
		buttonOK.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				dataManager.newData();
				dialog.remove();
				if(buttonDiagMovement.getText().toString().equals("DiagMovement OFF")){
				dataManager.getData().setAllowDiagMovement(false);
				}
			}
		});
		buttonCancel.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				dialog.remove();
				
				
				
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
		if(dialog!=null){
			//dialog.setPosition(stage.getWidth()/2f-dialog.getWidth()/2, stage.getHeight()/2f-dialog.getHeight()/2);
		}
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
