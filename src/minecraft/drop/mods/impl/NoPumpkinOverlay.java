package drop.mods.impl;

import drop.gui.GuiDropClientScreen;
import drop.mods.Mod;

public class NoPumpkinOverlay extends Mod {	
	public NoPumpkinOverlay() {
		super(false);
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return null;
	}
}