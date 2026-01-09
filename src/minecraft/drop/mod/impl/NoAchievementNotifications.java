package drop.mod.impl;

import drop.gui.GuiDropClientScreen;
import drop.mod.Mod;

public class NoAchievementNotifications extends Mod {	
	public NoAchievementNotifications() {
		super(true);
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return null;
	}
}