package rubik.mods.impl;

import java.util.Collection;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggable;

public class PotionEffects extends ModDraggable {
    private int color = 0xFFFFFFFF;
    private boolean shadow = true;
    private final int EFFECT_HEIGHT = 24;

    @Override
    public int getWidth() {
        return 70;
    }

    @Override
    public int getHeight() {
        int potionEffectsSize = mc.thePlayer.getActivePotionEffects().size();
        
        return potionEffectsSize > 0 ? potionEffectsSize * EFFECT_HEIGHT : 2 * EFFECT_HEIGHT;
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
        renderPotionEffect(pos, 0, new PotionEffect(1, 60));
        renderPotionEffect(pos, EFFECT_HEIGHT, new PotionEffect(2, 60));
    }

    private void renderPotionEffect(ScreenPosition pos, int yOffset, PotionEffect effect) {
        if (effect == null) {
            return;
        }

        GL11.glPushMatrix();
        
        Potion potion = Potion.potionTypes[effect.getPotionID()];

        if (potion.hasStatusIcon())
        {
          int iconIndex = potion.getStatusIconIndex();
            new Gui().drawTexturedModalRect(pos.getAbsoluteX(), pos.getAbsoluteY() + yOffset + 4, iconIndex % 8 * 18, 198 + iconIndex / 8 * 18, 18, 18);
        }
        
        font.drawString(I18n.format(potion.getName(), new Object[0]), pos.getAbsoluteX() + 22, pos.getAbsoluteY() + yOffset + 4, color);
        font.drawString(potion.getDurationString(effect), pos.getAbsoluteX() + 22, pos.getAbsoluteY() + yOffset + font.FONT_HEIGHT + 4, color);

        GL11.glPopMatrix();
    }

    public void setShadowEnabled(boolean enabled) {
        shadow = enabled;
    }

    public boolean isShadowEnabled() {
        return shadow;
    }
}
