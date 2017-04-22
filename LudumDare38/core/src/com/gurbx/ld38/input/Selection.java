package com.gurbx.ld38.input;

import java.util.ArrayList;
import java.util.HashMap;

import com.gurbx.ld38.mobs.Mob;

public class Selection {
	private float x, y;
	private ArrayList<Mob> selectedMobs;
//	private HashMap<Mob, Integer> selectedTypes;
	
	public Selection(float x, float y, float selectionWidth, float selectionHeight, ArrayList<Mob> selectedMobs) {
		this.selectedMobs = selectedMobs;
//		selectedTypes = new HashMap<Mob, Integer>();
		
		for (int i = 0; i < selectedMobs.size(); i++) {
//			selectedMobs.get(i)
			
		}
	}

}
