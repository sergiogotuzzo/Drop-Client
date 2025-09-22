package drop.mods.impl;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collection;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import drop.gui.GuiSettings;
import drop.gui.hud.ScreenPosition;
import drop.mods.option.ParentOption;
import drop.mods.option.type.BooleanOption;
import drop.mods.option.type.BracketsOption;
import drop.mods.option.type.ColorOption;
import drop.mods.ModColor;
import drop.mods.ModDraggable;
import drop.mods.ModOptions;
import drop.mods.option.Brackets;

public class PotionEffects extends ModDraggable {
	public PotionEffects() {
		super(true, 0.5, 0.5);
		
		this.options = new ModOptions(
				new ColorOption(this, "durationTextColor", ModColor.fromColor(Color.WHITE, false), new GuiSettings(1, "Duration Text Color", true, false)),
				new BooleanOption(this, "durationTextShadow", true, new GuiSettings(2, "Duration Text Shadow")),
				new BooleanOption(this, "showName", true, new GuiSettings(3, "Show Name")),
				new ColorOption(this, "nameTextColor", ModColor.fromColor(Color.WHITE, false), new ParentOption("showName"), new GuiSettings(4, "Name Text Color", true, false)),
				new BooleanOption(this, "nameTextShadow", true, new ParentOption("showName"), new GuiSettings(5, "Name Text Shadow")),
				new BooleanOption(this, "showIcon", true, new GuiSettings(6, "Show Icon")),
				new BooleanOption(this, "blink", true, new GuiSettings(7, "Blink")),
				new BooleanOption(this, "showInInv", true, new GuiSettings(8, "Show In Inventory")),
				new BooleanOption(this, "reverse", false, new GuiSettings(false))
				);
				
		saveOptions();
	}
	
	private Collection<PotionEffect> dummyPotionEffects = Arrays.asList(new PotionEffect(Potion.moveSpeed.getId(), 20 * 60, 3), new PotionEffect(Potion.damageBoost.getId(), 20, 3));

	@Override
    public int getWidth() {
		int width = 2;
		
		if (options.getBooleanOption("showIcon").isToggled()) {
			width += 20;
		}
		
		if (options.getBooleanOption("showName").isToggled()) {
			width += font.getStringWidth("Strenght IV");
		} else {
			width += font.getStringWidth("00:00");
		}
		
        return width;
    }

    @Override
    public int getHeight() {    	
        return dummyPotionEffects.size() * 20;
    }

    @Override
    public void render(ScreenPosition pos) {
    	if (pos.getRelativeX() < 1.0 / 3.0 && options.getBooleanOption("reverse").isToggled()) {
    		options.getBooleanOption("reverse").toggle(false);
		} else if (pos.getRelativeX() > 2.0 / 3.0 && !options.getBooleanOption("reverse").isToggled()) {
			options.getBooleanOption("reverse").toggle(true);
		}
    	
        int offsetY = 0;

        for (int i = 0; i < mc.thePlayer.getActivePotionEffects().size(); i++) {
            PotionEffect potionEffect = (PotionEffect) mc.thePlayer.getActivePotionEffects().toArray()[i];

            drawPotionEffect(pos, offsetY, potionEffect);
            
            offsetY += 20;
        }
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
    	if (pos.getRelativeX() < 1.0 / 3.0 && options.getBooleanOption("reverse").isToggled()) {
    		options.getBooleanOption("reverse").toggle(false);
		} else if (pos.getRelativeX() > 2.0 / 3.0 && !options.getBooleanOption("reverse").isToggled()) {
			options.getBooleanOption("reverse").toggle(true);
		}
    	
        int offsetY = 0;

        for (int i = 0; i < dummyPotionEffects.size(); i++) {
            PotionEffect potionEffect = (PotionEffect) dummyPotionEffects.toArray()[i];

            drawPotionEffect(pos, offsetY, potionEffect);
            
            offsetY += 20;
        }
    }

    private void drawPotionEffect(ScreenPosition pos, int offsetY, PotionEffect potionEffect) {
        if (potionEffect != null) {
        	if (options.getBooleanOption("showIcon").isToggled()) {
            	Potion potion = Potion.potionTypes[potionEffect.getPotionID()];
                
                int iconX = (options.getBooleanOption("reverse").isToggled() ? pos.getAbsoluteX() + getWidth() - 20 : pos.getAbsoluteX());

                if (potion.hasStatusIcon()) {
                    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

                    mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/inventory.png"));
                    
                    int iconIndex = potion.getStatusIconIndex();
                      
                    drawTexturedModalRect(iconX, pos.getAbsoluteY() + offsetY + 2, iconIndex % 8 * 18, 198 + iconIndex / 8 * 18, 18, 18);
                }
            }
            
            int i = options.getBooleanOption("showIcon").isToggled() ? 20 : 0;
            
            if (options.getBooleanOption("showName").isToggled()) {
                String potionName = getPotionName(potionEffect);

                int nameX = (options.getBooleanOption("reverse").isToggled() ? pos.getAbsoluteX() + getWidth() - font.getStringWidth(potionName) - i - 2: pos.getAbsoluteX() + i + 2);

            	drawText(potionName, nameX, pos.getAbsoluteY() + offsetY + 2, options.getColorOption("nameTextColor").getColor(), options.getBooleanOption("nameTextShadow").isToggled());
            }
            
        	String durationString = Potion.getDurationString(potionEffect);
            int durationX = (options.getBooleanOption("reverse").isToggled() ? pos.getAbsoluteX() + getWidth() - font.getStringWidth(durationString) - i - 2: pos.getAbsoluteX() + i + 2);
            int durationY = pos.getAbsoluteY() + offsetY + font.FONT_HEIGHT + (options.getBooleanOption("showName").isToggled() ? 2 : -2);
            
            if (options.getBooleanOption("blink").isToggled()) {
        		if (potionEffect.getDuration() >= 20 * 10 || potionEffect.getDuration() % 20 < 10) {
            		drawText(durationString, durationX, durationY, options.getColorOption("durationTextColor").getColor(), options.getBooleanOption("durationTextShadow").isToggled());
                }
        	} else {
        		drawText(durationString, durationX, durationY, options.getColorOption("durationTextColor").getColor(), options.getBooleanOption("durationTextShadow").isToggled());
        	}
        }
    }
    
    private String getPotionName(PotionEffect potionEffect) {
    	String potionName = I18n.format(potionEffect.getEffectName(), new Object[0]);
    	
    	if (potionEffect.getAmplifier() == 1) {
    		potionName = potionName + " " + I18n.format("enchantment.level.2", new Object[0]);
        } else if (potionEffect.getAmplifier() == 2) {
        	potionName = potionName + " " + I18n.format("enchantment.level.3", new Object[0]);
        } else if (potionEffect.getAmplifier() == 3) {
        	potionName = potionName + " " + I18n.format("enchantment.level.4", new Object[0]);
        }
    	
    	return potionName;
    }
}
