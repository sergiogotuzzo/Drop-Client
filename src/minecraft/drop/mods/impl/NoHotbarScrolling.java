package drop.mods.impl;

import drop.gui.GuiDropClientScreen;
import drop.mods.Mod;

public class NoHotbarScrolling extends Mod {	
	public NoHotbarScrolling() {
		super(false);
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return null;
	}
}