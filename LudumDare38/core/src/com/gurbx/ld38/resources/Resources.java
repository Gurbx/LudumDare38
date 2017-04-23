package com.gurbx.ld38.resources;

public class Resources {
	private int maxUnits;
	private int maxResin;
	private int maxPollen;
	private int pollen;
	private int resin;
	private int units;
	
	public Resources(int resin, int pollen) {
		this.resin = resin;
		this.pollen = pollen;
		this.maxPollen = 100;
		this.maxResin = 100;
		this.maxUnits = 10;
		this.units = 0;
	}

	public void addPollen(int amount) {
		this.pollen += amount;
		if (pollen > maxPollen) {
			pollen = maxPollen;
		}
	}

	public void addResin(int amount) {
		this.resin += amount;
		if (resin > maxResin) {
			resin = maxResin;
		}
	}
	
	public int getResin() {
		return resin;
	}
	
	public int getPollen() {
		return pollen;
	}
	
	public boolean removeResin(int amount) {
		if (amount > resin) return false;
		resin -= amount;
		return true;
	}
	
	public boolean removePollen(int amount) {
		if (amount > pollen) return false;
		pollen -= amount;
		return true;
	}

	public void increaseMaxResin(int ammountIncreased) {
		maxResin += ammountIncreased;
	}
	
	public void increaseMaxPollen(int ammountIncreased) {
		maxPollen += ammountIncreased;
	}
	
	public void decreaseMaxResin(int ammount) {
		maxResin -= ammount;
		if (resin > maxResin) resin = maxResin;
	}
	
	public void decreaseMaxPollen(int ammount) {
		maxPollen -= ammount;
		if (pollen < maxPollen) pollen = maxPollen;
	}
	
	public int getMaxResin() {
		return maxResin;
	}
	
	public int getMaxPollen() {
		return maxPollen;
	}
	
	public int getMaxUnits() {
		return maxUnits;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int size) {
		this.units = size;
	}

	public boolean canBuyUnit() {
		if (units < maxUnits) return true;
		return false;
	}

	public void increaseMobSize(int ammountIncreased) {
		this.maxUnits += ammountIncreased;
		
	}

	public void decreaseMobSize(int ammountIncreased) {
		this.maxUnits -= ammountIncreased;
		
	}

}
