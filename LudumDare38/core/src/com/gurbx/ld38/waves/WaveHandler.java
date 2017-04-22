package com.gurbx.ld38.waves;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.gurbx.ld38.house.HouseHandler;
import com.gurbx.ld38.mobs.MobHandler;
import com.gurbx.ld38.utils.Constants;
import com.gurbx.ld38.utils.GameInterface;

public class WaveHandler implements GameInterface {
	private int waveNr;
	private float time_between_waves = 6f;
	private float timer;
	private Random random;
	private EnemyHandler enemies;
	private HouseHandler houseHandler;
	private MobHandler mobHandler;
	private TextureAtlas atlas;
	
	public WaveHandler(TextureAtlas atlas, HouseHandler houseHandler, MobHandler mobHandler) {
		this.houseHandler = houseHandler;
		this.mobHandler = mobHandler;
		random = new Random();
		waveNr = 1;
		timer = time_between_waves;
		this.atlas = atlas;
		enemies = new EnemyHandler(houseHandler, mobHandler);
	}

	@Override
	public void update(float delta) {
		timer -= delta;
		if (timer <= 0) {
			createWave();
			timer = time_between_waves;
		}
		enemies.update(delta);
	}

	private void createWave() {
		for (int i = 0; i < (1 + waveNr * random.nextInt(5)) ; i++) {
			Vector2 position = new Vector2(Constants.VIRTUAL_HEIGHT + 20 * i, Constants.UI_VIRTUAL_WIDTH + 20 * i);
			enemies.addEnemy(new Enemy(position, EnemyType.BUG, atlas));
		}
		waveNr++;
		
	}

	@Override
	public void render(SpriteBatch batch) {
		enemies.render(batch);
		
	}

	@Override
	public void dispse() {
		enemies.dispse();
		
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies.getEnemies();
	}

}
