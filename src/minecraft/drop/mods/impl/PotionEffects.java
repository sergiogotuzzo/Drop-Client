package drop.mods.impl;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collection;

import drop.ColorManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiPotionEffects;
import drop.mods.hud.ScreenPosition;
import drop.mods.ModDraggable;

public class PotionEffects extends ModDraggable {
	private Collection<PotionEffect> dummyPotionEffects = Arrays.asList(new PotionEffect(Potion.moveSpeed.getId(), 20 * 60, 3), new PotionEffect(Potion.damageBoost.getId(), 20, 3));
	
    private boolean blink = true;
	private boolean showIcon = true;
	private boolean showBackground = true;
    private boolean durationTextShadow = true;
    private ColorManager durationTextColor = ColorManager.fromColor(Color.WHITE, false);
    private boolean showName = true;
    private boolean nameTextShadow = true;
    private ColorManager nameTextColor = ColorManager.fromColor(Color.WHITE, false);
	private boolean reverse = false;
	
	public PotionEffects() {
		setBlink((boolean) getFromFile("blink", blink));
		setShowIcon((boolean) getFromFile("showIcon", showIcon));
		setShowBackground((boolean) getFromFile("showBackground", showBackground));
		setDurationTextShadow((boolean) getFromFile("durationTextShadow", durationTextShadow));
		setDurationTextColor((int) ((long) getFromFile("durationTextColor", durationTextColor.getRGB())));
		setDurationTextChroma((boolean) getFromFile("durationTextChroma", durationTextColor.isChromaToggled()));
    	setShowName((boolean) getFromFile("showName", showName));
    	setNameTextShadow((boolean) getFromFile("nameTextShadow", nameTextShadow));
		setNameTextColor((int) ((long) getFromFile("nameTextColor", nameTextColor.getRGB())));
		setNameTextChroma((boolean) getFromFile("nameTextChroma", nameTextColor.isChromaToggled()));
		setReverse((boolean) getFromFile("reverse", reverse));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiPotionEffects(previousGuiScreen);
	}

	@Override
    public int getWidth() {
		int width = 2;
		
		if (showIcon) {
			width += 20;
		}
		
		if (showName) {
			width += font.getStringWidth(getLongestEffectName(getPlayerPotionEffects()));
		} else {
			width += font.getStringWidth("00:00");
		}
		
		if (showBackground) {
			width += 8;
		}
		
        return width;
    }

    @Override
    public int getHeight() {    	
        return getPlayerPotionEffects().size() * 20 + (showBackground ? 8 : 0);
    }

    @Override
    public void render(ScreenPosition pos) {
    	if (pos.getRelativeX() < 1.0 / 3.0 && reverse) {
			setReverse(false);
		} else if (pos.getRelativeX() > 2.0 / 3.0 && !reverse) {
			setReverse(true);
		}
    	
        int offsetY = 0;

        for (int i = 0; i < mc.thePlayer.getActivePotionEffects().size(); i++) {
            PotionEffect potionEffect = (PotionEffect) mc.thePlayer.getActivePotionEffects().toArray()[i];

            drawPotionEffect(pos, offsetY, potionEffect);
            
            offsetY += (20 + (showBackground ? 4 : 0));
        }
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
    	if (pos.getRelativeX() < 1.0 / 3.0 && reverse) {
			setReverse(false);
		} else if (pos.getRelativeX() > 2.0 / 3.0 && !reverse) {
			setReverse(true);
		}
    	
        int offsetY = 0;

        for (int i = 0; i < getPlayerPotionEffects().size(); i++) {
            PotionEffect potionEffect = (PotionEffect) getPlayerPotionEffects().toArray()[i];

            drawPotionEffect(pos, offsetY, potionEffect);
            
            offsetY += (20 + (showBackground ? 4 : 0));
        }
    }
    
    private Collection<PotionEffect> getPlayerPotionEffects() {
    	return mc.thePlayer.getActivePotionEffects().size() == 0 ? dummyPotionEffects : mc.thePlayer.getActivePotionEffects();
    }
    
    private String getLongestEffectName(Collection<PotionEffect> effects) {
        if (effects == null || effects.isEmpty()) {
            return null;
        }
        
        String longestText = "";
        
        for (PotionEffect effect : effects) {
            String effectName = getPotionName(effect);
            
            if (font.getStringWidth(effectName) > font.getStringWidth(longestText)) {
                longestText = effectName;
            }
        }
        
        return longestText;
    }

