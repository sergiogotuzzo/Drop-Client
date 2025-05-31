package drop.mods.impl;

import java.awt.Color;

import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiPingDisplay;
import drop.mods.hud.ScreenPosition;
import drop.mods.ModDraggableDisplayText;

public class PingDisplay extends ModDraggableDisplayText {
	private boolean dynamicColors = true;

	public PingDisplay() {
		setDynamicColors((boolean) getFromFile("dynamicColors", dynamicColors));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiPingDisplay(previousGuiScreen);
	}
	
	@Override
	public int getWidth() {
		return showBackground ? 58 : font.getStringWidth(brackets.wrap((mc.isSingleplayer() ? -1 : mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime()) + " ms"));
	}

	@Override
	public int getHeight() {
		return showBackground ? 18 : font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (showBackground) {
			drawRect(pos);
		}
		
		int ping = mc.isSingleplayer() ? -1 : mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime();
		Color dynamicColor = Color.WHITE;
		
		if (dynamicColors) {
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

		drawCenteredText(brackets.wrap(ping + " ms"), pos.getAbsoluteX(), pos.getAbsoluteY(), dynamicColors ? dynamicColor.getRGB() : textColor.getRGB(), textShadow, textChroma && !dynamicColors);
	}
	
	public void setDynamicColors(boolean enabled) {
		dynamicColors = enabled;
		
		setToFile("dynamicColors", enabled);
	}
	
	public boolean isDynamicColorsEnabled() {
		return dynamicColors;
	}
}
