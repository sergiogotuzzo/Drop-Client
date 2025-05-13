package rubik.mods.impl;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collection;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import rubik.ColorManager;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggable;

public class PotionEffects extends ModDraggable {
	private Collection<PotionEffect> dummyPotionEffects = Arrays.asList(new PotionEffect(Potion.moveSpeed.getId(), 20 * 60, 3), new PotionEffect(Potion.damageBoost.getId(), 20, 3));
	
    private boolean showName = true;
    private boolean nameTextShadow = true;
    private boolean durationTextShadow = true;
    private ColorManager nameTextColor = ColorManager.fromColor(Color.WHITE);
    private ColorManager durationTextColor = ColorManager.fromColor(Color.WHITE);
    private boolean nameTextChroma = false;
    private boolean durationTextChroma = false;
    private boolean right = false;
    private boolean blink = true;
    
    public PotionEffects() {
    	setShowName((boolean) getFromFile("showName", showName));
    	setNameTextShadow((boolean) getFromFile("nameTextShadow", nameTextShadow));
    	setDurationTextShadow((boolean) getFromFile("durationTextShadow", durationTextShadow));
		setNameTextColor((int) ((long) getFromFile("nameTextColor", nameTextColor.getRGB())));
		setDurationTextColor((int) ((long) getFromFile("durationTextColor", durationTextColor.getRGB())));
		setNameTextChroma((boolean) getFromFile("nameTextChroma", nameTextChroma));
		setDurationTextChroma((boolean) getFromFile("durationTextChroma", durationTextChroma));
		setRight((boolean) getFromFile("right", right));
	}

	@Override
    public int getWidth() {    	
        return 20 + 2 + (showName ? font.getStringWidth(getLongestEffectName(getPlayerPotionEffects())) : font.getStringWidth("00:00"));
    }

    @Override
    public int getHeight() {    	
        return getPlayerPotionEffects().size() * 20;
    }

