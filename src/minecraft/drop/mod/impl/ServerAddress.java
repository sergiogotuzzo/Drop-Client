package drop.mod.impl;

import drop.gui.hud.ScreenPosition;
import drop.mod.ModColor;
import drop.mod.ModDraggable;
import drop.mod.option.Brackets;
import drop.mod.option.ParentOption;
import drop.mod.option.type.BooleanOption;
import drop.mod.option.type.ColorOption;
import drop.mod.option.type.EnumOption;
import net.minecraft.client.multiplayer.ServerData;

import java.awt.Color;

import drop.gui.GuiSettings;

public class ServerAddress extends ModDraggable {
	private String textToRender = "";
	private int RECT_GAP = 8;
	
	public ServerAddress() {
		super(false, 0.5, 0.5);
				
		saveOptions(
				new ColorOption(this, "textColor", ModColor.fromColor(Color.WHITE, false), new GuiSettings(1, "Text Color", true, false)),
				new BooleanOption(this, "textShadow", true, new GuiSettings(2, "Text Shadow")),
				new BooleanOption(this, "showBackground", false, new GuiSettings(3, "Background")),
				new ColorOption(this, "backgroundColor", ModColor.fromRGB(0, 0, 0, 102, false), new ParentOption("showBackground"), new GuiSettings(4, "Background Color", false, true)),
				new BooleanOption(this, "showBorder", false, new ParentOption("showBackground"), new GuiSettings(6, "Border")),
				new ColorOption(this, "borderColor", ModColor.fromRGB(0, 0, 0, 255, false), new ParentOption("showBorder"), new GuiSettings(7, "Border Color", false, true)),
				new EnumOption(this, "brackets", Brackets.NONE.getId(), Brackets.ANGULAR.getId(), Brackets.SQUARE.getId(), Brackets.SQUARE, new ParentOption("showBackground", true), new GuiSettings(5, "Brackets"))
				);
	}
	
	@Override
	public int getWidth() {
		return getBooleanOption("showBackground").isToggled() ? font.getStringWidth("mc.example.org") + RECT_GAP : font.getStringWidth(Brackets.fromId((int) getEnumOption("brackets").getValue()).wrap("mc.example.org"));
	}

	@Override
	public int getHeight() {
		return getBooleanOption("showBackground").isToggled() ? 17 : font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (mc.getCurrentServerData() != null) {
			textToRender = mc.getCurrentServerData().serverIP;
			
			if (getBooleanOption("showBackground").isToggled()) {
				getBounds().fill(getColorOption("backgroundColor").getColor().getRGB(),textToRender, RECT_GAP);
				
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
		textToRender = "mc.example.org";
		
		if (getBooleanOption("showBackground").isToggled()) {
			getBounds().fill(getColorOption("backgroundColor").getColor().getRGB(),textToRender, RECT_GAP);
			
			if (getBooleanOption("showBorder").isToggled()) {
		    	getBounds().stroke(getColorOption("borderColor").getColor().getRGB(), textToRender, RECT_GAP);
	    	}
			
			drawCenteredText(textToRender, RECT_GAP, pos.getAbsoluteX(), pos.getAbsoluteY(), getColorOption("textColor").getColor(), getBooleanOption("textShadow").isToggled());
    	} else {
		    drawAlignedText(Brackets.fromId((int) getEnumOption("brackets").getValue()).wrap(textToRender), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, getColorOption("textColor").getColor(), getBooleanOption("textShadow").isToggled());
    	}
	}
}
