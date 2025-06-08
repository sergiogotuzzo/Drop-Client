package drop.mods.impl;

import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiBobbing;
import drop.mods.Mod;

public class Bobbing extends Mod {
	private boolean minimalViewBobbing = true;
	
	public Bobbing() {
		super(true);
		
		setMinimalViewBobbing(getBooleanFromFile("minimalViewBobbing", minimalViewBobbing));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiBobbing(previousGuiScreen);
	}
	
	public void setMinimalViewBobbing(boolean toggled) {
		this.minimalViewBobbing = toggled;
		
		setToFile("minimalViewBobbing", minimalViewBobbing);
	}
	
	public boolean isMinimalViewBobbingToggled() {
		return minimalViewBobbing;
	}
}