    private void drawPotionEffect(ScreenPosition pos, int offsetY, PotionEffect pe) {
        if (pe == null) {
            return;
        }
        
        if (showBackground) {
        	drawRect(pos.getAbsoluteX(), pos.getAbsoluteY() + offsetY, pos.getAbsoluteX() + getWidth(), pos.getAbsoluteY() + offsetY + 24);
        }

        if (showIcon) {
        	Potion potion = Potion.potionTypes[pe.getPotionID()];
            
            int iconX = (reverse ? pos.getAbsoluteX() + getWidth() - 20 + (showBackground ? -4 : 0) : pos.getAbsoluteX() + (showBackground ? 4 : 0));

            if (potion.hasStatusIcon()) {
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

                mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/inventory.png"));
                
                int iconIndex = potion.getStatusIconIndex();
                  
                drawTexturedModalRect(iconX, pos.getAbsoluteY() + offsetY + 2 + (showBackground ? 2 : 0), iconIndex % 8 * 18, 198 + iconIndex / 8 * 18, 18, 18);
            }
        }

        String potionName = getPotionName(pe);
        String durationString = Potion.getDurationString(pe);
        
        int i = showIcon ? 20 : 0;
        
        if (showName) {
            int nameX = (reverse ? pos.getAbsoluteX() + getWidth() - font.getStringWidth(potionName) - i - 2 + (showBackground ? -2 : 0): pos.getAbsoluteX() + i + 2 + (showBackground ? 2 : 0));

        	drawText(potionName, nameX, pos.getAbsoluteY() + offsetY + 2 + (showBackground ? 2 : 0), nameTextColor, nameTextShadow);
        }
        
        int durationX = (reverse ? pos.getAbsoluteX() + getWidth() - font.getStringWidth(durationString) - i - 2 + (showBackground ? -2 : 0): pos.getAbsoluteX() + i + 2 + (showBackground ? 2 : 0));
        int durationY = pos.getAbsoluteY() + offsetY + font.FONT_HEIGHT + (showName ? 2 : -2) + (showBackground ? 2 : 0);
        
        if (blink) {
    		if (pe.getDuration() >= 20 * 10 || pe.getDuration() % 20 < 10) {
        		drawText(durationString, durationX, durationY, durationTextColor, durationTextShadow);
            }
    	} else {
    		drawText(durationString, durationX, durationY, durationTextColor, durationTextShadow);
    	}
    }
    
    private String getPotionName(PotionEffect pe) {
    	String potionName = I18n.format(pe.getEffectName(), new Object[0]);
    	
    	if (pe.getAmplifier() == 1) {
    		potionName = potionName + " " + I18n.format("enchantment.level.2", new Object[0]);
        } else if (pe.getAmplifier() == 2) {
        	potionName = potionName + " " + I18n.format("enchantment.level.3", new Object[0]);
        } else if (pe.getAmplifier() == 3) {
        	potionName = potionName + " " + I18n.format("enchantment.level.4", new Object[0]);
        }
    	
    	return potionName;
    }
	
	public void setBlink(boolean enabled) {
		this.blink = enabled;
		
		setToFile("blink", enabled);
	}
	
	public boolean isBlinkEnabled() {
		return blink;
	}
    
    public void setShowIcon(boolean enabled) {
    	showIcon = enabled;
		
    	setToFile("showIcon", enabled);
    }

    public boolean isShowIconEnabled() {
        return showIcon;
    }
	
	public void setShowBackground(boolean enabled) {
		showBackground = enabled;
		
		setToFile("showBackground", enabled);
	}
	
	public boolean isShowBackgroundEnabled() {
		return showBackground;
	}
	
	public void setDurationTextShadow(boolean enabled) {
		durationTextShadow = enabled;
		
		setToFile("durationTextShadow", enabled);
	}
	
	public boolean isDurationTextShadowEnabled() {
		return durationTextShadow;
	}
    
    public void setDurationTextColor(int rgb) {
    	durationTextColor.setRGB(rgb);
		
		setToFile("durationTextColor", rgb);
	}
    
    public ColorManager getDurationTextColor() {
		return durationTextColor;
	}
	
	public void setDurationTextChroma(boolean enabled) {
		durationTextColor.setChromaToggled(enabled);
		
		setToFile("durationTextChroma", enabled);
	}
	
	public boolean isDurationTextChromaEnabled() {
		return durationTextColor.isChromaToggled();
	}
    
    public void setShowName(boolean enabled) {
    	showName = enabled;
		
    	setToFile("showName", enabled);
    }

    public boolean isShowNameEnabled() {
        return showName;
    }

	public void setNameTextShadow(boolean enabled) {
		nameTextShadow = enabled;
		
		setToFile("nameTextShadow", enabled);
	}
	
	public boolean isNameTextShadowEnabled() {
		return nameTextShadow;
	}
	
	public void setNameTextColor(int rgb) {
		nameTextColor.setRGB(rgb);
		
		setToFile("nameTextColor", rgb);
	}
    
    public ColorManager getNameTextColor() {
		return nameTextColor;
	}
	
	public void setNameTextChroma(boolean enabled) {
		nameTextColor.setChromaToggled(enabled);
		
		setToFile("nameTextChroma", enabled);
	}
	
	public boolean isNameTextChromaEnabled() {
		return nameTextColor.isChromaToggled();
	}
	
	public void setReverse(boolean toggled) {
		reverse = toggled;
		
		setToFile("reverse", toggled);
	}
	
	public boolean isReverseToggled() {
		return reverse;
	}
}
