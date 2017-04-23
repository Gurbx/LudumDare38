package com.gurbx.ld38.house;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.gurbx.ld38.utils.FloatingTextHandler;
import com.gurbx.ld38.utils.GameInterface;
import com.gurbx.ld38.utils.Target;
import com.gurbx.ld38.utils.FloatingText.TextType;

public class House implements Target {
	protected TextureAtlas atlas;
	private TextureRegion placeTex, finalTex, buildTex, damagedTex;
	private Sprite placedSprite, buildSprite, finalSprite, damagedSprite, currentSprite;
	protected float x, y;
	protected float width;
	protected float height;
	protected HouseType type;
	private int health;
	private boolean shouldRemove;
	
	protected boolean placed;
	protected float progress;
	private float buildTime;
	protected float barWidth, barHeight = 4;
	protected boolean buildFinnished;
	
	public House(float x, float y, HouseType type, TextureAtlas atlas) {
		this.atlas = atlas;
		this.x = x;
		this.y = y;
		this.type = type;
		initTextures(atlas);
		this.progress = 0;
		this.buildTime = type.getBuildTime();
		this.barWidth = finalTex.getRegionWidth();
		this.buildFinnished = false;
		this.placed = false;
		shouldRemove = false;
		health = type.getHealth();
	}

	private void initTextures(TextureAtlas atlas) {
		this.placeTex = atlas.findRegion("housePlaced" + type.getPath());
		this.buildTex = atlas.findRegion("houseBuild" + type.getPath());
		this.finalTex = atlas.findRegion("houseFinal" + type.getPath());
//		this.damagedTex = atlas.findRegion("houseDamaged" + type.getPath());
		
		this.width = placeTex.getRegionWidth();
		this.height = placeTex.getRegionHeight();
		
		this.placedSprite = new Sprite(placeTex);
		this.placedSprite.setPosition(x, y);
		this.buildSprite = new Sprite(buildTex);
		this.buildSprite.setPosition(x, y);
		this.finalSprite = new Sprite(finalTex);
		this.finalSprite.setPosition(x, y);
//		this.damagedSprite = new Sprite(damagedTex);
//		this.damagedSprite.setPosition(x, y);
		
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
		shapeRenderer.setColor(Color.GREEN);
		if (progress < buildTime) {
			shapeRenderer.rect(x - width/2, y-height/2-5, barWidth * progress/buildTime, barHeight);
		}
		if (health < type.getHealth()) {
			shapeRenderer.setColor(Color.GRAY);
			shapeRenderer.rect(x - width/2, y+height/2+5, barWidth, barHeight);
			shapeRenderer.setColor(Color.GREEN);
			shapeRenderer.rect(x - width/2, y+height/2+5, barWidth * health/type.getHealth(), barHeight);
		}
		
	}

	public void render(SpriteBatch batch) {
		currentSprite.draw(batch);
	}
	
	public void renderSelection(SpriteBatch batch, Sprite selectionSprite) {
		int modifier = 2;
		selectionSprite.setRotation(0);
		selectionSprite.setPosition(x - (width*0.5f + modifier) - modifier, y + (height*0.5f + modifier)-2);
		selectionSprite.draw(batch);
		selectionSprite.setRotation(270);
		selectionSprite.setPosition(x + (width*0.5f + modifier) - modifier, y + (height*0.5f + modifier-2));
		selectionSprite.draw(batch);
		selectionSprite.setRotation(180);
		selectionSprite.setPosition(x + (width*0.5f + modifier) - modifier, y - (height*0.5f + modifier));
		selectionSprite.draw(batch);
		selectionSprite.setRotation(90);
		selectionSprite.setPosition(x - (width*0.5f + modifier) - modifier, y - (height*0.5f + modifier));
		selectionSprite.draw(batch);
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

	public boolean overlaps(float x2, float y2) {
		if (x-width/2 < x2 && x+width/2 > x2 &&
				y-height/2 < y2 && y+height/2 > y2) {
			return true;
		}
		return false;
	}

	public HouseType getType() {
		return type;
	}

	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}

	@Override
	public void damage(int damage) {
//		FloatingTextHandler.addText("" + damage, this.x - width/2, this.y -width/2 + 10, 50, 2.5f, Color.RED, TextType.BOUNCE);
		this.health -= damage;
		if (health <= 0) {
			health = 0;
			shouldRemove = true;
		}
		
	}

	public boolean shouldRemove() {
		return shouldRemove;
	}

	@Override
	public boolean isDead() {
		if (health <= 0) return true;
		return false;
	}

}
