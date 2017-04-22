package com.gurbx.ld38.mobs;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.gurbx.ld38.house.House;
import com.gurbx.ld38.utils.Constants;
import com.gurbx.ld38.utils.FloatingText.TextType;
import com.gurbx.ld38.utils.FloatingTextHandler;
import com.gurbx.ld38.utils.FriendlyProjectile;
import com.gurbx.ld38.utils.GameInterface;
import com.gurbx.ld38.utils.Target;
import com.gurbx.ld38.waves.Enemy;

public class Mob implements Target {
	private Vector2 position;
	private MobType type;
	private Animation move, stand, attack;
	private Animation currentAnimation;
	private TextureRegion projectileTex;
	private float targetX, targetY;
	private float dx, dy;
	private float width, height;
	private float radians;
	private boolean hasMovementTarget;
	private float elapsedTime;
	private float newTargetTimer;
	private float newTargetTime = 0.8f;
	private boolean canSelectNewTarget;
	
	private boolean selected;
	private boolean shouldRemove;
	private int health;
	private int maxHealth;
	private float speed;
	private Enemy target;
	
	private float attackTimer;
	private boolean canAttack;
	
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
		canSelectNewTarget = false;
		newTargetTimer = newTargetTime;
		initAnimation(atlas, type);
		attackTimer = type.getAttackSpeed();
		canAttack = false;
	}
	
	private void initAnimation(TextureAtlas atlas, MobType type) {
		   TextureRegion[] moveFrames = new TextureRegion[type.getMoveFrames()];
	       for (int i = 0; i < moveFrames.length; i++) {
	    	   moveFrames[i] = atlas.findRegion(type.getPath());
	        }
		   this.width = moveFrames[0].getRegionWidth();
		   this.height =moveFrames[0].getRegionHeight();
	       move = new Animation(1/16f, moveFrames);  
	       currentAnimation = move;
	       
	       projectileTex = new TextureRegion(atlas.findRegion("spear"));
	}

	public void moveTo(float x, float y) {
		newTargetTimer = newTargetTime;
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

	public void update(float delta, ArrayList<Enemy> enemies) {
		attackTimer -= delta;
		if (attackTimer <= 0) canAttack = true;
		newTargetTimer-=delta;
		if (newTargetTimer <= 0) {
			canSelectNewTarget = true;
		}
		elapsedTime += delta;
		handleMovement(delta, enemies);
	}

	private void handleMovement(float delta, ArrayList<Enemy> enemies) {
		if (hasMovementTarget) {
			position.x += dx * delta;
			position.y += dy * delta;
		}
		int targetRange = 2;
		if (Math.abs(position.x - targetX) <= targetRange && Math.abs(position.y - targetY) <= targetRange) {
			hasMovementTarget = false;
			
			if (target != null && type.isRanged() == false) {
				if (target.isDead() == false && canAttack) {
					target.damage(type.getDamage());
					canAttack = false;
					attackTimer = type.getAttackSpeed();
				}
			}
		}
		
		if (target != null) {
			if (target.isDead() == false && canAttack) {
				MobProjectileHandler.addProjectile(
						new FriendlyProjectile(position.x, position.y, target.getPosition().x, target.getPosition().y,
								100, projectileTex, enemies, type.getDamage()));
				canAttack = false;
				attackTimer = type.getAttackSpeed();
			}
		}
		
	}

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
	
	public void setTargetToClosest(ArrayList<Enemy> enemies) {
		if (canSelectNewTarget == false) return;
		if (enemies.isEmpty()) return;
		int index = 0;
		float mobDistace = 999999;
		for (int i = 0; i < enemies.size(); i++) {
			float x2 = enemies.get(i).getPosition().x;
			float y2 = enemies.get(i).getPosition().y;
			
			float newDistance = (float) Math.sqrt((position.x-x2)*(position.x-x2) + (position.y-y2)*(position.y-y2));
			
			if (newDistance < mobDistace) {
				index = i;
				mobDistace = newDistance;
			}
		}
		if (mobDistace <= type.getRange()) {
			target = enemies.get(index);
			if (type.isRanged() == false) moveTo(target.getPosition().x, target.getPosition().y);
			canSelectNewTarget = false;
			newTargetTimer = newTargetTime;
		} else {
			target = null;
		}
	}
	
	public void setSelected(boolean b) {
		selected = b;
	}
	
	public Vector2 getPosition() {
		return position;
	}

	@Override
	public void damage(int damage) {
//		FloatingTextHandler.addText("" + damage, position.x - width/2, position.y - width/2 + 10, 50, 2.5f, 
//				Color.RED, TextType.BOUNCE);
		this.health -= damage;
		if (health <= 0) {
			health = 0;
			shouldRemove = true;
		}
		
	}



}
