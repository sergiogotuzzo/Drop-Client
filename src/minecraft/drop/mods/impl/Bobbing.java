package drop.mods.impl;

import drop.mods.Mod;

public class Bobbing extends Mod {
	private boolean minimalViewBobbing = true;
	
	public Bobbing() {
		setMinimalViewBobbing((boolean) getFromFile("minimalViewBobbing", minimalViewBobbing));
	}
	
	public void setMinimalViewBobbing(boolean toggled) {
		this.minimalViewBobbing = toggled;
		
		setToFile("minimalViewBobbing", minimalViewBobbing);
	}
	
	public boolean isMinimalViewBobbingToggled() {
		return minimalViewBobbing;
	}
}
