package drop.mods.impl;

import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiNametags;
import drop.mods.Mod;

public class Nametags extends Mod {
	private boolean showInThirdPerson = true;
	
	public Nametags() {
		super(false);
		
		setShowInThirdPerson((boolean) getFromFile("showInThirdPerson", showInThirdPerson));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiNametags(previousGuiScreen);
	}
	
	public void setShowInThirdPerson(boolean toggled) {
		this.showInThirdPerson = toggled;
		
		setToFile("showInThirdPerson", toggled);
	}
	
	public boolean isShowInThirdPersonToggled() {
		return showInThirdPerson;
	}
}
