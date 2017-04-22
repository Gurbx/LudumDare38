package com.gurbx.ld38.waves;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gurbx.ld38.house.HouseHandler;
import com.gurbx.ld38.mobs.MobHandler;
import com.gurbx.ld38.utils.Constants;
import com.gurbx.ld38.utils.GameInterface;

public class EnemyHandler implements GameInterface {
	private ArrayList<Enemy> enemies;
	private HouseHandler houseHandler;
	private MobHandler mobHandler;
	
	public EnemyHandler(HouseHandler houseHandler, MobHandler mobHandler) {
		enemies = new ArrayList<Enemy>();
		this.houseHandler = houseHandler;
		this.mobHandler = mobHandler;
	}

	@Override
	public void update(float delta) {
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update(delta);
			enemies.get(i).setTargetToClosest(mobHandler.getMobs(), houseHandler.getHouses());
			if (enemies.get(i).shouldRemove()) {
				enemies.get(i).dispse();
				enemies.remove(i);
			}
		}
		
	}

	@Override
	public void render(SpriteBatch batch) {
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).render(batch);
		}
		
	}

	@Override
	public void dispse() {
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).dispse();
		}
	}
	
	public void addEnemy(Enemy enemy) {
		enemies.add(enemy);
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

}
