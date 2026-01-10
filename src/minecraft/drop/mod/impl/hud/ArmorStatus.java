package drop.mod.impl.hud;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import drop.gui.GuiScreenDC;
import drop.gui.mod.GuiSettings;
import drop.gui.mod.hud.ScreenPosition;
import drop.gui.mod.impl.armorstatus.GuiArmorStatus;
import drop.mod.ModDraggable;
import drop.mod.option.ModColor;
import drop.mod.option.ParentOption;
import drop.mod.option.type.BooleanOption;
import drop.mod.option.type.ColorOption;

public class ArmorStatus extends ModDraggable {
	public ArmorStatus() {
		super(true, 0.5, 0.5);
				
		saveOptions(
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
				new BooleanOption(this, "armor", true, new GuiSettings(19, "Armor")),
				new BooleanOption(this, "equippedItem", false, new GuiSettings(17, "Equipped Item")),
				new BooleanOption(this, "damageOverlays", true, new GuiSettings(18, "Damage Overlays")),
				new BooleanOption(this, "reverse", false, new GuiSettings(false))
				);
	}

	@Override
	public GuiScreenDC getGui(GuiScreenDC previousGuiScreen) {
		return new GuiArmorStatus(previousGuiScreen);
	}
	
	@Override
	public int getWidth() {
		int width = 0;
		
		if (getBooleanOption("showPercentage").isToggled()) {
			width = font.getStringWidth("100%");
		} else if (getBooleanOption("showDamage").isToggled() && !getBooleanOption("showMaxDamage").isToggled()) {
			width = (getBooleanOption("equippedItem").isToggled() ? font.getStringWidth("1561") : font.getStringWidth("528"));
		} else if (getBooleanOption("showDamage").isToggled() && getBooleanOption("showMaxDamage").isToggled()) {
			width = (getBooleanOption("equippedItem").isToggled() ? font.getStringWidth("1561/1561") : font.getStringWidth("528/528"));
		}
		
		return 16 + 2 + width;
	}

	@Override
	public int getHeight() {
		return 16 * ((getBooleanOption("armor").isToggled() ? 4 : 0) + (getBooleanOption("equippedItem").isToggled() ? 1 : 0));
	}

	@Override
	public void render(ScreenPosition pos) {
		if (pos.getRelativeX() < 1.0 / 3.0 && getBooleanOption("reverse").isToggled()) {
    		getBooleanOption("reverse").toggle(false);
		} else if (pos.getRelativeX() > 2.0 / 3.0 && !getBooleanOption("reverse").isToggled()) {
			getBooleanOption("reverse").toggle(true);
		}
		
		int i = 0;
		
		if (getBooleanOption("equippedItem").isToggled() && mc.thePlayer.inventory.getCurrentItem() != null) {
			drawItemStack(pos, i, mc.thePlayer.inventory.getCurrentItem());
			
			i++;
		}
		
		if (getBooleanOption("armor").isToggled()) {
			for (ItemStack itemStack : mc.thePlayer.inventory.armorInventory) {
				if (itemStack != null) {
					drawItemStack(pos, i, itemStack);
					
					i++;
				}
			}
		}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		if (pos.getRelativeX() < 1.0 / 3.0 && getBooleanOption("reverse").isToggled()) {
    		getBooleanOption("reverse").toggle(false);
		} else if (pos.getRelativeX() > 2.0 / 3.0 && !getBooleanOption("reverse").isToggled()) {
			getBooleanOption("reverse").toggle(true);
		}
				
		if (getBooleanOption("equippedItem").isToggled()) {
			drawItemStack(pos, 0, new ItemStack(Items.diamond_sword));
		}
		
		if (getBooleanOption("armor").isToggled()) {
			drawItemStack(pos, 0 + (getBooleanOption("equippedItem").isToggled() ? 1 : 0), new ItemStack(Items.diamond_boots));
			drawItemStack(pos, 1 + (getBooleanOption("equippedItem").isToggled() ? 1 : 0), new ItemStack(Items.diamond_leggings));
			drawItemStack(pos, 2 + (getBooleanOption("equippedItem").isToggled() ? 1 : 0), new ItemStack(Items.diamond_chestplate));
			drawItemStack(pos, 3 + (getBooleanOption("equippedItem").isToggled() ? 1 : 0), new ItemStack(Items.diamond_helmet));
		}
	}

	private void drawItemStack(ScreenPosition pos, int i, ItemStack itemStack) {
		if (itemStack != null) {
			GL11.glPushMatrix();
						
			double damagePercentage = ((itemStack.getMaxDamage() - itemStack.getItemDamage()) / (double) itemStack.getMaxDamage()) * 100;
			
			String text = "";
			
			if (getBooleanOption("showPercentage").isToggled()) {
				text = String.format("%.0f%%", damagePercentage);
			} else if (getBooleanOption("showDamage").isToggled() && !getBooleanOption("showMaxDamage").isToggled()) {
				text = String.valueOf(itemStack.getMaxDamage() - itemStack.getItemDamage());
			} else if (getBooleanOption("showDamage").isToggled() && getBooleanOption("showMaxDamage").isToggled()) {
				text = (itemStack.getMaxDamage() - itemStack.getItemDamage()) + "/" + itemStack.getMaxDamage();
			}
			
			ModColor color = getColorOption("textColor").getColor();
			boolean dropShadow = getBooleanOption("textShadow").isToggled();
			
			if (getBooleanOption("dynamicColors").isToggled()) {				
				if (damagePercentage <= 10) {
			        color = getColorOption("veryLowTextColor").getColor();
			        dropShadow = getBooleanOption("veryLowTextShadow").isToggled();
			    } else if (damagePercentage <= 25) {
			        color = getColorOption("lowTextColor").getColor();
			        dropShadow = getBooleanOption("lowTextShadow").isToggled();
			    } else if (damagePercentage <= 40) {
			        color = getColorOption("mediumTextColor").getColor();
			        dropShadow = getBooleanOption("mediumTextShadow").isToggled();
			    } else if (damagePercentage <= 60) {
			        color = getColorOption("highTextColor").getColor();
			        dropShadow = getBooleanOption("highTextShadow").isToggled();
			    } else if (damagePercentage <= 80) {
			        color = getColorOption("veryHighTextColor").getColor();
			        dropShadow = getBooleanOption("veryHighTextShadow").isToggled();
			    }
			}
			
			RenderHelper.enableGUIStandardItemLighting();
			
			int itemX = getBooleanOption("reverse").isToggled() ? pos.getAbsoluteX() + getWidth() - 16 : pos.getAbsoluteX();
			int damageX = getBooleanOption("reverse").isToggled() ? pos.getAbsoluteX() + getWidth() - font.getStringWidth(text) - 16 - 2 : pos.getAbsoluteX() + 16 + 2;
			int offsetY = (-16 * i) + getHeight() - 16;
			
			mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, itemX, pos.getAbsoluteY() + offsetY);
			
			if (itemStack.isStackable()) {
				mc.getRenderItem().renderItemOverlays(font, itemStack, itemX, pos.getAbsoluteY() + offsetY);
			}
			
			if ((getBooleanOption("showDamage").isToggled() || getBooleanOption("showPercentage").isToggled()) && itemStack.getItem().isDamageable()) {
				if (getBooleanOption("damageOverlays").isToggled()) {
					mc.getRenderItem().renderItemOverlays(font, itemStack, itemX, pos.getAbsoluteY() + offsetY);
				}
				
				drawText(text, damageX, pos.getAbsoluteY() + offsetY + 5, color, dropShadow);
			}
			
			GL11.glPopMatrix();
		}
	}
}
