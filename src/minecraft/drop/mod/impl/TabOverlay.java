package drop.mod.impl;

import java.awt.Color;

import drop.gui.GuiScreenDC;
import drop.gui.GuiSettings;
import drop.gui.mod.taboverlay.GuiTabOverlay;
import drop.mod.Mod;
import drop.mod.ModColor;
import drop.mod.ModOptions;
import drop.mod.option.ParentOption;
import drop.mod.option.type.BooleanOption;
import drop.mod.option.type.ColorOption;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.network.NetworkPlayerInfo;

public class TabOverlay extends Mod {
	public TabOverlay() {
		super(false);
		
		this.options = new ModOptions(
				new BooleanOption(this, "hidePing", false, new GuiSettings(1, "Hide Ping")),
				new BooleanOption(this, "pingNumbers", false, new ParentOption("hidePing", true), new GuiSettings(15, "Ping Numbers")),
				new ColorOption(this, "textColor", ModColor.fromColor(Color.WHITE, false), new ParentOption("pingNumbers"), new GuiSettings(2, "Ping Text Color", true, false)),
				new BooleanOption(this, "dynamicColors", true, new GuiSettings(false, 4, "Dynamic Colors")),
				new ColorOption(this, "excellentTextColor", ModColor.fromRGB(85, 255, 85, false), new GuiSettings(false, 5, "Excellent Text Color", true, false)),
				new BooleanOption(this, "excellentTextShadow", true, new GuiSettings(false, 6, "Excellent Text Shadow")),
				new ColorOption(this, "goodTextColor", ModColor.fromRGB(255, 255, 85, false), new GuiSettings(false, 7, "Good Text Color", true, false)),
				new BooleanOption(this, "goodTextShadow", true, new GuiSettings(false, 8, "Good Text Shadow")),
				new ColorOption(this, "moderateTextColor", ModColor.fromRGB(255, 170, 0, false), new GuiSettings(false, 9, "Moderate Text Color", true, false)),
				new BooleanOption(this, "moderateTextShadow", true, new GuiSettings(false, 10, "Moderate Text Shadow")),
				new ColorOption(this, "weakTextColor", ModColor.fromRGB(255, 85, 85, false), new GuiSettings(false, 11, "Weak Text Color", true, false)),
				new BooleanOption(this, "weakTextShadow", true, new GuiSettings(false, 12, "Weak Text Shadow")),
				new ColorOption(this, "unstableTextColor", ModColor.fromRGB(170, 0, 0, false), new GuiSettings(false, 13, "Unstable Text Color", true, false)),
				new BooleanOption(this, "unstableTextShadow", true, new GuiSettings(false, 14, "Unstable Text Shadow")),
				new BooleanOption(this, "showPlayerHeads", true, new GuiSettings(16, "Show Player Heads")),
				new BooleanOption(this, "showHeader", true, new GuiSettings(17, "Show Header")),
				new BooleanOption(this, "showFooter", true, new GuiSettings(18, "Show Footer")),
				new ColorOption(this, "backgroundColor", ModColor.fromRGB(Integer.MIN_VALUE, false), new GuiSettings(true, 19, "Background Color", false, true)),
				new ColorOption(this, "playerBackgroundColor", ModColor.fromRGB(553648127, false), new GuiSettings(true, 20, "Player Background Color", false, true)),
				new BooleanOption(this, "textShadow", true, new GuiSettings(3, "Text Shadow"))
				);
				
		saveOptions();
	}
	
	@Override
	public GuiScreenDC getGui(GuiScreenDC previousGuiScreen) {
		return new GuiTabOverlay(previousGuiScreen);
	}

	public void writePing(FontRenderer font, int p_175245_1_, int p_175245_2_, int p_175245_3_, NetworkPlayerInfo networkPlayerInfoIn) {
		int ping = networkPlayerInfoIn.getResponseTime();
		ModColor color = options.getColorOption("textColor").getColor(); // Default
		boolean dropShadow = options.getBooleanOption("textShadow").isToggled();
		
		if (options.getBooleanOption("dynamicColors").isToggled()) {
			if (ping > 300) {
				color = options.getColorOption("unstableTextColor").getColor(); // Unstable
				dropShadow = options.getBooleanOption("unstableTextShadow").isToggled();
			} else if (ping > 200) {
				color = options.getColorOption("weakTextColor").getColor(); // Weak
				dropShadow = options.getBooleanOption("weakTextShadow").isToggled();
			} else if (ping > 150) {
				color = options.getColorOption("moderateTextColor").getColor(); // Moderate
				dropShadow = options.getBooleanOption("moderateTextShadow").isToggled();
			} else if (ping > 100) {
				color = options.getColorOption("goodTextColor").getColor(); // Good
				dropShadow = options.getBooleanOption("goodTextShadow").isToggled();
			} else if (ping > 50) {
				color = options.getColorOption("excellentTextColor").getColor(); // Excellent
				dropShadow = options.getBooleanOption("excellentTextShadow").isToggled();
			}
		}
		
		String pingText = String.valueOf(ping);
		
		if (color.isChromaToggled()) {
			int textCharX = p_175245_2_ + p_175245_1_ - font.getStringWidth(pingText);
			
	        for (char textChar : pingText.toCharArray()) {
	            long t = System.currentTimeMillis() - (textCharX * 10 - p_175245_3_ * 10);
	            int c = Color.HSBtoRGB(t % (int) 2000.0F / 2000.0F, 0.8F, 0.8F);
	            
	            if (pingText.startsWith("�m")) {
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
}
