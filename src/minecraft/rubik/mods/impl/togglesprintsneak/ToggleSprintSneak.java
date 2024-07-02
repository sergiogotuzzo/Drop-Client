package rubik.mods.impl.togglesprintsneak;

import java.awt.Color;

import rubik.ColorManager;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggable;

public class ToggleSprintSneak extends ModDraggable {
	public int keyHoldTicks = 7;
	private boolean sprinting = false;
	private boolean sneaking = false;
	
	private boolean flyBoost = true;
	private float flyBoostFactor = 4.0F;
	private boolean toggleSprint = true;
	private boolean toggleSneak = false;
	private boolean textShadow = true;
	private ColorManager textColor = ColorManager.fromColor(Color.WHITE);
	private boolean textChroma = false;
	
	private String textToRender = "";
	
	public ToggleSprintSneak() {
		setFlyBoost((boolean) getFromFile("flyBoost", flyBoost));
		setFlyBoostFactor((float) ((double) getFromFile("flyBoostFactor", flyBoostFactor)));
		setToggleSprint((boolean) getFromFile("toggleSprint", toggleSprint));
		setToggleSneak((boolean) getFromFile("toggleSneak", toggleSneak));
		setTextShadow((boolean) getFromFile("textShadow", textShadow));
		setTextColor((int) ((long) getFromFile("textColor", textColor.getRGB())));
		setTextChroma((boolean) getFromFile("textChroma", textChroma));
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
	    textToRender = mc.thePlayer.movementInput.getDisplayText();
	    
	    int positionX;
	    double maxRelativeX = 1;
	    
	    if (pos.getRelativeX() < (maxRelativeX / (3 * 3))) {
	    	positionX = pos.getAbsoluteX();
	    } else if (pos.getRelativeX() < (maxRelativeX - (maxRelativeX / 3))) {
	    	positionX = pos.getAbsoluteX() + (getWidth() - font.getStringWidth(textToRender)) / 2;
	    } else {
	    	positionX = pos.getAbsoluteX() + getWidth() - font.getStringWidth(textToRender);
	    }
	    
	    drawText(textToRender, positionX + 1, pos.getAbsoluteY() + 1, textColor.getRGB(), textShadow, textChroma);
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		textToRender = "[Walking]";

		drawText(textToRender, pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, textColor.getRGB(), textShadow, textChroma);
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
	
	public void setTextShadow(boolean enabled) {
		textShadow = enabled;
		
		setToFile("textShadow", enabled);
	}
	
	public boolean isTextShadowEnabled() {
		return textShadow;
	}
	
	public void setTextColor(int rgb) {
		this.textColor = ColorManager.fromRGB(rgb);
		
		setToFile("textColor", rgb);
	}
	
	public ColorManager getTextColor() {
		return textColor;
	}
	
	public void setTextChroma(boolean enabled) {
		this.textChroma = enabled;
		
		setToFile("textChroma", enabled);
	}
	
	public boolean isTextChromaEnabled() {
		return textChroma;
	}
}
