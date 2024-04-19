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
		PERCENTAGE,
		DAMAGE,
		DAMAGE_MAX_DAMAGE
	}
	
	private ColorManager color = new ColorManager(new Color(255, 255, 255, 255));
	private boolean shadow = true;
	private boolean dynamicColors = true;
	private DamageMode mode = DamageMode.DAMAGE;
	
	@Override
	public int getWidth() {
		int width = 0;
		
		if (mode == DamageMode.PERCENTAGE) {
			width = 48;
		} else if (mode == DamageMode.DAMAGE) {
			width = 40;
		} else if (mode == DamageMode.DAMAGE_MAX_DAMAGE) {
			return 64;
		}
		
		return width;
	}

	@Override
	public int getHeight() {
		return 64;
	}

	@Override
	public void render(ScreenPosition pos) {
		for (int i = 0; i < mc.thePlayer.inventory.armorInventory.length; i++) {
			ItemStack itemStack = mc.thePlayer.inventory.armorInventory[i];
			
			renderItemStack(pos, i, itemStack);
		}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		renderItemStack(pos, 3, new ItemStack(Items.diamond_helmet));
		renderItemStack(pos, 2, new ItemStack(Items.diamond_chestplate));
		renderItemStack(pos, 1, new ItemStack(Items.diamond_leggings));
		renderItemStack(pos, 0, new ItemStack(Items.diamond_boots));
	}

	private void renderItemStack(ScreenPosition pos, int i, ItemStack is) {
		if (is == null) {
			return;
		}
		
		GL11.glPushMatrix();
		
		int yAdd = (-16 * i) + 48;
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
				font.drawStringWithShadow(getDamageText(is), pos.getAbsoluteX() + 20, pos.getAbsoluteY() + yAdd + 5, dynamicColors ? dynamicColor.getRGB() : color.getRGB());
			} else {
				font.drawString(getDamageText(is), pos.getAbsoluteX() + 20, pos.getAbsoluteY() + yAdd + 5, dynamicColors ? dynamicColor.getRGB() : color.getRGB());
			}
		}
		
		RenderHelper.enableGUIStandardItemLighting();
		
		mc.getRenderItem().renderItemAndEffectIntoGUI(is, pos.getAbsoluteX(), pos.getAbsoluteY() + yAdd);
		
		GL11.glPopMatrix();
	}
	
	private String getDamageText(ItemStack is) {
		if (mode == DamageMode.PERCENTAGE) {
			return String.format("%.0f%%", getDamagePercentage(is));
		} else if (mode == DamageMode.DAMAGE) {
			return "" + (is.getMaxDamage() - is.getItemDamage());
		} else if (mode == DamageMode.DAMAGE_MAX_DAMAGE) {
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
	
	public void setMode(DamageMode mode) {
		this.mode = mode;
	}
	
	public DamageMode getMode() {
		return mode;
	}
	
	public int getModeIndex() {
		if (getMode() == DamageMode.PERCENTAGE) {
        	return 1;
        } else if (getMode() == DamageMode.DAMAGE) {
        	return 2;
        } else if (getMode() == DamageMode.DAMAGE_MAX_DAMAGE) {
        	return 3;
        }
		
		return 0;
	}
}
