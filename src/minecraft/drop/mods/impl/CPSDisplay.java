package drop.mods.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import drop.ColorManager;
import net.minecraft.util.EnumChatFormatting;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiCPSDisplay;
import drop.gui.mod.hud.ScreenPosition;
import drop.mods.ModDraggableText;

public class CPSDisplay extends ModDraggableText {
	public boolean showRightCPS = false;
	private boolean showBackground = false;
	
	private List<Long> leftClicks = new ArrayList<>();
    private boolean wasLeftPressed;
    private long lastLeftPressed;
    
    private List<Long> rightClicks = new ArrayList<>();
    private boolean wasRightPressed;
    private long lastRightPressed;
    
	public CPSDisplay() {
		setShowRightCPS((boolean) getFromFile("showRightCPS", showRightCPS));
		setShowBackground((boolean) getFromFile("showBackground", showBackground));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiCPSDisplay(previousGuiScreen);
	}
	
	@Override
	public int getWidth() {
		return 58;
	}

	@Override
	public int getHeight() {
		return 18;
	}

	@Override
	public void render(ScreenPosition pos) {
		final boolean leftPressed = mc.gameSettings.keyBindAttack.isKeyDown();
        final boolean rightPressed = mc.gameSettings.keyBindUseItem.isKeyDown();

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
			drawRect(
					pos.getAbsoluteX(),
					pos.getAbsoluteY(),
					pos.getAbsoluteX() + getWidth(),
					pos.getAbsoluteY() + getHeight(),
					ColorManager.fromColor(Color.BLACK).setAlpha(102).getRGB()
					);
        }
                
        drawCenteredText(getCPSText(), pos.getAbsoluteX(), pos.getAbsoluteY(), textColor.getRGB(), textShadow, textChroma);
	}
	
	private int getCPS(List<Long> clicks) {
        final long time = System.currentTimeMillis();

        clicks.removeIf((aLong) -> aLong + 1000 < time);

        return clicks.size();
    }
	
	private String getCPSText() {
		String text = String.valueOf(getCPS(leftClicks));
		
		if (showRightCPS) {
			text += " ⎟ " + getCPS(rightClicks);
		}
		
		text += " CPS";
		
		return showBackground ? text : "[" + text + "]";
	}
	
	public void setShowRightCPS(boolean enabled) {
		showRightCPS = enabled;
		
		setToFile("showRightCPS", enabled);
	}
	
	public boolean isShowRightCPSEnabled() {
		return showRightCPS;
	}
	
	public void setShowBackground(boolean enabled) {
		showBackground = enabled;
		
		setToFile("showBackground", enabled);
	}
	
	public boolean isShowBackgroundEnabled() {
		return showBackground;
	}
}
