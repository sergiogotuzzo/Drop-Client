package rubik.mods.impl;

import rubik.mods.Mod;

public class TabOverlay extends Mod {
	private boolean showPlayerHeads = true;
	
	public TabOverlay() {
		setShowPlayerHeads((boolean) getFromFile("showPlayerHeads", showPlayerHeads));
	}

	public void setShowPlayerHeads(boolean enabled) {
		this.showPlayerHeads = enabled;
		
		setToFile("showPlayerHeads", showPlayerHeads);
	}
	
	public boolean isShowPlayerHeadsEnabled() {
		return showPlayerHeads;
	}
}
