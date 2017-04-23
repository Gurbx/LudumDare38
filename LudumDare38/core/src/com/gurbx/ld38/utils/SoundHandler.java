package com.gurbx.ld38.utils;

import java.util.Random;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gurbx.ld38.Application;

public class SoundHandler implements GameInterface {
	private static Random random;
	private static Sound hit1, select1, lazer1;
	private static Sound[] bling;
	private static Sound[] select;
	private static Sound[] hit;
	
	public SoundHandler(Application app) {
		random = new Random();
		bling = new Sound[2];
		select = new Sound[3];
		hit = new Sound[3];
		
		for (int i = 0; i < bling.length; i++) {
			bling[i] = app.assets.get("sound/bling" + (i+1) + ".wav", Sound.class);
		}
		for (int i = 0; i < select.length; i++) {
			select[i] = app.assets.get("sound/select" + (i+1) + ".wav", Sound.class);
		}
		for (int i = 0; i < hit.length; i++) {
			hit[i] = app.assets.get("sound/hit" + (i+1) + ".wav", Sound.class);
		}
		
//		hit1 = app.assets.get("sound/hit1.wav", Sound.class);
//		select1 = app.assets.get("sound/select1.wav", Sound.class);
		lazer1 = app.assets.get("sound/lazer1.wav", Sound.class);
//		app.assets.load("sound/plop1.wav", Sound.class);
		select1 = app.assets.get("sound/buttonSelect.wav", Sound.class);
	}

	public static void playHit() {
		int index = random.nextInt(hit.length);
		hit[index].play(0.5f);
	}
	
	public static void playLazer() {
		lazer1.play();
	}
	public static void playSelect(int i) {
		select[i].play();
	}
	
	public static void playBuildingFinished(int i) {
		bling[i].play();
	}
	
	public static void playButtonSelect() {
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
//		hit1.dispose();
		select1.dispose();
		lazer1.dispose();
		for (int i = 0; i < bling.length; i++) {
			bling[i].dispose();
		}
		for (int i = 0; i < select.length; i++) {
			select[i].dispose();
		}
		for (int i = 0; i < hit.length; i++) {
			hit[i].dispose();
		}
		
	}

}
