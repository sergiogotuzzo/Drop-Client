package drop.mods.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import drop.gui.GuiDropClientScreen;
import drop.gui.GuiSettings;
import drop.gui.mod.armorstatus.GuiArmorStatus;
import drop.mods.ModColor;
import drop.mods.ModDraggable;
import drop.mods.ModOptions;
import drop.gui.hud.ScreenPosition;
import drop.mods.option.ParentOption;
import drop.mods.option.type.BooleanOption;
import drop.mods.option.type.ColorOption;

public class ArmorStatus extends ModDraggable {
	public ArmorStatus() {
		super(true, 0.5, 0.5);
		
		this.options = new ModOptions(
				new ColorOption(this, "textColor", ModColor.fromColor(Color.WHITE, false), new GuiSettings(1, "Text Color", true, false)),
				new BooleanOption(this, "textShadow", true, new GuiSettings(2, "Text Shadow")),
				new BooleanOption(this, "dynamicColors", true, new GuiSettings(false, 3, "Dynamic Colors")),
				new ColorOption(this, "veryHighTextColor", ModColor.fromRGB(85, 255, 85, false), new GuiSettings(false, 4, "Very High Text Color", true, false)),
				new BooleanOption(this, "veryHighTextShadow", true, new GuiSettings(false, 5, "Very High Text Shadow")),
				new ColorOption(this, "highTextColor", ModColor.fromRGB(255, 255, 85, false), new GuiSettings(false, 6, "High Text Color", true, false)),
				new BooleanOption(this, "highTextShadow", true, new GuiSettings(false, 7, "High Text Shadow")),
				new ColorOption(this, "mediumTextColor", ModColor.fromRGB(255, 170, 0, false), new GuiSettings(false, 8, "Medium Text Color", true, false)),
				new BooleanOption(this, "mediumTextShadow", true, new GuiSettings(false, 9, "Medium Text Shadow")),
				new ColorOption(this, "lowTextColor", ModColor.fromRGB(255, 85, 85, false), new GuiSettings(false, 10, "Low Text Color", true, false)),
				new BooleanOption(this, "lowTextShadow", true, new GuiSettings(false, 11, "Low Text Shadow")),
				new ColorOption(this, "veryLowTextColor", ModColor.fromRGB(170, 0, 0, false), new GuiSettings(false, 12, "Very Low Text Color", true, false)),
				new BooleanOption(this, "veryLowTextShadow", true, new GuiSettings(false, 13, "Very Low Text Shadow")),
				new BooleanOption(this, "showPercentage", false, new GuiSettings(14, "Show Percentage")),
				new BooleanOption(this, "showDamage", true, new GuiSettings(15, "Show Damage")),
				new BooleanOption(this, "showMaxDamage", false, new ParentOption("showDamage"), new GuiSettings(16, "Show Max Damage")),
				new BooleanOption(this, "armor", true, new GuiSettings(19, "Show Armor")),
				new BooleanOption(this, "equippedItem", false, new GuiSettings(17, "Show Equipped Item")),
				new BooleanOption(this, "damageOverlays", true, new GuiSettings(18, "Damage Overlays")),
				new BooleanOption(this, "reverse", false, new GuiSettings(false))
				);
				
		saveOptions();
	}

	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiArmorStatus(previousGuiScreen);
	}
	
	@Override
	public int getWidth() {
		int width = 0;
		
		if (options.getBooleanOption("showPercentage").isToggled()) {
			width = font.getStringWidth("100%");
		} else if (options.getBooleanOption("showDamage").isToggled() && !options.getBooleanOption("showMaxDamage").isToggled()) {
			width = (options.getBooleanOption("equippedItem").isToggled() ? font.getStringWidth("1561") : font.getStringWidth("528"));
		} else if (options.getBooleanOption("showDamage").isToggled() && options.getBooleanOption("showMaxDamage").isToggled()) {
			width = (options.getBooleanOption("equippedItem").isToggled() ? font.getStringWidth("1561/1561") : font.getStringWidth("528/528"));
		}
		
		return 16 + 2 + width;
	}

	@Override
	public int getHeight() {
		return 16 * (4 + (options.getBooleanOption("equippedItem").isToggled() ? 1 : 0));
	}

	@Override
	public void render(ScreenPosition pos) {
		if (pos.getRelativeX() < 1.0 / 3.0 && options.getBooleanOption("reverse").isToggled()) {
    		options.getBooleanOption("reverse").toggle(false);
		} else if (pos.getRelativeX() > 2.0 / 3.0 && !options.getBooleanOption("reverse").isToggled()) {
			options.getBooleanOption("reverse").toggle(true);
		}
		
		int i = 0;
		
		for (ItemStack itemStack : getPlayerInventory()) {
			drawItemStack(pos, i, itemStack);
			
			i++;
		}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		if (pos.getRelativeX() < 1.0 / 3.0 && options.getBooleanOption("reverse").isToggled()) {
    		options.getBooleanOption("reverse").toggle(false);
		} else if (pos.getRelativeX() > 2.0 / 3.0 && !options.getBooleanOption("reverse").isToggled()) {
			options.getBooleanOption("reverse").toggle(true);
		}
		
		Collection<ItemStack> dummyPlayerInventory = new ArrayList<ItemStack>();
		
		if (options.getBooleanOption("equippedItem").isToggled()) {
			dummyPlayerInventory.add(new ItemStack(Items.diamond_sword));
		}
		
		if (options.getBooleanOption("armor").isToggled()) {
			dummyPlayerInventory.add(new ItemStack(Items.diamond_boots));
			dummyPlayerInventory.add(new ItemStack(Items.diamond_leggings));
			dummyPlayerInventory.add(new ItemStack(Items.diamond_chestplate));
			dummyPlayerInventory.add(new ItemStack(Items.diamond_helmet));
		}
		
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
			
			if (options.getBooleanOption("showPercentage").isToggled()) {
				text = String.format("%.0f%%", damagePercentage);
			} else if (options.getBooleanOption("showDamage").isToggled() && !options.getBooleanOption("showMaxDamage").isToggled()) {
				text = String.valueOf(itemStack.getMaxDamage() - itemStack.getItemDamage());
			} else if (options.getBooleanOption("showDamage").isToggled() && options.getBooleanOption("showMaxDamage").isToggled()) {
				text = (itemStack.getMaxDamage() - itemStack.getItemDamage()) + "/" + itemStack.getMaxDamage();
			}
			
			ModColor color = options.getColorOption("textColor").getColor(); // Default
			boolean dropShadow = options.getBooleanOption("textShadow").isToggled();
			
			if (options.getBooleanOption("dynamicColors").isToggled()) {				
				if (damagePercentage <= 10) {
			        color = options.getColorOption("veryLowTextColor").getColor(); // Very low
			        dropShadow = options.getBooleanOption("veryLowTextShadow").isToggled();
			    } else if (damagePercentage <= 25) {
			        color = options.getColorOption("lowTextColor").getColor(); // Low
			        dropShadow = options.getBooleanOption("lowTextShadow").isToggled();
			    } else if (damagePercentage <= 40) {
			        color = options.getColorOption("mediumTextColor").getColor(); // Medium
			        dropShadow = options.getBooleanOption("mediumTextShadow").isToggled();
			    } else if (damagePercentage <= 60) {
			        color = options.getColorOption("highTextColor").getColor(); // High
			        dropShadow = options.getBooleanOption("highTextShadow").isToggled();
			    } else if (damagePercentage <= 80) {
			        color = options.getColorOption("veryHighTextColor").getColor(); // Very high
			        dropShadow = options.getBooleanOption("veryHighTextShadow").isToggled();
			    }
			}
			
			RenderHelper.enableGUIStandardItemLighting();
			
			int itemX = options.getBooleanOption("reverse").isToggled() ? pos.getAbsoluteX() + getWidth() - 16 : pos.getAbsoluteX();
			int damageX = options.getBooleanOption("reverse").isToggled() ? pos.getAbsoluteX() + getWidth() - font.getStringWidth(text) - 16 - 2 : pos.getAbsoluteX() + 16 + 2;
			int offsetY = (-16 * i) + getHeight() - 16;
			
			mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, itemX, pos.getAbsoluteY() + offsetY);
			
			if (itemStack.isStackable()) {
				mc.getRenderItem().renderItemOverlays(font, itemStack, itemX, pos.getAbsoluteY() + offsetY);
			}
			
			if ((options.getBooleanOption("showDamage").isToggled() || options.getBooleanOption("showPercentage").isToggled()) && itemStack.getItem().isDamageable()) {
				if (options.getBooleanOption("damageOverlays").isToggled()) {
					mc.getRenderItem().renderItemOverlays(font, itemStack, itemX, pos.getAbsoluteY() + offsetY);
				}
				
				drawText(text, damageX, pos.getAbsoluteY() + offsetY + 5, color, dropShadow);
			}
			
			GL11.glPopMatrix();
		}
	}
	
	private Collection<ItemStack> getPlayerInventory() {
		Collection<ItemStack> playerInventory = new ArrayList<ItemStack>();
		
		if (options.getBooleanOption("equippedItem").isToggled()) {
			if (mc.thePlayer.inventory.getCurrentItem() != null) {
				playerInventory.add(mc.thePlayer.inventory.getCurrentItem());
			}
		}
		
		if (options.getBooleanOption("armor").isToggled()) {
			for (ItemStack itemStack : mc.thePlayer.inventory.armorInventory) {
				if (itemStack != null) {
					playerInventory.add(itemStack);
				}
			}
		}
		
		return playerInventory;
	}
}
