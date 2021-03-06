package com.gurbx.ld38.ui;

import com.badlogic.gdx.graphics.Color;
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
	private final float rY = Constants.UI_VIRTUAL_HEIGHT - 25;
	private final float pY = Constants.UI_VIRTUAL_HEIGHT - 50;
	private final float sY = Constants.UI_VIRTUAL_HEIGHT - 75;
	private Resources resources;
	private TextureRegion resinTex, pollenTex, unitTex;
	private Sprite resin, pollen, units;
	private BitmapFont font;

	public ResourceUI(Resources resources, TextureAtlas atlas, BitmapFont font) {
		this.font = font;
		this.resources = resources;
		resinTex = new TextureRegion(atlas.findRegion("resin"));
		pollenTex = new TextureRegion(atlas.findRegion("pollen"));
		unitTex = new TextureRegion(atlas.findRegion("mob"));
		resin = new Sprite(resinTex);
		pollen = new Sprite(pollenTex);
		units = new Sprite(unitTex);
		resin.setPosition(x, rY);
		pollen.setPosition(x, pY);
		units.setPosition(x, sY);
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(SpriteBatch batch) {
		resin.draw(batch);
		pollen.draw(batch);
		units.draw(batch);
		
		font.setColor(Color.WHITE);
		font.draw(batch, "" + resources.getResin() + " / " + resources.getMaxResin(), x + 26, rY + 13);
		font.draw(batch, "" + resources.getPollen() + " / " + resources.getMaxPollen(), x + 26, pY + 13);
		font.draw(batch, "" + resources.getUnits() + " / " + resources.getMaxUnits(), x + 26, sY + 13);
		
	}

	@Override
	public void dispse() {
		// TODO Auto-generated method stub
		
	}

	public TextureRegion getResinTex() {
		return resinTex;
	}
	
	public TextureRegion getPollenTex() {
		return pollenTex;
	}

}
