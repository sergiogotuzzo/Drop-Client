package drop.mods.impl;

import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiBossbar;
import drop.mods.Mod;

public class Bossbar extends Mod {
	private boolean showName = true;
	private boolean showHealth = true;
	
	public Bossbar() {
		setShowName((boolean) getFromFile("showName", showName));
		setShowHealth((boolean) getFromFile("showHealth", showHealth));
	}
	
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiBossbar(previousGuiScreen);
	}
	
	public void setShowName(boolean toggled) {
		this.showName = toggled;
		
		setToFile("showName", toggled);
	}
	
	public boolean isShowNameToggled() {
		return showName;
	}
	
	public void setShowHealth(boolean toggled) {
		this.showHealth = toggled;
		
		setToFile("showHealth", toggled);
	}
	
	public boolean isShowHealthToggled() {
		return showHealth;
	}
}
