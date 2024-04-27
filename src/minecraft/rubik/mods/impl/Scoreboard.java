package rubik.mods.impl;

import rubik.mods.Mod;

public class Scoreboard extends Mod {
	private boolean hideNumbers = false;
	private boolean textShadow = false;
	private boolean showBackground = true;
	
	public void setHideNumbers(boolean enabled) {
		hideNumbers = enabled;
	}
	
	public boolean isHideNumbersEnabled() {
		return hideNumbers;
	}
	
	public void setTextShadow(boolean enabled) {
		textShadow = enabled;
	}
	
	public boolean isTextShadowEnabled() {
		return textShadow;
	}
	
	public void setShowBackground(boolean enabled) {
		showBackground = enabled;
	}
	
	public boolean isShowBackgroundEnabled() {
		return showBackground;
	}
}
