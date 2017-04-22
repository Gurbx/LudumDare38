package com.gurbx.ld38.house;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.gurbx.ld38.Application;
import com.gurbx.ld38.utils.GameInterface;

public class HouseHandler implements GameInterface {
	private TextureAtlas atlas;
	private ArrayList<House> houses;
	
	public HouseHandler(TextureAtlas atlas) {
		this.atlas = atlas;
		houses = new ArrayList<House>();
		houses.add(new House(205, 400, HouseType.BASIC, atlas));
	}

	@Override
	public void update(float delta) {
		for (int i = 0; i < houses.size(); i++) {
			houses.get(i).update(delta);
		}
		
	}
	
	public void renderBars(ShapeRenderer shapeRenderer) {
		shapeRenderer.begin(ShapeType.Filled);
		for (int i = 0; i < houses.size(); i++) {
			houses.get(i).renderBars(shapeRenderer);
		}
		shapeRenderer.end();
	}

	@Override
	public void render(SpriteBatch batch) {
		for (int i = 0; i < houses.size(); i++) {
			houses.get(i).render(batch);;
		}
		
	}

	@Override
	public void dispse() {
		for (int i = 0; i < houses.size(); i++) {
			houses.get(i).dispse();
		}
		
	}
	

}
