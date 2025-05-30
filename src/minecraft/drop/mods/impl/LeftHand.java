package drop.mods.impl;

import drop.mods.Mod;

public class LeftHand extends Mod {
	private boolean enabled = false;
	
	public LeftHand() {
		setEnabled((boolean) getFromFile("enabled", enabled));
	}
}