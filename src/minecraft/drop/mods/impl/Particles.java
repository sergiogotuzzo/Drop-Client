package drop.mods.impl;

import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiParticles;
import drop.mods.Mod;

public class Particles extends Mod {
	private boolean affectCriticals = true;
	private boolean affectSharpness = true;
	private boolean alwaysSharpness = false;
	private int multiplierFactor = 1;
	
	public Particles() {
		super(false);
		
		setAffectCriticals(getBooleanFromFile("affectCriticals", affectCriticals));
		setAffectSharpness(getBooleanFromFile("affectSharpness", affectSharpness));
		setAlwaysSharpness(getBooleanFromFile("alwaysSharpness", alwaysSharpness));
		setMultiplierFactor(getIntFromFile("multiplierFactor", multiplierFactor));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiParticles(previousGuiScreen);
	}
	
	public void setAffectCriticals(boolean toggled) {
		this.affectCriticals = toggled;
		
		setToFile("affectCriticals", toggled);
	}
	
	public boolean isAffectCriticalsToggled() {
		return affectCriticals;
	}
	
	public void setAffectSharpness(boolean toggled) {
		this.affectSharpness = toggled;
		
		setToFile("affectSharpness", toggled);
	}
	
	public boolean isAffectSharpnessToggled() {
		return affectSharpness;
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
