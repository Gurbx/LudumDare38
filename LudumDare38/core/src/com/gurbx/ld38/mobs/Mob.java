package com.gurbx.ld38.mobs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.gurbx.ld38.utils.Constants;
import com.gurbx.ld38.utils.GameInterface;

public class Mob implements GameInterface {
	private Vector2 position;
	private MobType type;
	private Animation move, stand, attack;
	private Animation currentAnimation;
	private float targetX, targetY;
	private float dx, dy;
	private float width, height;
	private float radians;
	private boolean hasMovementTarget;
	private float elapsedTime;
	
	private boolean selected;
	private boolean shouldRemove;
	private int health;
	private int maxHealth;
	private float speed;
	
	public Mob(Vector2 position, MobType type, TextureAtlas atlas) {
		this.position = position;
		this.type = type;
		this.selected = false;
		this.shouldRemove = false;
		this.hasMovementTarget = false;
		this.health = type.getHealth();
		this.maxHealth = health;
		this.speed = type.getMovementSpeed();
		this.elapsedTime = 0;
		initAnimation(atlas, type);
	}
	
	private void initAnimation(TextureAtlas atlas, MobType type) {
		   TextureRegion[] moveFrames = new TextureRegion[type.getMoveFrames()];
	       for (int i = 0; i < moveFrames.length; i++) {
	    	   moveFrames[i] = atlas.findRegion("mob");
	        }
		   this.width = moveFrames[0].getRegionWidth();
		   this.height =moveFrames[0].getRegionHeight();
	       move = new Animation(1/16f, moveFrames);  
	       currentAnimation = move;
	}

	public void moveTo(float x, float y) {
		this.hasMovementTarget = true;
		this.targetX = x;
		this.targetY = y;
		
		if (targetX < 10) targetX = 10;
		if (targetX > Constants.VIRTUAL_WIDTH -10) targetX = Constants.VIRTUAL_WIDTH-10;
		if (targetY < 10) targetY = 10;
		if (targetY > Constants.VIRTUAL_HEIGHT-10) targetY = Constants.VIRTUAL_HEIGHT-10;
		
		radians = (float) Math.atan2(targetY - position.y, targetX - position.x);
		
		dx = MathUtils.cos(radians) * speed;
		dy = MathUtils.sin(radians) * speed;
	}

	@Override
	public void update(float delta) {
		elapsedTime += delta;
		handleMovement(delta);
	}

	private void handleMovement(float delta) {
		if (hasMovementTarget) {
			position.x += dx * delta;
			position.y += dy * delta;
		}
		int targetRange = 2;
		if (Math.abs(position.x - targetX) <= targetRange && Math.abs(position.y - targetY) <= targetRange) {
			hasMovementTarget = false;
		}
		
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(currentAnimation.getKeyFrame(elapsedTime, true), position.x - width*0.5f, position.y-height*0.5f, width/2, height/2, width, height, 1f, 1f, (float) Math.toDegrees(radians) + 90);
		
	}
	
	public void renderSelection(SpriteBatch batch, Sprite selectionSprite) {
		int modifier = 2;
		selectionSprite.setRotation(0);
		selectionSprite.setPosition(position.x - (width*0.5f + modifier), position.y + (height*0.5f + modifier));
		selectionSprite.draw(batch);
		selectionSprite.setRotation(270);
		selectionSprite.setPosition(position.x + (width*0.5f + modifier), position.y + (height*0.5f + modifier));
		selectionSprite.draw(batch);
		selectionSprite.setRotation(180);
		selectionSprite.setPosition(position.x + (width*0.5f + modifier), position.y - (height*0.5f + modifier));
		selectionSprite.draw(batch);
		selectionSprite.setRotation(90);
		selectionSprite.setPosition(position.x - (width*0.5f + modifier), position.y - (height*0.5f + modifier));
		selectionSprite.draw(batch);
		
	}
	
	public void renderBars(ShapeRenderer shapeRenderer) {
		if (health < maxHealth) {
			shapeRenderer.setColor(Color.GRAY);
			shapeRenderer.rect(position.x - width*0.5f, position.y - height*0.5f + height + 3, width, 3);
			shapeRenderer.setColor(Color.GREEN);
			shapeRenderer.rect(position.x - width * 0.5f, position.y - height * 0.5f + height + 3, width * (float) health/maxHealth, 3);
		}
	}

	@Override
	public void dispse() {
		
	}
	
	public boolean shouldRemove() {
		return shouldRemove;
	}
	
	public boolean isDead(){
		if (this.health <= 0) {
			shouldRemove = true;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isWithinRegion(float x, float y, float x2, float y2) {

		if (position.x >= x && position.x <= x2 &&
				position.y >= y && position.y <= y2) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setSelected(boolean b) {
		selected = b;
	}
	
	public Vector2 getPosition() {
		return position;
	}



}
