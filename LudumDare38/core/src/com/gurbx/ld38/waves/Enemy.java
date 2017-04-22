package com.gurbx.ld38.waves;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.gurbx.ld38.mobs.MobType;
import com.gurbx.ld38.utils.Constants;
import com.gurbx.ld38.utils.GameInterface;

public class Enemy implements GameInterface {
	private Vector2 position;
	private float targetX, targetY, dx, dy;
	private float width, height;
	private float radians, speed;
	private boolean hasMovementTarget;
	private Animation animation;
	private EnemyType type;
	private float elapsedTime;
	
	public Enemy(Vector2 position, EnemyType type, TextureAtlas atlas) {
		elapsedTime = 0;
		this.type = type;
		this.position = position;
		this.speed = type.getMovementSpeed();
		initAnimation(atlas, type);
		
	}
	
	private void initAnimation(TextureAtlas atlas, EnemyType type) {
		   TextureRegion[] moveFrames = new TextureRegion[type.getMoveFrames()];
	       for (int i = 0; i < moveFrames.length; i++) {
	    	   moveFrames[i] = atlas.findRegion(type.getPath() + (i+1));
	        }
		   this.width = moveFrames[0].getRegionWidth();
		   this.height =moveFrames[0].getRegionHeight();
	       animation = new Animation(1/5f, moveFrames);  
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

	public void moveTo(float x, float y) {
		this.hasMovementTarget = true;
		this.targetX = x;
		this.targetY = y;
		
		radians = (float) Math.atan2(targetY - position.y, targetX - position.x);
		
		dx = MathUtils.cos(radians) * speed;
		dy = MathUtils.sin(radians) * speed;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(animation.getKeyFrame(elapsedTime, true), position.x - width*0.5f, position.y-height*0.5f, width/2, height/2, width, height, 1f, 1f, (float) Math.toDegrees(radians) + 90);
		
	}

	@Override
	public void dispse() {
		// TODO Auto-generated method stub
		
	}
	
	

}
