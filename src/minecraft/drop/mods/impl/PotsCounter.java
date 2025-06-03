package drop.mods.impl;

import net.minecraft.item.ItemStack;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiModDraggableDisplayText;
import drop.mods.hud.ScreenPosition;
import drop.mods.ModDraggableDisplayText;

public class PotsCounter extends ModDraggableDisplayText {
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiModDraggableDisplayText(previousGuiScreen, this);
	}
	
	@Override
	public int getWidth() {
		return showBackground ? 58 : font.getStringWidth(brackets.wrap((mc.thePlayer != null ? getPotsCount() : 0) + " pots"));
	}

	@Override
	public int getHeight() {
		return showBackground ? 18 : font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (showBackground) {
			drawRect(pos);
		}
		
		String text = getPotsCount() + " pots";
		
		drawCenteredText(showBackground ? text : brackets.wrap(text), pos.getAbsoluteX(), pos.getAbsoluteY(), textColor, textShadow, textColor.isChromaToggled());
	}
	
	private int getPotsCount() {
		int potsCount = 0;
		
		for (int i = 0; i < mc.thePlayer.inventory.getSizeInventory(); i++) {
			ItemStack itemStack = mc.thePlayer.inventory.getStackInSlot(i);
			
			if (itemStack != null && itemStack.getItem().getIdFromItem(itemStack.getItem()) == 373) {
				potsCount++;
			}
		}
		
		return potsCount;
	}
}
