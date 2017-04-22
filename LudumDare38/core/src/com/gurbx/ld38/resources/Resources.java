package com.gurbx.ld38.resources;

public class Resources {
	private int pollen;
	private int resin;
	
	public Resources(int resin, int pollen) {
		this.resin = resin;
		this.pollen = pollen;
	}

	public void addPollen(int amount) {
		this.pollen += amount;
	}

	public void addResin(int amount) {
		this.resin += amount;
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

}
