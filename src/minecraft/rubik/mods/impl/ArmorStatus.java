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
	public static enum DamageMode {
		PERCENTAGE(48),
		DAMAGE(40),
		DAMAGE_MAX_DAMAGE(64);
		
		private int width = 0;
		
		private DamageMode(int width) {
			this.width = width;
		}
		
		public int getWidth() {
			return width;
		}
	}
	
	public static enum ArmorStatusMode {
		LEFT,
		RIGHT
	}
	
	private ColorManager color = new ColorManager(new Color(255, 255, 255, 255));
	private boolean shadow = true;
	private boolean dynamicColors = true;
	private DamageMode damageMode = DamageMode.DAMAGE;
	private ArmorStatusMode mode = ArmorStatusMode.LEFT;
	private boolean showCurrentItem = false;
	
	private final int ITEM_HEIGHT = 16;
	
	@Override
	public int getWidth() {
		return damageMode.getWidth();
	}

	@Override
	public int getHeight() {
		return showCurrentItem ? ITEM_HEIGHT * 5 : ITEM_HEIGHT * 4;
	}

	@Override
	public void render(ScreenPosition pos) {
		for (int i = 0; i < mc.thePlayer.inventory.armorInventory.length; i++) {
			ItemStack itemStack = mc.thePlayer.inventory.armorInventory[i];
			
			if (showCurrentItem) {
				if (mc.thePlayer.inventory.getCurrentItem() == null) {
					renderItemStack(pos, i, itemStack);
				} else {
					renderItemStack(pos, 0, mc.thePlayer.inventory.getCurrentItem());
					renderItemStack(pos, i + 1, itemStack);
				}
			} else {
				renderItemStack(pos, i, itemStack);
			}
		}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		if (showCurrentItem) {
			renderItemStack(pos, 4, new ItemStack(Items.diamond_helmet));
			renderItemStack(pos, 3, new ItemStack(Items.diamond_chestplate));
			renderItemStack(pos, 2, new ItemStack(Items.diamond_leggings));
			renderItemStack(pos, 1, new ItemStack(Items.diamond_boots));
			renderItemStack(pos, 0, new ItemStack(Items.compass, 1));
		} else {
			renderItemStack(pos, 3, new ItemStack(Items.diamond_helmet));
			renderItemStack(pos, 2, new ItemStack(Items.diamond_chestplate));
			renderItemStack(pos, 1, new ItemStack(Items.diamond_leggings));
			renderItemStack(pos, 0, new ItemStack(Items.diamond_boots));
		}
	}

	private void renderItemStack(ScreenPosition pos, int i, ItemStack is) {
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
		
		if (is.getItem().isDamageable()) {
			if (shadow) {
				if (mode == ArmorStatusMode.LEFT) {
					font.drawStringWithShadow(getDamageText(is), pos.getAbsoluteX() + 20, pos.getAbsoluteY() + yAdd + 5, dynamicColors ? dynamicColor.getRGB() : color.getRGB());
				} else if (mode == ArmorStatusMode.RIGHT) {
					font.drawStringWithShadow(getDamageText(is), pos.getAbsoluteX() + getWidth() - font.getStringWidth(getDamageText(is)) - 16, pos.getAbsoluteY() + yAdd + 5, dynamicColors ? dynamicColor.getRGB() : color.getRGB());
				}
			} else {
				if (mode == ArmorStatusMode.LEFT) {
					font.drawString(getDamageText(is), pos.getAbsoluteX() + 20, pos.getAbsoluteY() + yAdd + 5, dynamicColors ? dynamicColor.getRGB() : color.getRGB());
				} else if (mode == ArmorStatusMode.RIGHT) {
					font.drawString(getDamageText(is), pos.getAbsoluteX() + getWidth() - font.getStringWidth(getDamageText(is)) - 16, pos.getAbsoluteY() + yAdd + 5, dynamicColors ? dynamicColor.getRGB() : color.getRGB());
				}
			}
		} else if (is.isStackable()) {
			if (shadow) {
				if (mode == ArmorStatusMode.LEFT) {
					font.drawStringWithShadow("" + is.stackSize, pos.getAbsoluteX() + 20, pos.getAbsoluteY() + yAdd + 5, dynamicColors ? Color.WHITE.getRGB() : color.getRGB());
				} else if (mode == ArmorStatusMode.RIGHT) {
					font.drawStringWithShadow("" + is.stackSize, pos.getAbsoluteX() + getWidth() - font.getStringWidth("" + is.stackSize) - 16, pos.getAbsoluteY() + yAdd + 5, dynamicColors ? Color.WHITE.getRGB() : color.getRGB());
				}
			} else {
				if (mode == ArmorStatusMode.LEFT) {
					font.drawString("" + is.stackSize, pos.getAbsoluteX() + 20, pos.getAbsoluteY() + yAdd + 5, dynamicColors ? Color.WHITE.getRGB() : color.getRGB());
				} else if (mode == ArmorStatusMode.RIGHT) {
					font.drawString("" + is.stackSize, pos.getAbsoluteX() + getWidth() - font.getStringWidth(getDamageText(is)) - 16, pos.getAbsoluteY() + yAdd + 5, dynamicColors ? Color.WHITE.getRGB() : color.getRGB());
				}
			}
		}
		
		RenderHelper.enableGUIStandardItemLighting();
		
		if (mode == ArmorStatusMode.LEFT) {
			mc.getRenderItem().renderItemAndEffectIntoGUI(is, pos.getAbsoluteX() + 2, pos.getAbsoluteY() + yAdd);
		} else if (mode == ArmorStatusMode.RIGHT) {
			mc.getRenderItem().renderItemAndEffectIntoGUI(is, pos.getAbsoluteX() + 20 + 4, pos.getAbsoluteY() + yAdd);
		}
		
		GL11.glPopMatrix();
	}
	
	private String getDamageText(ItemStack is) {
		if (damageMode == DamageMode.PERCENTAGE) {
			return String.format("%.0f%%", getDamagePercentage(is));
		} else if (damageMode == DamageMode.DAMAGE) {
			return "" + (is.getMaxDamage() - is.getItemDamage());
		} else if (damageMode == DamageMode.DAMAGE_MAX_DAMAGE) {
			return (is.getMaxDamage() - is.getItemDamage()) + "/" + is.getMaxDamage();
		}
		
		return null;
	}
	
	private double getDamagePercentage(ItemStack is) {
		return ((is.getMaxDamage() - is.getItemDamage()) / (double) is.getMaxDamage()) * 100;
	}
	
	public ColorManager getColorManager() {
		return color;
	}
	
	public Color getColor() {
		return color.getColor();
	}
	
	public void setShadowEnabled(boolean enabled) {
		shadow = enabled;
	}
	
	public boolean isShadowEnabled() {
		return shadow;
	}
	
	public void setDynamicColorsEnabled(boolean enabled) {
		dynamicColors = enabled;
	}
	
	public boolean isDynamicColorsEnabled() {
		return dynamicColors;
	}
	
	public void setDamageMode(DamageMode mode) {
		this.damageMode = mode;
	}
	
	public DamageMode getDamageMode() {
		return damageMode;
	}
	
	public int getDamageModeIndex() {
		if (damageMode == DamageMode.PERCENTAGE) {
        	return 1;
        } else if (damageMode == DamageMode.DAMAGE) {
        	return 2;
        } else if (damageMode == DamageMode.DAMAGE_MAX_DAMAGE) {
        	return 3;
        }
		
		return 0;
	}
	
	public void setMode(ArmorStatusMode mode) {
		this.mode = mode;
	}
	
	public ArmorStatusMode getMode() {
		return mode;
	}
	
	public void setShowCurrentItemEnabled(boolean enabled) {
		showCurrentItem = enabled;
	}
	
	public boolean isShowCurrentItemEnabled() {
		return showCurrentItem;
	}
}
