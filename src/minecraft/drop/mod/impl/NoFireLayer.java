package drop.mod.impl;

import drop.gui.GuiScreenDC;
import drop.mod.Mod;

public class NoFireLayer extends Mod {	
	public NoFireLayer() {
		super(false);
	}
	
	@Override
	public GuiScreenDC getGui(GuiScreenDC previousGuiScreen) {
		return null;
	}
}