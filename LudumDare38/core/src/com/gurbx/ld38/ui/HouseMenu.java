package com.gurbx.ld38.ui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gurbx.ld38.house.Barracks;
import com.gurbx.ld38.house.HouseHandler;
import com.gurbx.ld38.house.HouseType;

public class HouseMenu {
	private TextureAtlas atlas;
	private ImageButton[] houseButton;
	private HouseHandler houseHandler;
	private boolean active;
	
	public HouseMenu(Stage stage, TextureAtlas atlas, HouseHandler houseHandler) {
		this.houseHandler = houseHandler;
		initButtons(stage, atlas);
		this.atlas = atlas;
		this.active = true;
	}

	private void initButtons(Stage stage, TextureAtlas atlas) {
		houseButton = new ImageButton[2];
		float buttonX = 56; 
		float buttonY = 10;
        Skin skin = new Skin(atlas);
        ImageButton.ImageButtonStyle style1 = new ImageButton.ImageButtonStyle();
        style1.imageUp = skin.getDrawable("houseButton1");
        style1.imageOver = skin.getDrawable("houseButtonOver1");
        style1.imageDown = skin.getDrawable("houseButtonPressed1");

        houseButton[0] = new ImageButton(style1);
        houseButton[1] = new ImageButton(style1);
        
        
        //Standard
        houseButton[0].addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	if (!active) return;
            	if (houseHandler.canPlaceHouse(HouseType.BARRACKS) == false) return;
            	houseHandler.placeNewHouse(HouseType.BARRACKS);
            };
        });
        //Pollen Pump
        houseButton[1].addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	if (!active) return;
            	if (houseHandler.canPlaceHouse(HouseType.POLLEN_PUMP) == false) return;
            	houseHandler.placeNewHouse(HouseType.POLLEN_PUMP);
            };
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

}
