package drop.mods.impl;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import net.minecraft.util.EnumChatFormatting;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiCPSDisplay;
import drop.mods.hud.ScreenPosition;
import drop.mods.ModDraggableDisplayText;

public class CPSDisplay extends ModDraggableDisplayText {
	public boolean showRightCPS = false;
	
	private List<Long> leftClicks = new ArrayList<>();
    private boolean wasLeftPressed;
    private long lastLeftPressed;
    
    private List<Long> rightClicks = new ArrayList<>();
    private boolean wasRightPressed;
    private long lastRightPressed;
        
	public CPSDisplay() {
		setShowRightCPS((boolean) getFromFile("showRightCPS", showRightCPS));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiCPSDisplay(previousGuiScreen);
	}
	
	@Override
	public int getWidth() {
		return showBackground ? 58 : font.getStringWidth(brackets.wrap(getCPS(leftClicks) + (showRightCPS ? " ⎟ " + getCPS(rightClicks) : "") + " CPS"));
	}

	@Override
	public int getHeight() {
		return showBackground ? 18 : font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		final boolean leftPressed = Mouse.isButtonDown(0);
        final boolean rightPressed = Mouse.isButtonDown(1);

        if (leftPressed != this.wasLeftPressed) {
            this.lastLeftPressed = System.currentTimeMillis();
            this.wasLeftPressed = leftPressed;

            if (leftPressed) {
                this.leftClicks.add(this.lastLeftPressed);
            }
        }

        if (rightPressed != this.wasRightPressed) {
            this.lastRightPressed = System.currentTimeMillis();
            this.wasRightPressed = rightPressed;

            if (rightPressed) {
                this.rightClicks.add(this.lastRightPressed);
            }
        }
                
        if (showBackground) {
        	drawRect(pos);
        }
        
        String text = getCPS(leftClicks) + (showRightCPS ? " ⎟ " + getCPS(rightClicks) : "") + " CPS";
                
        drawCenteredText(showBackground ? text : brackets.wrap(text), pos.getAbsoluteX(), pos.getAbsoluteY(), textColor, textShadow, textColor.isChromaToggled());
	}
	
	private int getCPS(List<Long> clicks) {
        final long time = System.currentTimeMillis();

        clicks.removeIf((aLong) -> aLong + 1000 < time);

        return clicks.size();
    }
	
	public void setShowRightCPS(boolean enabled) {
		showRightCPS = enabled;
		
		setToFile("showRightCPS", enabled);
	}
	
	public boolean isShowRightCPSEnabled() {
		return showRightCPS;
	}
}
