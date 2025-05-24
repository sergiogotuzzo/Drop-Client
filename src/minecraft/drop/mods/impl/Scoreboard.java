package drop.mods.impl;

import drop.mods.Mod;

public class Scoreboard extends Mod {
	private boolean hideNumbers = false;
	private boolean textShadow = false;
	private int backgroundOpacity = 127;
	
	public Scoreboard() {
		setHideNumbers((boolean) getFromFile("hideNumbers", textShadow));
		setTextShadow((boolean) getFromFile("textShadow", textShadow));
		setBackgroundOpacity((int) ((long) getFromFile("backgroundOpacity", backgroundOpacity)));
	}
	
	public void setHideNumbers(boolean enabled) {
		hideNumbers = enabled;
		
		setToFile("hideNumbers", enabled);
	}
	
	public boolean isHideNumbersEnabled() {
		return hideNumbers;
	}
	
	public void setTextShadow(boolean enabled) {
		textShadow = enabled;
		
		setToFile("textShadow", enabled);
	}
	
	public boolean isTextShadowEnabled() {
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
