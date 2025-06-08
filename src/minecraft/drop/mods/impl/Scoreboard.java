package drop.mods.impl;

import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiScoreboard;
import drop.mods.Mod;

public class Scoreboard extends Mod {
	private boolean hideNumbers = false;
	private boolean textShadow = false;
	private int backgroundOpacity = 127;
	
	public Scoreboard() {
		super(true);
		setHideNumbers(getBooleanFromFile("hideNumbers", textShadow));
		setTextShadow(getBooleanFromFile("textShadow", textShadow));
		setBackgroundOpacity(getIntFromFile("backgroundOpacity", backgroundOpacity));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiScoreboard(previousGuiScreen);
	}
	
	public void setHideNumbers(boolean enabled) {
		hideNumbers = enabled;
		
		setToFile("hideNumbers", enabled);
	}
	
	public boolean isHideNumbersToggled() {
		return hideNumbers;
	}
	
	public void setTextShadow(boolean enabled) {
		textShadow = enabled;
		
		setToFile("textShadow", enabled);
	}
	
	public boolean isTextShadowToggled() {
		return textShadow;
	}
	
	public void setBackgroundOpacity(int opacity) {
		backgroundOpacity = opacity;
		
		setToFile("backgroundOpacity", opacity);
	}
	
	public int getBackgroundOpacity() {
		return backgroundOpacity;
	}
}
