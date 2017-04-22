package com.gurbx.ld38.house;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.gurbx.ld38.utils.GameInterface;

public class House {
	private TextureRegion placeTex, finalTex, buildTex, damagedTex;
	private Sprite placedSprite, buildSprite, finalSprite, damagedSprite, currentSprite;
	private float x, y;
	private float width, height;
	protected HouseType type;
	
	private boolean placed;
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
		this.placed = false;
	}

	private void initTextures(TextureAtlas atlas) {
		this.placeTex = atlas.findRegion("housePlaced" + type.getPath());
		this.buildTex = atlas.findRegion("houseBuild" + type.getPath());
		this.finalTex = atlas.findRegion("houseFinal" + type.getPath());
		this.damagedTex = atlas.findRegion("houseDamaged" + type.getPath());
		
		this.width = placeTex.getRegionWidth();
		this.height = placeTex.getRegionHeight();
		
		this.placedSprite = new Sprite(placeTex);
		this.placedSprite.setPosition(x, y);
		this.buildSprite = new Sprite(buildTex);
		this.buildSprite.setPosition(x, y);
		this.finalSprite = new Sprite(finalTex);
		this.finalSprite.setPosition(x, y);
		this.damagedSprite = new Sprite(damagedTex);
		this.damagedSprite.setPosition(x, y);
		
		this.currentSprite = placedSprite;
		this.currentSprite.setPosition(x, y);
	}

	public void update(float delta, float x, float y) {
		
		if (placed) {
			progress += delta;	
		} else {
			this.x = x;
			this.y = y;
		}
		if (progress >= buildTime) {
			this.buildFinnished = true;
			currentSprite = finalSprite;
		}
		currentSprite.setPosition(this.x-width/2, this.y-height/2);
	}
	
	public void renderBars(ShapeRenderer shapeRenderer) {
		//Render progress bar
		shapeRenderer.setColor(Color.YELLOW);
		if (progress < buildTime) {
			shapeRenderer.rect(x - width/2, y-height/2-10, barWidth * progress/buildTime, barHeight);
		}
		
	}

	public void render(SpriteBatch batch) {
		currentSprite.draw(batch);
		
	}

	public void dispse() {
		// TODO Auto-generated method stub
	}
	
	public void place() {
		placed = true;
		currentSprite = buildSprite;
		currentSprite.setPosition(x - width/2, y - height/2);
	}
	
	public boolean isPlaced() {
		return placed;
	}

}
