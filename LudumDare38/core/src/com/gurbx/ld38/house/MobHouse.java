package com.gurbx.ld38.house;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.gurbx.ld38.resources.Resources;

public class MobHouse extends House {
	private Resources resources;

	public MobHouse(float x, float y, HouseType type, TextureAtlas atlas, Resources resources) {
		super(x, y, type, atlas);
		this.resources = resources;
	}

}
