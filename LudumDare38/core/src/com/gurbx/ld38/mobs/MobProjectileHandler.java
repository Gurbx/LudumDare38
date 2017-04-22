package com.gurbx.ld38.mobs;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.gurbx.ld38.utils.FriendlyProjectile;
import com.gurbx.ld38.utils.GameInterface;
import com.gurbx.ld38.waves.Enemy;

public class MobProjectileHandler implements GameInterface {
	private TextureAtlas atlas;
	private static ArrayList<FriendlyProjectile> projectiles;
	private ArrayList<Enemy> enemies;
	
	
	public MobProjectileHandler(ArrayList<Enemy> enemies) {
		projectiles = new ArrayList<FriendlyProjectile>();
		this.enemies = enemies;
	}

	@Override
	public void update(float delta) {
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update(delta);
			if (projectiles.get(i).shouldRemove()) {
				projectiles.remove(i);
			}
		}
		
	}

	@Override
	public void render(SpriteBatch batch) {
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(batch);
		}
	}

	@Override
	public void dispse() {
		// TODO Auto-generated method stub
	}
	
	public static void addProjectile(FriendlyProjectile projectile) {
		projectiles.add(projectile);
	}

}
