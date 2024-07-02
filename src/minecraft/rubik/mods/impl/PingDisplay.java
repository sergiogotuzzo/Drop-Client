package rubik.mods.impl;

import java.awt.Color;

import rubik.ColorManager;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggable;

public class PingDisplay extends ModDraggable {
	private boolean showBackground = false;
	private boolean textShadow = true;
	private ColorManager textColor = ColorManager.fromColor(Color.WHITE);
	private ColorManager backgroundColor = ColorManager.fromColor(Color.BLACK).setAlpha(102);
	private boolean textChroma = false;
	private boolean dynamicColors = true;
	
	public PingDisplay() {
		setShowBackground((boolean) getFromFile("showBackground", showBackground));
		setTextShadow((boolean) getFromFile("textShadow", textShadow));
		setTextColor((int) ((long) getFromFile("textColor", textColor.getRGB())));
		setBackgroundColor((int) ((long) getFromFile("backgroundColor", backgroundColor.getRGB())));
		setTextChroma((boolean) getFromFile("textChroma", textChroma));
		setDynamicColors((boolean) getFromFile("dynamicColors", dynamicColors));
	}
	
	@Override
	public int getWidth() {
		return 58;
	}

	@Override
	public int getHeight() {
		return 18;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (showBackground) {
			drawRect(
					pos.getAbsoluteX(),
					pos.getAbsoluteY(),
					pos.getAbsoluteX() + getWidth(),
					pos.getAbsoluteY() + getHeight(),
					backgroundColor.getRGB()
					);
		}
		
		Color dynamicColor = Color.WHITE;
		
		if (dynamicColors) {
			int ping = getPing();
			
			if (ping > 300) {
				dynamicColor = new Color(170, 0, 0);
			} else if (ping > 200) {
				dynamicColor = new Color(255, 85, 85);
			} else if (ping > 150) {
				dynamicColor = new Color(255, 170, 0);
			} else if (ping > 100) {
				dynamicColor = new Color(255, 255, 85);
			} else if (ping > 50) {
				dynamicColor = new Color(85, 255, 85);
			}
		}
		
		drawCenteredText(getPingText(), pos.getAbsoluteX(), pos.getAbsoluteY(), dynamicColors ? dynamicColor.getRGB() : textColor.getRGB(), textShadow, textChroma && !dynamicColors);
	}
	
	private String getPingText() {
		String pingText = getPing() + " ms";
		
		return showBackground ? pingText : "[" + pingText + "]";
	}
	
	private int getPing() {
		return mc.isSingleplayer() ? -1 : mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime();
	}
	
	public void setShowBackground(boolean enabled) {
		showBackground = enabled;
		
		setToFile("showBackground", enabled);
	}
	
	public boolean isShowBackgroundEnabled() {
		return showBackground;
	}
	
	public void setTextShadow(boolean enabled) {
		textShadow = enabled;
		
		setToFile("textShadow", enabled);
	}
	
	public boolean isTextShadowEnabled() {
		return textShadow;
	}
	
	public void setTextColor(int rgb) {
		this.textColor = ColorManager.fromRGB(rgb);
		
		setToFile("textColor", rgb);
	}
	
	public ColorManager getTextColor() {
		return textColor;
	}
	
	public void setBackgroundColor(int rgb) {
		this.backgroundColor = ColorManager.fromRGB(rgb).setAlpha(102);
		
		setToFile("backgroundColor", rgb);
	}
	
	public ColorManager getBackgroundColor() {
		return backgroundColor;
	}
	
	public void setTextChroma(boolean enabled) {
		this.textChroma = enabled;
		
		setToFile("textChroma", enabled);
	}
	
	public boolean isTextChromaEnabled() {
		return textChroma;
	}
	
	public void setDynamicColors(boolean enabled) {
		dynamicColors = enabled;
		
		setToFile("dynamicColors", enabled);
	}
	
	public boolean isDynamicColorsEnabled() {
		return dynamicColors;
	}
}
