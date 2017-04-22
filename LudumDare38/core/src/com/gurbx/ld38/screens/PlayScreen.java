package com.gurbx.ld38.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.gurbx.ld38.Application;
import com.gurbx.ld38.house.HouseHandler;
import com.gurbx.ld38.input.Input;
import com.gurbx.ld38.mobs.Mob;
import com.gurbx.ld38.mobs.MobHandler;
import com.gurbx.ld38.mobs.MobType;

public class PlayScreen extends GameScreen {
	private Input input;
	private HouseHandler houseHandler;
	private MobHandler mobHandler;

	public PlayScreen(Application app) {
		super(app);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show() {
		TextureAtlas villageAtlas = app.assets.get("img/villagePack.atlas", TextureAtlas.class);
//		mob = new Mob(new Vector2(100, 100), MobType.SOLIDER, villageAtlas);
		this.mobHandler = new MobHandler(villageAtlas);
		this.input = new Input(mobHandler);
		
		houseHandler = new HouseHandler(villageAtlas);
		
		Gdx.input.setInputProcessor(input);
	}
	
	private void update(float delta) {
		input.update(delta);
		mobHandler.update(delta);
		houseHandler.update(delta);
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
		
	}

}
