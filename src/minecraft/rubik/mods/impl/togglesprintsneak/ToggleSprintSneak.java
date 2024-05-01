package rubik.mods.impl.togglesprintsneak;

import java.awt.Color;

import net.minecraft.client.gui.Gui;
import rubik.ColorManager;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggable;

public class ToggleSprintSneak extends ModDraggable {
	public int keyHoldTicks = 7;
	private boolean sprinting = false;
	private boolean sneaking = false;
	
	private boolean flyBoost = true;
	private float flyBoostFactor = 4;
	private boolean toggleSprint = true;
	private boolean toggleSneak = false;
	private boolean textShadow = true;
	private ColorManager color = new ColorManager(Color.WHITE);
	
	private String textToRender = "";

	@Override
	public int getWidth() {
		return font.getStringWidth("[Sprinting (Vanilla)]") + 4;
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT + 3;
	}

	@Override
	public void render(ScreenPosition pos) {
	    textToRender = mc.thePlayer.movementInput.getDisplayText();
	    
	    int positionX;
	    double maxRelativeX = 1;
	    
	    if (pos.getRelativeX() < (maxRelativeX / (3 * 3))) {
	    	positionX = pos.getAbsoluteX() + 3;
	    } else if (pos.getRelativeX() < (maxRelativeX - (maxRelativeX / 3))) {
	    	positionX = pos.getAbsoluteX() + 1 + (getWidth() - font.getStringWidth(textToRender)) / 2;
	    } else {
	    	positionX = pos.getAbsoluteX() - 1 + getWidth() - font.getStringWidth(textToRender);
	    }

	    if (textShadow) {
	        font.drawStringWithShadow(textToRender, positionX, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
	    } else {
	        font.drawString(textToRender, positionX, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
	    }
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		textToRender = "[Sprinting (Vanilla)]";

	    if (textShadow) {
	        font.drawStringWithShadow(textToRender, pos.getAbsoluteX() + 1 + (getWidth() - font.getStringWidth(textToRender)) / 2, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
	    } else {
	        font.drawString(textToRender, pos.getAbsoluteX() + 1 + (getWidth() - font.getStringWidth(textToRender)) / 2, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
	    }
	}
	
	public void setSprinting(boolean sprinting) {
		this.sprinting = sprinting;
	}
	
	public boolean isSprinting() {
		return sprinting;
	}
	
	public void setSneaking(boolean sneaking) {
		this.sneaking = sneaking;
	}
	
	public boolean isSneaking() {
		return sneaking;
	}
	
	public void setFlyBoost(boolean enabled) {
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
	
	public void setToggleSprint(boolean enabled) {
		toggleSprint = enabled;
	}
	
	public boolean isToggleSprintEnabled() {
		return toggleSprint;
	}
	
	public void setToggleSneak(boolean enabled) {
		toggleSneak = enabled;
	}
	
	public boolean isToggleSneakEnabled() {
		return toggleSneak;
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
