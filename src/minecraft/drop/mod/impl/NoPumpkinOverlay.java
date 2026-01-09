package drop.mod.impl;

import drop.gui.GuiDropClientScreen;
import drop.mod.Mod;

public class NoPumpkinOverlay extends Mod {	
	public NoPumpkinOverlay() {
		super(false);
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return null;
	}
}