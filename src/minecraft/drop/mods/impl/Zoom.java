package drop.mods.impl;

import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiZoom;
import drop.mods.Mod;

public class Zoom extends Mod {
	private boolean scrollToZoom = true;
	private boolean smoothCamera = true;
	private int zoomLevel = 4;
	private int zoomLevelMin = 4;
	private int zoomLevelMax = 16;

	public Zoom() {
		super(true);
		
		setScrollToZoom(getBooleanFromFile("scrollToZoom", scrollToZoom));
		setSmoothCamera(getBooleanFromFile("smoothCamera", smoothCamera));
		setZoomLevel(getIntFromFile("zoomLevel", zoomLevel));
		setZoomLevelMin(getIntFromFile("zoomLevelMin", zoomLevelMin));
		setZoomLevelMax(getIntFromFile("zoomLevelMax", zoomLevelMax));
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
	
	public void setZoomLevel(int level) {
		zoomLevel = level;
		
		setToFile("zoomLevel", level);
	}
	
	public int getZoomLevel() {
		return zoomLevel;
	}
	
	public void setZoomLevelMin(int level) {
		zoomLevelMin = level;
		
		setToFile("zoomLevelMin", level);
	}
	
	public int getZoomLevelMin() {
		return zoomLevelMin;
	}
	
	public void setZoomLevelMax(int level) {
		zoomLevelMax = level;
		
		setToFile("zoomLevelMax", level);
	}
	
	public int getZoomLevelMax() {
		return zoomLevelMax;
	}
}
