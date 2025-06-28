package drop.mods.impl;

import java.awt.Color;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import drop.gui.hud.ScreenPosition;
import drop.mods.option.ParentOption;
import drop.mods.option.type.BooleanOption;
import drop.mods.option.type.BracketsOption;
import drop.mods.option.type.ColorOption;
import drop.mods.option.type.FloatOption;
import drop.ColorManager;
import drop.gui.GuiSettings;
import drop.mods.ModDraggable;
import drop.mods.ModOptions;
import drop.mods.option.Brackets;

public class Clock extends ModDraggable {    
	public Clock() {
		super(false, 0.5, 0.5);
		
		this.options = new ModOptions(
				new ColorOption(this, "textColor", ColorManager.fromColor(Color.WHITE, false), new GuiSettings(1, "Text Color", true, false)),
				new BooleanOption(this, "textShadow", true, new GuiSettings(2, "Text Shadow")),
				new BooleanOption(this, "showBackground", false, new GuiSettings(3, "Show Background")),
				new ColorOption(this, "backgroundColor", ColorManager.fromRGB(0, 0, 0, 102, false), new ParentOption("showBackground"), new GuiSettings(4, "Background Color", false, true)),
				new BracketsOption(this, "brackets", Brackets.SQUARE, new ParentOption("showBackground", true), new GuiSettings(5, "Brackets"))
				);
				
		saveOptions();
	}
	
	@Override
	public int getWidth() {
		return options.getBooleanOption("showBackground").isToggled() ? 58 : font.getStringWidth(options.getBracketsOption("brackets").wrap("12:00"));
	}

	@Override
	public int getHeight() {
		return options.getBooleanOption("showBackground").isToggled() ? 18 : font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (options.getBooleanOption("showBackground").isToggled()) {
	    	getBounds().fill(options.getColorOption("backgroundColor").getColor().getRGB());
	    	
			drawCenteredText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")), pos.getAbsoluteX(), pos.getAbsoluteY(), options.getColorOption("textColor").getColor(), options.getBooleanOption("textShadow").isToggled());
    	} else {
		    drawAlignedText(options.getBracketsOption("brackets").wrap(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, options.getColorOption("textColor").getColor(), options.getBooleanOption("textShadow").isToggled());
    	}
	}

	@Override
	public void renderDummy(ScreenPosition pos) {
		if (options.getBooleanOption("showBackground").isToggled()) {
	    	getBounds().fill(options.getColorOption("backgroundColor").getColor().getRGB());
	    	
			drawCenteredText("12:00", pos.getAbsoluteX(), pos.getAbsoluteY(), options.getColorOption("textColor").getColor(), options.getBooleanOption("textShadow").isToggled());
    	} else {
		    drawAlignedText(options.getBracketsOption("brackets").wrap("12:00"), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, options.getColorOption("textColor").getColor(), options.getBooleanOption("textShadow").isToggled());
    	}
	}
}