    @Override
    public void render(ScreenPosition pos) {
    	if (pos.getAbsoluteX() > (mc.displayWidth / 3)) {
			right = true;
		} else {
			right = false;
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
    	if (pos.getAbsoluteX() > (mc.displayWidth / 3)) {
			right = true;
		} else {
			right = false;
		}
    	
        if (mc.thePlayer.getActivePotionEffects().size() == 0) {
        	int offsetY = 0;

            for (int i = 0; i < dummyPotionEffects.size(); i++) {
                PotionEffect potionEffect = (PotionEffect) dummyPotionEffects.toArray()[i];

                drawPotionEffect(pos, offsetY, potionEffect);
                
                offsetY += 20;
            }
        } else {
        	render(pos);
        }
    }
    
    private Collection<PotionEffect> getPlayerPotionEffects() {
    	return mc.thePlayer.getActivePotionEffects().size() == 0 ? dummyPotionEffects : mc.thePlayer.getActivePotionEffects();
    }
    
    private String getLongestEffectName(Collection<PotionEffect> effects) {
        if (effects == null || effects.isEmpty()) {
            return null;
        }
        
        PotionEffect longestEffect = null;
        
        int maxLength = 0;
        
        for (PotionEffect effect : effects) {
            String effectName = getPotionName(effect);
            
            if (effectName.length() > maxLength) {
                maxLength = effectName.length();
                longestEffect = effect;
            }
        }
        
        return getPotionName(longestEffect);
    }

    private void drawPotionEffect(ScreenPosition pos, int offsetY, PotionEffect pe) {
        if (pe == null) {
            return;
        }

        Potion potion = Potion.potionTypes[pe.getPotionID()];
                
        int i = 2;
        
        int iconX = right ? pos.getAbsoluteX() + getWidth() - 18 : pos.getAbsoluteX() + 2;

        if (potion.hasStatusIcon()) {
            mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/inventory.png"));
            
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            
            int iconIndex = potion.getStatusIconIndex();
              
            drawTexturedModalRect(iconX, pos.getAbsoluteY() + offsetY + i, iconIndex % 8 * 18, 198 + iconIndex / 8 * 18, 18, 18);
        }

        String potionName = getPotionName(pe);
        String durationString = Potion.getDurationString(pe);
        
        int textX = right ? pos.getAbsoluteX() + 2 : pos.getAbsoluteX() + getWidth() - font.getStringWidth(showName ? getLongestEffectName(getPlayerPotionEffects()) : durationString);
        
        if (showName) {
        	drawText(potionName, textX, pos.getAbsoluteY() + offsetY + i, nameTextColor.getRGB(), nameTextShadow, nameTextChroma);
        	
        	if (blink) {
        		if (pe.getDuration() >= 20 * 10 || pe.getDuration() % 20 < 10) {
            		drawText(durationString, textX, pos.getAbsoluteY() + offsetY + font.FONT_HEIGHT + i, durationTextColor.getRGB(), durationTextShadow, durationTextChroma);
                }
        	} else {
        		drawText(durationString, textX, pos.getAbsoluteY() + offsetY + font.FONT_HEIGHT + i, durationTextColor.getRGB(), durationTextShadow, durationTextChroma);
        	}
        } else {
        	if (blink) {
        		if (pe.getDuration() >= 20 * 10 || pe.getDuration() % 20 < 10) {
            		drawText(durationString, textX, pos.getAbsoluteY() + offsetY + font.FONT_HEIGHT - i, durationTextColor.getRGB(), durationTextShadow, durationTextChroma);
                }
        	} else {
        		drawText(durationString, textX, pos.getAbsoluteY() + offsetY + font.FONT_HEIGHT - i, durationTextColor.getRGB(), durationTextShadow, durationTextChroma);
        	}
        }
    }
    
    private String getPotionName(PotionEffect pe) {
    	String potionName = I18n.format(pe.getEffectName(), new Object[0]);
    	
    	if (pe.getAmplifier() == 1)
        {
    		potionName = potionName + " " + I18n.format("enchantment.level.2", new Object[0]);
        } else if (pe.getAmplifier() == 2)
        {
        	potionName = potionName + " " + I18n.format("enchantment.level.3", new Object[0]);
        } else if (pe.getAmplifier() == 3)
        {
        	potionName = potionName + " " + I18n.format("enchantment.level.4", new Object[0]);
        }
    	
    	return potionName;
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
	
	public void setDurationTextShadow(boolean enabled) {
		durationTextShadow = enabled;
		
		setToFile("durationTextShadow", enabled);
	}
	
	public boolean isDurationTextShadowEnabled() {
		return durationTextShadow;
	}
	
	public void setNameTextColor(int rgb) {
		this.nameTextColor = ColorManager.fromRGB(rgb);
		
		setToFile("nameTextColor", rgb);
	}
    
    public ColorManager getNameTextColor() {
		return nameTextColor;
	}
    
    public void setDurationTextColor(int rgb) {
		this.durationTextColor = ColorManager.fromRGB(rgb);
		
		setToFile("durationTextColor", rgb);
	}
    
    public ColorManager getDurationTextColor() {
		return durationTextColor;
	}
	
	public void setNameTextChroma(boolean enabled) {
		this.nameTextChroma = enabled;
		
		setToFile("nameTextChroma", enabled);
	}
	
	public boolean isNameTextChromaEnabled() {
		return nameTextChroma;
	}
	
	public void setDurationTextChroma(boolean enabled) {
		this.durationTextChroma = enabled;
		
		setToFile("durationTextChroma", enabled);
	}
	
	public boolean isDurationTextChromaEnabled() {
		return durationTextChroma;
	}
	
	public void setRight(boolean enabled) {
		this.right = enabled;
		
		setToFile("right", enabled);
	}
	
	public boolean isRightEnabled() {
		return right;
	}
	
	public void setBlink(boolean enabled) {
		this.blink = enabled;
		
		setToFile("blink", enabled);
	}
	
	public boolean isBlinkEnabled() {
		return blink;
	}
}
