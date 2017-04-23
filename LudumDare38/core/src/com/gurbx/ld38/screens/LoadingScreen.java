package com.gurbx.ld38.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.gurbx.ld38.Application;

public class LoadingScreen extends GameScreen {
	public enum LoadNext { PLAY, MENU, GAMEOVER; }
	private LoadNext load;

	public LoadingScreen(Application app) {
		super(app);
		load = LoadNext.MENU;
	}
	
	public void setLoad(LoadNext load) {
		this.load = load;
	}

	@Override
	public void show() {
		loadMenu();
		loadPlay();
//		switch (load) {
//		case MENU:
//			loadMenu();
//			break;
//		case PLAY:
//			loadPlay();
//			break;
//		case GAMEOVER:
//			loadMenu();
//			break;
//		default:
//			break;
//		}
	}

	private void loadMenu() {
		app.assets.load("img/menuPack.atlas", TextureAtlas.class);
		app.assets.load("sound/select1.wav", Sound.class);
		
	}

	private void loadPlay() {
		app.assets.load("img/villagePack.atlas", TextureAtlas.class);
		//sound
		app.assets.load("sound/hit1.wav", Sound.class);
		app.assets.load("sound/hit2.wav", Sound.class);
		app.assets.load("sound/hit3.wav", Sound.class);
		app.assets.load("sound/select1.wav", Sound.class);
		app.assets.load("sound/select2.wav", Sound.class);
		app.assets.load("sound/select3.wav", Sound.class);
		app.assets.load("sound/lazer1.wav", Sound.class);
		app.assets.load("sound/bling1.wav", Sound.class);
		app.assets.load("sound/bling2.wav", Sound.class);
		app.assets.load("sound/plop1.wav", Sound.class);
		app.assets.load("sound/buttonSelect.wav", Sound.class);
	}
	
	private void update(float delta) {
		app.assets.update();
		if (app.assets.getProgress() >= 1) {
			switch (load) {
			case MENU:
				app.setScreen(app.menuScreen);
				break;
			case PLAY:
				app.setScreen(app.playScreen);
				break;
			case GAMEOVER:
				app.setScreen(app.gameOverScreen);
				break;
			default:
				break;
			}
		}
		
	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
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
