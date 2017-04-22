package com.gurbx.ld38.utils;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FloatingText {
	private float x, y;
	private float lifeTime;
	private float timer;
	private float velocity;
	private boolean shouldRemove;
	private String text;
	
	public FloatingText(String text, float x, float y, float velocity, float lifeTime) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.velocity = velocity;
		this.lifeTime = lifeTime;
		this.timer = lifeTime;
		shouldRemove = false;
	}

	public void update(float delta) {
		timer -= delta;
		if (timer < 0) {
			shouldRemove = true;
		}
		y += velocity * delta;
	}

	public void render(SpriteBatch batch, BitmapFont font) {
		font.draw(batch, text, x, y);
		
	}

	public void dispse() {
		// TODO Auto-generated method stub
		
	}

	public boolean shouldRemove() {
		return shouldRemove;
	}
	

}
