package rubik.mods.impl;

import rubik.mods.Mod;

public class Fullbright extends Mod {
	private float fullbrightGamma = 10.0F;
	private float defaultGamma = 1.0F;
	
	public Fullbright() {
		if (isEnabled()) {
			mc.gameSettings.gammaSetting = fullbrightGamma;
		} else {
			mc.gameSettings.gammaSetting = defaultGamma;
		}
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		
		if (enabled) {
			mc.gameSettings.gammaSetting = fullbrightGamma;
		} else {
			mc.gameSettings.gammaSetting = defaultGamma;
		}
	}
}
