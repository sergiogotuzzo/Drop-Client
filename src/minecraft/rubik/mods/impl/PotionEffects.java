package rubik.mods.impl;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collection;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import rubik.ColorManager;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggable;

public class PotionEffects extends ModDraggable {
	private float zLevel;
	private Collection<PotionEffect> dummyPotionEffects = Arrays.asList(new PotionEffect(Potion.moveSpeed.getId(), 20 * 60, 3), new PotionEffect(Potion.damageBoost.getId(), 20, 3));
	
    private boolean showTime = true;
    private boolean textShadow = true;
	private ColorManager color = new ColorManager(Color.WHITE);
    
    private final int EFFECT_HEIGHT = 20;

    @Override
    public int getWidth() {
    	Collection<PotionEffect> activePotionEffects = mc.thePlayer.getActivePotionEffects();
    	
        return activePotionEffects.size() == 0 ?
        		22 + font.getStringWidth(getLongestEffectName(dummyPotionEffects)) :
        		22 + font.getStringWidth(getLongestEffectName(activePotionEffects));
    }

    @Override
    public int getHeight() {
    	int activePotionEffectsSize = mc.thePlayer.getActivePotionEffects().size() == 0 ? dummyPotionEffects.size() : mc.thePlayer.getActivePotionEffects().size();
    	
        return activePotionEffectsSize * EFFECT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        int yOffset = 0;

        for (int i = 0; i < mc.thePlayer.getActivePotionEffects().size(); i++) {
            PotionEffect potionEffect = (PotionEffect) mc.thePlayer.getActivePotionEffects().toArray()[i];

            renderPotionEffect(pos, yOffset, potionEffect);
            
            yOffset += EFFECT_HEIGHT;
        }
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        if (mc.thePlayer.getActivePotionEffects().size() == 0) {
        	int yOffset = 0;

            for (int i = 0; i < dummyPotionEffects.size(); i++) {
                PotionEffect potionEffect = (PotionEffect) dummyPotionEffects.toArray()[i];

                renderPotionEffect(pos, yOffset, potionEffect);
                
                yOffset += EFFECT_HEIGHT;
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

    private void renderPotionEffect(ScreenPosition pos, int yOffset, PotionEffect pe) {
        if (pe == null) {
            return;
        }

        Potion potion = Potion.potionTypes[pe.getPotionID()];
        int i = 2;
        int j = 22;

        if (potion.hasStatusIcon()) {
            mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/inventory.png"));
            
            int iconIndex = potion.getStatusIconIndex();
              
            drawTexturedModalRect(pos.getAbsoluteX(), pos.getAbsoluteY() + yOffset + i, iconIndex % 8 * 18, 198 + iconIndex / 8 * 18, 18, 18);
        }

        String potionName = getPotionName(pe);
        String durationString = Potion.getDurationString(pe);

        if (textShadow) {
        	if (showTime) {
        		font.drawStringWithShadow(potionName, pos.getAbsoluteX() + j, pos.getAbsoluteY() + yOffset + i, color.getRGB());

                if (pe.getDuration() >= 20 * 10 || pe.getDuration() % 20 < 10) {
                    font.drawStringWithShadow(durationString, pos.getAbsoluteX() + j, pos.getAbsoluteY() + yOffset + font.FONT_HEIGHT + i, color.getRGB());
                }
        	} else {
        		font.drawStringWithShadow(potionName, pos.getAbsoluteX() + j, pos.getAbsoluteY() + yOffset + font.FONT_HEIGHT - i, color.getRGB());
        	}
        } else {
            if (showTime) {
            	font.drawString(potionName, pos.getAbsoluteX() + j, pos.getAbsoluteY() + yOffset + i, color.getRGB());

                if (pe.getDuration() >= 20 * 10 || pe.getDuration() % 20 < 10) {
                    font.drawString(durationString, pos.getAbsoluteX() + j, pos.getAbsoluteY() + yOffset + font.FONT_HEIGHT + i, color.getRGB());
                }
            } else {
            	font.drawString(potionName, pos.getAbsoluteX() + j, pos.getAbsoluteY() + yOffset + font.FONT_HEIGHT - i, color.getRGB());
            }
        }
    }

    private void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        
        worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        worldrenderer.func_181662_b((double)(x + 0), (double)(y + height), (double)this.zLevel).func_181673_a((double)((float)(textureX + 0) * f), (double)((float)(textureY + height) * f1)).func_181675_d();
        worldrenderer.func_181662_b((double)(x + width), (double)(y + height), (double)this.zLevel).func_181673_a((double)((float)(textureX + width) * f), (double)((float)(textureY + height) * f1)).func_181675_d();
        worldrenderer.func_181662_b((double)(x + width), (double)(y + 0), (double)this.zLevel).func_181673_a((double)((float)(textureX + width) * f), (double)((float)(textureY + 0) * f1)).func_181675_d();
        worldrenderer.func_181662_b((double)(x + 0), (double)(y + 0), (double)this.zLevel).func_181673_a((double)((float)(textureX + 0) * f), (double)((float)(textureY + 0) * f1)).func_181675_d();
        
        tessellator.draw();
    }
    
    private String getPotionName(PotionEffect pe) {
    	String potionName = I18n.format(pe.getEffectName(), new Object[0]);
    	
    	if (pe.getAmplifier() == 1)
        {
    		potionName = potionName + " " + I18n.format("enchantment.level.2", new Object[0]);
        }
        else if (pe.getAmplifier() == 2)
        {
        	potionName = potionName + " " + I18n.format("enchantment.level.3", new Object[0]);
        }
        else if (pe.getAmplifier() == 3)
        {
        	potionName = potionName + " " + I18n.format("enchantment.level.4", new Object[0]);
        }
    	
    	return potionName;
    }
    
    public void setShowTime(boolean enabled) {
    	showTime = enabled;
    }

    public boolean isShowTimeEnabled() {
        return showTime;
    }

	public void setTextShadow(boolean enabled) {
		textShadow = enabled;
	}
	
	public boolean isTextShadowEnabled() {
		return textShadow;
	}
    
    public ColorManager getColor() {
		return color;
	}
}
