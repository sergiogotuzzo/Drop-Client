package rubik.mods.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import net.minecraft.client.gui.Gui;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggable;

public class CPSDisplay extends ModDraggable {
	private boolean background = false;
	private int color = 0xFFFFFFFF;
	private boolean shadow = true;
	private boolean right = true;
	
	private List<Long> leftClicks = new ArrayList<>();
    private boolean wasLeftPressed;
    private long lastLeftPressed;
    
    private List<Long> rightClicks = new ArrayList<>();
    private boolean wasRightPressed;
    private long lastRightPressed;
	
	@Override
	public int getWidth() {
		return font.getStringWidth("9 | 9 CPS");
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
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
			font.drawStringWithShadow(getCPSText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getCPSText())) / 2, pos.getAbsoluteY() + 1, color);
		} else {
			font.drawString(getCPSText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getCPSText())) / 2, pos.getAbsoluteY() + 1, color);
		}
	}
	
	private int getCPS(List<Long> clicks) {
        final long time = System.currentTimeMillis();

        clicks.removeIf((aLong) -> aLong + 1000 < time);

        return clicks.size();
    }
	
	public String getCPSText() {
		String cps = right ? getCPS(leftClicks) + " §7⎟ §f" + getCPS(rightClicks) : "" + getCPS(leftClicks);
		
		return background ? cps + " CPS" : "[" + cps + " CPS]";
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
	
	public void setRightEnabled(boolean enabled) {
		right = enabled;
	}
	
	public boolean isRightEnabled() {
		return right;
	}
}
