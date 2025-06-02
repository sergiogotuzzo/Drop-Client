package drop.mods.impl;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import drop.ColorManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiArmorStatus;
import drop.mods.ModDraggableText;
import drop.mods.hud.ScreenPosition;

public class ArmorStatus extends ModDraggableText {
	private boolean equippedItem = false;
	private boolean showPercentage = false;
	private boolean showDamage = true;
	private boolean showMaxDamage = false;
	private boolean damageOverlays = true;
	private boolean dynamicColors = true;
	private boolean reverse = false;

	public ArmorStatus() {
		setEquippedItem((boolean) getFromFile("equippedItem", equippedItem));
		setShowPercentage((boolean) getFromFile("showPercentage", showPercentage));
		setShowDamage((boolean) getFromFile("showDamage", showDamage));
		setShowMaxDamage((boolean) getFromFile("showMaxDamage", showMaxDamage));
		setDamageOverlays((boolean) getFromFile("damageOverlays", damageOverlays));
		setDynamicColors((boolean) getFromFile("dynamicColors", dynamicColors));
		setReverse((boolean) getFromFile("reverse", reverse));
	}

	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiArmorStatus(previousGuiScreen);
	}
	
	@Override
	public int getWidth() {
		int width = 0;
		
		if (showPercentage) {
			width = 2 + font.getStringWidth("100%");
		} else if (showDamage && !showMaxDamage) {
			width = 2 + (equippedItem ? font.getStringWidth("1561") : font.getStringWidth("528"));
		} else if (showDamage && showMaxDamage) {
			width = 2 + (equippedItem ? font.getStringWidth("1561/1561") : font.getStringWidth("528/528"));
		}
		
		return 16 + width;
	}

	@Override
	public int getHeight() {
		return equippedItem ? 16 * 5 : 16 * 4;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (pos.getRelativeX() < 1.0 / 3.0 && reverse) {
			setReverse(false);
		} else if (pos.getRelativeX() > 2.0 / 3.0 && !reverse) {
			setReverse(true);
		}
		
		int i = 0;
		
		if (equippedItem) {
			if (mc.thePlayer.inventory.getCurrentItem() != null) {
				drawItemStack(pos, 0, mc.thePlayer.inventory.getCurrentItem());
				
				i = 1;
			}
		}
		
		for (ItemStack itemStack : mc.thePlayer.inventory.armorInventory) {
			if (itemStack != null) {
				drawItemStack(pos, i, itemStack);
				
				i++;
			}
		}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		if (pos.getRelativeX() < 1.0 / 3.0 && reverse) {
			setReverse(false);
		} else if (pos.getRelativeX() > 2.0 / 3.0 && !reverse) {
			setReverse(true);
		}
		
		if (equippedItem) {
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
		
		int yAdd = (-16 * i) + getHeight() - 16;
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
		
		int itemX = reverse ? pos.getAbsoluteX() + getWidth() - 16 : pos.getAbsoluteX();
		int damageX = reverse ? pos.getAbsoluteX() + getWidth() - font.getStringWidth(getDamageText(is)) - 16 - 2 : pos.getAbsoluteX() + 16 + 2;
		
		mc.getRenderItem().renderItemAndEffectIntoGUI(is, itemX, pos.getAbsoluteY() + yAdd);
		
		if (is.isStackable()) {
			mc.getRenderItem().renderItemOverlays(font, is, itemX, pos.getAbsoluteY() + yAdd);
		}
		
		if ((showDamage || showPercentage) && is.getItem().isDamageable()) {
			if (damageOverlays) {
				mc.getRenderItem().renderItemOverlays(font, is, itemX, pos.getAbsoluteY() + yAdd);
			}
			
			drawText(getDamageText(is), damageX, pos.getAbsoluteY() + yAdd + 5, dynamicColors ? dynamicColor.getRGB() : textColor.getRGB(), textShadow, textChroma && !dynamicColors);
		}
		
		GL11.glPopMatrix();
	}
	
	private String getDamageText(ItemStack is) {
		if (showPercentage) {
			return String.format("%.0f%%", getDamagePercentage(is));
		} else if (showDamage && !showMaxDamage) {
			return String.valueOf(is.getMaxDamage() - is.getItemDamage());
		} else if (showDamage && showMaxDamage) {
			return (is.getMaxDamage() - is.getItemDamage()) + "/" + is.getMaxDamage();
		}
		
		return null;
	}
	
	private double getDamagePercentage(ItemStack is) {
		return ((is.getMaxDamage() - is.getItemDamage()) / (double) is.getMaxDamage()) * 100;
	}
	
	public void setEquippedItem(boolean enabled) {
		equippedItem = enabled;
		
		setToFile("equippedItem", enabled);
	}
	
	public boolean isEquippedItemEnabled() {
		return equippedItem;
	}
	
	public void setShowPercentage(boolean enabled) {
		showPercentage = enabled;
		
		setToFile("showPercentage", enabled);
	}
	
	public boolean isShowPercentageEnabled() {
		return showPercentage;
	}
	
	public void setShowDamage(boolean enabled) {
		showDamage = enabled;
		
		setToFile("showDamage", enabled);
	}
	
	public boolean isShowDamageEnabled() {
		return showDamage;
	}
	
	public void setShowMaxDamage(boolean enabled) {
		showMaxDamage = enabled;
		
		setToFile("showMaxDamage", enabled);
	}
	
	public boolean isShowMaxDamageEnabled() {
		return showMaxDamage;
	}
	
	public void setDamageOverlays(boolean enabled) {
		damageOverlays = enabled;
		
		setToFile("damageOverlays", enabled);
	}
	
	public boolean isDamageOverlaysEnabled() {
		return damageOverlays;
	}
	
	public void setDynamicColors(boolean enabled) {
		dynamicColors = enabled;
		
		setToFile("dynamicColors", enabled);
	}
	
	public boolean isDynamicColorsEnabled() {
		return dynamicColors;
	}
	
	public void setReverse(boolean toggled) {
		reverse = toggled;
		
		setToFile("reverse", toggled);
	}
	
	public boolean isReverseToggled() {
		return reverse;
	}
}
