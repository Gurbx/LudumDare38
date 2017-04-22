package com.gurbx.ld38.house;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.gurbx.ld38.mobs.Mob;
import com.gurbx.ld38.mobs.MobHandler;
import com.gurbx.ld38.mobs.MobType;

public class Barracks extends House {
	private boolean spawningMob;
	private MobHandler mobHandler;
	private MobType type;
	private float spawnTimer;
	private float spawnTime;

	public Barracks(float x, float y, HouseType type, TextureAtlas atlas, MobHandler mobHandler) {
		super(x, y, type, atlas);
		spawningMob = false;
		spawnTimer = 0;
		this.mobHandler = mobHandler;
	}

	public void spawnMob(MobType type) {
		spawningMob = true;
		this.type = type;
		spawnTime = type.getSpawnTime();
		spawnTimer = 0;
	}
	
	@Override
	public void update(float delta, float x, float y) {
		super.update(delta, x, y);
		spawnTimer += delta;
		if (spawningMob && spawnTimer >= spawnTime) {
			mobHandler.addMob(new Mob(new Vector2(this.x, this.y), type, atlas));
			spawningMob = false;
		}
	}
	
	@Override
	public void renderBars(ShapeRenderer shapeRenderer) {
		super.renderBars(shapeRenderer);
		shapeRenderer.setColor(Color.YELLOW);
		if (spawningMob) {
			shapeRenderer.rect(x - width/2, y-height/2-10, barWidth * spawnTimer/spawnTime, barHeight);
		}
	}

	public boolean canSpawnMob() {
		if (placed == true && buildFinnished && spawningMob == false) {
			return true;
		} 
		return false;
	}

}
