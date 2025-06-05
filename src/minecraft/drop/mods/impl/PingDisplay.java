package drop.mods.impl;

import java.awt.Color;

import drop.ColorManager;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.pingdisplay.GuiPingDisplay;
import drop.mods.hud.ScreenPosition;
import drop.mods.ModDraggableDisplayText;

public class PingDisplay extends ModDraggableDisplayText {
	private ColorManager excellentTextColor = ColorManager.fromRGB(85, 255, 85, false);
	private boolean excellentTextShadow = true;
	private ColorManager goodTextColor = ColorManager.fromRGB(255, 255, 85, false);
	private boolean goodTextShadow = true;
	private ColorManager moderateTextColor = ColorManager.fromRGB(255, 170, 0, false);
	private boolean moderateTextShadow = true;
	private ColorManager weakTextColor = ColorManager.fromRGB(255, 85, 85, false);
	private boolean weakTextShadow = true;
	private ColorManager unstableTextColor = ColorManager.fromRGB(170, 0, 0, false);
	private boolean unstableTextShadow = true;
	private boolean dynamicColors = true;

	public PingDisplay() {
		setExcellentTextColor((int) ((long) getFromFile("excellentTextColor", excellentTextColor.getRGB())));
		setExcellentTextChroma((boolean) getFromFile("excellentTextChroma", excellentTextColor.isChromaToggled()));
		setExcellentTextShadow((boolean) getFromFile("excellentTextShadow", excellentTextShadow));
		setGoodTextColor((int) ((long) getFromFile("goodTextColor", goodTextColor.getRGB())));
		setGoodTextChroma((boolean) getFromFile("goodTextChroma", goodTextColor.isChromaToggled()));
		setGoodTextShadow((boolean) getFromFile("goodTextShadow", goodTextShadow));
		setModerateTextColor((int) ((long) getFromFile("moderateTextColor", moderateTextColor.getRGB())));
		setModerateTextChroma((boolean) getFromFile("moderateTextChroma", moderateTextColor.isChromaToggled()));
		setModerateTextShadow((boolean) getFromFile("moderateTextShadow", moderateTextShadow));
		setWeakTextColor((int) ((long) getFromFile("weakTextColor", weakTextColor.getRGB())));
		setWeakTextChroma((boolean) getFromFile("weakTextChroma", weakTextColor.isChromaToggled()));
		setWeakTextShadow((boolean) getFromFile("weakTextShadow", weakTextShadow));
		setUnstableTextColor((int) ((long) getFromFile("unstableTextColor", unstableTextColor.getRGB())));
		setUnstableTextChroma((boolean) getFromFile("unstableTextChroma", unstableTextColor.isChromaToggled()));
		setUnstableTextShadow((boolean) getFromFile("unstableTextShadow", unstableTextShadow));
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
	
	public void setExcellentTextColor(int rgb) {
		excellentTextColor.setRGB(rgb);
		
		setToFile("excellentTextColor", rgb);
	}
	
	public ColorManager getExcellentTextColor() {
		return excellentTextColor;
	}
	
	public void setExcellentTextChroma(boolean enabled) {
		excellentTextColor.setChromaToggled(enabled);
		
		setToFile("excellentTextChroma", enabled);
	}
	
	public boolean isExcellentTextChromaEnabled() {
		return excellentTextColor.isChromaToggled();
	}
	
	public void setExcellentTextShadow(boolean enabled) {
		excellentTextShadow = enabled;
		
		setToFile("excellentTextShadow", enabled);
	}
	
	public boolean isExcellentTextShadowEnabled() {
		return excellentTextShadow;
	}
	
	public void setGoodTextColor(int rgb) {
		goodTextColor.setRGB(rgb);
		
		setToFile("goodTextColor", rgb);
	}
	
	public ColorManager getGoodTextColor() {
		return goodTextColor;
	}
	
	public void setGoodTextChroma(boolean enabled) {
		goodTextColor.setChromaToggled(enabled);
		
		setToFile("goodTextChroma", enabled);
	}
	
	public boolean isGoodTextChromaEnabled() {
		return goodTextColor.isChromaToggled();
	}
	
	public void setGoodTextShadow(boolean enabled) {
		goodTextShadow = enabled;
		
		setToFile("goodTextShadow", enabled);
	}
	
	public boolean isGoodTextShadowEnabled() {
		return goodTextShadow;
	}
	
	public void setModerateTextColor(int rgb) {
		moderateTextColor.setRGB(rgb);
		
		setToFile("moderateTextColor", rgb);
	}
	
	public ColorManager getModerateTextColor() {
		return moderateTextColor;
	}
	
	public void setModerateTextChroma(boolean enabled) {
		moderateTextColor.setChromaToggled(enabled);
		
		setToFile("moderateTextChroma", enabled);
	}
	
	public boolean isModerateTextChromaEnabled() {
		return moderateTextColor.isChromaToggled();
	}
	
	public void setModerateTextShadow(boolean enabled) {
		moderateTextShadow = enabled;
		
		setToFile("moderateTextShadow", enabled);
	}
	
	public boolean isModerateTextShadowEnabled() {
		return moderateTextShadow;
	}
	
	public void setWeakTextColor(int rgb) {
		weakTextColor.setRGB(rgb);
		
		setToFile("weakTextColor", rgb);
	}
	
	public ColorManager getWeakTextColor() {
		return weakTextColor;
	}
	
	public void setWeakTextChroma(boolean enabled) {
		weakTextColor.setChromaToggled(enabled);
		
		setToFile("weakTextChroma", enabled);
	}
	
	public boolean isWeakTextChromaEnabled() {
		return weakTextColor.isChromaToggled();
	}
	
	public void setWeakTextShadow(boolean enabled) {
		weakTextShadow = enabled;
		
		setToFile("WeakTextShadow", enabled);
	}
	
	public boolean isWeakTextShadowEnabled() {
		return weakTextShadow;
	}
	
	public void setUnstableTextColor(int rgb) {
		unstableTextColor.setRGB(rgb);
		
		setToFile("unstableTextColor", rgb);
	}
	
	public ColorManager getUnstableTextColor() {
		return unstableTextColor;
	}
	
	public void setUnstableTextChroma(boolean enabled) {
		unstableTextColor.setChromaToggled(enabled);
		
		setToFile("unstableTextChroma", enabled);
	}
	
	public boolean isUnstableTextChromaEnabled() {
		return unstableTextColor.isChromaToggled();
	}
	
	public void setUnstableTextShadow(boolean enabled) {
		unstableTextShadow = enabled;
		
		setToFile("unstableTextShadow", enabled);
	}
	
	public boolean isUnstableTextShadowEnabled() {
		return unstableTextShadow;
	}
	
	public void setDynamicColors(boolean enabled) {
		dynamicColors = enabled;
		
		setToFile("dynamicColors", enabled);
	}
	
	public boolean isDynamicColorsEnabled() {
		return dynamicColors;
	}
}
