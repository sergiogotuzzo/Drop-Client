package rubik.mods.impl;

import rubik.mods.Mod;

public class Fullbright extends Mod {
	public Fullbright() {
		if (isEnabled()) {
			mc.gameSettings.gammaSetting = 10.0F;
		} else {
			mc.gameSettings.gammaSetting = 1.0F;
		}
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		
		if (enabled) {
			mc.gameSettings.gammaSetting = 10.0F;
		} else {
			mc.gameSettings.gammaSetting = 1.0F;
		}
	}
}
