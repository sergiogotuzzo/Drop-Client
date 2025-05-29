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
import drop.gui.mod.hud.ScreenPosition;
import drop.mods.ModDraggable;

public class PotionEffects extends ModDraggable {
	private Collection<PotionEffect> dummyPotionEffects = Arrays.asList(new PotionEffect(Potion.moveSpeed.getId(), 20 * 60, 3), new PotionEffect(Potion.damageBoost.getId(), 20, 3));
	
    private boolean blink = true;
	private boolean showIcon = true;
    private boolean durationTextShadow = true;
    private ColorManager durationTextColor = ColorManager.fromColor(Color.WHITE);
    private boolean durationTextChroma = false;
    private boolean showName = true;
    private boolean nameTextShadow = true;
    private ColorManager nameTextColor = ColorManager.fromColor(Color.WHITE);
    private boolean nameTextChroma = false;
    private boolean right = false;
	
	public PotionEffects() {
		setBlink((boolean) getFromFile("blink", blink));
		setShowIcon((boolean) getFromFile("showIcon", showIcon));
		setDurationTextShadow((boolean) getFromFile("durationTextShadow", durationTextShadow));
		setDurationTextColor((int) ((long) getFromFile("durationTextColor", durationTextColor.getRGB())));
		setDurationTextChroma((boolean) getFromFile("durationTextChroma", durationTextChroma));
    	setShowName((boolean) getFromFile("showName", showName));
    	setNameTextShadow((boolean) getFromFile("nameTextShadow", nameTextShadow));
		setNameTextColor((int) ((long) getFromFile("nameTextColor", nameTextColor.getRGB())));
		setNameTextChroma((boolean) getFromFile("nameTextChroma", nameTextChroma));
		setRight((boolean) getFromFile("right", right));
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
		
        return width;
    }

    @Override
    public int getHeight() {    	
        return getPlayerPotionEffects().size() * 20;
    }

    @Override
    public void render(ScreenPosition pos) {
    	if (pos.getRelativeX() < 1.0 / 3.0) {
			if (right) {
				setRight(false);
			}
		} else if (pos.getRelativeX() > 2.0 / 3.0) {
			if (!right) {
				setRight(true);
			}
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
    	if (pos.getRelativeX() < 1.0 / 3.0) {
			if (right) {
				setRight(false);
			}
		} else if (pos.getRelativeX() > 2.0 / 3.0) {
			if (!right) {
				setRight(true);
			}
		}
    	
        int offsetY = 0;

        for (int i = 0; i < getPlayerPotionEffects().size(); i++) {
            PotionEffect potionEffect = (PotionEffect) getPlayerPotionEffects().toArray()[i];

            drawPotionEffect(pos, offsetY, potionEffect);
            
            offsetY += 20;
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

        if (showIcon) {
        	Potion potion = Potion.potionTypes[pe.getPotionID()];
            
            int iconX = right ? pos.getAbsoluteX() + getWidth() - 20 : pos.getAbsoluteX();

            if (potion.hasStatusIcon()) {
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

                mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/inventory.png"));
                
                int iconIndex = potion.getStatusIconIndex();
                  
                drawTexturedModalRect(iconX, pos.getAbsoluteY() + offsetY + 2, iconIndex % 8 * 18, 198 + iconIndex / 8 * 18, 18, 18);
            }
        }

        String potionName = getPotionName(pe);
        String durationString = Potion.getDurationString(pe);
        
        int i = showIcon ? 20 : 0;
        
        if (showName) {
            int nameX = right ? pos.getAbsoluteX() + getWidth() - font.getStringWidth(potionName) - i - 2: pos.getAbsoluteX() + i + 2;

        	drawText(potionName, nameX, pos.getAbsoluteY() + offsetY + 2, nameTextColor, nameTextShadow, nameTextChroma);
        }
        
        int durationX = right ? pos.getAbsoluteX() + getWidth() - font.getStringWidth(durationString) - i - 2: pos.getAbsoluteX() + i + 2;
        int durationY = pos.getAbsoluteY() + offsetY + font.FONT_HEIGHT + (showName ? 2 : -2);
        
        if (blink) {
    		if (pe.getDuration() >= 20 * 10 || pe.getDuration() % 20 < 10) {
        		drawText(durationString, durationX, durationY, durationTextColor, durationTextShadow, durationTextChroma);
            }
    	} else {
    		drawText(durationString, durationX, pos.getAbsoluteY() + offsetY + font.FONT_HEIGHT + 2, durationTextColor, durationTextShadow, durationTextChroma);
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
	
	public void setDurationTextShadow(boolean enabled) {
		durationTextShadow = enabled;
		
		setToFile("durationTextShadow", enabled);
	}
	
	public boolean isDurationTextShadowEnabled() {
		return durationTextShadow;
	}
    
    public void setDurationTextColor(int rgb) {
		this.durationTextColor = ColorManager.fromRGB(rgb);
		
		setToFile("durationTextColor", rgb);
	}
    
    public ColorManager getDurationTextColor() {
		return durationTextColor;
	}
	
	public void setDurationTextChroma(boolean enabled) {
		this.durationTextChroma = enabled;
		
		setToFile("durationTextChroma", enabled);
	}
	
	public boolean isDurationTextChromaEnabled() {
		return durationTextChroma;
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
		this.nameTextColor = ColorManager.fromRGB(rgb);
		
		setToFile("nameTextColor", rgb);
	}
    
    public ColorManager getNameTextColor() {
		return nameTextColor;
	}
	
	public void setNameTextChroma(boolean enabled) {
		this.nameTextChroma = enabled;
		
		setToFile("nameTextChroma", enabled);
	}
	
	public boolean isNameTextChromaEnabled() {
		return nameTextChroma;
	}
	
	public void setRight(boolean enabled) {
		this.right = enabled;
		
		setToFile("right", enabled);
	}
	
	public boolean isRightEnabled() {
		return right;
	}
}
