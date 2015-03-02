package com.rpg.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.rpg.game.AdultGame;
import com.rpg.game.entities.creature.Player;
import com.rpg.game.handler.B2DVars;
import com.rpg.game.handler.ContendHolder;


public class HUD {
	

	private TextureRegion coinHud;
	private TextureRegion[] font;
	private HealthBar hp;
	private Player player;
	private Image coinImage, fontImage, previesImage ;
	private Group group;

	//stage2d
private Stage stage;
	private Button buttonGun, butonKnife;
	private    Skin skin;
	private  TextureAtlas buttonAtlas;
	private ButtonStyle buttonStyleGun, buttonStyleKnife;
	private Table table;
	private Slider healthSlider;
	
	
	
	public HUD(Player player,Stage stage) {
		this.stage= stage;
		hp= new HealthBar();
		this.player = player;
		group= new Group();
		group.setSize(110*B2DVars.INTERFACESCALE, 15*B2DVars.INTERFACESCALE);
		
		
		Texture tex = AdultGame.res.getTexture("hud");
		coinHud = new TextureRegion(tex, 100, 0, 128, 128);
		coinImage = new Image(coinHud);
		coinImage.setScale(.1f*B2DVars.INTERFACESCALE);
		
		
		
		
		/**
		 * 1.gracz sprawdza  dystans  od obiektu na wartwie dach
		 * 2.  gracz spprawdza czy  znaiduje sie na dachu i go wylacza
		 * 3. dach jest obiektem  i znika gry jest kontakt z graczem lub enemy
		 * 4.
		 * 
		 * 
		 * 
		 * */
		
		
		
		
		
		
		font = new TextureRegion[11];
		for(int i = 0; i < 6; i++) {
			font[i] = new TextureRegion(tex, 32 + i * 9, 16, 9, 9);
		
		}
		for(int i = 0; i < 5; i++) {
			font[i + 6] = new TextureRegion(tex, 32 + i * 9, 25, 9, 9);
		}

		
		buttons();
	}
	
	
	
	public void update(){
		 stage.getViewport().update(AdultGame.G_WIDTH, AdultGame.G_HEIGHT, true);
		  table.setWidth(AdultGame.G_WIDTH);
	      table.setHeight(AdultGame.G_HEIGHT);
	    
		
	}
	
	
    public void buttons() {  
    	
	       
        buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/actionbuttons.pack"));
        skin = new Skin();
        skin.addRegions(buttonAtlas);
        buttonStyleGun = new ButtonStyle();
      //  textButtonStyle.font = font;
        buttonStyleGun.up = skin.getDrawable("noactivegun-up");
        buttonStyleGun.down = skin.getDrawable("activegun-down");
        buttonStyleGun.pressedOffsetX= 100;
        buttonStyleGun.pressedOffsetY=-100;
       // textButtonStyle.checked = skin.getDrawable("checked-button");
        buttonGun = new Button(buttonStyleGun);
        buttonGun.pad(20);
        
        
        //////////////////
        buttonStyleKnife = new ButtonStyle();
        buttonStyleKnife.up = skin.getDrawable("noactiveknife-up");
        buttonStyleKnife.down = skin.getDrawable("activeknife-down");

        butonKnife= new Button(buttonStyleKnife);
        butonKnife.pad(20);
        
        //slider
        SliderStyle style = new SliderStyle();
        
  		style.background = hp.loadingBarBackground;
  		style.background.setLeftWidth(0);
  		style.background.setRightWidth(0);

  		style.knobBefore = hp.loadingBar; 
 

  		
        healthSlider = new Slider(0, 100, 1, false, style);
       
       healthSlider.setVisible(true);
  		
        ///table
        table= new Table();
		 table.setWidth(AdultGame.G_WIDTH);
	   table.setHeight(AdultGame.G_HEIGHT);
	   table.setPosition(0, 0);
	   table.columnDefaults(10);
    
        table.align(Align.center|Align.top);
        table.add(healthSlider).center().top().fillX().pad(5).expand();
        table.add(group).pad(5).center().left().top().expand();
        table.add().expand();
        table.row();
        table.add();
        table.add();
      
        table.add(butonKnife).center().right().width(100).height(70).pad(20);;
        table.row();
        table.add();
        table.add();
        table.add(buttonGun).center().right().width(100).height(70).pad(20);
 
        
        
      // table.debug();
        
    //    buttonGun.debug();
       
        stage.addActor(table);
       
    }

	
	
	
	
	
	
	
	
	
	
	
	
	
	public void render(SpriteBatch sb) {
		  healthSlider.setValue(player.getHealth());
		sb.begin();
		healthSlider.draw(sb, 1);
		

	
		// draw crystal
	//	sb.draw(coinHud,  stage.getWidth()-stage.getWidth()/8, stage.getHeight()-stage.getHeight()/18);
		
		// draw crystal amount
		//drawString(sb, player.getCOINS() + " / " + player.getTotalCrystals(), 132, 211);
		group.clear();
		group.addActor(coinImage);
		drawString(sb, " "+ContendHolder.getCashAmount()+" " , stage.getWidth()-stage.getWidth()/10, stage.getHeight()-stage.getHeight()/18);
		sb.end();
	
		
		
	}
	
	private void drawString(SpriteBatch sb, String s, float x, float y) {
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
		//	if(c == '/') c = 10;
			//else if(c >= '0' && c <= '9') c -= '0';
			 if(c >= '0' && c <= '9') c -= '0';
			else continue;
		//	sb.draw(font[c], x + i * 9, y);
			//fontImage= new Image(font[c]);
			 fontImage =new Image(new TextureRegion(font[c], 0, 0, 9, 9));
			 fontImage.scaleBy(B2DVars.INTERFACESCALE);
			fontImage.setPosition(i*12*B2DVars.INTERFACESCALE,0 );
			group.addActor(fontImage);
		}
	//	
		
	}



	public Button getButtonGun() {
		return buttonGun;
	}



	public Button getButonKnife() {
		return butonKnife;
	}
	
}
