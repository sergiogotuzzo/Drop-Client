package drop.mods.impl;

import net.minecraft.item.ItemStack;
import drop.mods.hud.ScreenPosition;
import drop.mods.ModDraggableDisplayText;

public class PotsCounter extends ModDraggableDisplayText {
	public PotsCounter() {
		super(false, 0.5, 0.5, "0 pots");
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
		
		drawTextToRender(pos, potsCount + " pots");
	}
}
