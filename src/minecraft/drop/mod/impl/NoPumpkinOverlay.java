package drop.mod.impl;

import drop.gui.GuiBlurredScreen;
import drop.mod.Mod;

public class NoPumpkinOverlay extends Mod {	
	public NoPumpkinOverlay() {
		super(false);
	}
	
	@Override
	public GuiBlurredScreen getGui(GuiBlurredScreen previousGuiScreen) {
		return null;
	}
}