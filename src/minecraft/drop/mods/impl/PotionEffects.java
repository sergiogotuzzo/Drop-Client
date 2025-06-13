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
    private ColorManager durationTextColor = ColorManager.fromColor(Color.WHITE, false);
    private boolean durationTextShadow = true;
    private boolean showName = true;
    private ColorManager nameTextColor = ColorManager.fromColor(Color.WHITE, false);
    private boolean nameTextShadow = true;
	private boolean showIcon = true;
    private boolean blink = true;
	private boolean reverse = false;
	
	public PotionEffects() {
		super(true, 0.5, 0.5);
		
		setDurationTextColor(getIntFromFile("durationTextColor", durationTextColor.getRGB()));
		setDurationTextShadow(getBooleanFromFile("durationTextShadow", durationTextShadow));
		setDurationTextChroma(getBooleanFromFile("durationTextChroma", durationTextColor.isChromaToggled()));
    	setShowName(getBooleanFromFile("showName", showName));
		setNameTextColor(getIntFromFile("nameTextColor", nameTextColor.getRGB()));
		setNameTextChroma(getBooleanFromFile("nameTextChroma", nameTextColor.isChromaToggled()));
    	setNameTextShadow(getBooleanFromFile("nameTextShadow", nameTextShadow));
		setShowIcon(getBooleanFromFile("showIcon", showIcon));
		setBlink(getBooleanFromFile("blink", blink));
		setReverse(getBooleanFromFile("reverse", reverse));
	}
	
	private Collection<PotionEffect> dummyPotionEffects = Arrays.asList(new PotionEffect(Potion.moveSpeed.getId(), 20 * 60, 3), new PotionEffect(Potion.damageBoost.getId(), 20, 3));
	
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
    	if (pos.getRelativeX() < 1.0 / 3.0 && reverse) {
			setReverse(false);
		} else if (pos.getRelativeX() > 2.0 / 3.0 && !reverse) {
			setReverse(true);
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
    	if (pos.getRelativeX() < 1.0 / 3.0 && reverse) {
			setReverse(false);
		} else if (pos.getRelativeX() > 2.0 / 3.0 && !reverse) {
			setReverse(true);
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
        	if (showIcon) {
            	Potion potion = Potion.potionTypes[potionEffect.getPotionID()];
                
                int iconX = (reverse ? pos.getAbsoluteX() + getWidth() - 20 : pos.getAbsoluteX());

                if (potion.hasStatusIcon()) {
                    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

                    mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/inventory.png"));
                    
                    int iconIndex = potion.getStatusIconIndex();
                      
                    drawTexturedModalRect(iconX, pos.getAbsoluteY() + offsetY + 2, iconIndex % 8 * 18, 198 + iconIndex / 8 * 18, 18, 18);
                }
            }
            
            int i = showIcon ? 20 : 0;
            
            if (showName) {
                String potionName = getPotionName(potionEffect);

                int nameX = (reverse ? pos.getAbsoluteX() + getWidth() - font.getStringWidth(potionName) - i - 2: pos.getAbsoluteX() + i + 2);

            	drawText(potionName, nameX, pos.getAbsoluteY() + offsetY + 2, nameTextColor, nameTextShadow);
            }
            
        	String durationString = Potion.getDurationString(potionEffect);
            int durationX = (reverse ? pos.getAbsoluteX() + getWidth() - font.getStringWidth(durationString) - i - 2: pos.getAbsoluteX() + i + 2);
            int durationY = pos.getAbsoluteY() + offsetY + font.FONT_HEIGHT + (showName ? 2 : -2);
            
            if (blink) {
        		if (potionEffect.getDuration() >= 20 * 10 || potionEffect.getDuration() % 20 < 10) {
            		drawText(durationString, durationX, durationY, durationTextColor, durationTextShadow);
                }
        	} else {
        		drawText(durationString, durationX, durationY, durationTextColor, durationTextShadow);
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
    
    public void setDurationTextColor(int rgb) {
    	durationTextColor.setRGB(rgb);
		
		setToFile("durationTextColor", rgb);
	}
    
    public ColorManager getDurationTextColor() {
		return durationTextColor;
	}
	
	public void setDurationTextChroma(boolean toggled) {
		durationTextColor.setChromaToggled(toggled);
		
		setToFile("durationTextChroma", toggled);
	}
	
	public void setDurationTextShadow(boolean toggled) {
		durationTextShadow = toggled;
		
		setToFile("durationTextShadow", toggled);
	}
	
	public boolean isDurationTextShadowToggled() {
		return durationTextShadow;
	}
	
	public boolean isDurationTextChromaToggled() {
		return durationTextColor.isChromaToggled();
	}
    
    public void setShowName(boolean toggled) {
    	showName = toggled;
		
    	setToFile("showName", toggled);
    }

    public boolean isShowNameToggled() {
        return showName;
    }
	
	public void setNameTextColor(int rgb) {
		nameTextColor.setRGB(rgb);
		
		setToFile("nameTextColor", rgb);
	}
    
    public ColorManager getNameTextColor() {
		return nameTextColor;
	}
	
	public void setNameTextChroma(boolean toggled) {
		nameTextColor.setChromaToggled(toggled);
		
		setToFile("nameTextChroma", toggled);
	}
	
	public boolean isNameTextChromaToggled() {
		return nameTextColor.isChromaToggled();
	}

	public void setNameTextShadow(boolean toggled) {
		nameTextShadow = toggled;
		
		setToFile("nameTextShadow", toggled);
	}
	
	public boolean isNameTextShadowToggled() {
		return nameTextShadow;
	}
    
    public void setShowIcon(boolean toggled) {
    	showIcon = toggled;
		
    	setToFile("showIcon", toggled);
    }

    public boolean isShowIconToggled() {
        return showIcon;
    }
	
	public void setBlink(boolean toggled) {
		this.blink = toggled;
		
		setToFile("blink", toggled);
	}
	
	public boolean isBlinkToggled() {
		return blink;
	}
	
	public void setReverse(boolean toggled) {
		reverse = toggled;
		
		setToFile("reverse", toggled);
	}
	
	public boolean isReverseToggled() {
		return reverse;
	}
}
