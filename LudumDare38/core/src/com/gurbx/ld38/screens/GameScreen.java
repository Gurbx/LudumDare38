package com.gurbx.ld38.screens;

import com.badlogic.gdx.Screen;
import com.gurbx.ld38.Application;

public abstract class GameScreen implements Screen {
	protected Application app;
	
	public GameScreen(Application app) {
		this.app = app;
	}

}
