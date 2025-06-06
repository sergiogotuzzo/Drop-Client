package drop.mods.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;

import org.lwjgl.opengl.GL11;

import drop.ColorManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.armorstatus.GuiArmorStatus;
import drop.mods.ModDraggableText;
import drop.mods.hud.ScreenPosition;

public class ArmorStatus extends ModDraggableText {
	private ColorManager veryHighTextColor = ColorManager.fromRGB(85, 255, 85, false);
	private boolean veryHighTextShadow = true;
	private ColorManager highTextColor = ColorManager.fromRGB(255, 255, 85, false);
	private boolean highTextShadow = true;
	private ColorManager mediumTextColor = ColorManager.fromRGB(255, 170, 0, false);
	private boolean mediumTextShadow = true;
	private ColorManager lowTextColor = ColorManager.fromRGB(255, 85, 85, false);
	private boolean lowTextShadow = true;
	private ColorManager veryLowTextColor = ColorManager.fromRGB(170, 0, 0, false);
	private boolean veryLowTextShadow = true;
	private boolean dynamicColors = true;
	private boolean showPercentage = false;
	private boolean showDamage = true;
	private boolean showMaxDamage = false;
	private boolean equippedItem = false;
	private boolean damageOverlays = true;
	private boolean reverse = false;

	public ArmorStatus() {
		setVeryHighTextColor((int) ((long) getFromFile("veryHighTextColor", veryHighTextColor.getRGB())));
		setVeryHighTextChroma((boolean) getFromFile("veryHighTextChroma", veryHighTextColor.isChromaToggled()));
		setVeryHighTextShadow((boolean) getFromFile("veryHighTextShadow", veryHighTextShadow));
		setHighTextColor((int) ((long) getFromFile("highTextColor", highTextColor.getRGB())));
		setHighTextChroma((boolean) getFromFile("highTextChroma", highTextColor.isChromaToggled()));
		setHighTextShadow((boolean) getFromFile("highTextShadow", highTextShadow));
		setMediumTextColor((int) ((long) getFromFile("mediumTextColor", mediumTextColor.getRGB())));
		setMediumTextChroma((boolean) getFromFile("mediumTextChroma", mediumTextColor.isChromaToggled()));
		setMediumTextShadow((boolean) getFromFile("mediumTextShadow", mediumTextShadow));
		setLowTextColor((int) ((long) getFromFile("lowTextColor", lowTextColor.getRGB())));
		setLowTextChroma((boolean) getFromFile("lowTextChroma", lowTextColor.isChromaToggled()));
		setLowTextShadow((boolean) getFromFile("lowTextShadow", lowTextShadow));
		setVeryLowTextColor((int) ((long) getFromFile("veryLowTextColor", veryLowTextColor.getRGB())));
		setVeryLowTextChroma((boolean) getFromFile("veryLowTextChroma", veryLowTextColor.isChromaToggled()));
		setVeryLowTextShadow((boolean) getFromFile("veryLowTextShadow", veryLowTextShadow));
		setDynamicColors((boolean) getFromFile("dynamicColors", dynamicColors));
		setShowPercentage((boolean) getFromFile("showPercentage", showPercentage));
		setShowDamage((boolean) getFromFile("showDamage", showDamage));
		setShowMaxDamage((boolean) getFromFile("showMaxDamage", showMaxDamage));
		setEquippedItem((boolean) getFromFile("equippedItem", equippedItem));
		setDamageOverlays((boolean) getFromFile("damageOverlays", damageOverlays));
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
			width = font.getStringWidth("100%");
		} else if (showDamage && !showMaxDamage) {
			width = (equippedItem ? font.getStringWidth("1561") : font.getStringWidth("528"));
		} else if (showDamage && showMaxDamage) {
			width = (equippedItem ? font.getStringWidth("1561/1561") : font.getStringWidth("528/528"));
		}
		
