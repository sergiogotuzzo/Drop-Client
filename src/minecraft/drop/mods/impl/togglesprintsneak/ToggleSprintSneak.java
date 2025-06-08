package drop.mods.impl.togglesprintsneak;

import java.awt.Color;

import drop.ColorManager;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiToggleSprintSneak;
import drop.mods.hud.ScreenPosition;
import drop.mods.ModDraggableDisplayText;

public class ToggleSprintSneak extends ModDraggableDisplayText {
	private boolean showText = true;
	private boolean toggleSprint = true;
	private boolean toggleSneak = false;
	private boolean flyBoost = true;
	private float flyBoostFactor = 4.0F;
	
	public ToggleSprintSneak() {
		super(true, 0.5, 0.5);
		
		setShowText((boolean) getFromFile("showText", showText));
		setToggleSprint((boolean) getFromFile("toggleSprint", toggleSprint));
		setToggleSneak((boolean) getFromFile("toggleSneak", toggleSneak));
		setFlyBoost((boolean) getFromFile("flyBoost", flyBoost));
		setFlyBoostFactor((float) ((double) getFromFile("flyBoostFactor", flyBoostFactor)));
	}
	
	public int keyHoldTicks = 7;
	private boolean sprinting = false;
	private boolean sneaking = false;
	
	private String textToRender = "";
	
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
			drawCenteredAlignedText(pos, textToRender, 20, pos.getAbsoluteX(), pos.getAbsoluteY(), textColor, textShadow);
    	} else {
		    drawAlignedText(textToRender, pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, textColor, textShadow);
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
	
	public void setShowText(boolean toggled) {
		showText = toggled;
		
		setToFile("showText", toggled);
	}
	
	public void toggleShowText() {
		setShowText(!showText);
	}
	
	public boolean isShowTextToggled() {
		return showText;
	}
	
	public void setToggleSprint(boolean enabled) {
		toggleSprint = enabled;
		
		setToFile("toggleSprint", enabled);
	}
	
	public boolean isToggleSprintToggled() {
		return toggleSprint;
	}
	
	public void setToggleSneak(boolean enabled) {
		toggleSneak = enabled;
		
		setToFile("toggleSneak", enabled);
	}
	
	public boolean isToggleSneakToggled() {
		return toggleSneak;
	}
	
	public void setFlyBoost(boolean enabled) {
		flyBoost = enabled;
		
		setToFile("flyBoost", enabled);
	}
	
	public boolean isFlyBoostToggled() {
		return flyBoost;
	}
	
	public void setFlyBoostFactor(float factor) {
		flyBoostFactor = factor;
		
		setToFile("flyBoostFactor", factor);
	}
	
	public float getFlyBoostFactor() {
		return flyBoostFactor;
	}
}
