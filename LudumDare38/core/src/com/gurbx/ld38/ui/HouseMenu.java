package com.gurbx.ld38.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gurbx.ld38.house.HouseHandler;
import com.gurbx.ld38.house.HouseType;

import javafx.geometry.HPos;

public class HouseMenu {
	private String barrackDescription;
	private String resinPumpDescription;
	private String pollenPumpDescription;
	private String resinStorageDesc;
	private String pollenStorageDesc;
	private String mobHouseDescription;
	private int hover;
	private float x = 55;
	private float y = 95;
	
	
	private TextureAtlas atlas;
	private ImageButton[] houseButton;
	private HouseHandler houseHandler;
	private boolean active;
	
	public HouseMenu(Stage stage, TextureAtlas atlas, HouseHandler houseHandler) {
		this.houseHandler = houseHandler;
		initButtons(stage, atlas);
		initDescriptions();
		this.atlas = atlas;
		this.active = true;
		hover = 0;
	}

	private void initDescriptions() {
		barrackDescription = "Solider training";
		pollenPumpDescription = "Gathers pollen";
		resinPumpDescription = "Gathers resin";
		resinStorageDesc = "Increases the storage capacity of resin by 100"; 
		pollenStorageDesc = "Increases the storage capacity of pollen by 100"; 
		mobHouseDescription = "Increases the unit capacity by 5";
	}

