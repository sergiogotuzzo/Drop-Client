package drop.mod.impl;

import java.awt.Color;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import drop.gui.hud.ScreenPosition;
import drop.mod.ModColor;
import drop.mod.ModDraggable;
import drop.mod.ModOptions;
import drop.mod.option.Brackets;
import drop.mod.option.ParentOption;
import drop.mod.option.type.BooleanOption;
import drop.mod.option.type.ColorOption;
import drop.mod.option.type.EnumOption;
import drop.mod.option.type.FloatOption;
import drop.gui.GuiSettings;

public class Clock extends ModDraggable {    
	public Clock() {
		super(false, 0.5, 0.5);
		
		this.options = new ModOptions(
				new ColorOption(this, "textColor", ModColor.fromColor(Color.WHITE, false), new GuiSettings(1, "Text Color", true, false)),
				new BooleanOption(this, "textShadow", true, new GuiSettings(2, "Text Shadow")),
				new BooleanOption(this, "showBackground", false, new GuiSettings(3, "Background")),
				new ColorOption(this, "backgroundColor", ModColor.fromRGB(0, 0, 0, 102, false), new ParentOption("showBackground"), new GuiSettings(4, "Background Color", false, true)),
				new BooleanOption(this, "showBorder", false, new ParentOption("showBackground"), new GuiSettings(6, "Border")),
				new ColorOption(this, "borderColor", ModColor.fromRGB(0, 0, 0, 255, false), new ParentOption("showBorder"), new GuiSettings(7, "Border Color", false, true)),
				new EnumOption(this, "brackets", Brackets.NONE.getId(), Brackets.ANGULAR.getId(), Brackets.SQUARE.getId(), Brackets.SQUARE, new ParentOption("showBackground", true), new GuiSettings(5, "Brackets"))
			);
				
		saveOptions();
	}
	
	@Override
	public int getWidth() {
		return options.getBooleanOption("showBackground").isToggled() ? 53 : font.getStringWidth(Brackets.fromId((int) options.getEnumOption("brackets").getValue()).wrap("12:00"));
	}

	@Override
	public int getHeight() {
		return options.getBooleanOption("showBackground").isToggled() ? 17 : font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (options.getBooleanOption("showBackground").isToggled()) {
	    	getBounds().fill(options.getColorOption("backgroundColor").getColor().getRGB());
	    	
	    	if (options.getBooleanOption("showBorder").isToggled()) {
		    	getBounds().stroke(options.getColorOption("borderColor").getColor().getRGB());
	    	}
	    	
			drawCenteredText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")), pos.getAbsoluteX(), pos.getAbsoluteY(), options.getColorOption("textColor").getColor(), options.getBooleanOption("textShadow").isToggled());
    	} else {
		    drawAlignedText(Brackets.fromId((int) options.getEnumOption("brackets").getValue()).wrap(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, options.getColorOption("textColor").getColor(), options.getBooleanOption("textShadow").isToggled());
    	}
	}

	@Override
	public void renderDummy(ScreenPosition pos) {
		if (options.getBooleanOption("showBackground").isToggled()) {
	    	getBounds().fill(options.getColorOption("backgroundColor").getColor().getRGB());
	    	
	    	if (options.getBooleanOption("showBorder").isToggled()) {
		    	getBounds().stroke(options.getColorOption("borderColor").getColor().getRGB());
	    	}
	    	
			drawCenteredText("12:00", pos.getAbsoluteX(), pos.getAbsoluteY(), options.getColorOption("textColor").getColor(), options.getBooleanOption("textShadow").isToggled());
    	} else {
		    drawAlignedText(Brackets.fromId((int) options.getEnumOption("brackets").getValue()).wrap("12:00"), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, options.getColorOption("textColor").getColor(), options.getBooleanOption("textShadow").isToggled());
    	}
	}
}
