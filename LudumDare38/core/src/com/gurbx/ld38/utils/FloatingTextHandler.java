package com.gurbx.ld38.utils;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FloatingTextHandler {
	private static ArrayList<FloatingText> text;
	
	public FloatingTextHandler() {
		text = new ArrayList<FloatingText>();
	}
	
	public void update(float delta) {
		for (int i = 0; i < text.size(); i++) {
			text.get(i).update(delta);
			if (text.get(i).shouldRemove()) {
				text.get(i).dispse();
				text.remove(i);
			}
		}
	}
	
	public void render(SpriteBatch batch, BitmapFont font) {
		for (int i = 0; i < text.size(); i++) {
			text.get(i).render(batch, font);
		}
	}
	
	public void dispose() {
		for (int i = 0; i < text.size(); i++) {
			text.get(i).dispse();
		}
	}
	
	public static void addText(String string, float x, float y, float velocity, float lifeTime) {
		text.add(new FloatingText(string, x, y, velocity, lifeTime));
	}

}
