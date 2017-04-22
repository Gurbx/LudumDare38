package com.gurbx.ld38.mobs;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.gurbx.ld38.utils.GameInterface;

public class Mob implements GameInterface {
	private Vector2 position;
	private MobEnum type;
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
	private float speed;
	
	public Mob(Vector2 position, MobEnum type, TextureAtlas atlas) {
		this.position = position;
		this.type = type;
		this.selected = false;
		this.shouldRemove = false;
		this.hasMovementTarget = false;
		this.health = type.getHealth();
		this.speed = type.getMovementSpeed();
		this.elapsedTime = 0;
		initAnimation(atlas, type);
	}
	
	private void initAnimation(TextureAtlas atlas, MobEnum type) {
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
//		batch.draw(currentAnimation.getKeyFrame(elapsedTime, true), position.x, position.y);
		batch.draw(currentAnimation.getKeyFrame(elapsedTime, true), position.x, position.y, width/2, height/2, width, height, 1f, 1f, (float) Math.toDegrees(radians) + 90);
		
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

}
