package drop.mods.impl.togglesprintsneak;

import java.awt.Color;

import drop.ColorManager;
import drop.gui.GuiSettings;
import drop.gui.hud.ScreenPosition;
import drop.mods.option.ParentOption;
import drop.mods.option.type.BooleanOption;
import drop.mods.option.type.BracketsOption;
import drop.mods.option.type.ColorOption;
import drop.mods.option.type.FloatOption;
import drop.mods.ModDraggable;
import drop.mods.ModOptions;
import drop.mods.option.Brackets;

public class ToggleSprintSneak extends ModDraggable {
	public ToggleSprintSneak() {
		super(true, 0.5, 0.5);
		
		this.options = new ModOptions(
				new BooleanOption(this, "showText", true, new GuiSettings(1, "Show Text")),
				new ColorOption(this, "textColor", ColorManager.fromColor(Color.WHITE, false), new ParentOption("showText"), new GuiSettings(2, "Text Color", true, false)),
				new BooleanOption(this, "textShadow", true, new ParentOption("showText"), new GuiSettings(3, "Text Shadow")),
				new BooleanOption(this, "showBackground", false, new ParentOption("showText"), new GuiSettings(4, "Show Background")),
				new ColorOption(this, "backgroundColor", ColorManager.fromRGB(0, 0, 0, 102, false), new ParentOption("showBackground"), new GuiSettings(5, "Background Color", false, true)),
				new BracketsOption(this, "brackets", Brackets.SQUARE, new ParentOption("showBackground", true), new GuiSettings(6, "Brackets")),
				new BooleanOption(this, "toggleSprint", true, new GuiSettings(7, "Toggle Sprint")),
				new BooleanOption(this, "toggleSneak", false, new GuiSettings(8, "Toggle Sneak")),
				new BooleanOption(this, "flyBoost", true, new GuiSettings(9, "Fly Boost")),
				new FloatOption(this, "flyBoostFactor", 2.0F, 8.0F, 4.0F, new ParentOption("flyBoost"), new GuiSettings(10, "Fly Boost Factor"))
				);
				
		saveOptions();
	}
	
	public int keyHoldTicks = 7;
	public boolean sprinting = false;
	public boolean sneaking = false;
	private String textToRender = "";

	@Override
	public int getWidth() {
		return options.getBooleanOption("showBackground").isToggled() ? font.getStringWidth("Sprinting (Toggled)") + 20 : font.getStringWidth(options.getBracketsOption("brackets").wrap("Sprinting (Toggled)"));
	}

	@Override
	public int getHeight() {
		return options.getBooleanOption("showBackground").isToggled() ? 18 : font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
	    if (options.getBooleanOption("showText").isToggled() && mc.thePlayer.movementInput.getDisplayText() != "") {
	    	String text = mc.thePlayer.movementInput.getDisplayText();
	    	
	    	drawTextToRender(pos, textToRender = (options.getBooleanOption("showBackground").isToggled() ? text : options.getBracketsOption("brackets").wrap(text)));
	    }
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		if (options.getBooleanOption("showText").isToggled()) {
			String text = "Sprinting (Toggled)";
	    	
	    	drawTextToRender(pos, textToRender = (options.getBooleanOption("showBackground").isToggled() ? text : options.getBracketsOption("brackets").wrap(text)));
	    }
	}
	
	public void drawTextToRender(ScreenPosition pos, String textToRender) {
		if (options.getBooleanOption("showBackground").isToggled()) {
	    	drawAlignedRect(pos, textToRender, 20, options.getColorOption("backgroundColor").getColor().getRGB());
			drawCenteredAlignedText(pos, textToRender, 20, pos.getAbsoluteX(), pos.getAbsoluteY(), options.getColorOption("textColor").getColor(), options.getBooleanOption("textShadow").isToggled());
    	} else {
		    drawAlignedText(textToRender, pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, options.getColorOption("textColor").getColor(), options.getBooleanOption("textShadow").isToggled());
    	}
	}
}
