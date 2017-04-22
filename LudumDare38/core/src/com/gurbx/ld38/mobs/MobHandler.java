package com.gurbx.ld38.mobs;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.gurbx.ld38.utils.GameInterface;

public class MobHandler implements GameInterface {
	private ArrayList<Mob> mobs;
	private TextureAtlas atlas;
	
	public MobHandler(TextureAtlas atlas) {
		this.atlas = atlas;
		mobs = new ArrayList<Mob>();
		mobs.add(new Mob(new Vector2(100, 100), MobType.SOLIDER, atlas));
	}

	@Override
	public void update(float delta) {
		for (int i = 0; i < mobs.size(); i++) {
			mobs.get(i).update(delta);
			if (mobs.get(i).shouldRemove()) {
				mobs.get(i).dispse();
				mobs.remove(i);
			}
		}
		
	}

	@Override
	public void render(SpriteBatch batch) {
		for (int i = 0; i < mobs.size(); i++) {
			mobs.get(i).render(batch);
		}	
	}
	
	public void renderBars(ShapeRenderer shapeRenderer) {
		shapeRenderer.begin(ShapeType.Filled);
		for (int i = 0; i < mobs.size(); i++) {
			mobs.get(i).renderBars(shapeRenderer);
		}
		shapeRenderer.end();
		
	}

	@Override
	public void dispse() {
		for (int i = 0; i < mobs.size(); i++) {
			mobs.get(i).dispse();
		}
		
	}

	public ArrayList<Mob> getMobs() {
		return mobs;
	}

}
