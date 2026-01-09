package drop.mod.impl;

import drop.gui.GuiScreenDC;
import drop.mod.Mod;

public class NoPumpkinOverlay extends Mod {	
	public NoPumpkinOverlay() {
		super(false);
	}
	
	@Override
	public GuiScreenDC getGui(GuiScreenDC previousGuiScreen) {
		return null;
	}
}