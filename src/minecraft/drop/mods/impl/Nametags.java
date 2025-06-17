package drop.mods.impl;

import drop.ColorManager;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiNametags;
import drop.mods.Mod;

public class Nametags extends Mod {
	private boolean showInThirdPerson = true;
	private boolean textShadow = false;
	private ColorManager backgroundColor = ColorManager.fromRGB(0, 0, 0, 64, false);
	
	public Nametags() {
		super(false);
		
		setShowInThirdPerson(getBooleanFromFile("showInThirdPerson", showInThirdPerson));
		setTextShadow(getBooleanFromFile("textShadow", textShadow));
		setBackgroundColor(getIntFromFile("backgroundColor", backgroundColor.getRGB()));
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
	
	public void setTextShadow(boolean toggled) {
		textShadow = toggled;
		
		setToFile("textShadow", toggled);
	}
	
	public boolean isTextShadowToggled() {
		return textShadow;
	}
	
	public void setBackgroundColor(int rgb) {
		backgroundColor = backgroundColor.fromRGB(rgb, false);
		
		setToFile("backgroundColor", rgb);
	}
	
	public ColorManager getBackgroundColor() {
		return backgroundColor;
	}
}
