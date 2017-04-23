package com.gurbx.ld38.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gurbx.ld38.house.Barracks;
import com.gurbx.ld38.house.HouseType;
import com.gurbx.ld38.mobs.Mob;
import com.gurbx.ld38.mobs.MobHandler;
import com.gurbx.ld38.mobs.MobType;
import com.gurbx.ld38.resources.Resources;

public class BarrackMenu {
	private String soliderDescription;
	private String arhcerDescription;
	private String wizzardDescription;
	private int hover;
	private float x = 55;
	private float y = 95;
	
	
	private float buttonX = 56; 
	private float buttonY = 10;
	private float modifier = 64;
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
		initDescriptions();
		hover = 0;
		
	}
	
	private void initDescriptions() {
		soliderDescription = "Basic solider unit who chases down his enemies and beats them up";
		arhcerDescription = "Throws sticks on anyone who dares to cross him";
		wizzardDescription = "He's a wizzard!";
		
	}
	
	private void initButtons(Stage stage) {
		mobButton = new ImageButton[3];
		buttonX = 56; 
		buttonY = 10;
        Skin skin = new Skin(atlas);
        ImageButton.ImageButtonStyle style1 = new ImageButton.ImageButtonStyle();
        style1.imageUp = skin.getDrawable("mobButton1");
        style1.imageOver = skin.getDrawable("mobButton1");
        style1.imageDown = skin.getDrawable("mobButtonPressed1");
        mobButton[0] = new ImageButton(style1);
        
        ImageButton.ImageButtonStyle style2 = new ImageButton.ImageButtonStyle();
        style2.imageUp = skin.getDrawable("mobButton1");
        style2.imageOver = skin.getDrawable("mobButton1");
        style2.imageDown = skin.getDrawable("mobButtonPressed1");
        mobButton[1] = new ImageButton(style2);
        
        ImageButton.ImageButtonStyle style3 = new ImageButton.ImageButtonStyle();
        style3.imageUp = skin.getDrawable("mobButton1");
        style3.imageOver = skin.getDrawable("mobButton1");
        style3.imageDown = skin.getDrawable("mobButtonPressed1");
        mobButton[2] = new ImageButton(style3);
        
        
        //Solider
        mobButton[0].addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	if (!active) return;
            	if (mobHandler.canBuyMob(MobType.SOLIDER) == false) return;
            	if (barracks.canSpawnMob(MobType.SOLIDER) == false) return;
            	mobHandler.buyMob(MobType.SOLIDER);
            	barracks.spawnMob(MobType.SOLIDER);
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
        
        //Archer
        mobButton[1].addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	if (!active) return;
            	if (mobHandler.canBuyMob(MobType.ARCHER) == false) return;
            	if (barracks.canSpawnMob(MobType.ARCHER) == false) return;
            	mobHandler.buyMob(MobType.ARCHER);
            	barracks.spawnMob(MobType.ARCHER);
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
        
        //Wizzard
        mobButton[2].addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	if (!active) return;
            	if (mobHandler.canBuyMob(MobType.WIZZARD) == false) return;
            	if (barracks.canSpawnMob(MobType.WIZZARD) == false) return;
            	mobHandler.buyMob(MobType.WIZZARD);
            	barracks.spawnMob(MobType.WIZZARD);
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
        
    
        for (int i = 0; i < mobButton.length; i++) {
            mobButton[i].setPosition(buttonX + modifier * i, buttonY);
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
	
	public void render(SpriteBatch batch, BitmapFont font, TextureRegion pollenTex) {
		renderDescription(batch, font, pollenTex);
		
		
		if (barracks != null) {
			font.setColor(Color.WHITE);
			if (barracks.getMobQueueType() != null) {
				switch (barracks.getMobQueueType()) {
				case SOLIDER:
					font.draw(batch, "" + barracks.getMobQueueSize(), buttonX + 45 , buttonY+55);
					break;
				case ARCHER:
					font.draw(batch, "" + barracks.getMobQueueSize(), buttonX + 45 + modifier , buttonY+55);
					break;
				case WIZZARD:
					font.draw(batch, "" + barracks.getMobQueueSize(), buttonX + 45 + modifier * 2 , buttonY+55);
					break;

				default:
					break;
				}
			}
		}
	}

	private void renderDescription(SpriteBatch batch, BitmapFont font, TextureRegion pollenTex) {
		String header = "";
		String description = "";
		MobType type = MobType.SOLIDER;
		//renderDescription
		switch (hover) {
		case 1:
			type = MobType.SOLIDER;
			header = type.getName();
			description = soliderDescription;
			break;
		case 2:
			type = MobType.ARCHER;
			header = type.getName();
			description = arhcerDescription;
			break;
		case 3:
			type = MobType.WIZZARD;
			header = type.getName();
			description = wizzardDescription;
			break;

		default:
			break;
		}
		
		if (hover != 0) {
			batch.draw(pollenTex, x + 80, y - 15);
			font.draw(batch, "Cost: " + type.getCost(), x, y);
			font.draw(batch, "Spawn Time: " + type.getSpawnTime() + " s", x + 150, y);
			font.draw(batch, header, x, y + 56);
			font.draw(batch, description, x, y + 38);
		}
		
	}

}
