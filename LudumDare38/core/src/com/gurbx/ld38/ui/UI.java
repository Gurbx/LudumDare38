package com.gurbx.ld38.ui;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gurbx.ld38.Application;
import com.gurbx.ld38.house.HouseHandler;
import com.gurbx.ld38.utils.Constants;
import com.gurbx.ld38.utils.GameInterface;

public class UI implements GameInterface {
	private Application app;
	private Stage stage;
	private Bar bar;
	private HouseMenu houseMenu;
	
	public UI(Application app, TextureAtlas atlas, HouseHandler houseHandler) {
		this.app = app;
		stage = new Stage(app.uiViewport);
		bar = new Bar(atlas);
		houseMenu = new HouseMenu(stage, atlas, houseHandler);
	}


	@Override
	public void update(float delta) {
		bar.update(delta);
		stage.act();
		
	}

	@Override
	public void render(SpriteBatch batch) {
		bar.render(batch);
	}
	
	public void renderStage() {
		stage.draw();
	}

	@Override
	public void dispse() {
		stage.dispose();
		bar.dispse();
	}
	
	public Stage getStage() {
		return stage;
	}

}
