package drop.mod.impl;

import drop.gui.GuiScreenDC;
import drop.mod.Mod;

public class NoAchievementNotifications extends Mod {	
	public NoAchievementNotifications() {
		super(true);
	}
	
	@Override
	public GuiScreenDC getGui(GuiScreenDC previousGuiScreen) {
		return null;
	}
}