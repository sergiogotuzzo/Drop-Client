package rubik.mods.impl;

import java.awt.Color;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import rubik.ColorManager;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggable;

public class PotsCounter extends ModDraggable {
	private boolean showBackground = false;
	private boolean textShadow = true;
	private ColorManager textColor = ColorManager.fromColor(Color.WHITE);
	private boolean textChroma = false;
	
	public PotsCounter() {
		setShowBackground((boolean) getFromFile("showBackground", showBackground));
		setTextShadow((boolean) getFromFile("textShadow", textShadow));
		setTextColor((int) ((long) getFromFile("textColor", textColor.getRGB())));
		setTextChroma((boolean) getFromFile("textChroma", textChroma));
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
			drawRect(
					pos.getAbsoluteX(),
					pos.getAbsoluteY(),
					pos.getAbsoluteX() + getWidth(),
					pos.getAbsoluteY() + getHeight(),
					ColorManager.fromColor(Color.BLACK).setAlpha(102).getRGB()
					);
		}
		
		drawCenteredText(getPotsCountText(), pos.getAbsoluteX(), pos.getAbsoluteY(), textColor.getRGB(), textShadow, textChroma);
	}
	
	private String getPotsCountText() {
		int ping = getPotsCount();
		String potsCountText = ping + " pots";
		
		return showBackground ? potsCountText : "[" + potsCountText + "]";
	}
	
	private int getPotsCount() {
		int potsCount = 0;
		InventoryPlayer inventory = mc.thePlayer.inventory;
		
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack itemStack = inventory.getStackInSlot(i);
			
			if (itemStack != null && itemStack.getItem().getIdFromItem(itemStack.getItem()) == 373) {
				potsCount++;
			}
		}
		
		return potsCount;
	}

	public void setShowBackground(boolean enabled) {
		showBackground = enabled;
		
		setToFile("showBackground", enabled);
	}
	
	public boolean isShowBackgroundEnabled() {
		return showBackground;
	}
	
	public void setTextShadow(boolean enabled) {
		textShadow = enabled;
		
		setToFile("textShadow", enabled);
	}
	
	public boolean isTextShadowEnabled() {
		return textShadow;
	}
	
	public void setTextColor(int rgb) {
		this.textColor = ColorManager.fromRGB(rgb);
		
		setToFile("textColor", rgb);
	}
	
	public ColorManager getTextColor() {
		return textColor;
	}
	
	public void setTextChroma(boolean enabled) {
		this.textChroma = enabled;
		
		setToFile("textChroma", enabled);
	}
	
	public boolean isTextChromaEnabled() {
		return textChroma;
	}
}
