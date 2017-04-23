package com.gurbx.ld38.house;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.gurbx.ld38.mobs.Mob;
import com.gurbx.ld38.mobs.MobHandler;
import com.gurbx.ld38.mobs.MobType;
import com.gurbx.ld38.resources.Resources;

public class Barracks extends House {
	private boolean spawningMob;
	private MobHandler mobHandler;
	private MobType type;
	private float spawnTimer;
	private float spawnTime;
	private ArrayList<Mob> mobQueue;

	public Barracks(float x, float y, HouseType type, TextureAtlas atlas, MobHandler mobHandler, boolean instantPlacement) {
		super(x, y, type, atlas, instantPlacement);
		mobQueue = new ArrayList<Mob>();
		spawningMob = false;
		spawnTimer = 0;
		this.mobHandler = mobHandler;
	}

	public void spawnMob(MobType type) {
		if (spawningMob) {
			mobQueue.add(new Mob(new Vector2(this.x, this.y), type, atlas));
		} else {
			mobQueue.add(new Mob(new Vector2(this.x, this.y), type, atlas));
			spawningMob = true;
			this.type = type;
			spawnTime = type.getSpawnTime();
			spawnTimer = 0;	
		}
	}
	
	@Override
	public void update(float delta, float x, float y) {
		super.update(delta, x, y);
		spawnTimer += delta;
		if (spawningMob && spawnTimer >= spawnTime) {
			mobHandler.addMob(mobQueue.get(0));
			mobQueue.remove(0);
			spawnTimer = 0;
			if (mobQueue.isEmpty()) {
				spawningMob = false;
			}
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

	public boolean canSpawnMob(MobType type, Resources resources) {
		if (placed == true && buildFinnished) {
			if (spawningMob && this.type != type) return false;
			if (resources.getUnits() + getMobQueueSize() >= resources.getMaxUnits()) return false;
			return true;
		} 
		return false;
	}
	
	public int getMobQueueSize() {
		return mobQueue.size();
	}

	public MobType getMobQueueType() {
		return type;
	}

}
