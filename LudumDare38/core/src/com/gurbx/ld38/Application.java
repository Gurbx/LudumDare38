package com.gurbx.ld38;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gurbx.ld38.screens.GameOverScreen;
import com.gurbx.ld38.screens.LoadingScreen;
import com.gurbx.ld38.screens.LoadingScreen.LoadNext;
import com.gurbx.ld38.screens.MenuScreen;
import com.gurbx.ld38.screens.PlayScreen;
import com.gurbx.ld38.utils.Constants;

public class Application extends Game {
	public SpriteBatch batch;
	public OrthographicCamera camera, uiCamera;
	public Viewport viewport, uiViewport;
	public AssetManager assets;
	
	public ShapeRenderer shapeRenderer;
	public BitmapFont font;
	
	public LoadingScreen loadingScreen;
	public PlayScreen playScreen;
	public MenuScreen menuScreen;
	public GameOverScreen gameOverScreen;
	
	public static int waveNr;
	
	@Override
	public void create () {
		initGeneral();
		initScreens();
	}
	
	private void initGeneral() {
		batch = new SpriteBatch();
		assets = new AssetManager();
		camera = new OrthographicCamera();
		uiCamera = new OrthographicCamera();
		viewport = new StretchViewport(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT, camera);
		uiViewport = new StretchViewport(Constants.UI_VIRTUAL_WIDTH, Constants.UI_VIRTUAL_HEIGHT, uiCamera);
		viewport.apply();
		uiViewport.apply();
		camera.position.set(Constants.VIRTUAL_WIDTH/2, Constants.VIRTUAL_HEIGHT/2, 0);
		uiCamera.position.set(Constants.UI_VIRTUAL_WIDTH/2, Constants.UI_VIRTUAL_HEIGHT/2, 0);
		camera.update();
		uiCamera.update();
		
		shapeRenderer = new ShapeRenderer();
		font = new BitmapFont();
	}
	
	private void initScreens() {
		playScreen = new PlayScreen(this);
		loadingScreen = new LoadingScreen(this);
		menuScreen = new MenuScreen(this);
		gameOverScreen = new GameOverScreen(this);
		loadingScreen.setLoad(LoadNext.MENU);
		setScreen(loadingScreen);
	}


	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		uiViewport.update(width, height);
		super.resize(width, height);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		assets.dispose();
		shapeRenderer.dispose();
		font.dispose();
	}
}
