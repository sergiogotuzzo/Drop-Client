package rubik.mods.impl;

import rubik.mods.Mod;

public class Chat extends Mod {
	private boolean transparentBackground = true;
	
	public void setTransparentBackground(boolean enabled) {
		transparentBackground = enabled;
	}
	
	public boolean isTransparentBackgroundEnabled() {
		return transparentBackground;
	}
}
