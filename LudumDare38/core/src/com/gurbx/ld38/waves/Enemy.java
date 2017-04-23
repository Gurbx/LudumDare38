package com.gurbx.ld38.waves;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.gurbx.ld38.house.House;
import com.gurbx.ld38.mobs.Mob;
import com.gurbx.ld38.mobs.MobType;
import com.gurbx.ld38.utils.Constants;
import com.gurbx.ld38.utils.FloatingText.TextType;
import com.gurbx.ld38.utils.FloatingTextHandler;
import com.gurbx.ld38.utils.GameInterface;
import com.gurbx.ld38.utils.ParticleEffectHandler;
import com.gurbx.ld38.utils.SoundHandler;
import com.gurbx.ld38.utils.Target;

public class Enemy implements GameInterface, Target {
	private Vector2 position;
	private float targetX, targetY, dx, dy;
	private float width, height;
	private float radians, speed;
	private boolean hasMovementTarget;
	private Animation animation;
	private EnemyType type;
	private float elapsedTime;
	private boolean shouldRemove;
	private int health, maxHealth;
	private Target target;
	
	private float attackTimer;
	private boolean canAttack;
	
	public Enemy(Vector2 position, EnemyType type, TextureAtlas atlas) {
		elapsedTime = 0;
		this.type = type;
		this.position = position;
		this.speed = type.getMovementSpeed();
		this.health = type.getHealth();
		this.maxHealth = health;
		this.shouldRemove = false;
		attackTimer = type.getAttackCooldown();
		canAttack = false;
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
		attackTimer -= delta;
		if (attackTimer <= 0) canAttack = true;
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
			
			//Attack
			hasMovementTarget = false;
			
			if (target.isDead() == false && canAttack) {
				target.damage(type.getDamage());
				canAttack = false;
				attackTimer = type.getAttackCooldown();
			}
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
	
	public void setTargetToClosest(ArrayList<Mob> mobs, ArrayList<House> houses) {
		if (mobs.isEmpty() && houses.isEmpty()) return;
		int index = 0;
		float mobDistace = 999999;
		for (int i = 0; i < mobs.size(); i++) {
			float x2 = mobs.get(i).getPosition().x;
			float y2 = mobs.get(i).getPosition().y;
			
			float newDistance = (float) Math.sqrt((position.x-x2)*(position.x-x2) + (position.y-y2)*(position.y-y2));
			
			if (newDistance < mobDistace) {
				index = i;
				mobDistace = newDistance;
			}
		}
		int jndex = 0;
		float houseDistance = 99999;
		
		if (!type.onlyAttacksHouses()) {
			for (int i = 0; i < houses.size(); i++) {
				float x2 = houses.get(i).getX();
				float y2 = houses.get(i).getY();
				
				float newDistance = (float) Math.sqrt((position.x-x2)*(position.x-x2) + (position.y-y2)*(position.y-y2));
				
				if (newDistance < houseDistance) {
					jndex = i;
					houseDistance = newDistance;
				}
			}
		}
		if (mobDistace < houseDistance && mobs.isEmpty() == false) {
			moveTo(mobs.get(index).getPosition().x, mobs.get(index).getPosition().y);
			target = mobs.get(index);
			
		} else if (houses.isEmpty() == false) {
			moveTo(houses.get(jndex).getX(), houses.get(jndex).getY());
			target = houses.get(jndex);
		}
		
	}
	
	public boolean shouldRemove() {
		return shouldRemove;
	}

	public boolean hasTarget() {
		return hasMovementTarget;
	}

	public Vector2 getPosition() {
		return position;
	}

	public boolean isDead() {
		if (this.health <= 0) {
			shouldRemove = true;
			return true;
		} else {
			return false;
		}
	}
	
	public void damage(int damage) {
		ParticleEffectHandler.addBloodEffect(position.x + width/2, position.y + height/2);
		if (onScreen()) SoundHandler.playHit();
		FloatingTextHandler.addText("" + damage, position.x - width/2, position.y - width/2 + 10, 200, 1f,
				Color.RED, TextType.BOUNCE);
		this.health -= damage;
		if (health < 0) {
			health = 0;
			shouldRemove = true;
		}
	}

	private boolean onScreen() {
		if (position.x >= 0 && position.x <= Constants.VIRTUAL_WIDTH &&
				position.y >= 0 && position.y <= Constants.VIRTUAL_HEIGHT) {
			return true;
		}
		return false;
	}
	
	

}
