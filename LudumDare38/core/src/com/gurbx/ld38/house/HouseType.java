package com.gurbx.ld38.house;

public enum HouseType {
	BASIC("basic", 5f, 20, 0, 0, "1" );
	
	private String name;
	private float buildTime;
	private int health;
	private int damage;
	private int attackSpeed;
	private String path;
	
	private HouseType(String name, float buildTime, int health, int damage, int attackSpeed, String path) {
		this.name = name;
		this.buildTime = buildTime;
		this.health = health;
		this.damage = damage;
		this.attackSpeed = attackSpeed;
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public float getBuildTime() {
		return buildTime;
	}

	public int getHealth() {
		return health;
	}

	public int getDamage() {
		return damage;
	}

	public int getAttackSpeed() {
		return attackSpeed;
	}

	public String getPath() {
		return path;
	}
	
	
	
	

}
