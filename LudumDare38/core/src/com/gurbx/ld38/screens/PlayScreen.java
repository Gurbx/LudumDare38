package com.gurbx.ld38.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.gurbx.ld38.Application;
import com.gurbx.ld38.mobs.Mob;
import com.gurbx.ld38.mobs.MobEnum;
import com.gurbx.ld38.utils.Input;

public class PlayScreen extends GameScreen {
	private Input input;
	private Mob mob;

	public PlayScreen(Application app) {
		super(app);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show() {
		mob = new Mob(new Vector2(100, 100), MobEnum.SOLIDER, app.assets.get("img/villagePack.atlas", TextureAtlas.class));
		this.input = new Input(mob);
		
		Gdx.input.setInputProcessor(input);
	}
	
	private void update(float delta) {
		mob.update(delta);
	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0, 0.8f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		app.batch.begin();
		mob.render(app.batch);
		app.batch.end();
		
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
		// TODO Auto-generated method stub
		
	}

}
