package com.gurbx.ld38.house;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.gurbx.ld38.utils.GameInterface;

public class House implements GameInterface {
	private TextureRegion finalTex, buildTex, damagedTex;
	private Sprite buildSprite, finalSprite, damagedSprite, currentSprite;
	private float x, y;
	protected HouseType type;
	
	private float progress;
	private float buildTime;
	private float barWidth, barHeight = 6;
	private boolean buildFinnished;
	
	public House(float x, float y, HouseType type, TextureAtlas atlas) {
		this.x = x;
		this.y = y;
		this.type = type;
		initTextures(atlas);
		this.progress = 0;
		this.buildTime = type.getBuildTime();
		this.barWidth = finalTex.getRegionWidth();
		this.buildFinnished = false;
	}

	private void initTextures(TextureAtlas atlas) {
		this.buildTex = atlas.findRegion(type.getPath() + "Build");
		this.finalTex = atlas.findRegion(type.getPath() + "Final");
		this.damagedTex = atlas.findRegion(type.getPath() + "Damaged");
		
		this.buildSprite = new Sprite(buildTex);
		this.buildSprite.setPosition(x, y);
		this.finalSprite = new Sprite(finalTex);
		this.finalSprite.setPosition(x, y);
		this.damagedSprite = new Sprite(damagedTex);
		this.damagedSprite.setPosition(x, y);
		
		this.currentSprite = buildSprite;
		this.currentSprite.setPosition(x, y);
	}

	@Override
	public void update(float delta) {
		progress += delta;
		if (progress >= buildTime) {
			this.buildFinnished = true;
			currentSprite = finalSprite;
		}
	}
	
	public void renderBars(ShapeRenderer shapeRenderer) {
		//Render progress bar
		shapeRenderer.setColor(Color.YELLOW);
		if (progress < buildTime) {
			shapeRenderer.rect(x, y-10, barWidth * progress/buildTime, barHeight);
		}
		
	}

	@Override
	public void render(SpriteBatch batch) {
		currentSprite.draw(batch);
		
	}

	@Override
	public void dispse() {
		// TODO Auto-generated method stub
		
	}

}
