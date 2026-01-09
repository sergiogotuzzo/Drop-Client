package drop.mod.impl;

import drop.gui.GuiScreenDC;
import drop.mod.Mod;

public class NoBobbing extends Mod {	
	public NoBobbing() {
		super(false);
	}
	
	@Override
	public GuiScreenDC getGui(GuiScreenDC previousGuiScreen) {
		return null;
	}
}