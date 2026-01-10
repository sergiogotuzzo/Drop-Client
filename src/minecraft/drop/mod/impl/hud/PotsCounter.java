package drop.mod.impl.hud;

import net.minecraft.item.ItemStack;
import drop.gui.mod.GuiSettings;
import drop.gui.mod.hud.ScreenPosition;
import drop.mod.ModDraggable;
import drop.mod.option.Brackets;
import drop.mod.option.ModColor;
import drop.mod.option.ParentOption;
import drop.mod.option.type.BooleanOption;
import drop.mod.option.type.ColorOption;
import drop.mod.option.type.EnumOption;

import java.awt.Color;

public class PotsCounter extends ModDraggable {
	public PotsCounter() {
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
		return getBooleanOption("showBackground").isToggled() ? 53 : font.getStringWidth(Brackets.fromId((int) getEnumOption("brackets").getValue()).wrap("0 pots"));
	}

	@Override
	public int getHeight() {
		return getBooleanOption("showBackground").isToggled() ? 17 : font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		int potsCount = 0;
		
		for (int i = 0; i < mc.thePlayer.inventory.getSizeInventory(); i++) {
			ItemStack itemStack = mc.thePlayer.inventory.getStackInSlot(i);
			
			if (itemStack != null && itemStack.getItem().getIdFromItem(itemStack.getItem()) == 373) {
				potsCount++;
			}
		}
		
		if (getBooleanOption("showBackground").isToggled()) {
	    	getBounds().fill(getColorOption("backgroundColor").getColor().getRGB());
	    	
	    	if (getBooleanOption("showBorder").isToggled()) {
		    	getBounds().stroke(getColorOption("borderColor").getColor().getRGB());
	    	}
	    	
			drawCenteredText(potsCount + " pots", pos.getAbsoluteX(), pos.getAbsoluteY(), getColorOption("textColor").getColor(), getBooleanOption("textShadow").isToggled());
    	} else {
		    drawAlignedText(Brackets.fromId((int) getEnumOption("brackets").getValue()).wrap(potsCount + " pots"), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, getColorOption("textColor").getColor(), getBooleanOption("textShadow").isToggled());
    	}
	}

	@Override
	public void renderDummy(ScreenPosition pos) {
		if (getBooleanOption("showBackground").isToggled()) {
	    	getBounds().fill(getColorOption("backgroundColor").getColor().getRGB());
	    	
	    	if (getBooleanOption("showBorder").isToggled()) {
		    	getBounds().stroke(getColorOption("borderColor").getColor().getRGB());
	    	}
	    	
			drawCenteredText("0 pots", pos.getAbsoluteX(), pos.getAbsoluteY(), getColorOption("textColor").getColor(), getBooleanOption("textShadow").isToggled());
    	} else {
		    drawAlignedText(Brackets.fromId((int) getEnumOption("brackets").getValue()).wrap("0 pots"), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, getColorOption("textColor").getColor(), getBooleanOption("textShadow").isToggled());
    	}
	}
}
