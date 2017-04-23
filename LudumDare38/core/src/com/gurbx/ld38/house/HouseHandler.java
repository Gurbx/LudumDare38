package com.gurbx.ld38.house;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.gurbx.ld38.mobs.MobHandler;
import com.gurbx.ld38.resources.Resources;
import com.gurbx.ld38.utils.GameInterface;

public class HouseHandler implements GameInterface {
	private TextureAtlas atlas;
	private ArrayList<House> houses;
	private House placedHouse;
	private House selectedHouse;
	private Resources resources;
	private float mouseX, mouseY;
	private Sprite selectionSprite;
	private MobHandler mobHandler;
	
	public HouseHandler(TextureAtlas atlas, Resources resources, MobHandler mobHandler) {
		this.mobHandler = mobHandler;
		this.resources = resources;
		this.atlas = atlas;
		houses = new ArrayList<House>();
		this.selectionSprite = new Sprite(new TextureRegion(atlas.findRegion("selection")));
		
		//Starting houses
		int distance = 30;
		int x = 400;
		int y = 440;
		houses.add(new Barracks(x, y, HouseType.BARRACKS, atlas, mobHandler, true));
		
		houses.add(new ResinPump(x + distance, y + distance, HouseType.RESIN_PUMP, atlas, resources, true));
		houses.add(new PollenPump(x - distance, y - distance, HouseType.POLLEN_PUMP, atlas, resources, true));
		houses.add(new ResinStorage(x - distance, y + distance, HouseType.RESIN_STORAGE, atlas, resources, true));
		houses.add(new PollenStorage(x + distance, y - distance, HouseType.POLLEN_STORAGE, atlas, resources, true));
	}
	
	@Override
	public void update(float delta) {
		for (int i = 0; i < houses.size(); i++) {
			houses.get(i).update(delta, mouseX, mouseY);
			if (houses.get(i).shouldRemove()) {
				if (selectedHouse != null) {
					if (selectedHouse.equals(houses.get(i))) {
						deselect();
					}
				}
				houses.get(i).dispse();
				houses.remove(i);
			}
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
		if (selectedHouse != null) selectedHouse.renderSelection(batch, selectionSprite);
		
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
	
	public boolean canPlaceHouse(HouseType type) {
		if (resources.getResin() < type.getCost()) return false; 
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
			resources.removeResin(type.getCost());
			placedHouse = new House(mouseX, mouseY, type, atlas, false);
			break;
		case POLLEN_PUMP:
			resources.removeResin(type.getCost());
			placedHouse = new PollenPump(mouseX, mouseY, type, atlas, resources, false);
			break;
		case BARRACKS:
			resources.removeResin(type.getCost());
			placedHouse = new Barracks(mouseX, mouseY, type, atlas, mobHandler, false);
			break;
		case RESIN_PUMP:
			resources.removeResin(type.getCost());
			placedHouse = new ResinPump(mouseX, mouseY, type, atlas, resources, false);
			break;
		case RESIN_STORAGE:
			resources.removeResin(type.getCost());
			placedHouse = new ResinStorage(mouseX, mouseY, type, atlas, resources, false);
			break;
		case POLLEN_STORAGE:
			resources.removeResin(type.getCost());
			placedHouse = new PollenStorage(mouseX, mouseY, type, atlas, resources, false);
			break;
		case MOB_HOUSE:
			resources.removeResin(type.getCost());
			placedHouse = new MobHouse(mouseX, mouseY, type, atlas, resources, false);
			break;
			

		default:
			break;
		}
//		placedHouse = new House(mouseX, mouseY, basic.type, atlas);
		
	}

	public void select(float x, float y) {
		for (int i = 0; i < houses.size(); i++) {
			if (houses.get(i).overlaps(x,y)) {
				selectedHouse = houses.get(i);
			}
		}
	}
	
	public void deselect() {
		selectedHouse = null;
	}
	
	public House getSelectedHouse() {
		return selectedHouse;
	}

	public ArrayList<House> getHouses() {
		return houses;
	}
	

}
