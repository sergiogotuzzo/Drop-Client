package drop.mods.impl;

import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiZoom;
import drop.mods.Mod;

public class Zoom extends Mod {
	private boolean scrollToZoom = true;
	private boolean smoothCamera = true;
	
	public Zoom() {
		super(true);
		
		setScrollToZoom((boolean) getFromFile("scrollToZoom", scrollToZoom));
		setSmoothCamera((boolean) getFromFile("smoothCamera", smoothCamera));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiZoom(previousGuiScreen);
	}
	
	public void setScrollToZoom(boolean toggled) {
		this.scrollToZoom = toggled;
		
		setToFile("scrollToZoom", toggled);
	}
	
	public boolean isScrollToZoomToggled() {
		return scrollToZoom;
	}
	
	public void setSmoothCamera(boolean toggled) {
		this.smoothCamera = toggled;
		
		setToFile("smoothCamera", toggled);
	}
	
	public boolean isSmoothCameraToggled() {
		return smoothCamera;
	}
}
