package com.gurbx.ld38.house;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.gurbx.ld38.resources.Resources;
import com.gurbx.ld38.utils.FloatingText;
import com.gurbx.ld38.utils.FloatingTextHandler;

public class PollenPump extends House {
	private int amountGenerated = 5;
	private float cooldown = 2;
	private float timer;
	private Resources resources;

	public PollenPump(float x, float y, HouseType type, TextureAtlas atlas, Resources resources) {
		super(x, y, type, atlas);
		this.resources = resources;
		timer = cooldown;
	}
	
	@Override
	public void update(float delta, float x, float y) {
		super.update(delta, x, y);
		if (buildFinnished) {
			timer -= delta;
		}
		if (timer < 0) {
			generatePollen();
			FloatingTextHandler.addText("" +amountGenerated, this.x - 3, this.y + 10, 20, 4.5f);
			timer = cooldown;
		}
	}

	private void generatePollen() {
		resources.addPollen(amountGenerated);
	}

}
