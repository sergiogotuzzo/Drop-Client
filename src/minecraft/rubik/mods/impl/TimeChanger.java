package rubik.mods.impl;

import rubik.mods.Mod;

public class TimeChanger extends Mod {
	private float time = 0.5F;
	
	public TimeChanger() {
		setTime((float) ((double) getFromFile("time", time)));
	}
	
	public void setTime(float time) {
		this.time = time;
		
		setToFile("time", time);
	}
	
	public float getTime() {
		return time;
	}
}
