package rubik.mods.impl;

import rubik.mods.Mod;

public class Chat extends Mod {
	private boolean textShadow = true;
	private boolean transparentBackground = false;
	
	public void setTextShadow(boolean enabled) {
		textShadow = enabled;
	}
	
	public boolean isTextShadowEnabled() {
		return textShadow;
	}
	
	public void setTransparentBackground(boolean enabled) {
		transparentBackground = enabled;
	}
	
	public boolean isTransparentBackgroundEnabled() {
		return transparentBackground;
	}
}
