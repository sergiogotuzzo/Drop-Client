package drop.mods.impl;

import java.awt.Color;

import drop.ColorManager;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.pingdisplay.GuiPingDisplay;
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
		if (!mc.isSingleplayer()) {
			if (showBackground) {
				drawRect(pos);
			}
			
			int ping = mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime();
			ColorManager color = textColor; // Default
			
			if (dynamicColors) {
				if (ping > 300) {
					color = ColorManager.fromRGB(170, 0, 0, false); // Unstable
				} else if (ping > 200) {
					color = ColorManager.fromRGB(255, 85, 85, false); // Weak
				} else if (ping > 150) {
					color = ColorManager.fromRGB(255, 170, 0, false); // Moderate
				} else if (ping > 100) {
					color = ColorManager.fromRGB(255, 255, 85, false); // Good
				} else if (ping > 50) {
					color = ColorManager.fromRGB(85, 255, 85, false); // Excellent
				}
			}
			
			String text = ping + " ms";

			drawCenteredText(showBackground ? text : brackets.wrap(text), pos.getAbsoluteX(), pos.getAbsoluteY(), color, textShadow);
		}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		if (showBackground) {
			drawRect(pos);
		}
		
		int ping = mc.isSingleplayer() ? -1 : mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime();
		ColorManager color = textColor; // Default
		
		if (dynamicColors) {
			if (ping > 300) {
				color = ColorManager.fromRGB(170, 0, 0, false); // Unstable
			} else if (ping > 200) {
				color = ColorManager.fromRGB(255, 85, 85, false); // Weak
			} else if (ping > 150) {
				color = ColorManager.fromRGB(255, 170, 0, false); // Moderate
			} else if (ping > 100) {
				color = ColorManager.fromRGB(255, 255, 85, false); // Good
			} else if (ping > 50) {
				color = ColorManager.fromRGB(85, 255, 85, false); // Excellent
			}
		}
		
		String text = ping + " ms";

		drawCenteredText(showBackground ? text : brackets.wrap(text), pos.getAbsoluteX(), pos.getAbsoluteY(), color, textShadow);
	}
	
	public void setDynamicColors(boolean enabled) {
		dynamicColors = enabled;
		
		setToFile("dynamicColors", enabled);
	}
	
	public boolean isDynamicColorsEnabled() {
		return dynamicColors;
	}
}
