package com.gurbx.ld38.mobs;

public enum MobType {
	SOLIDER("Mob", 10, 1, 1f, 25f, false, 10, "mob", 1,1,1 );
	
	private String name;
	private int health;
	private int damage;
	private float attackSpeed;
	private float movementSpeed;
	private boolean ranged;
	private int range;
	private String path;
	
	private int moveFrames;
	private int standFrames;
	private int attackFrames;
	
	
	private MobType(String name, int health, int damage, float attackSpeed, float movementSpeed, boolean ranged,
			int range, String path,
			int moveF, int standF, int attackF) {
		this.name = name;
		this.health = health;
		this.damage = damage;
		this.attackSpeed = attackSpeed;
		this.movementSpeed = movementSpeed;
		this.ranged = ranged;
		this.range = range;
		this.path = path;
		this.moveFrames = moveF;
		this.standFrames = standF;
		this.attackFrames = attackF;
	}


	public String getName() {
		return name;
	}


	public int getHealth() {
		return health;
	}


	public int getDamage() {
		return damage;
	}


	public float getAttackSpeed() {
		return attackSpeed;
	}


	public float getMovementSpeed() {
		return movementSpeed;
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
		return moveFrames;
	}


	public int getStandFrames() {
		return standFrames;
	}


	public int getAttackFrames() {
		return attackFrames;
	}
	
	
	

}
