package com.gurbx.ld38.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.gurbx.ld38.Application;
import com.gurbx.ld38.house.HouseHandler;
import com.gurbx.ld38.input.Input;
import com.gurbx.ld38.mobs.MobHandler;
import com.gurbx.ld38.resources.Resources;
import com.gurbx.ld38.ui.UI;

public class PlayScreen extends GameScreen {
	private Input input;
	private Resources resources;
	private HouseHandler houseHandler;
	private MobHandler mobHandler;
	private UI ui;

	public PlayScreen(Application app) {
		super(app);
	}

	@Override
	public void show() {
		TextureAtlas villageAtlas = app.assets.get("img/villagePack.atlas", TextureAtlas.class);
		resources = new Resources(100, 50);
//		mob = new Mob(new Vector2(100, 100), MobType.SOLIDER, villageAtlas);
		this.houseHandler = new HouseHandler(villageAtlas);
		this.mobHandler = new MobHandler(villageAtlas);
		this.input = new Input(mobHandler, houseHandler);
		
		this.ui = new UI(app, villageAtlas, houseHandler, resources);
		
		InputMultiplexer multiPlex = new InputMultiplexer();
		multiPlex.addProcessor(ui.getStage());
		multiPlex.addProcessor(input);
		Gdx.input.setInputProcessor(multiPlex);
	}
	
	private void update(float delta) {
		input.update(delta);
		mobHandler.update(delta);
		houseHandler.update(delta);
		ui.update(delta);
	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0, 0.8f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		app.batch.begin();
		houseHandler.render(app.batch);
		mobHandler.render(app.batch);
		app.batch.end();
		
		houseHandler.renderBars(app.shapeRenderer);
		mobHandler.renderBars(app.shapeRenderer);
		input.renderSelection(app.shapeRenderer);
		
		app.batch.begin();
		ui.render(app.batch);
		app.batch.end();
		
		ui.renderStage();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		houseHandler.dispse();
		mobHandler.dispse();
		ui.dispse();
		
	}

}
