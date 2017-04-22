package com.gurbx.ld38.utils;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class Projectile implements GameInterface {
	private float x, y, dx, dy;
	private float targetX, targetY;
	private float width, height, radians;
	private float lifeTime;
	protected boolean shouldRemove;
	private Sprite sprite;
	protected int damage;
	
	public Projectile(float x, float y, float targetX, float targetY, float speed, TextureRegion texture, int damage) {
		this.sprite = new Sprite(texture);
		this.x = x;
		this.y = y;
		this.sprite.setPosition(x, y);
		this.width = texture.getRegionWidth();
		this.height = texture.getRegionHeight();
		this.sprite.setPosition(x, y);
		lifeTime = 6f;
		this.damage = damage;
		sprite.setScale(1.5f);
		
		radians = (float) Math.atan2(targetY - y, targetX - x);
		
		dx = MathUtils.cos(radians) * speed;
		dy = MathUtils.sin(radians) * speed;
	}

	@Override
	public void update(float delta) {
		lifeTime-=delta;
		if (lifeTime <= 0) shouldRemove = true;
		
		x += dx * delta;
		y += dy * delta;
		
		sprite.setRotation((float) Math.toDegrees(radians) + 90);
		sprite.setPosition(x, y);
	}
	
	protected boolean hitsTarget(float eX, float eY) {
		int hitRange = 10;
		if (eX > x && eX < x+hitRange &&
				eY > y && eY < y + hitRange) {
			return true;
		}
		return false;
	}

	@Override
	public void render(SpriteBatch batch) {
		sprite.draw(batch);
	}

	@Override
	public void dispse() {
		// TODO Auto-generated method stub
	}
	
	public boolean shouldRemove() {
		return shouldRemove;
	}
	

}
