package rubik.mods.impl.togglesprintsneak;

import java.awt.Color;

import net.minecraft.client.gui.Gui;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggable;

public class ToggleSprintSneak extends ModDraggable {
	public int keyHoldTicks = 7;
	
	private boolean background = false;
	private int color = 0xFFFFFFFF;
	private boolean shadow = true;
	private boolean toggleSprint = true;
	private boolean toggleSneak = false;
	private boolean flyBoost = true;
	private float flyBoostFactor = 4;
	
	private String textToRender = "";

	@Override
	public int getWidth() {
		return font.getStringWidth(background ? textToRender.replace("[", "").replace("]", "") : textToRender);
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		textToRender = background ? mc.thePlayer.movementInput.getDisplayText().replace("[", "").replace("]", "") : mc.thePlayer.movementInput.getDisplayText();
		
		if (background && !textToRender.isBlank()) {
			int paddingX = 6;
	        int paddingY = 4;
	        
			Gui.drawRect(
					pos.getAbsoluteX() - paddingX,
					pos.getAbsoluteY() - paddingY,
					pos.getAbsoluteX() + getWidth() + paddingX,
					pos.getAbsoluteY() + getHeight() + paddingY,
					new Color(0, 0, 0, 102).getRGB()
					);
		}
		
		if (shadow) {
			font.drawStringWithShadow(textToRender, pos.getAbsoluteX() + (getWidth() - font.getStringWidth(textToRender)) / 2, pos.getAbsoluteY() + 1, color);
		} else {
			font.drawString(textToRender, pos.getAbsoluteX() + (getWidth() - font.getStringWidth(textToRender)) / 2, pos.getAbsoluteY() + 1, color);
		}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		textToRender = background ? "Sprinting (Vanilla)" : "[Sprinting (Vanilla)]";
		
		if (background) {
			int paddingX = 6;
	        int paddingY = 4;
	        
			Gui.drawRect(
					pos.getAbsoluteX() - paddingX,
					pos.getAbsoluteY() - paddingY,
					pos.getAbsoluteX() + getWidth() + paddingX,
					pos.getAbsoluteY() + getHeight() + paddingY,
					new Color(0, 0, 0, 102).getRGB()
					);
		}
		
		if (shadow) {
			font.drawStringWithShadow(textToRender, pos.getAbsoluteX() + (getWidth() - font.getStringWidth(textToRender)) / 2, pos.getAbsoluteY() + 1, color);
		} else {
			font.drawString(textToRender, pos.getAbsoluteX() + (getWidth() - font.getStringWidth(textToRender)) / 2, pos.getAbsoluteY() + 1, color);
		}
	}
	
	public void setBackgroundEnabled(boolean enabled) {
		background = enabled;
	}
	
	public boolean isBackgroundEnabled() {
		return background;
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
