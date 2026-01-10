package drop.mod.impl.togglesprintsneak;

import java.awt.Color;

import drop.gui.GuiSettings;
import drop.gui.hud.ScreenPosition;
import drop.mod.ModColor;
import drop.mod.ModDraggable;
import drop.mod.option.Brackets;
import drop.mod.option.ParentOption;
import drop.mod.option.type.BooleanOption;
import drop.mod.option.type.ColorOption;
import drop.mod.option.type.EnumOption;
import drop.mod.option.type.FloatOption;

public class ToggleSprintSneak extends ModDraggable {
	public int keyHoldTicks = 7;
	public boolean sprinting = false;
	public boolean sneaking = false;
	
	private String textToRender = "";
	private int RECT_GAP = 10;
	
	public ToggleSprintSneak() {
		super(true, 0.5, 0.5);
				
		saveOptions(
				new BooleanOption(this, "showText", true, new GuiSettings(1, "Show Text")),
				new ColorOption(this, "textColor", ModColor.fromColor(Color.WHITE, false), new ParentOption("showText"), new GuiSettings(2, "Text Color", true, false)),
				new BooleanOption(this, "textShadow", true, new ParentOption("showText"), new GuiSettings(3, "Text Shadow")),
				new BooleanOption(this, "showBackground", false, new ParentOption("showText"), new GuiSettings(4, "Background")),
				new ColorOption(this, "backgroundColor", ModColor.fromRGB(0, 0, 0, 102, false), new ParentOption("showBackground"), new GuiSettings(5, "Background Color", false, true)),
				new BooleanOption(this, "showBorder", false, new ParentOption("showBackground"), new GuiSettings(11, "Border")),
				new ColorOption(this, "borderColor", ModColor.fromRGB(0, 0, 0, 255, false), new ParentOption("showBorder"), new GuiSettings(12, "Border Color", false, true)),
				new EnumOption(this, "brackets", Brackets.NONE.getId(), Brackets.ANGULAR.getId(), Brackets.SQUARE.getId(), Brackets.SQUARE, new ParentOption("showBackground", true), new GuiSettings(6, "Brackets")),
				new BooleanOption(this, "toggleSprint", true, new GuiSettings(7, "Toggle Sprint")),
				new BooleanOption(this, "toggleSneak", false, new GuiSettings(8, "Toggle Sneak")),
				new BooleanOption(this, "flyBoost", true, new GuiSettings(9, "Fly Boost")),
				new FloatOption(this, "flyBoostFactor", 2.0F, 8.0F, 4.0F, new ParentOption("flyBoost"), new GuiSettings(10, "Fly Boost Factor"))
				);
	}
	
	@Override
	public String getName() {
		return "Toggle Sprint / Sneak";
	}

	@Override
	public int getWidth() {
		return getBooleanOption("showBackground").isToggled() ? font.getStringWidth("Sprinting (Toggled)") + RECT_GAP : font.getStringWidth(Brackets.fromId((int) getEnumOption("brackets").getValue()).wrap("Sprinting (Toggled)"));
	}

	@Override
	public int getHeight() {
		return getBooleanOption("showBackground").isToggled() ? 17 : font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
	    if (getBooleanOption("showText").isToggled() && mc.thePlayer.movementInput.getDisplayText() != "") {
	    	textToRender = mc.thePlayer.movementInput.getDisplayText();
	    	
	    	if (getBooleanOption("showBackground").isToggled()) {	    		
	    		getBounds().fill(getColorOption("backgroundColor").getColor().getRGB(), textToRender, RECT_GAP);
		    	
		    	if (getBooleanOption("showBorder").isToggled()) {
		    		getBounds().stroke(getColorOption("borderColor").getColor().getRGB(), textToRender, RECT_GAP);
		    	}
		    	
				drawCenteredText(textToRender, RECT_GAP, pos.getAbsoluteX(), pos.getAbsoluteY(), getColorOption("textColor").getColor(), getBooleanOption("textShadow").isToggled());
	    	} else {
			    drawAlignedText(Brackets.fromId((int) getEnumOption("brackets").getValue()).wrap(textToRender), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, getColorOption("textColor").getColor(), getBooleanOption("textShadow").isToggled());
	    	}
	    }
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		if (getBooleanOption("showText").isToggled()) {
			textToRender = "Sprinting (Toggled)";
			
			if (getBooleanOption("showBackground").isToggled()) {	    		
	    		getBounds().fill(getColorOption("backgroundColor").getColor().getRGB(), textToRender, RECT_GAP);
		    	
		    	if (getBooleanOption("showBorder").isToggled()) {
		    		getBounds().stroke(getColorOption("borderColor").getColor().getRGB(), textToRender, RECT_GAP);
		    	}
		    	
				drawCenteredText(textToRender, RECT_GAP, pos.getAbsoluteX(), pos.getAbsoluteY(), getColorOption("textColor").getColor(), getBooleanOption("textShadow").isToggled());
	    	} else {
			    drawAlignedText(Brackets.fromId((int) getEnumOption("brackets").getValue()).wrap(textToRender), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, getColorOption("textColor").getColor(), getBooleanOption("textShadow").isToggled());
	    	}
	    }
	}
}
