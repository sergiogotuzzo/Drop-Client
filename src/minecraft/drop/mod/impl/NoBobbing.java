package drop.mod.impl;

import drop.gui.GuiDropClientScreen;
import drop.mod.Mod;

public class NoBobbing extends Mod {	
	public NoBobbing() {
		super(false);
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return null;
	}
}