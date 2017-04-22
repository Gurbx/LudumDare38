package com.gurbx.ld38.utils;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gurbx.ld38.waves.Enemy;

public class Projectile implements GameInterface {
	private float x, y;
	private float targetX, targetY;
	private float width, height;
	private float lifeTime;
	private boolean shouldRemove;
	private Sprite sprite;
	
	public Projectile(float x, float y, float targetX, float targetY, float speed, ArrayList<Enemy> enemies, TextureRegion texture) {
		this.sprite = new Sprite(texture);
		this.width = texture.getRegionWidth();
		this.height = texture.getRegionHeight();
		this.sprite.setPosition(x, y);
		lifeTime = 6f;
	}

	@Override
	public void update(float delta) {
		lifeTime-=delta;
		if (lifeTime <= 0) shouldRemove = true;
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispse() {
		// TODO Auto-generated method stub
	}
	
	public boolean shouldRemove() {
		return shouldRemove;
	}
	

}
