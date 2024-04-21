package rubik.mods.impl.togglesprintsneak;

import java.awt.Color;

import net.minecraft.client.gui.Gui;
import rubik.ColorManager;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggable;

public class ToggleSprintSneak extends ModDraggable {
	public int keyHoldTicks = 7;
	
	private ColorManager color = new ColorManager(new Color(255, 255, 255, 255));
	private boolean shadow = true;
	private boolean toggleSprint = true;
	private boolean toggleSneak = false;
	private boolean flyBoost = true;
	private float flyBoostFactor = 4;
	
	private String textToRender = "";

	@Override
	public int getWidth() {
		return font.getStringWidth(textToRender);
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		textToRender = mc.thePlayer.movementInput.getDisplayText();
		
		if (shadow) {
			font.drawStringWithShadow(textToRender, pos.getAbsoluteX() + (getWidth() - font.getStringWidth(textToRender)) / 2, pos.getAbsoluteY() + 1, color.getRGB());
		} else {
			font.drawString(textToRender, pos.getAbsoluteX() + (getWidth() - font.getStringWidth(textToRender)) / 2, pos.getAbsoluteY() + 1, color.getRGB());
		}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		textToRender ="[Sprinting (Vanilla)]";
		
		if (shadow) {
			font.drawStringWithShadow(textToRender, pos.getAbsoluteX() + (getWidth() - font.getStringWidth(textToRender)) / 2, pos.getAbsoluteY() + 1, color.getRGB());
		} else {
			font.drawString(textToRender, pos.getAbsoluteX() + (getWidth() - font.getStringWidth(textToRender)) / 2, pos.getAbsoluteY() + 1, color.getRGB());
		}
	}
	
	public ColorManager getColorManager() {
		return color;
	}
	
	public Color getColor() {
		return color.getColor();
	}
	
	public void setShadowEnabled(boolean enabled) {
		shadow = enabled;
	}
	
	public boolean isShadowEnabled() {
		return shadow;
	}
	
	public void setToggleSprintEnabled(boolean enabled) {
		toggleSprint = enabled;
	}
	
	public boolean isToggleSprintEnabled() {
		return toggleSprint;
	}
	
	public void setToggleSneakEnabled(boolean enabled) {
		toggleSneak = enabled;
	}
	
	public boolean isToggleSneakEnabled() {
		return toggleSneak;
	}
	
	public void setFlyBoostEnabled(boolean enabled) {
		flyBoost = enabled;
	}
	
	public boolean isFlyBoostEnabled() {
		return flyBoost;
	}
	
	public void setFlyBoostFactor(float factor) {
		flyBoostFactor = factor;
	}
	
	public float getFlyBoostFactor() {
		return flyBoostFactor;
	}
}
