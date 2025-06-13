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
        
	public CPSDisplay() {
		super(true, 0.5, 0.5, "0 CPS");
		
		setShowRightCPS(getBooleanFromFile("showRightCPS", showRightCPS));
		
		if (showRightCPS) {
			dummyText = "0 ⎟ 0 CPS";
		}
	}
	
	private List<Long> leftClicks = new ArrayList<>();
    private boolean wasLeftPressed;
    private long lastLeftPressed;
    
    private List<Long> rightClicks = new ArrayList<>();
    private boolean wasRightPressed;
    private long lastRightPressed;
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiCPSDisplay(previousGuiScreen);
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
                        
		drawTextToRender(pos, getCPS(leftClicks) + (showRightCPS ? " ⎟ " + getCPS(rightClicks) : "") + " CPS");
	}
	
	private int getCPS(List<Long> clicks) {
        final long time = System.currentTimeMillis();

        clicks.removeIf((aLong) -> aLong + 1000 < time);

        return clicks.size();
    }
	
	public void setShowRightCPS(boolean toggled) {
		showRightCPS = toggled;
		
		setToFile("showRightCPS", toggled);
	}
	
	public boolean isShowRightCPSToggled() {
		return showRightCPS;
	}
}
