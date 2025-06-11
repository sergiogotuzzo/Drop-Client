package drop.mods.impl;

import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiParticles;
import drop.mods.Mod;

public class Particles extends Mod {
	private boolean showCriticals = true;
	private boolean alwaysSharpness = false;
	private int multiplierFactor = 1;
	
	public Particles() {
		super(false);
		
		setShowCriticals(getBooleanFromFile("showCriticals", showCriticals));
		setAlwaysSharpness(getBooleanFromFile("alwaysSharpness", alwaysSharpness));
		setMultiplierFactor(getIntFromFile("multiplierFactor", multiplierFactor));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiParticles(previousGuiScreen);
	}
	
	public void setShowCriticals(boolean toggled) {
		this.showCriticals = toggled;
		
		setToFile("showCriticals", toggled);
	}
	
	public boolean isShowCriticalsToggled() {
		return showCriticals;
	}
	
	public void setAlwaysSharpness(boolean toggled) {
		this.alwaysSharpness = toggled;
		
		setToFile("alwaysSharpness", toggled);
	}
	
	public boolean isAlwaysSharpnessToggled() {
		return alwaysSharpness;
	}
	
	public void setMultiplierFactor(int factor) {
		this.multiplierFactor = factor;
		
		setToFile("multiplierFactor", factor);
	}
	
	public int getMultiplierFactor() {
		return multiplierFactor;
	}
}
