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
	private float time_between_waves = 50f;
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
		timer = time_between_waves * 1.5f;
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
		int side = random.nextInt(4);
		float x;
		float y;
		float range = 300;
		
		switch (side) {
		case 0:
			//Right
			x = Constants.UI_VIRTUAL_WIDTH + range;
			y = Constants.VIRTUAL_HEIGHT/2;
			break;
		case 1:
			//Left
			x = 0 - range;
			y = Constants.VIRTUAL_HEIGHT/2;
			break;
		case 2:
			//Top
			x = Constants.UI_VIRTUAL_WIDTH/2;
			y = Constants.VIRTUAL_HEIGHT + range;
			break;
		case 3:
			//Bottom
			x = Constants.UI_VIRTUAL_WIDTH/2;
			y = 0 - range;
			break;
		default:
			x = Constants.UI_VIRTUAL_WIDTH + range;
			y = Constants.VIRTUAL_HEIGHT/2;
			break;
		}
		
		//Always bugs
		for (int i = 0; i < (1 + waveNr * random.nextInt(5)) ; i++) {
			Vector2 position = new Vector2(x - range/2 + random.nextInt((int) range), y - range/2 + random.nextInt((int) range));
			enemies.addEnemy(new Enemy(position, EnemyType.MINI_BUG, atlas));
		}
		for (int i = 0; i < (1 + waveNr * random.nextInt(5)) ; i++) {
			Vector2 position = new Vector2(x - range/2 + random.nextInt((int) range), y - range/2 + random.nextInt((int) range));
			enemies.addEnemy(new Enemy(position, EnemyType.BUG, atlas));
		}
		
		//After 4
		if (waveNr >= 4) {
			for (int i = 0; i < (1 + waveNr * random.nextInt(5)) ; i++) {
				Vector2 position = new Vector2(x - range/2 + random.nextInt((int) range), y - range/2 + random.nextInt((int) range));
				enemies.addEnemy(new Enemy(position, EnemyType.BIGGER_BUG, atlas));
			}
			
		}
		
		//After 6
		
		if (waveNr >= 6) {
			for (int i = 0; i < (1 + waveNr * random.nextInt(2)) ; i++) {
				Vector2 position = new Vector2(x - range/2 + random.nextInt((int) range), y - range/2 + random.nextInt((int) range));
				enemies.addEnemy(new Enemy(position, EnemyType.BIGGER_BUG, atlas));
			}
		}
		
		//After 10
		if (waveNr >= 10) {
			for (int i = 0; i < (1 + waveNr * random.nextInt(2)) ; i++) {
				Vector2 position = new Vector2(x - range/2 + random.nextInt((int) range), y - range/2 + random.nextInt((int) range));
				enemies.addEnemy(new Enemy(position, EnemyType.MEGA_BUG, atlas));
			}
		}
		
		//After 15
		if (waveNr >= 15) {
			for (int i = 0; i < (1 + waveNr/2) ; i++) {
				Vector2 position = new Vector2(x - range/2 + random.nextInt((int) range), y - range/2 + random.nextInt((int) range));
				enemies.addEnemy(new Enemy(position, EnemyType.BROWN_BUG, atlas));
			}
		}
		
		//After 20
		if (waveNr >= 20) {
			for (int i = 0; i < (1 + waveNr/2) ; i++) {
				Vector2 position = new Vector2(x - range/2 + random.nextInt((int) range), y - range/2 + random.nextInt((int) range));
				enemies.addEnemy(new Enemy(position, EnemyType.PURPLE_BUG, atlas));
			}
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
	
	public int getWaveNr() {
		return waveNr-1;
	}

}
