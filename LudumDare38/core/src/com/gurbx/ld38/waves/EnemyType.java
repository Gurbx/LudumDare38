package com.gurbx.ld38.waves;

public enum EnemyType {
	BUG (5, 1, 20, 1, false, 15, "bug", 4);
	
	private int health;
	private int damage;
	private float movementSpeed;
	private float attackCooldown;
	private boolean ranged;
	private int range;
	private String path;
	private int frames;
	
	private EnemyType(int health, int damage, float movementSpeed, float attackCooldown, boolean ranged, int range,
			String path, int frames) {
		this.health = health;
		this.damage = damage;
		this.movementSpeed = movementSpeed;
		this.attackCooldown = attackCooldown;
		this.ranged = ranged;
		this.range = range;
		this.path = path;
		this.frames = frames;
	}

	public int getHealth() {
		return health;
	}

	public int getDamage() {
		return damage;
	}

	public float getMovementSpeed() {
		return movementSpeed;
	}

	public float getAttackCooldown() {
		return attackCooldown;
	}

	public boolean isRanged() {
		return ranged;
	}

	public int getRange() {
		return range;
	}

	public String getPath() {
		return path;
	}

	public int getMoveFrames() {
		return frames;
	}
	
	
	
	

}
