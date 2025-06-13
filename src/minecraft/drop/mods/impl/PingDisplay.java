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
		super(false, 0.5, 0.5, "-1 ms");
		
		setExcellentTextColor(getIntFromFile("excellentTextColor", excellentTextColor.getRGB()));
		setExcellentTextChroma(getBooleanFromFile("excellentTextChroma", excellentTextColor.isChromaToggled()));
		setExcellentTextShadow(getBooleanFromFile("excellentTextShadow", excellentTextShadow));
		setGoodTextColor(getIntFromFile("goodTextColor", goodTextColor.getRGB()));
		setGoodTextChroma(getBooleanFromFile("goodTextChroma", goodTextColor.isChromaToggled()));
		setGoodTextShadow(getBooleanFromFile("goodTextShadow", goodTextShadow));
		setModerateTextColor(getIntFromFile("moderateTextColor", moderateTextColor.getRGB()));
		setModerateTextChroma(getBooleanFromFile("moderateTextChroma", moderateTextColor.isChromaToggled()));
		setModerateTextShadow(getBooleanFromFile("moderateTextShadow", moderateTextShadow));
		setWeakTextColor(getIntFromFile("weakTextColor", weakTextColor.getRGB()));
		setWeakTextChroma(getBooleanFromFile("weakTextChroma", weakTextColor.isChromaToggled()));
		setWeakTextShadow(getBooleanFromFile("weakTextShadow", weakTextShadow));
		setUnstableTextColor(getIntFromFile("unstableTextColor", unstableTextColor.getRGB()));
		setUnstableTextChroma(getBooleanFromFile("unstableTextChroma", unstableTextColor.isChromaToggled()));
		setUnstableTextShadow(getBooleanFromFile("unstableTextShadow", unstableTextShadow));
		setDynamicColors(getBooleanFromFile("dynamicColors", dynamicColors));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiPingDisplay(previousGuiScreen);
	}

	@Override
	public void render(ScreenPosition pos) {
		if (!mc.isSingleplayer()) {
			if (showBackground) {
				drawRect(pos);
			}
			
			int ping = mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime();
			ColorManager color = textColor; // Default
			boolean dropShadow = textShadow;
			
			if (dynamicColors) {
				if (ping > 300) {
					color = unstableTextColor; // Unstable
					dropShadow = unstableTextShadow;
				} else if (ping > 200) {
					color = weakTextColor; // Weak
					dropShadow = weakTextShadow;
				} else if (ping > 150) {
					color = moderateTextColor; // Moderate
					dropShadow = moderateTextShadow;
				} else if (ping > 100) {
					color = goodTextColor; // Good
					dropShadow = goodTextShadow;
				} else if (ping > 50) {
					color = excellentTextColor; // Excellent
					dropShadow = excellentTextShadow;
				}
			}
			
			String text = ping + " ms";
			
			if (showBackground) {
		    	drawRect(pos);
				drawCenteredText(text, pos.getAbsoluteX(), pos.getAbsoluteY(), color, dropShadow);
	    	} else {
			    drawAlignedText(brackets.wrap(text), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color, dropShadow);
	    	}
		}
	}
	
	public void setExcellentTextColor(int rgb) {
		excellentTextColor.setRGB(rgb);
		
		setToFile("excellentTextColor", rgb);
	}
	
	public ColorManager getExcellentTextColor() {
		return excellentTextColor;
	}
	
	public void setExcellentTextChroma(boolean toggled) {
		excellentTextColor.setChromaToggled(toggled);
		
		setToFile("excellentTextChroma", toggled);
	}
	
	public boolean isExcellentTextChromaToggled() {
		return excellentTextColor.isChromaToggled();
	}
	
	public void setExcellentTextShadow(boolean toggled) {
		excellentTextShadow = toggled;
		
		setToFile("excellentTextShadow", toggled);
	}
	
	public boolean isExcellentTextShadowToggled() {
		return excellentTextShadow;
	}
	
	public void setGoodTextColor(int rgb) {
		goodTextColor.setRGB(rgb);
		
		setToFile("goodTextColor", rgb);
	}
	
	public ColorManager getGoodTextColor() {
		return goodTextColor;
	}
	
	public void setGoodTextChroma(boolean toggled) {
		goodTextColor.setChromaToggled(toggled);
		
		setToFile("goodTextChroma", toggled);
	}
	
	public boolean isGoodTextChromaToggled() {
		return goodTextColor.isChromaToggled();
	}
	
	public void setGoodTextShadow(boolean toggled) {
		goodTextShadow = toggled;
		
		setToFile("goodTextShadow", toggled);
	}
	
	public boolean isGoodTextShadowToggled() {
		return goodTextShadow;
	}
	
	public void setModerateTextColor(int rgb) {
		moderateTextColor.setRGB(rgb);
		
		setToFile("moderateTextColor", rgb);
	}
	
	public ColorManager getModerateTextColor() {
		return moderateTextColor;
	}
	
	public void setModerateTextChroma(boolean toggled) {
		moderateTextColor.setChromaToggled(toggled);
		
		setToFile("moderateTextChroma", toggled);
	}
	
	public boolean isModerateTextChromaToggled() {
		return moderateTextColor.isChromaToggled();
	}
	
	public void setModerateTextShadow(boolean toggled) {
		moderateTextShadow = toggled;
		
		setToFile("moderateTextShadow", toggled);
	}
	
	public boolean isModerateTextShadowToggled() {
		return moderateTextShadow;
	}
	
	public void setWeakTextColor(int rgb) {
		weakTextColor.setRGB(rgb);
		
		setToFile("weakTextColor", rgb);
	}
	
	public ColorManager getWeakTextColor() {
		return weakTextColor;
	}
	
	public void setWeakTextChroma(boolean toggled) {
		weakTextColor.setChromaToggled(toggled);
		
		setToFile("weakTextChroma", toggled);
	}
	
	public boolean isWeakTextChromaToggled() {
		return weakTextColor.isChromaToggled();
	}
	
	public void setWeakTextShadow(boolean toggled) {
		weakTextShadow = toggled;
		
		setToFile("WeakTextShadow", toggled);
	}
	
	public boolean isWeakTextShadowToggled() {
		return weakTextShadow;
	}
	
	public void setUnstableTextColor(int rgb) {
		unstableTextColor.setRGB(rgb);
		
		setToFile("unstableTextColor", rgb);
	}
	
	public ColorManager getUnstableTextColor() {
		return unstableTextColor;
	}
	
	public void setUnstableTextChroma(boolean toggled) {
		unstableTextColor.setChromaToggled(toggled);
		
		setToFile("unstableTextChroma", toggled);
	}
	
	public boolean isUnstableTextChromaToggled() {
		return unstableTextColor.isChromaToggled();
	}
	
	public void setUnstableTextShadow(boolean toggled) {
		unstableTextShadow = toggled;
		
		setToFile("unstableTextShadow", toggled);
	}
	
	public boolean isUnstableTextShadowToggled() {
		return unstableTextShadow;
	}
	
	public void setDynamicColors(boolean toggled) {
		dynamicColors = toggled;
		
		setToFile("dynamicColors", toggled);
	}
	
	public boolean isDynamicColorsToggled() {
		return dynamicColors;
	}
}
