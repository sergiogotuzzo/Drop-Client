package drop.mods.impl.togglesprintsneak;

import java.awt.Color;

import drop.ColorManager;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiToggleSprintSneak;
import drop.mods.hud.ScreenPosition;
import drop.mods.ModDraggableDisplayText;

public class ToggleSprintSneak extends ModDraggableDisplayText {
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
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiToggleSprintSneak(previousGuiScreen);
	}

	@Override
	public int getWidth() {
		return showBackground ? font.getStringWidth("Sprinting (Toggled)") + 20 : font.getStringWidth(brackets.wrap("Sprinting (Toggled)"));
	}

	@Override
	public int getHeight() {
		return showBackground ? 18 : font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
	    if (showText && mc.thePlayer.movementInput.getDisplayText() != "") {
	    	String text = mc.thePlayer.movementInput.getDisplayText();
	    	
	    	drawTextToRender(pos, textToRender = (showBackground ? text : brackets.wrap(text)));
	    }
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		if (showText) {
			String text = "Sprinting (Toggled)";
	    	
	    	drawTextToRender(pos, textToRender = (showBackground ? text : brackets.wrap(text)));
	    }
	}
	
	private void drawTextToRender(ScreenPosition pos, String textToRender) {
		if (showBackground) {
	    	drawAlignedRect(pos, textToRender, 20);
			drawCenteredAlignedText(pos, textToRender, 20, pos.getAbsoluteX(), pos.getAbsoluteY(), textColor, textShadow, textChroma);
    	} else {
		    drawAlignedText(textToRender, pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, textColor, textShadow, textChroma);
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
