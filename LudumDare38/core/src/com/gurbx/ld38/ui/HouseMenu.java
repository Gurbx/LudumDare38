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
	
	public HouseMenu(Stage stage, TextureAtlas atlas, HouseHandler houseHandler) {
		this.houseHandler = houseHandler;
		initButtons(stage, atlas);
		this.atlas = atlas;
	}

	private void initButtons(Stage stage, TextureAtlas atlas) {
		houseButton = new ImageButton[1];
		float buttonX = 56; 
		float buttonY = 10;
        Skin skin = new Skin(atlas);
        ImageButton.ImageButtonStyle style1 = new ImageButton.ImageButtonStyle();
        style1.imageUp = skin.getDrawable("houseButton1");
        style1.imageOver = skin.getDrawable("houseButtonOver1");
        style1.imageDown = skin.getDrawable("houseButtonPressed1");

        houseButton[0] = new ImageButton(style1);
        
        
        houseButton[0].addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	if (houseHandler.canPlaceHouse(HouseType.BASIC) == false) return;
            	houseHandler.placeNewHouse(HouseType.BASIC);
            };
        });
        
        
        for (int i = 0; i < houseButton.length; i++) {
            houseButton[i].setPosition(buttonX + 64 * i, buttonY);
			stage.addActor(houseButton[i]);
		}
		
	}

}
