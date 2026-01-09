package drop.mod.impl;

import drop.gui.GuiBlurredScreen;
import drop.mod.Mod;

public class NoHotbarScrolling extends Mod {	
	public NoHotbarScrolling() {
		super(false);
	}
	
	@Override
	public GuiBlurredScreen getGui(GuiBlurredScreen previousGuiScreen) {
		return null;
	}
}