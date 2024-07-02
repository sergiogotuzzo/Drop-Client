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
    private boolean textShadow = true;
    private ColorManager nameTextColor = ColorManager.fromColor(Color.WHITE);
    private ColorManager durationTextColor = ColorManager.fromColor(Color.WHITE);
    private boolean nameTextChroma = false;
    private boolean durationTextChroma = false;
    private boolean right = false;
    private boolean blink = true;
    
    private final int EFFECT_HEIGHT = 20;
    private final int EFFECT_WIDTH = 22;
    
    public PotionEffects() {
    	setShowName((boolean) getFromFile("showName", showName));
		setTextShadow((boolean) getFromFile("textShadow", textShadow));
		setNameTextColor((int) ((long) getFromFile("nameTextColor", nameTextColor.getRGB())));
		setDurationTextColor((int) ((long) getFromFile("durationTextColor", durationTextColor.getRGB())));
		setNameTextChroma((boolean) getFromFile("nameTextChroma", nameTextChroma));
		setDurationTextChroma((boolean) getFromFile("durationTextChroma", durationTextChroma));
		setRight((boolean) getFromFile("right", right));
	}

	@Override
    public int getWidth() {
    	Collection<PotionEffect> activePotionEffects = mc.thePlayer.getActivePotionEffects();
    	
        return showName ? activePotionEffects.size() == 0 ?
        		EFFECT_WIDTH + font.getStringWidth(getLongestEffectName(dummyPotionEffects)) :
        		EFFECT_WIDTH + font.getStringWidth(getLongestEffectName(activePotionEffects)) : EFFECT_WIDTH + font.getStringWidth("00:00");
    }

    @Override
    public int getHeight() {
    	int activePotionEffectsSize = mc.thePlayer.getActivePotionEffects().size() == 0 ? dummyPotionEffects.size() : mc.thePlayer.getActivePotionEffects().size();
    	
        return activePotionEffectsSize * EFFECT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        int offsetY = 0;

        for (int i = 0; i < mc.thePlayer.getActivePotionEffects().size(); i++) {
            PotionEffect potionEffect = (PotionEffect) mc.thePlayer.getActivePotionEffects().toArray()[i];

            drawPotionEffect(pos, offsetY, potionEffect);
            
            offsetY += EFFECT_HEIGHT;
        }
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        if (mc.thePlayer.getActivePotionEffects().size() == 0) {
        	int offsetY = 0;

            for (int i = 0; i < dummyPotionEffects.size(); i++) {
                PotionEffect potionEffect = (PotionEffect) dummyPotionEffects.toArray()[i];

                drawPotionEffect(pos, offsetY, potionEffect);
                
                offsetY += EFFECT_HEIGHT;
            }
        } else {
        	render(pos);
        }
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
        int iconX = right ? showName ? pos.getAbsoluteX() + font.getStringWidth(getLongestEffectName(mc.thePlayer.getActivePotionEffects())) + i * 2 : pos.getAbsoluteX() + font.getStringWidth("00:00") + i * 2 : pos.getAbsoluteX() + i;
        int nameTextX = right ? pos.getAbsoluteX() + i : pos.getAbsoluteX() + EFFECT_WIDTH;
        int durationTextX = right ? pos.getAbsoluteX() + i : pos.getAbsoluteX() + EFFECT_WIDTH;

        if (potion.hasStatusIcon()) {
            mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/inventory.png"));
            
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            
            int iconIndex = potion.getStatusIconIndex();
              
            drawTexturedModalRect(iconX, pos.getAbsoluteY() + offsetY + i, iconIndex % 8 * 18, 198 + iconIndex / 8 * 18, 18, 18);
        }

        String potionName = getPotionName(pe);
        String durationString = Potion.getDurationString(pe);
        
        if (showName) {
        	drawText(potionName, nameTextX, pos.getAbsoluteY() + offsetY + i, nameTextColor.getRGB(), textShadow, nameTextChroma);
        	
        	if (blink) {
        		if (pe.getDuration() >= 20 * 10 || pe.getDuration() % 20 < 10) {
            		drawText(durationString, durationTextX, pos.getAbsoluteY() + offsetY + font.FONT_HEIGHT + i, durationTextColor.getRGB(), textShadow, durationTextChroma);
                }
        	} else {
        		drawText(durationString, durationTextX, pos.getAbsoluteY() + offsetY + font.FONT_HEIGHT + i, durationTextColor.getRGB(), textShadow, durationTextChroma);
        	}
        } else {
        	if (blink) {
        		if (pe.getDuration() >= 20 * 10 || pe.getDuration() % 20 < 10) {
            		drawText(durationString, durationTextX, pos.getAbsoluteY() + offsetY + font.FONT_HEIGHT - i, durationTextColor.getRGB(), textShadow, durationTextChroma);
                }
        	} else {
        		drawText(durationString, durationTextX, pos.getAbsoluteY() + offsetY + font.FONT_HEIGHT - i, durationTextColor.getRGB(), textShadow, durationTextChroma);
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

	public void setTextShadow(boolean enabled) {
		textShadow = enabled;
		
		setToFile("textShadow", enabled);
	}
	
	public boolean isTextShadowEnabled() {
		return textShadow;
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
