package rubik.mods.impl;

import rubik.mods.Mod;

public class Scoreboard extends Mod {
	private boolean hideNumbers = false;
	private boolean textShadow = false;
	private boolean showBackground = true;
	
	public Scoreboard() {
		setHideNumbers((boolean) getFromFile("hideNumbers", textShadow));
		setTextShadow((boolean) getFromFile("textShadow", textShadow));
		setShowBackground((boolean) getFromFile("showBackground", showBackground));
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
	
	public void setShowBackground(boolean enabled) {
		showBackground = enabled;
		
		setToFile("showBackground", enabled);
	}
	
	public boolean isShowBackgroundEnabled() {
		return showBackground;
	}
}
