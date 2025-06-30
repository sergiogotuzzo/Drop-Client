package drop.mods.impl;

import drop.gui.hud.ScreenPosition;
import drop.mods.option.ParentOption;
import drop.mods.option.type.BooleanOption;
import drop.mods.option.type.BracketsOption;
import drop.mods.option.type.ColorOption;
import net.minecraft.client.multiplayer.ServerData;

import java.awt.Color;

import drop.ColorManager;
import drop.gui.GuiSettings;
import drop.mods.ModDraggable;
import drop.mods.ModOptions;
import drop.mods.option.Brackets;

public class ServerAddress extends ModDraggable {
	public ServerAddress() {
		super(false, 0.5, 0.5);

		this.options = new ModOptions(
				new ColorOption(this, "textColor", ColorManager.fromColor(Color.WHITE, false), new GuiSettings(1, "Text Color", true, false)),
				new BooleanOption(this, "textShadow", true, new GuiSettings(2, "Text Shadow")),
				new BooleanOption(this, "showBackground", false, new GuiSettings(3, "Background")),
				new ColorOption(this, "backgroundColor", ColorManager.fromRGB(0, 0, 0, 102, false), new ParentOption("showBackground"), new GuiSettings(4, "Background Color", false, true)),
				new BooleanOption(this, "showBorder", false, new ParentOption("showBackground"), new GuiSettings(6, "Border")),
				new ColorOption(this, "borderColor", ColorManager.fromRGB(0, 0, 0, 255, false), new ParentOption("showBorder"), new GuiSettings(7, "Border Color", false, true)),
				new BracketsOption(this, "brackets", Brackets.SQUARE, new ParentOption("showBackground", true), new GuiSettings(5, "Brackets"))
				);
				
		saveOptions();
	}
	
	@Override
	public int getWidth() {
		return options.getBooleanOption("showBackground").isToggled() ? font.getStringWidth("mc.example.org") + 8 : font.getStringWidth(options.getBracketsOption("brackets").wrap("mc.example.org"));
	}

	@Override
	public int getHeight() {
		return options.getBooleanOption("showBackground").isToggled() ? 18 : font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (mc.getCurrentServerData() != null) {
			if (options.getBooleanOption("showBackground").isToggled()) {
				drawAlignedRect(pos, mc.getCurrentServerData().serverIP, 8, options.getColorOption("backgroundColor").getColor().getRGB());
				
				if (options.getBooleanOption("showBorder").isToggled()) {
			    	drawAlignedHollowRect(pos, mc.getCurrentServerData().serverIP, 8, options.getColorOption("borderColor").getColor().getRGB());
		    	}
				
				drawCenteredAlignedText(pos, mc.getCurrentServerData().serverIP, 8, pos.getAbsoluteX(), pos.getAbsoluteY(), options.getColorOption("textColor").getColor(), options.getBooleanOption("textShadow").isToggled());
	    	} else {
			    drawAlignedText(options.getBracketsOption("brackets").wrap(mc.getCurrentServerData().serverIP), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, options.getColorOption("textColor").getColor(), options.getBooleanOption("textShadow").isToggled());
	    	}
		}
	}

	@Override
	public void renderDummy(ScreenPosition pos) {
		if (options.getBooleanOption("showBackground").isToggled()) {
			drawAlignedRect(pos, "mc.example.org", 8, options.getColorOption("backgroundColor").getColor().getRGB());
			
			if (options.getBooleanOption("showBorder").isToggled()) {
		    	drawAlignedHollowRect(pos, "mc.example.org", 8, options.getColorOption("borderColor").getColor().getRGB());
	    	}
			
			drawCenteredAlignedText(pos, "mc.example.org", 8, pos.getAbsoluteX(), pos.getAbsoluteY(), options.getColorOption("textColor").getColor(), options.getBooleanOption("textShadow").isToggled());
    	} else {
		    drawAlignedText(options.getBracketsOption("brackets").wrap("mc.example.org"), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, options.getColorOption("textColor").getColor(), options.getBooleanOption("textShadow").isToggled());
    	}
	}
}
