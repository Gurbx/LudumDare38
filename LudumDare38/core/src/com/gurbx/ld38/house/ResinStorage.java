package com.gurbx.ld38.house;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.gurbx.ld38.resources.Resources;

public class ResinStorage extends House {
	private int ammountIncreased = 100;
	private Resources resources;
	private boolean storageIncreased;
	

	public ResinStorage(float x, float y, HouseType type, TextureAtlas atlas, Resources resources) {
		super(x, y, type, atlas);
		this.resources = resources;
		storageIncreased = false;
	}
	
	@Override
	public void update(float delta, float x, float y) {
		super.update(delta, x, y);
		if (buildFinnished && storageIncreased == false) {
			increaseStorage();
		}
	}

	private void increaseStorage() {
		storageIncreased = true;
		resources.increaseMaxResin(ammountIncreased);
		
	}

}
