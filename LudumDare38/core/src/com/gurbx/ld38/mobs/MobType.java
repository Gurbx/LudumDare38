package com.gurbx.ld38.mobs;

import com.gurbx.ld38.utils.ProjectileType;

public enum MobType {
	SOLIDER("Solider", 20, 3, 1f, 25f, false, 80, 100, "mob", "spear" ,10, 			7.5f, 1,1,1 , ProjectileType.STANDARD),
	ARCHER("Archer", 10, 1, 1f, 25f, true, 280, 100, "mobArcher", "spear", 25,		 15f, 1,1,1, ProjectileType.STANDARD ),
	WIZZARD("Wizzard", 5, 15, 4f, 25f, true, 350, 200, "mobWizzard", "spell", 150, 	40f, 1,1,1, ProjectileType.WIZZARDSPELL ),
	WARLOCK("Warlock", 5, 50, 2f, 25f, true, 350, 200, "mobWarlock", "spell2", 300, 	60f, 1,1,1, ProjectileType.WARLOCKSPELL );
	
	private String name;
	private int health;
	private int damage;
	private float attackSpeed;
	private float movementSpeed;
	private boolean ranged;
	private int range;
	private int projectileSpeed;
	private String path;
	private String projectilePath;
	private int cost; //in pollen
	private float spawnTime;
	
	private int moveFrames;
	private int standFrames;
	private int attackFrames;
	
	private ProjectileType pType;
	
	
	private MobType(String name, int health, int damage, float attackSpeed, float movementSpeed, boolean ranged,
			int range, int projectileSpeed, String path, String projectilePath, int cost, float spawnTime,
			int moveF, int standF, int attackF, ProjectileType pType) {
		this.name = name;
		this.health = health;
		this.damage = damage;
		this.attackSpeed = attackSpeed;
		this.movementSpeed = movementSpeed;
		this.ranged = ranged;
		this.range = range;
		this.projectileSpeed = projectileSpeed;
		this.path = path;
		this.projectilePath = projectilePath;
		this.cost = cost;
		this.spawnTime = spawnTime;
		this.moveFrames = moveF;
		this.standFrames = standF;
		this.attackFrames = attackF;
		this.pType = pType;
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


	public int getCost() {
		return cost;
	}
	
	public float getSpawnTime() {
		return spawnTime;
	}


	public float getProjectileSpeed() {
		return projectileSpeed;
	}
	
	public String getProjectilePath() {
		return projectilePath;
	}
	
	public ProjectileType getProjectileType() {
		return pType;
	}
	
	
	

}
