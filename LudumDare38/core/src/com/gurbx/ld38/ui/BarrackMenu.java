package com.gurbx.ld38.ui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gurbx.ld38.house.Barracks;
import com.gurbx.ld38.mobs.Mob;
import com.gurbx.ld38.mobs.MobHandler;
import com.gurbx.ld38.mobs.MobType;
import com.gurbx.ld38.resources.Resources;

public class BarrackMenu {
	private float spawmX, spawnY;
	private TextureAtlas atlas;
	private ImageButton[] mobButton;
	private MobHandler mobHandler;
	private Resources resources;
	private boolean active;
	private Barracks barracks;
	
	public BarrackMenu(Stage stage, TextureAtlas atlas, MobHandler mobHandler, Resources resources) {
		this.atlas = atlas;
		this.mobHandler = mobHandler;
		this.resources = resources;
		initButtons(stage);
		setActive(false, null);
	}
	
	private void initButtons(Stage stage) {
		mobButton = new ImageButton[1];
		float buttonX = 56; 
		float buttonY = 10;
        Skin skin = new Skin(atlas);
        ImageButton.ImageButtonStyle style1 = new ImageButton.ImageButtonStyle();
        style1.imageUp = skin.getDrawable("houseButton1");
        style1.imageOver = skin.getDrawable("houseButtonOver1");
        style1.imageDown = skin.getDrawable("houseButtonPressed1");

        mobButton[0] = new ImageButton(style1);
        
        
        //Standard
        mobButton[0].addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	if (!active) return;
            	if (mobHandler.canBuyMob(MobType.SOLIDER) == false) return;
            	if (barracks.canSpawnMob() == false) return;
            	mobHandler.buyMob(MobType.SOLIDER);
            	barracks.spawnMob(MobType.SOLIDER);
            	
//            	houseHandler.placeNewHouse(HouseType.BASIC);
//            	mobHandler.addMob(new Mob(new Vector2(spawmX,spawnY), MobType.SOLIDER, atlas));
            };
        });
        
    
        for (int i = 0; i < mobButton.length; i++) {
            mobButton[i].setPosition(buttonX + 64 * i, buttonY);
			stage.addActor(mobButton[i]);
		}
	}

	public void setActive(boolean b, Barracks barracks) {
		this.barracks = barracks;
		this.active = b;
		for (int i = 0; i < mobButton.length; i++) {
			mobButton[i].setVisible(active);
		}
	}

}
