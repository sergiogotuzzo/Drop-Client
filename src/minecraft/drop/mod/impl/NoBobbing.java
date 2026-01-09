package drop.mod.impl;

import drop.gui.GuiBlurredScreen;
import drop.mod.Mod;

public class NoBobbing extends Mod {	
	public NoBobbing() {
		super(false);
	}
	
	@Override
	public GuiBlurredScreen getGui(GuiBlurredScreen previousGuiScreen) {
		return null;
	}
}