package com.gurbx.ld38.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.gurbx.ld38.Application;
import com.gurbx.ld38.screens.LoadingScreen.LoadNext;
import com.gurbx.ld38.utils.Constants;

public class GameOverScreen extends GameScreen {
	private TextureAtlas atlas;
	private TextureRegion bgTile;
	private Stage stage;
	private Sound select;
	
	private TextButton menuButton;

	public GameOverScreen(Application app) {
		super(app);
	}

	@Override
	public void show() {
		stage = new Stage(app.uiViewport);
		atlas = app.assets.get("img/menuPack.atlas", TextureAtlas.class);
		bgTile = atlas.findRegion("bgTile");
		initButtons();
		select = app.assets.get("sound/select1.wav", Sound.class);
		Gdx.input.setInputProcessor(stage);
		
	}
	
	private void initButtons() {
        Skin skin = new Skin(atlas);
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = app.font;
        style.up = skin.getDrawable("menuButton");
        style.down = skin.getDrawable("menuButtonPressed");
        
        menuButton = new TextButton("BACK", style);
        menuButton.setPosition(Constants.UI_VIRTUAL_WIDTH/2-184, Constants.UI_VIRTUAL_HEIGHT/2 - 36 - 100);
        menuButton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	select.play();
            	app.loadingScreen.setLoad(LoadNext.MENU);
            	app.setScreen(app.loadingScreen);
            };
        });
        
        stage.addActor(menuButton);
		
	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		app.batch.begin();
		app.batch.end();
		
		stage.draw();
		
	}

	private void update(float delta) {
		// TODO Auto-generated method stub
		
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
		stage.dispose();
		atlas.dispose();
		select.dispose();
	}

}
