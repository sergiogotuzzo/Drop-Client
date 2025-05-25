package drop.mods.impl;

import drop.mods.Mod;

public class Nametags extends Mod {
	private boolean showInThirdPerson = true;
	
	public Nametags() {
		setShowInThirdPerson((boolean) getFromFile("showInThirdPerson", showInThirdPerson));
	}
	
	public void setShowInThirdPerson(boolean toggled) {
		this.showInThirdPerson = toggled;
		
		setToFile("showInThirdPerson", toggled);
	}
	
	public boolean isShowInThirdPersonToggled() {
		return showInThirdPerson;
	}
}
