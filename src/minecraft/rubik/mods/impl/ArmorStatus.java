package rubik.mods.impl;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import rubik.ColorManager;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggable;

public class ArmorStatus extends ModDraggable {
	public static enum ArmorStatusMode {
		PERCENTAGE(1),
		DAMAGE(2),
		DAMAGE_MAX_DAMAGE(3);
		
		private int index = 0;
		
		private ArmorStatusMode(int index) {
			this.index = index;
		}
		
		public int getIndex() {
			return index;
		}
	}
	
	private boolean showEquippedItem = false;
	private boolean right = false;
	private ArmorStatusMode mode = ArmorStatusMode.DAMAGE;
	private boolean dynamicColors = true;
	private ColorManager textColor = ColorManager.fromColor(Color.WHITE);
	private boolean textShadow = true;
	private boolean textChroma = true;
	
	private final int ITEM_HEIGHT = 16;
	
	public ArmorStatus() {
		setShowEquippedItem((boolean) getFromFile("showEquippedItem", showEquippedItem));
		setRight((boolean) getFromFile("right", right));
		setMode((int) ((long) getFromFile("mode", mode.getIndex())));
		setDynamicColors((boolean) getFromFile("dynamicColors", dynamicColors));
		setTextColor((int) ((long) getFromFile("textColor", textColor.getRGB())));
		setTextShadow((boolean) getFromFile("textShadow", textShadow));
		setTextChroma((boolean) getFromFile("textChroma", textChroma));
	}
	
	@Override
	public int getWidth() {
		switch (mode) {
			case PERCENTAGE:
				return 44;
			case DAMAGE:
				return showEquippedItem ? 44 : 38;
			case DAMAGE_MAX_DAMAGE:
				return showEquippedItem ? 74 : 62;
			default:
				return 0;
		}
	}

	@Override
	public int getHeight() {
		return showEquippedItem ? ITEM_HEIGHT * 5 : ITEM_HEIGHT * 4;
	}

	@Override
	public void render(ScreenPosition pos) {
		int i = 0;
		
		for (ItemStack itemStack : mc.thePlayer.inventory.armorInventory) {
			if (itemStack != null) {
				if (showEquippedItem) {
					if (mc.thePlayer.inventory.getCurrentItem() == null) {
						drawItemStack(pos, i, itemStack);
					} else {
						drawItemStack(pos, 0, mc.thePlayer.inventory.getCurrentItem());
						drawItemStack(pos, i + 1, itemStack);
					}
				} else {
					drawItemStack(pos, i, itemStack);
				}
				
				i++;
			}
		}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		if (showEquippedItem) {
			drawItemStack(pos, 4, new ItemStack(Items.diamond_helmet));
			drawItemStack(pos, 3, new ItemStack(Items.diamond_chestplate));
			drawItemStack(pos, 2, new ItemStack(Items.diamond_leggings));
			drawItemStack(pos, 1, new ItemStack(Items.diamond_boots));
			drawItemStack(pos, 0, new ItemStack(Items.diamond_sword));
		} else {
			drawItemStack(pos, 3, new ItemStack(Items.diamond_helmet));
			drawItemStack(pos, 2, new ItemStack(Items.diamond_chestplate));
			drawItemStack(pos, 1, new ItemStack(Items.diamond_leggings));
			drawItemStack(pos, 0, new ItemStack(Items.diamond_boots));
		}
	}

