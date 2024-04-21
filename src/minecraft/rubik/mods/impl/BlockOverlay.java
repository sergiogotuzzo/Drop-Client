package rubik.mods.impl;

import java.awt.Color;

import rubik.ColorManager;
import rubik.mods.Mod;

public class BlockOverlay extends Mod {
	private ColorManager color = new ColorManager(new Color(53, 66, 57));
	private boolean fill = true;
	private float outline = 5.0F;
	
	public ColorManager getColorManager() {
		return color;
	}
	
	public Color getColor() {
		return color.getColor();
	}
	
	public void setFillEnabled(boolean enabled) {
		this.fill = enabled;
	}
	
	public boolean isFillEnabled() {
		return fill;
	}
	
	public void setOutline(float outlineWidth) {
		this.outline = outlineWidth;
	}
	
	public float getOutline() {
		return outline;
	}
}
