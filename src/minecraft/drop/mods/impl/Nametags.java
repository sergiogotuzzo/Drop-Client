package drop.mods.impl;

import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiNametags;
import drop.mods.Mod;

public class Nametags extends Mod {
	private boolean showInThirdPerson = true;
	private boolean textShadow = false;
	private int backgroundOpacity = 64;
	
	public Nametags() {
		super(false);
		
		setShowInThirdPerson(getBooleanFromFile("showInThirdPerson", showInThirdPerson));
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
	
	public void setTextShadow(boolean enabled) {
		textShadow = enabled;
		
		setToFile("textShadow", enabled);
	}
	
	public boolean isTextShadowToggled() {
		return textShadow;
	}
	
	public void setBackgroundOpacity(int opacity) {
		backgroundOpacity = opacity;
		
		setToFile("backgroundOpacity", opacity);
	}
	
	public int getBackgroundOpacity() {
		return backgroundOpacity;
	}
}
