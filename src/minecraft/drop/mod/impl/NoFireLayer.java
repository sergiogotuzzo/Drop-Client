package drop.mod.impl;

import drop.gui.GuiDropClientScreen;
import drop.mod.Mod;

public class NoFireLayer extends Mod {	
	public NoFireLayer() {
		super(false);
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return null;
	}
}