package rubik.events.impl;

import rubik.events.Event;
import rubik.gui.GuiRubikClientScreen;

public class ClickEvent extends Event {
	private final GuiRubikClientScreen settingsGui;
	
	public ClickEvent(GuiRubikClientScreen settingsGui) {
		this.settingsGui = settingsGui;
	}
	
	public GuiRubikClientScreen getSettingsGui() {
		return settingsGui;
	}
}