	private void drawItemStack(ScreenPosition pos, int i, ItemStack is) {
		if (is == null) {
			return;
		}
		
		GL11.glPushMatrix();
		
		int yAdd = (-16 * i) + getHeight() - ITEM_HEIGHT;
		Color dynamicColor = Color.WHITE;
		
		if (dynamicColors) {
			double damagePercentage = getDamagePercentage(is);
			
			if (damagePercentage > 80) {
				dynamicColor = Color.WHITE;
			} else if (damagePercentage > 60) {
				dynamicColor = new Color(85, 255, 85);
			} else if (damagePercentage > 40) {
				dynamicColor = new Color(255, 255, 85);
			} else if (damagePercentage > 25) {
				dynamicColor = new Color(255, 170, 0);
			} else if (damagePercentage > 10) {
				dynamicColor = new Color(255, 85, 85);
			} else if (damagePercentage < 10) {
				dynamicColor = new Color(170, 0, 0);
			}
		}
		
		RenderHelper.enableGUIStandardItemLighting();
		
		int itemX = right ? pos.getAbsoluteX() + getWidth() - 16 : pos.getAbsoluteX();
		int damageX = right ? pos.getAbsoluteX() + getWidth() - font.getStringWidth(getDamageText(is)) - 16 - 2 : pos.getAbsoluteX() + 16 + 2;
		
		mc.getRenderItem().renderItemAndEffectIntoGUI(is, itemX, pos.getAbsoluteY() + yAdd);
		
		if (is.isStackable()) {
			mc.getRenderItem().renderItemOverlays(font, is, itemX, pos.getAbsoluteY() + yAdd);
		}
		
		if (is.getItem().isDamageable()) {
			drawText(getDamageText(is), damageX, pos.getAbsoluteY() + yAdd + 5, dynamicColors ? dynamicColor.getRGB() : textColor.getRGB(), textShadow, textChroma && !dynamicColors);
		}
		
		GL11.glPopMatrix();
	}
	
	private String getDamageText(ItemStack is) {
		if (mode == ArmorStatusMode.PERCENTAGE) {
			return String.format("%.0f%%", getDamagePercentage(is));
		} else if (mode == ArmorStatusMode.DAMAGE) {
			return String.valueOf(is.getMaxDamage() - is.getItemDamage());
		} else if (mode == ArmorStatusMode.DAMAGE_MAX_DAMAGE) {
			return (is.getMaxDamage() - is.getItemDamage()) + "/" + is.getMaxDamage();
		}
		
		return null;
	}
	
	private double getDamagePercentage(ItemStack is) {
		return ((is.getMaxDamage() - is.getItemDamage()) / (double) is.getMaxDamage()) * 100;
	}
	
	public void setShowEquippedItem(boolean enabled) {
		showEquippedItem = enabled;
		
		setToFile("showEquippedItem", enabled);
	}
	
	public boolean isShowEquippedItemEnabled() {
		return showEquippedItem;
	}
	
	public void setRight(boolean enabled) {
		right = enabled;
		
		setToFile("right", enabled);
	}
	
	public boolean isRightEnabled() {
		return right;
	}
	
	public void setMode(int modeIndex) {
		switch (modeIndex) {
			case 1:
				this.mode = ArmorStatusMode.PERCENTAGE;
				break;
			case 2:
				this.mode = ArmorStatusMode.DAMAGE;
				break;
			case 3:
				this.mode = ArmorStatusMode.DAMAGE_MAX_DAMAGE;
				break;
		}
		
		setToFile("mode", modeIndex);
	}
	
	public ArmorStatusMode getMode() {
		return mode;
	}
	
	public void setDynamicColors(boolean enabled) {
		dynamicColors = enabled;
		
		setToFile("dynamicColors", enabled);
	}
	
	public boolean isDynamicColorsEnabled() {
		return dynamicColors;
	}
	
	public void setTextColor(int rgb) {
		this.textColor = ColorManager.fromRGB(rgb);
		
		setToFile("textColor", rgb);
	}
	
	public ColorManager getTextColor() {
		return textColor;
	}
	
	public void setTextShadow(boolean enabled) {
		textShadow = enabled;
		
		setToFile("textShadow", enabled);
	}
	
	public boolean isTextShadowEnabled() {
		return textShadow;
	}
	
	public void setTextChroma(boolean enabled) {
		this.textChroma = enabled;
		
		setToFile("textChroma", enabled);
	}
	
	public boolean isTextChromaEnabled() {
		return textChroma;
	}
}
