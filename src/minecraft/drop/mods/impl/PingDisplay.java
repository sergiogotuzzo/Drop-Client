package drop.mods.impl;

import java.awt.Color;

import drop.ColorManager;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiPingDisplay;
import drop.gui.mod.hud.ScreenPosition;
import drop.mods.ModDraggableText;

public class PingDisplay extends ModDraggableText {
	private boolean showBackground = false;
	private boolean dynamicColors = true;

	public PingDisplay() {
		setShowBackground((boolean) getFromFile("showBackground", showBackground));
		setDynamicColors((boolean) getFromFile("dynamicColors", dynamicColors));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiPingDisplay(previousGuiScreen);
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
					ColorManager.fromColor(Color.BLACK).setAlpha(102).getRGB()
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
	
	public void setDynamicColors(boolean enabled) {
		dynamicColors = enabled;
		
		setToFile("dynamicColors", enabled);
	}
	
	public boolean isDynamicColorsEnabled() {
		return dynamicColors;
	}
}
