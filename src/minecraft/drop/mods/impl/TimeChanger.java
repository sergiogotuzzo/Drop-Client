package drop.mods.impl;

import java.time.LocalTime;

import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiTimeChanger;
import drop.mods.Mod;

public class TimeChanger extends Mod {
	private float time = 0.0F;
	private boolean useRealCurrentTime = false;
	
	public TimeChanger() {
		setTime((float) ((double) getFromFile("time", time)));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiTimeChanger(previousGuiScreen);
	}
	
	public static float getRealCurrentTimeInMinecraftTime() {
		LocalTime now = LocalTime.now();
	    float totalHours = now.getHour() + (now.getMinute() / 60f);
	    
	    return ((totalHours - 12f + 24f) % 24f) / 24f;
	}
	
	public void setTime(float time) {
		this.time = time;
		
		setToFile("time", time);
	}
	
	public float getTime() {
		return time;
	}
	
	public void setUseRealCurrentTime(boolean toggled) {
		this.useRealCurrentTime = toggled;
		
		setToFile("useRealCurrentTime", toggled);
	}
	
	public boolean isUseRealCurrentTimeToggled() {
		return useRealCurrentTime;
	}
}
