package drop.mod.impl;

import drop.gui.GuiDropClientScreen;
import drop.mod.Mod;

public class NoHotbarScrolling extends Mod {	
	public NoHotbarScrolling() {
		super(false);
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return null;
	}
}