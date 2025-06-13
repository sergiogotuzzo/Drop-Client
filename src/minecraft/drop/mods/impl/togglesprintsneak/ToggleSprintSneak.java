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
		
		setShowText(getBooleanFromFile("showText", showText));
		setToggleSprint(getBooleanFromFile("toggleSprint", toggleSprint));
		setToggleSneak(getBooleanFromFile("toggleSneak", toggleSneak));
		setFlyBoost(getBooleanFromFile("flyBoost", flyBoost));
		setFlyBoostFactor(getFloatFromFile("flyBoostFactor", flyBoostFactor));
	}
	
	public int keyHoldTicks = 7;
	public boolean sprinting = false;
	public boolean sneaking = false;
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
	
	public void setShowText(boolean toggled) {
		showText = toggled;
		
		setToFile("showText", toggled);
	}
	
	public boolean isShowTextToggled() {
		return showText;
	}
	
	public void setToggleSprint(boolean toggled) {
		toggleSprint = toggled;
		
		setToFile("toggleSprint", toggled);
	}
	
	public boolean isToggleSprintToggled() {
		return toggleSprint;
	}
	
	public void setToggleSneak(boolean toggled) {
		toggleSneak = toggled;
		
		setToFile("toggleSneak", toggled);
	}
	
	public boolean isToggleSneakToggled() {
		return toggleSneak;
	}
	
	public void setFlyBoost(boolean toggled) {
		flyBoost = toggled;
		
		setToFile("flyBoost", toggled);
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
