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
		super(true, 0.5, 0.5);
		
		setVeryHighTextColor(getIntFromFile("veryHighTextColor", veryHighTextColor.getRGB()));
		setVeryHighTextChroma(getBooleanFromFile("veryHighTextChroma", veryHighTextColor.isChromaToggled()));
		setVeryHighTextShadow(getBooleanFromFile("veryHighTextShadow", veryHighTextShadow));
		setHighTextColor(getIntFromFile("highTextColor", highTextColor.getRGB()));
		setHighTextChroma(getBooleanFromFile("highTextChroma", highTextColor.isChromaToggled()));
		setHighTextShadow(getBooleanFromFile("highTextShadow", highTextShadow));
		setMediumTextColor(getIntFromFile("mediumTextColor", mediumTextColor.getRGB()));
		setMediumTextChroma(getBooleanFromFile("mediumTextChroma", mediumTextColor.isChromaToggled()));
		setMediumTextShadow(getBooleanFromFile("mediumTextShadow", mediumTextShadow));
		setLowTextColor(getIntFromFile("lowTextColor", lowTextColor.getRGB()));
		setLowTextChroma(getBooleanFromFile("lowTextChroma", lowTextColor.isChromaToggled()));
		setLowTextShadow(getBooleanFromFile("lowTextShadow", lowTextShadow));
		setVeryLowTextColor(getIntFromFile("veryLowTextColor", veryLowTextColor.getRGB()));
		setVeryLowTextChroma(getBooleanFromFile("veryLowTextChroma", veryLowTextColor.isChromaToggled()));
		setVeryLowTextShadow(getBooleanFromFile("veryLowTextShadow", veryLowTextShadow));
		setDynamicColors(getBooleanFromFile("dynamicColors", dynamicColors));
		setShowPercentage(getBooleanFromFile("showPercentage", showPercentage));
		setShowDamage(getBooleanFromFile("showDamage", showDamage));
		setShowMaxDamage(getBooleanFromFile("showMaxDamage", showMaxDamage));
		setEquippedItem(getBooleanFromFile("equippedItem", equippedItem));
		setDamageOverlays(getBooleanFromFile("damageOverlays", damageOverlays));
		setReverse(getBooleanFromFile("reverse", reverse));
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
		return 16 * (4 + (equippedItem ? 1 : 0));
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

	private void drawItemStack(ScreenPosition pos, int i, ItemStack itemStack) {
		if (itemStack != null) {
			GL11.glPushMatrix();
						
			double damagePercentage = ((itemStack.getMaxDamage() - itemStack.getItemDamage()) / (double) itemStack.getMaxDamage()) * 100;
			
			String text = "";
			
			if (showPercentage) {
				text = String.format("%.0f%%", damagePercentage);
			} else if (showDamage && !showMaxDamage) {
				text = String.valueOf(itemStack.getMaxDamage() - itemStack.getItemDamage());
			} else if (showDamage && showMaxDamage) {
				text = (itemStack.getMaxDamage() - itemStack.getItemDamage()) + "/" + itemStack.getMaxDamage();
			}
			
			ColorManager color = textColor; // Default
			boolean dropShadow = textShadow;
			
			if (dynamicColors) {				
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
			int damageX = reverse ? pos.getAbsoluteX() + getWidth() - font.getStringWidth(text) - 16 - 2 : pos.getAbsoluteX() + 16 + 2;
			int offsetY = (-16 * i) + getHeight() - 16;
			
			mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, itemX, pos.getAbsoluteY() + offsetY);
			
			if (itemStack.isStackable()) {
				mc.getRenderItem().renderItemOverlays(font, itemStack, itemX, pos.getAbsoluteY() + offsetY);
			}
			
			if ((showDamage || showPercentage) && itemStack.getItem().isDamageable()) {
				if (damageOverlays) {
					mc.getRenderItem().renderItemOverlays(font, itemStack, itemX, pos.getAbsoluteY() + offsetY);
				}
				
				drawText(text, damageX, pos.getAbsoluteY() + offsetY + 5, color, dropShadow);
			}
			
			GL11.glPopMatrix();
		}
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
	
	public void setVeryHighTextChroma(boolean toggled) {
		veryHighTextColor.setChromaToggled(toggled);
		
		setToFile("veryHighTextChroma", toggled);
	}
	
	public boolean isVeryHighTextChromaToggled() {
		return veryHighTextColor.isChromaToggled();
	}
	
	public void setVeryHighTextShadow(boolean toggled) {
		veryHighTextShadow = toggled;
		
		setToFile("veryHighTextShadow", toggled);
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
	
	public void setHighTextChroma(boolean toggled) {
		highTextColor.setChromaToggled(toggled);
		
		setToFile("highTextChroma", toggled);
	}
	
	public boolean isHighTextChromaToggled() {
		return highTextColor.isChromaToggled();
	}
	
	public void setHighTextShadow(boolean toggled) {
		highTextShadow = toggled;
		
		setToFile("highTextShadow", toggled);
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
	
	public void setMediumTextChroma(boolean toggled) {
		mediumTextColor.setChromaToggled(toggled);
		
		setToFile("mediumTextChroma", toggled);
	}
	
	public boolean isMediumTextChromaToggled() {
		return mediumTextColor.isChromaToggled();
	}
	
	public void setMediumTextShadow(boolean toggled) {
		mediumTextShadow = toggled;
		
		setToFile("mediumTextShadow", toggled);
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
	
	public void setLowTextChroma(boolean toggled) {
		lowTextColor.setChromaToggled(toggled);
		
		setToFile("lowTextChroma", toggled);
	}
	
	public boolean isLowTextChromaToggled() {
		return lowTextColor.isChromaToggled();
	}
	
	public void setLowTextShadow(boolean toggled) {
		lowTextShadow = toggled;
		
		setToFile("lowTextShadow", toggled);
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
	
	public void setVeryLowTextChroma(boolean toggled) {
		veryLowTextColor.setChromaToggled(toggled);
		
		setToFile("veryLowTextChroma", toggled);
	}
	
	public boolean isVeryLowTextChromaToggled() {
		return veryLowTextColor.isChromaToggled();
	}
	
	public void setVeryLowTextShadow(boolean toggled) {
		veryLowTextShadow = toggled;
		
		setToFile("veryLowTextShadow", toggled);
	}
	
	public boolean isVeryLowTextShadowToggled() {
		return veryLowTextShadow;
	}
	
	public void setDynamicColors(boolean toggled) {
		dynamicColors = toggled;
		
		setToFile("dynamicColors", toggled);
	}
	
	public boolean isDynamicColorsToggled() {
		return dynamicColors;
	}
	
	public void setShowPercentage(boolean toggled) {
		showPercentage = toggled;
		
		setToFile("showPercentage", toggled);
	}
	
	public boolean isShowPercentageToggled() {
		return showPercentage;
	}
	
	public void setShowDamage(boolean toggled) {
		showDamage = toggled;
		
		setToFile("showDamage", toggled);
	}
	
	public boolean isShowDamageToggled() {
		return showDamage;
	}
	
	public void setShowMaxDamage(boolean toggled) {
		showMaxDamage = toggled;
		
		setToFile("showMaxDamage", toggled);
	}
	
	public boolean isShowMaxDamageToggled() {
		return showMaxDamage;
	}
	
	public void setEquippedItem(boolean toggled) {
		equippedItem = toggled;
		
		setToFile("equippedItem", toggled);
	}
	
	public boolean isEquippedItemToggled() {
		return equippedItem;
	}
	
	public void setDamageOverlays(boolean toggled) {
		damageOverlays = toggled;
		
		setToFile("damageOverlays", toggled);
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
