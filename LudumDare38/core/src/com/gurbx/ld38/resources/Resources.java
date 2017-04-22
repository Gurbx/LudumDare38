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

}
