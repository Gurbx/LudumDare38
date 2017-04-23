package com.gurbx.ld38.ui;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gurbx.ld38.utils.Constants;
import com.gurbx.ld38.utils.GameInterface;

public class Bar implements GameInterface {
	private TextureRegion barTex;
	private Sprite bar;
	private boolean active;
	
	public Bar(TextureAtlas atlas) {
		barTex = new TextureRegion(atlas.findRegion("uiBar"));
		bar = new Sprite(barTex);
		bar.setPosition(Constants.UI_VIRTUAL_WIDTH/2 - barTex.getRegionWidth()/2, 0);
		active = true;
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(SpriteBatch batch) {
		if (active) bar.draw(batch);
		
	}

	@Override
	public void dispse() {
		// TODO Auto-generated method stub
		
	}
	
	public void setActive(boolean b) {
		active = b;
	}

}
