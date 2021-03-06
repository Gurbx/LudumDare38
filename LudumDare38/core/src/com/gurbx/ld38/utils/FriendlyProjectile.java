package com.gurbx.ld38.utils;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gurbx.ld38.waves.Enemy;

public class FriendlyProjectile extends Projectile {
	private ArrayList<Enemy> enemies;
	
	public FriendlyProjectile(float x, float y, float targetX, float targetY, float speed, 
			TextureRegion texture, ArrayList<Enemy> enemies, int damage, ProjectileType type) {
		super(x, y, targetX, targetY, speed, texture, damage, type);
		this.enemies = enemies;
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		checkCollision();
	}

	private void checkCollision() {
		for (int i = 0; i < enemies.size(); i++) {
			if (hitsTarget(enemies.get(i).getPosition().x, enemies.get(i).getPosition().y)) {
				switch (type) {
				case WIZZARDSPELL:
					ParticleEffectHandler.addSpellEffect(x, y);
					break;
				case WARLOCKSPELL:
					ParticleEffectHandler.addWarlockSpell(x, y);
					break;
				default:
					break;
				}
				enemies.get(i).damage((int) (damage + Math.random() * 2));
				shouldRemove = true;
			}
		}
	}
	

}
