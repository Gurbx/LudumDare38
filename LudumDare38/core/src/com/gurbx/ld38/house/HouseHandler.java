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
	private House placedHouse;
	private float mouseX, mouseY;
	
	public HouseHandler(TextureAtlas atlas) {
		this.atlas = atlas;
		houses = new ArrayList<House>();
//		houses.add(new House(205, 400, HouseType.BASIC, atlas));
	}

	@Override
	public void update(float delta) {
		for (int i = 0; i < houses.size(); i++) {
			houses.get(i).update(delta, mouseX, mouseY);
		}
		if (placedHouse != null) placedHouse.update(delta, mouseX, mouseY);
		
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
		if (placedHouse != null) placedHouse.render(batch);
		
	}

	@Override
	public void dispse() {
		for (int i = 0; i < houses.size(); i++) {
			houses.get(i).dispse();
		}
		
	}
	
	public void setMouseX(float x) {
		this.mouseX = x;
	}
	
	public void setMouseY(float y) {
		this.mouseY = y;
	}
	
	public boolean canPlaceHouse() {
		if (placedHouse == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public void placeCurrentHouse() {
		if (placedHouse == null) return;
		placedHouse.place();
		houses.add(placedHouse);
		placedHouse = null;
	}

	public void placeNewHouse(HouseType type) {
		if (placedHouse != null) return;
		switch (type) {
		case BASIC:
			placedHouse = new House(mouseX, mouseY, type, atlas);
			break;

		default:
			break;
		}
//		placedHouse = new House(mouseX, mouseY, basic.type, atlas);
		
	}
	

}
