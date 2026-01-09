package drop.mod.impl;

import drop.gui.GuiScreenDC;
import drop.mod.Mod;

public class NoHotbarScrolling extends Mod {	
	public NoHotbarScrolling() {
		super(false);
	}
	
	@Override
	public GuiScreenDC getGui(GuiScreenDC previousGuiScreen) {
		return null;
	}
}