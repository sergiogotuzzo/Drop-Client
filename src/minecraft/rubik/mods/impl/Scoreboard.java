package rubik.mods.impl;

import rubik.mods.Mod;

public class Scoreboard extends Mod {
	private boolean hideNumbers = false;
	private boolean textShadow = false;
	
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
}
