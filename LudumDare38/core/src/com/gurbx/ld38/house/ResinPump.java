package com.gurbx.ld38.house;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.gurbx.ld38.resources.Resources;
import com.gurbx.ld38.utils.FloatingTextHandler;
import com.gurbx.ld38.utils.FloatingText.TextType;

public class ResinPump extends House {
	private int amountGenerated = 5;
	private float cooldown = 0.2f;
	private float timer;
	private Resources resources;

	public ResinPump(float x, float y, HouseType type, TextureAtlas atlas, Resources resources) {
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
			generateResin();
			FloatingTextHandler.addText("" +amountGenerated, this.x - 3, this.y + 10, 20, 4.5f, Color.ORANGE, TextType.FLOAT);
			timer = cooldown;
		}
	}


	private void generateResin() {
		resources.addResin(amountGenerated);
		
	}

}
