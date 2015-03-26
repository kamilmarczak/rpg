package com.rpg.game.entities.creature;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rpg.game.AdultGame;
import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.GameStateManager;

public abstract class Npc extends Creature{
	
	
	private static String bodyTAG = "npc";
	private static String sensorTAG = "npcSensor";
	private static short categoryBit =B2DVars.BIT_NPC;
	private static int maskBits = B2DVars.BIT_PLAYER;
	static boolean isSensor = false;
	private  int animationRow=0;
	private int type = 4;
	private String textureName = "npc";
// qlog
	protected Texture texture;
	protected BitmapFont fontBlack, font;
	protected TextButton buttonOK,buttonCancel;
	protected TextButtonStyle textButtonStyle;
	protected Skin skin;
	protected TextureAtlas buttonAtlas;
	protected WindowStyle windowStyle;
	protected Dialog dialog;
	protected LabelStyle labelStyle;
	
	




	public Npc(float x, float y,World world,Player player) {
		super(x, y, bodyTAG, sensorTAG, categoryBit, maskBits, isSensor,world);
		sprite.playAnimation(animationRow, textureName);
		texture= AdultGame.res.getTexture("mainmenu");
	font = new BitmapFont();
	buttonCreator();
	buttonSetter();
	setDialog();
		
		
		
		

	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public void update(float dt) {
		
		
	}

	@Override
	public void render(SpriteBatch sb) {
		sprite.render(sb);
		
	}

	@Override
	public void attack() {
	
		
	}
	public void showDialog() {
		

	}
	private  void setDialog(){
		
		skin= new Skin(buttonAtlas);
		windowStyle= new WindowStyle();
		windowStyle.background = skin.getDrawable("buttonUP");
		windowStyle.titleFont= new BitmapFont();
		labelStyle= new  LabelStyle();
		BitmapFont  font = new BitmapFont();
		
		labelStyle.font= font;
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
        buttonOK= new TextButton("Sure", textButtonStyle);
        buttonCancel= new TextButton("No way", textButtonStyle);

	}
	private void buttonSetter(){

		

		buttonOK.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				
				dialog.remove();

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
	
	
	
	public int getAnimationRow() {return animationRow;}
	public void setAnimationRow(int animationRow) {this.animationRow = animationRow;}

}
