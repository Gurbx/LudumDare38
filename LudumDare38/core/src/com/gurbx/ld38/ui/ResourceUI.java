package com.gurbx.ld38.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gurbx.ld38.resources.Resources;
import com.gurbx.ld38.utils.Constants;
import com.gurbx.ld38.utils.GameInterface;

public class ResourceUI implements GameInterface {
	private final float x = 5;
	private final float rY = Constants.UI_VIRTUAL_HEIGHT - 40;
	private final float pY = Constants.UI_VIRTUAL_HEIGHT - 80;
	private Resources resources;
	private Sprite resin, pollen;
	private BitmapFont font;

	public ResourceUI(Resources resources, TextureAtlas atlas, BitmapFont font) {
		this.font = font;
		this.resources = resources;
		resin = new Sprite(new TextureRegion(atlas.findRegion("resin")));
		pollen = new Sprite(new TextureRegion(atlas.findRegion("pollen")));
		resin.setPosition(x, rY);
		pollen.setPosition(x, pY);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(SpriteBatch batch) {
		resin.draw(batch);
		pollen.draw(batch);
		
		font.draw(batch, "Resin: " + resources.getResin(), x, rY);
		font.draw(batch, "Pollen: " + resources.getPollen(), x, pY);
		
	}

	@Override
	public void dispse() {
		// TODO Auto-generated method stub
		
	}

}
