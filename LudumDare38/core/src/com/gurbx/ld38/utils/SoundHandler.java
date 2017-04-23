package com.gurbx.ld38.utils;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gurbx.ld38.Application;

public class SoundHandler implements GameInterface {
	private static Sound hit1, select1, lazer1;
	
	public SoundHandler(Application app) {
		hit1 = app.assets.get("sound/hit1.wav", Sound.class);
		select1 = app.assets.get("sound/select1.wav", Sound.class);
		lazer1 = app.assets.get("sound/lazer1.wav", Sound.class);
	}

	public static void playHit() {
		hit1.play();
	}
	
	public static void playLazer() {
		lazer1.play();
	}
	public static void playSelect() {
		select1.play();
	}
	
	@Override
	public void update(float delta) {
		
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispse() {
		hit1.dispose();
		select1.dispose();
		lazer1.dispose();
		
	}

}
