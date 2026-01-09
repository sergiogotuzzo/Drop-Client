package drop.mod.impl;

import java.awt.Color;

import drop.gui.GuiScreenDC;
import drop.gui.GuiSettings;
import drop.gui.mod.pingdisplay.GuiPingDisplay;
import drop.mod.ModColor;
import drop.mod.ModDraggable;
import drop.mod.ModOptions;
import drop.mod.option.Brackets;
import drop.mod.option.ParentOption;
import drop.mod.option.type.BooleanOption;
import drop.mod.option.type.ColorOption;
import drop.mod.option.type.EnumOption;
import drop.gui.hud.ScreenPosition;

public class PingDisplay extends ModDraggable {
	public PingDisplay() {
		super(false, 0.5, 0.5);
		
		this.options = new ModOptions(
				new ColorOption(this, "textColor", ModColor.fromColor(Color.WHITE, false), new GuiSettings(1, "Text Color", true, false)),
				new BooleanOption(this, "textShadow", true, new GuiSettings(2, "Text Shadow")),
				new BooleanOption(this, "showBackground", false, new GuiSettings(3, "Background")),
				new ColorOption(this, "backgroundColor", ModColor.fromRGB(0, 0, 0, 102, false), new ParentOption("showBackground"), new GuiSettings(4, "Background Color", false, true)),
				new BooleanOption(this, "showBorder", false, new ParentOption("showBackground"), new GuiSettings(17, "Border")),
				new ColorOption(this, "borderColor", ModColor.fromRGB(0, 0, 0, 255, false), new ParentOption("showBorder"), new GuiSettings(18, "Border Color", false, true)),
				new EnumOption(this, "brackets", Brackets.NONE.getId(), Brackets.ANGULAR.getId(), Brackets.SQUARE.getId(), Brackets.SQUARE, new ParentOption("showBackground", true), new GuiSettings(5, "Brackets")),
				new BooleanOption(this, "dynamicColors", true, new GuiSettings(false, 6, "Dynamic Colors")),
				new ColorOption(this, "excellentTextColor", ModColor.fromRGB(85, 255, 85, false), new GuiSettings(false, 7, "Excellent Text Color", true, false)),
				new BooleanOption(this, "excellentTextShadow", true, new GuiSettings(false, 8, "Excellent Text Shadow")),
				new ColorOption(this, "goodTextColor", ModColor.fromRGB(255, 255, 85, false), new GuiSettings(false, 9, "Good Text Color", true, false)),
				new BooleanOption(this, "goodTextShadow", true, new GuiSettings(false, 10, "Good Text Shadow")),
				new ColorOption(this, "moderateTextColor", ModColor.fromRGB(255, 170, 0, false), new GuiSettings(false, 11, "Moderate Text Color", true, false)),
				new BooleanOption(this, "moderateTextShadow", true, new GuiSettings(false, 12, "Moderate Text Shadow")),
				new ColorOption(this, "weakTextColor", ModColor.fromRGB(255, 85, 85, false), new GuiSettings(false, 13, "Weak Text Color", true, false)),
				new BooleanOption(this, "weakTextShadow", true, new GuiSettings(false, 14, "Weak Text Shadow")),
				new ColorOption(this, "unstableTextColor", ModColor.fromRGB(170, 0, 0, false), new GuiSettings(false, 15, "Unstable Text Color", true, false)),
				new BooleanOption(this, "unstableTextShadow", true, new GuiSettings(false, 16, "Unstable Text Shadow"))
				);
				
		saveOptions();
	}
	
	@Override
	public GuiScreenDC getGui(GuiScreenDC previousGuiScreen) {
		return new GuiPingDisplay(previousGuiScreen);
	}
	
	@Override
	public int getWidth() {
		return options.getBooleanOption("showBackground").isToggled() ? 53 : font.getStringWidth(Brackets.fromId((int) options.getEnumOption("brackets").getValue()).wrap("-1 ms"));
	}

	@Override
	public int getHeight() {
		return options.getBooleanOption("showBackground").isToggled() ? 17 : font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (!mc.isSingleplayer()) {
			int ping = mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime();
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
			
			String text = ping + " ms";
			
			if (options.getBooleanOption("showBackground").isToggled()) {
				getBounds().fill(options.getColorOption("backgroundColor").getColor().getRGB());
				
				if (options.getBooleanOption("showBorder").isToggled()) {
			    	getBounds().stroke(options.getColorOption("borderColor").getColor().getRGB());
		    	}

				drawCenteredText(text, pos.getAbsoluteX(), pos.getAbsoluteY(), color, dropShadow);
	    	} else {
			    drawAlignedText(Brackets.fromId((int) options.getEnumOption("brackets").getValue()).wrap(text), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color, dropShadow);
	    	}
		}
	}

	@Override
	public void renderDummy(ScreenPosition pos) {
		if (options.getBooleanOption("showBackground").isToggled()) {
	    	getBounds().fill(options.getColorOption("backgroundColor").getColor().getRGB());
	    	
	    	if (options.getBooleanOption("showBorder").isToggled()) {
		    	getBounds().stroke(options.getColorOption("borderColor").getColor().getRGB());
	    	}
	    	
			drawCenteredText("-1 ms", pos.getAbsoluteX(), pos.getAbsoluteY(), options.getColorOption("textColor").getColor(), options.getBooleanOption("textShadow").isToggled());
    	} else {
		    drawAlignedText(Brackets.fromId((int) options.getEnumOption("brackets").getValue()).wrap("-1 ms"), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, options.getColorOption("textColor").getColor(), options.getBooleanOption("textShadow").isToggled());
    	}
	}
}