	private void initButtons(Stage stage, TextureAtlas atlas) {
		houseButton = new ImageButton[6];
//		float buttonX = 56; 
		float buttonX = 200;
		float buttonY = 10;
        Skin skin = new Skin(atlas);
        ImageButton.ImageButtonStyle style1 = new ImageButton.ImageButtonStyle();
        style1.imageUp = skin.getDrawable("houseButton1");
        style1.imageOver = skin.getDrawable("houseButtonOver1");
        style1.imageDown = skin.getDrawable("houseButtonPressed1");
        
        ImageButton.ImageButtonStyle style2 = new ImageButton.ImageButtonStyle();
        style2.imageUp = skin.getDrawable("houseButton2");
        style2.imageOver = skin.getDrawable("houseButtonOver2");
        style2.imageDown = skin.getDrawable("houseButtonPressed2");
        
        ImageButton.ImageButtonStyle style3 = new ImageButton.ImageButtonStyle();
        style3.imageUp = skin.getDrawable("houseButton3");
        style3.imageOver = skin.getDrawable("houseButtonOver3");
        style3.imageDown = skin.getDrawable("houseButtonPressed2");
        
        ImageButton.ImageButtonStyle style4 = new ImageButton.ImageButtonStyle();
        style4.imageUp = skin.getDrawable("houseButton4");
        style4.imageOver = skin.getDrawable("houseButtonOver4");
        style4.imageDown = skin.getDrawable("houseButtonPressed2");
        
        ImageButton.ImageButtonStyle style5 = new ImageButton.ImageButtonStyle();
        style5.imageUp = skin.getDrawable("houseButton5");
        style5.imageOver = skin.getDrawable("houseButtonOver5");
        style5.imageDown = skin.getDrawable("houseButtonPressed2");
        
        ImageButton.ImageButtonStyle style6 = new ImageButton.ImageButtonStyle();
        style6.imageUp = skin.getDrawable("houseButton6");
        style6.imageOver = skin.getDrawable("houseButtonOver6");
        style6.imageDown = skin.getDrawable("houseButtonPressed2");

        houseButton[0] = new ImageButton(style1);
        houseButton[1] = new ImageButton(style2);
        houseButton[2] = new ImageButton(style3);
        houseButton[3] = new ImageButton(style4);
        houseButton[4] = new ImageButton(style5);
        houseButton[5] = new ImageButton(style6);
        
        
        //Standard
        houseButton[0].addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	if (!active) return;
            	if (houseHandler.canPlaceHouse(HouseType.BARRACKS) == false) return;
            	houseHandler.placeNewHouse(HouseType.BARRACKS);
            };
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            	super.enter(event, x, y, pointer, fromActor);
            	hover = 1;
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            	super.exit(event, x, y, pointer, toActor);
            	hover = 0;
            }
        });
        //Pollen Pump
        houseButton[1].addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	if (!active) return;
            	if (houseHandler.canPlaceHouse(HouseType.POLLEN_PUMP) == false) return;
            	houseHandler.placeNewHouse(HouseType.POLLEN_PUMP);
            };
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            	super.enter(event, x, y, pointer, fromActor);
            	hover = 2;
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            	super.exit(event, x, y, pointer, toActor);
            	hover = 0;
            }
        });
        
        //Resin pump
        houseButton[2].addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	if (!active) return;
            	if (houseHandler.canPlaceHouse(HouseType.RESIN_PUMP) == false) return;
            	houseHandler.placeNewHouse(HouseType.RESIN_PUMP);
            };
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            	super.enter(event, x, y, pointer, fromActor);
            	hover = 3;
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            	super.exit(event, x, y, pointer, toActor);
            	hover = 0;
            }
        });
        
        //Resin storage
        houseButton[3].addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	if (!active) return;
            	if (houseHandler.canPlaceHouse(HouseType.RESIN_STORAGE) == false) return;
            	houseHandler.placeNewHouse(HouseType.RESIN_STORAGE);
            };
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            	super.enter(event, x, y, pointer, fromActor);
            	hover = 4;
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            	super.exit(event, x, y, pointer, toActor);
            	hover = 0;
            }
        });
        
        //Pollen storage
        houseButton[4].addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	if (!active) return;
            	if (houseHandler.canPlaceHouse(HouseType.POLLEN_STORAGE) == false) return;
            	houseHandler.placeNewHouse(HouseType.POLLEN_STORAGE);
            };
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            	super.enter(event, x, y, pointer, fromActor);
            	hover = 5;
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            	super.exit(event, x, y, pointer, toActor);
            	hover = 0;
            }
        });
        
        //Mob House
        houseButton[5].addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	if (!active) return;
            	if (houseHandler.canPlaceHouse(HouseType.MOB_HOUSE) == false) return;
            	houseHandler.placeNewHouse(HouseType.MOB_HOUSE);
            };
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            	super.enter(event, x, y, pointer, fromActor);
            	hover = 6;
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            	super.exit(event, x, y, pointer, toActor);
            	hover = 0;
            }
        });
        
        
        for (int i = 0; i < houseButton.length; i++) {
            houseButton[i].setPosition(buttonX + 64 * i, buttonY);
			stage.addActor(houseButton[i]);
		}
		
	}
	
	public void setActive(boolean b) {
		this.active = b;
		for (int i = 0; i < houseButton.length; i++) {
			houseButton[i].setVisible(active);
		}
	}
	
	public void render(SpriteBatch batch, BitmapFont font, TextureRegion resinTex) {
		String header = "";
		String description = "";
		HouseType type = HouseType.BASIC;
		//renderDescription
		switch (hover) {
		case 1:
			header = "Barracks";
			description = barrackDescription;
			type = HouseType.BARRACKS;
			break;
		case 2:
			header = "Flower";
			description = pollenPumpDescription;
			type = HouseType.POLLEN_PUMP;
			break;
		case 3:
			header = "Acorn";
			description = resinPumpDescription;
			type = HouseType.RESIN_PUMP;
			break;
		case 4:
			header = "Resin Storage";
			description = resinStorageDesc;
			type = HouseType.RESIN_STORAGE;
			break;
		case 5:
			header = "Pollen Storage";
			description = pollenStorageDesc;
			type = HouseType.POLLEN_STORAGE;
			break;
		case 6:
			header = "House";
			description = mobHouseDescription;
			type = HouseType.MOB_HOUSE;
			break;

		default:
			break;
		}
		
		if (hover != 0) {
			batch.draw(resinTex, x + 80, y - 15);
			font.draw(batch, "Cost: " + type.getCost(), x, y);
			font.draw(batch, "Build Time: " + type.getBuildTime() + " s", x + 150, y);
			font.draw(batch, header, x, y + 56);
			font.draw(batch, description, x, y + 38);
		}
		
	}

}
