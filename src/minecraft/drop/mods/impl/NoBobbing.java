package drop.mods.impl;

import drop.gui.GuiDropClientScreen;
import drop.mods.Mod;

public class NoBobbing extends Mod {	
	public NoBobbing() {
		super(true);
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return null;
	}
}