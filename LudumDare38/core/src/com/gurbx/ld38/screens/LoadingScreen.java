package com.gurbx.ld38.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.gurbx.ld38.Application;

public class LoadingScreen extends GameScreen {

	public LoadingScreen(Application app) {
		super(app);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void show() {
		loadAssets();
	}

	private void loadAssets() {
		app.assets.load("img/villagePack.atlas", TextureAtlas.class);
		//sound
		app.assets.load("sound/hit1.wav", Sound.class);
	}
	
	private void update(float delta) {
		app.assets.update();
		if (app.assets.getProgress() >= 1) {
			app.setScreen(app.playScreen);
		}
		
	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		app.batch.begin();
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
