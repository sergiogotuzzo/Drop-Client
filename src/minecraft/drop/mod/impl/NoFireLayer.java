package drop.mod.impl;

import drop.gui.GuiBlurredScreen;
import drop.mod.Mod;

public class NoFireLayer extends Mod {	
	public NoFireLayer() {
		super(false);
	}
	
	@Override
	public GuiBlurredScreen getGui(GuiBlurredScreen previousGuiScreen) {
		return null;
	}
}