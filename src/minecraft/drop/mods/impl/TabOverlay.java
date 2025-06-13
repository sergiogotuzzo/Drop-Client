package drop.mods.impl;

import java.awt.Color;

import drop.ColorManager;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.taboverlay.GuiTabOverlay;
import drop.mods.Mod;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.network.NetworkPlayerInfo;

public class TabOverlay extends Mod {
	protected ColorManager textColor = ColorManager.fromColor(Color.WHITE, false);
	protected boolean textShadow = true;
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
	private boolean showPlayerHeads = true;
	private boolean hidePing = false;
	private boolean pingNumbers = false;
	
	public TabOverlay() {
		super(true);
		
		setTextColor(getIntFromFile("textColor", textColor.getRGB()));
		setTextChroma(getBooleanFromFile("textChroma", textColor.isChromaToggled()));
		setTextShadow(getBooleanFromFile("textShadow", textShadow));
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
		setShowPlayerHeads(getBooleanFromFile("showPlayerHeads", showPlayerHeads));
		setHidePing(getBooleanFromFile("hidePing", hidePing));
		setPingNumbers(getBooleanFromFile("pingNumbers", pingNumbers));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiTabOverlay(previousGuiScreen);
	}

	public void writePing(FontRenderer font, int p_175245_1_, int p_175245_2_, int p_175245_3_, NetworkPlayerInfo networkPlayerInfoIn) {
		int ping = networkPlayerInfoIn.getResponseTime();
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
		
		String pingText = String.valueOf(ping);
		
		if (color.isChromaToggled()) {
			int textCharX = p_175245_2_ + p_175245_1_ - font.getStringWidth(pingText);
			
	        for (char textChar : pingText.toCharArray()) {
	            long t = System.currentTimeMillis() - (textCharX * 10 - p_175245_3_ * 10);
	            int c = Color.HSBtoRGB(t % (int) 2000.0F / 2000.0F, 0.8F, 0.8F);
	            
	            if (pingText.startsWith("§m")) {
	            	font.drawString(pingText, p_175245_2_ + p_175245_1_ - font.getStringWidth(pingText), p_175245_3_, c, dropShadow);
	            } else {
	            	font.drawString(String.valueOf(textChar), textCharX, p_175245_3_, c, dropShadow);
	            }
	            
	            textCharX += font.getCharWidth(textChar);
	        }
		} else {
			font.drawString(pingText, p_175245_2_ + p_175245_1_ - font.getStringWidth(pingText), p_175245_3_, color.getRGB(), dropShadow);
		}
	}
	
	public void setTextColor(int rgb) {
		textColor.setRGB(rgb);
		
		setToFile("textColor", rgb);
	}
	
	public ColorManager getTextColor() {
		return textColor;
	}
	
	public void setTextChroma(boolean toggled) {
		textColor.setChromaToggled(toggled);
		
		setToFile("textChroma", toggled);
	}
	
	public boolean isTextChromaToggled() {
		return textColor.isChromaToggled();
	}
	
	public void setTextShadow(boolean toggled) {
		textShadow = toggled;
		
		setToFile("textShadow", toggled);
	}
	
	public boolean isTextShadowToggled() {
		return textShadow;
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

	public void setShowPlayerHeads(boolean toggled) {
		this.showPlayerHeads = toggled;
		
		setToFile("showPlayerHeads", showPlayerHeads);
	}
	
	public boolean isShowPlayerHeadsToggled() {
		return showPlayerHeads;
	}

	public void setHidePing(boolean toggled) {
		this.hidePing = toggled;
		
		setToFile("hidePing", hidePing);
	}
	
	public boolean isHidePingToggled() {
		return hidePing;
	}

	public void setPingNumbers(boolean toggled) {
		this.pingNumbers = toggled;
		
		setToFile("pingNumbers", pingNumbers);
	}
	
	public boolean isPingNumbersToggled() {
		return pingNumbers;
	}
}
