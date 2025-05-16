package rubik.mods.impl;

import java.awt.Color;

import rubik.ColorManager;
import rubik.mods.Mod;

public class BlockOverlay extends Mod {
	private boolean outline = true;
	private float outlineWidth = 2.0F;
	private ColorManager outlineColor = ColorManager.fromColor(Color.BLACK);
	private boolean outlineChroma = false;
	private boolean overlay = false;
	private ColorManager overlayColor = ColorManager.fromColor(Color.WHITE).setAlpha(50);
	private boolean overlayChroma = true;
	
	public BlockOverlay() {
		setOutline((boolean) getFromFile("outline", outline));
		setOutlineWidth((float) ((double) getFromFile("outlineWidth", outlineWidth)));
		setOutlineColor((int) ((long) getFromFile("outlineColor", outlineColor.getRGB())));
		setOutlineChroma((boolean) getFromFile("outlineChroma", outlineChroma));
		setOverlay((boolean) getFromFile("overlay", overlay));
		setOverlayColor((int) ((long) getFromFile("overlayColor", overlayColor.getRGB())));
		setOverlayChroma((boolean) getFromFile("overlayChroma", overlayChroma));
	}
	
	public void setOutline(boolean enabled) {
		this.outline = enabled;
		
		setToFile("outline", enabled);
	}
	
	public boolean isOutlineEnabled() {
		return outline;
	}
	
	public void setOutlineWidth(float width) {
		this.outlineWidth = width;
		
		setToFile("outlineWidth", width);
	}
	
	public float getOutlineWidth() {
		return outlineWidth;
	}
	
	public void setOutlineColor(int rgb) {
		this.outlineColor = ColorManager.fromRGB(rgb);
		
		setToFile("outlineColor", rgb);
	}
	
	public ColorManager getOutlineColor() {
		return outlineColor;
	}
	
	public void setOutlineChroma(boolean enabled) {
		this.outlineChroma = enabled;
		
		setToFile("outlineChroma", enabled);
	}
	
	public boolean isOutlineChromaEnabled() {
		return outlineChroma;
	}
	
	public void setOverlay(boolean enabled) {
		this.overlay = enabled;
		
		setToFile("overlay", enabled);
	}
	
	public boolean isOverlayEnabled() {
		return overlay;
	}
	
	public void setOverlayColor(int rgb) {
		this.overlayColor = ColorManager.fromRGB(rgb);
		
		setToFile("overlayColor", rgb);
	}
	
	public ColorManager getOverlayColor() {
		return overlayColor;
	}
	
	public void setOverlayChroma(boolean enabled) {
		this.overlayChroma = enabled;
		
		setToFile("overlayChroma", enabled);
	}
	
	public boolean isOverlayChromaEnabled() {
		return overlayChroma;
	}
}
