package drop.mods.impl;

import drop.gui.GuiDropClientScreen;
import drop.mods.Mod;

public class NoAchievementNotifications extends Mod {	
	public NoAchievementNotifications() {
		super(true);
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return null;
	}
}