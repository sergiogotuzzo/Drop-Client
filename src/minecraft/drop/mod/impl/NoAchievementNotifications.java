package drop.mod.impl;

import drop.gui.GuiBlurredScreen;
import drop.mod.Mod;

public class NoAchievementNotifications extends Mod {	
	public NoAchievementNotifications() {
		super(true);
	}
	
	@Override
	public GuiBlurredScreen getGui(GuiBlurredScreen previousGuiScreen) {
		return null;
	}
}