package rubik.mods.impl.togglesprintsneak;

import java.awt.Color;

import rubik.ColorManager;
import rubik.gui.mod.hud.ScreenPosition;
import rubik.mods.ModDraggableText;

public class ToggleSprintSneak extends ModDraggableText {
	public int keyHoldTicks = 7;
	private boolean sprinting = false;
	private boolean sneaking = false;

	private boolean toggleSprint = true;
	private boolean toggleSneak = false;
	private boolean flyBoost = true;
	private float flyBoostFactor = 4.0F;
	private boolean showText = true;
	
	private String textToRender = "";
	
	public ToggleSprintSneak() {
		setToggleSprint((boolean) getFromFile("toggleSprint", toggleSprint));
		setToggleSneak((boolean) getFromFile("toggleSneak", toggleSneak));
		setFlyBoost((boolean) getFromFile("flyBoost", flyBoost));
		setFlyBoostFactor((float) ((double) getFromFile("flyBoostFactor", flyBoostFactor)));
		setShowText((boolean) getFromFile("showText", showText));
	}

	@Override
	public int getWidth() {
		return font.getStringWidth("[Walking]");
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
	    if (showText) {
	    	textToRender = mc.thePlayer.movementInput.getDisplayText();
		    
		    drawAlignedText(textToRender, pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, textColor.getRGB(), textShadow, textChroma);
	    }
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		if (showText) {
			textToRender = "[Walking]";

			drawAlignedText(textToRender, pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, textColor.getRGB(), textShadow, textChroma);
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
	
	public void setToggleSprint(boolean enabled) {
		toggleSprint = enabled;
		
		setToFile("toggleSprint", enabled);
	}
	
	public boolean isToggleSprintEnabled() {
		return toggleSprint;
	}
	
	public void setToggleSneak(boolean enabled) {
		toggleSneak = enabled;
		
		setToFile("toggleSneak", enabled);
	}
	
	public boolean isToggleSneakEnabled() {
		return toggleSneak;
	}
	
	public void setFlyBoost(boolean enabled) {
		flyBoost = enabled;
		
		setToFile("flyBoost", enabled);
	}
	
	public boolean isFlyBoostEnabled() {
		return flyBoost;
	}
	
	public void setFlyBoostFactor(float factor) {
		flyBoostFactor = factor;
		
		setToFile("flyBoostFactor", factor);
	}
	
	public float getFlyBoostFactor() {
		return flyBoostFactor;
	}
	
	public void setShowText(boolean enabled) {
		showText = enabled;
		
		setToFile("showText", enabled);
	}
	
	public boolean isShowTextEnabled() {
		return showText;
	}
}
