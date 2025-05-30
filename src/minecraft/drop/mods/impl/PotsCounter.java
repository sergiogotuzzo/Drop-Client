package drop.mods.impl;

import net.minecraft.item.ItemStack;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiPotsCounter;
import drop.gui.mod.hud.ScreenPosition;
import drop.mods.ModDraggableText;

public class PotsCounter extends ModDraggableText {
	private boolean showBackground = false;
	
	public PotsCounter() {
		setShowBackground((boolean) getFromFile("showBackground", showBackground));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiPotsCounter(previousGuiScreen);
	}
	
	@Override
	public int getWidth() {
		return 58;
	}

	@Override
	public int getHeight() {
		return 18;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (showBackground) {
			drawRect(pos);
		}
		
		int potsCount = 0;
		
		for (int i = 0; i < mc.thePlayer.inventory.getSizeInventory(); i++) {
			ItemStack itemStack = mc.thePlayer.inventory.getStackInSlot(i);
			
			if (itemStack != null && itemStack.getItem().getIdFromItem(itemStack.getItem()) == 373) {
				potsCount++;
			}
		}
		
		String text = potsCount + " pots";
		
		drawCenteredText(brackets.wrap(text), pos.getAbsoluteX(), pos.getAbsoluteY(), textColor, textShadow, textChroma);
	}
	
	public void setShowBackground(boolean enabled) {
		showBackground = enabled;
		
		setToFile("showBackground", enabled);
	}
	
	public boolean isShowBackgroundEnabled() {
		return showBackground;
	}
}