		return 16 + 2 + width;
	}

	@Override
	public int getHeight() {
		return 16 * (equippedItem ? 5 : 4);
	}

	@Override
	public void render(ScreenPosition pos) {
		if (pos.getRelativeX() < 1.0 / 3.0 && reverse) {
			setReverse(false);
		} else if (pos.getRelativeX() > 2.0 / 3.0 && !reverse) {
			setReverse(true);
		}
		
		int i = 0;
		
		for (ItemStack itemStack : getPlayerInventory()) {
			drawItemStack(pos, i, itemStack);
			
			i++;
		}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		if (pos.getRelativeX() < 1.0 / 3.0 && reverse) {
			setReverse(false);
		} else if (pos.getRelativeX() > 2.0 / 3.0 && !reverse) {
			setReverse(true);
		}
		
		Collection<ItemStack> dummyPlayerInventory = new ArrayList<ItemStack>();
		
		if (equippedItem) {
			dummyPlayerInventory.add(new ItemStack(Items.diamond_sword));
		}
		
		dummyPlayerInventory.add(new ItemStack(Items.diamond_boots));
		dummyPlayerInventory.add(new ItemStack(Items.diamond_leggings));
		dummyPlayerInventory.add(new ItemStack(Items.diamond_chestplate));
		dummyPlayerInventory.add(new ItemStack(Items.diamond_helmet));
		
		int i = 0;
		
		for (ItemStack itemStack : dummyPlayerInventory) {
			drawItemStack(pos, i, itemStack);
			
			i++;
		}
	}

	private void drawItemStack(ScreenPosition pos, int i, ItemStack is) {
		if (is == null) {
			return;
		}
		
		GL11.glPushMatrix();
		
		int offsetY = (-16 * i) + getHeight() - 16;
		ColorManager color = textColor; // Default
		boolean dropShadow = textShadow;
		
		if (dynamicColors) {
			double damagePercentage = getDamagePercentage(is);
			
			if (damagePercentage <= 10) {
		        color = veryLowTextColor; // Very low
		        dropShadow = veryLowTextShadow;
		    } else if (damagePercentage <= 25) {
		        color = lowTextColor; // Low
		        dropShadow = lowTextShadow;
		    } else if (damagePercentage <= 40) {
		        color = mediumTextColor; // Medium
		        dropShadow = mediumTextShadow;
		    } else if (damagePercentage <= 60) {
		        color = highTextColor; // High
		        dropShadow = highTextShadow;
		    } else if (damagePercentage <= 80) {
		        color = veryHighTextColor; // Very high
		        dropShadow = veryHighTextShadow;
		    }
		}
		
		RenderHelper.enableGUIStandardItemLighting();
		
		int itemX = reverse ? pos.getAbsoluteX() + getWidth() - 16 : pos.getAbsoluteX();
		int damageX = reverse ? pos.getAbsoluteX() + getWidth() - font.getStringWidth(getDamageText(is)) - 16 - 2 : pos.getAbsoluteX() + 16 + 2;
		
		mc.getRenderItem().renderItemAndEffectIntoGUI(is, itemX, pos.getAbsoluteY() + offsetY);
		
		if (is.isStackable()) {
			mc.getRenderItem().renderItemOverlays(font, is, itemX, pos.getAbsoluteY() + offsetY);
		}
		
		if ((showDamage || showPercentage) && is.getItem().isDamageable()) {
			if (damageOverlays) {
				mc.getRenderItem().renderItemOverlays(font, is, itemX, pos.getAbsoluteY() + offsetY);
			}
			
			drawText(getDamageText(is), damageX, pos.getAbsoluteY() + offsetY + 5, color, dropShadow);
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
	
	private Collection<ItemStack> getPlayerInventory() {
		Collection<ItemStack> playerInventory = new ArrayList<ItemStack>();
		
		if (equippedItem) {
			if (mc.thePlayer.inventory.getCurrentItem() != null) {
				playerInventory.add(mc.thePlayer.inventory.getCurrentItem());
			}
		}
		
		for (ItemStack itemStack : mc.thePlayer.inventory.armorInventory) {
			if (itemStack != null) {
				playerInventory.add(itemStack);
			}
		}
		
		return playerInventory;
	}
	
	public void setVeryHighTextColor(int rgb) {
		veryHighTextColor.setRGB(rgb);
		
		setToFile("veryHighTextColor", rgb);
	}
	
	public ColorManager getVeryHighTextColor() {
		return veryHighTextColor;
	}
	
	public void setVeryHighTextChroma(boolean enabled) {
		veryHighTextColor.setChromaToggled(enabled);
		
		setToFile("veryHighTextChroma", enabled);
	}
	
	public boolean isVeryHighTextChromaToggled() {
		return veryHighTextColor.isChromaToggled();
	}
	
	public void setVeryHighTextShadow(boolean enabled) {
		veryHighTextShadow = enabled;
		
		setToFile("veryHighTextShadow", enabled);
	}
	
	public boolean isVeryHighTextShadowToggled() {
		return veryHighTextShadow;
	}
	
	public void setHighTextColor(int rgb) {
		highTextColor.setRGB(rgb);
		
		setToFile("highTextColor", rgb);
	}
	
	public ColorManager getHighTextColor() {
		return highTextColor;
	}
	
	public void setHighTextChroma(boolean enabled) {
		highTextColor.setChromaToggled(enabled);
		
		setToFile("highTextChroma", enabled);
	}
	
	public boolean isHighTextChromaToggled() {
		return highTextColor.isChromaToggled();
	}
	
	public void setHighTextShadow(boolean enabled) {
		highTextShadow = enabled;
		
		setToFile("highTextShadow", enabled);
	}
	
	public boolean isHighTextShadowToggled() {
		return highTextShadow;
	}
	
	public void setMediumTextColor(int rgb) {
		mediumTextColor.setRGB(rgb);
		
		setToFile("mediumTextColor", rgb);
	}
	
	public ColorManager getMediumTextColor() {
		return mediumTextColor;
	}
	
	public void setMediumTextChroma(boolean enabled) {
		mediumTextColor.setChromaToggled(enabled);
		
		setToFile("mediumTextChroma", enabled);
	}
	
	public boolean isMediumTextChromaToggled() {
		return mediumTextColor.isChromaToggled();
	}
	
	public void setMediumTextShadow(boolean enabled) {
		mediumTextShadow = enabled;
		
		setToFile("mediumTextShadow", enabled);
	}
	
	public boolean isMediumTextShadowToggled() {
		return mediumTextShadow;
	}
	
	public void setLowTextColor(int rgb) {
		lowTextColor.setRGB(rgb);
		
		setToFile("lowTextColor", rgb);
	}
	
	public ColorManager getLowTextColor() {
		return lowTextColor;
	}
	
	public void setLowTextChroma(boolean enabled) {
		lowTextColor.setChromaToggled(enabled);
		
		setToFile("lowTextChroma", enabled);
	}
	
	public boolean isLowTextChromaToggled() {
		return lowTextColor.isChromaToggled();
	}
	
	public void setLowTextShadow(boolean enabled) {
		lowTextShadow = enabled;
		
		setToFile("lowTextShadow", enabled);
	}
	
	public boolean isLowTextShadowToggled() {
		return lowTextShadow;
	}
	
	public void setVeryLowTextColor(int rgb) {
		veryLowTextColor.setRGB(rgb);
		
		setToFile("veryLowTextColor", rgb);
	}
	
	public ColorManager getVeryLowTextColor() {
		return veryLowTextColor;
	}
	
	public void setVeryLowTextChroma(boolean enabled) {
		veryLowTextColor.setChromaToggled(enabled);
		
		setToFile("veryLowTextChroma", enabled);
	}
	
	public boolean isVeryLowTextChromaToggled() {
		return veryLowTextColor.isChromaToggled();
	}
	
	public void setVeryLowTextShadow(boolean enabled) {
		veryLowTextShadow = enabled;
		
		setToFile("veryLowTextShadow", enabled);
	}
	
	public boolean isVeryLowTextShadowToggled() {
		return veryLowTextShadow;
	}
	
	public void setDynamicColors(boolean enabled) {
		dynamicColors = enabled;
		
		setToFile("dynamicColors", enabled);
	}
	
	public boolean isDynamicColorsToggled() {
		return dynamicColors;
	}
	
	public void setShowPercentage(boolean enabled) {
		showPercentage = enabled;
		
		setToFile("showPercentage", enabled);
	}
	
	public boolean isShowPercentageToggled() {
		return showPercentage;
	}
	
	public void setShowDamage(boolean enabled) {
		showDamage = enabled;
		
		setToFile("showDamage", enabled);
	}
	
	public boolean isShowDamageToggled() {
		return showDamage;
	}
	
	public void setShowMaxDamage(boolean enabled) {
		showMaxDamage = enabled;
		
		setToFile("showMaxDamage", enabled);
	}
	
	public boolean isShowMaxDamageToggled() {
		return showMaxDamage;
	}
	
	public void setEquippedItem(boolean enabled) {
		equippedItem = enabled;
		
		setToFile("equippedItem", enabled);
	}
	
	public boolean isEquippedItemToggled() {
		return equippedItem;
	}
	
	public void setDamageOverlays(boolean enabled) {
		damageOverlays = enabled;
		
		setToFile("damageOverlays", enabled);
	}
	
	public boolean isDamageOverlaysToggled() {
		return damageOverlays;
	}
	
	public void setReverse(boolean toggled) {
		reverse = toggled;
		
		setToFile("reverse", toggled);
	}
	
	public boolean isReverseToggled() {
		return reverse;
	}
}
