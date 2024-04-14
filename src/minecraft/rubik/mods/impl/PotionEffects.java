package rubik.mods.impl;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggable;

public class PotionEffects extends ModDraggable {
	private float zLevel;
	
    private int color = 0xFFFFFFFF;
    private boolean shadow = true;
    private final int EFFECT_HEIGHT = 24;

    @Override
    public int getWidth() {
        return 83;
    }

    @Override
    public int getHeight() {
        return 2 * EFFECT_HEIGHT;
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
        renderPotionEffect(pos, 0, new PotionEffect(Potion.moveSpeed.getId(), 20 * 60, 3));
        renderPotionEffect(pos, EFFECT_HEIGHT, new PotionEffect(Potion.damageBoost.getId(), 20, 3));
    }

    private void renderPotionEffect(ScreenPosition pos, int yOffset, PotionEffect pe) {
        if (pe == null) {
            return;
        }
        
        Potion potion = Potion.potionTypes[pe.getPotionID()];

        if (potion.hasStatusIcon())
        {
        	mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/inventory.png"));
        	
        	int iconIndex = potion.getStatusIconIndex();
          
            drawTexturedModalRect(pos.getAbsoluteX() + 2, pos.getAbsoluteY() + yOffset + 4, iconIndex % 8 * 18, 198 + iconIndex / 8 * 18, 18, 18);
        }
        
        if (shadow) {
        	font.drawStringWithShadow(getPotionName(pe), pos.getAbsoluteX() + 24, pos.getAbsoluteY() + yOffset + 4, color);
            font.drawStringWithShadow(Potion.getDurationString(pe), pos.getAbsoluteX() + 24, pos.getAbsoluteY() + yOffset + font.FONT_HEIGHT + 4, color);
        } else {
        	font.drawString(getPotionName(pe), pos.getAbsoluteX() + 24, pos.getAbsoluteY() + yOffset + 4, color);
            font.drawString(Potion.getDurationString(pe), pos.getAbsoluteX() + 24, pos.getAbsoluteY() + yOffset + font.FONT_HEIGHT + 4, color);
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

    public void setShadowEnabled(boolean enabled) {
        shadow = enabled;
    }

    public boolean isShadowEnabled() {
        return shadow;
    }
}
