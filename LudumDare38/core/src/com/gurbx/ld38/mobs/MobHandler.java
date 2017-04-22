package com.gurbx.ld38.mobs;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.gurbx.ld38.utils.GameInterface;

public class MobHandler implements GameInterface {
	private ArrayList<Mob> mobs;
	private ArrayList<Mob> selectedMobs;
	private TextureAtlas atlas;
	private Sprite selectionSprite;
	private Random random;
	
	public MobHandler(TextureAtlas atlas) {
		this.atlas = atlas;
		this.selectionSprite = new Sprite(new TextureRegion(atlas.findRegion("selection")));
		mobs = new ArrayList<Mob>();
		selectedMobs = new ArrayList<Mob>();
		mobs.add(new Mob(new Vector2(100, 100), MobType.SOLIDER, atlas));
		for (int i = 0; i < 20; i++) {
			mobs.add(new Mob(new Vector2(10 + 25 * i, 100 + i * 10), MobType.SOLIDER, atlas));
		}
		random = new Random();
	}
	
	public void select(float x, float y, float width, float height) {
		float x2 = x + width;
		float y2 = y + height;
		
		if (x2 < x) {
			float tempX = x2;
			x2 = x;
			x = tempX;
		}
		
		if (y2 < y) {
			float tempY = y2;
			y2 = y;
			y = tempY;
		}
		
		for (int i = 0; i < mobs.size(); i++) {
			if (mobs.get(i).isWithinRegion(x, y, x2, y2)) {
				mobs.get(i).setSelected(true);
				selectedMobs.add(mobs.get(i));
			}
		}
	}
	
	public void deselect() {
		for (int i = 0; i < mobs.size(); i++) {
			mobs.get(i).setSelected(false);
			selectedMobs.clear();
		}	
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
		
		//Render selection icon
		for (int i = 0; i < selectedMobs.size(); i++) {
			selectedMobs.get(i).renderSelection(batch, selectionSprite);
		}
	}
	
	public void renderBars(ShapeRenderer shapeRenderer) {
		shapeRenderer.begin(ShapeType.Filled);
		for (int i = 0; i < mobs.size(); i++) {
			mobs.get(i).renderBars(shapeRenderer);
		}
		shapeRenderer.end();
		
	}
	
	
	public void moveSelectedMobsToPoint(float x, float y) {
		int modifier = 10;
		for (int i = 0; i < selectedMobs.size(); i++) {
			selectedMobs.get(i).moveTo(i * (random.nextInt(modifier) - modifier/2) + x, i * (random.nextInt(modifier) - modifier/2) + y);
		}
	}
	

	@Override
	public void dispse() {
		for (int i = 0; i < mobs.size(); i++) {
			mobs.get(i).dispse();
		}
	}
	
	public ArrayList<Mob> getSelection() {
		return selectedMobs;
	}


}
