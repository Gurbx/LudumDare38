package com.gurbx.ld38.waves;

public enum EnemyType {
	BUG (15, 1, 25, 1, false, false, 15, "bug", 4),
	MINI_BUG (5, 1, 35, 1, false, false, 15, "miniBug", 1),
	BIGGER_BUG (25, 2, 15, 1, false, false, 15, "biggerBug", 4),
	MEGA_BUG (100, 10, 15, 1, false, false, 15, "megaBug", 4);
	
	private int health;
	private int damage;
	private float movementSpeed;
	private float attackCooldown;
	private boolean ranged;
	private boolean attacksOnlyHouses;
	private int range;
	private String path;
	private int frames;
	
	private EnemyType(int health, int damage, float movementSpeed, float attackCooldown, boolean ranged, boolean onlyHouses, int range,
			String path, int frames) {
		this.health = health;
		this.damage = damage;
		this.movementSpeed = movementSpeed;
		this.attackCooldown = attackCooldown;
		this.ranged = ranged;
		this.attacksOnlyHouses = onlyHouses;
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
	
	public boolean onlyAttacksHouses() {
		return attacksOnlyHouses;
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
