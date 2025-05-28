package drop.mods.impl;

import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiTimeChanger;
import drop.mods.Mod;

public class TimeChanger extends Mod {
	private float time = 0.5F;
	
	public TimeChanger() {
		setTime((float) ((double) getFromFile("time", time)));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiTimeChanger(previousGuiScreen);
	}
	
	public void setTime(float time) {
		this.time = time;
		
		setToFile("time", time);
	}
	
	public float getTime() {
		return time;
	}
}
