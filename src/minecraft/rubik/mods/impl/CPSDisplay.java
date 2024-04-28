package rubik.mods.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.EnumChatFormatting;
import rubik.ColorManager;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggable;

public class CPSDisplay extends ModDraggable {
	private boolean showBackground = false;
	private boolean textShadow = true;
	private ColorManager color = new ColorManager(Color.WHITE);
	private boolean showRightCPS = true;
	
	private List<Long> leftClicks = new ArrayList<>();
    private boolean wasLeftPressed;
    private long lastLeftPressed;
    
    private List<Long> rightClicks = new ArrayList<>();
    private boolean wasRightPressed;
    private long lastRightPressed;
	
	@Override
	public int getWidth() {
		return showBackground ? 58 : font.getStringWidth(showRightCPS ? "[0 ⎟ 0 CPS]" : "[0 CPS]");
	}

	@Override
	public int getHeight() {
		return showBackground ? 16 : font.FONT_HEIGHT;
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
			Gui.drawRect(
					pos.getAbsoluteX(),
					pos.getAbsoluteY(),
					pos.getAbsoluteX() + getWidth(),
					pos.getAbsoluteY() + getHeight(),
					new Color(0, 0, 0, 102).getRGB()
					);
			
			if (textShadow) {
				font.drawStringWithShadow(getCPSText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getCPSText())) / 2, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
			} else {
				font.drawString(getCPSText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getCPSText())) / 2, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
			}
		} else {
			if (textShadow) {
				font.drawStringWithShadow(getCPSText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getCPSText())) / 2, pos.getAbsoluteY() + 1, color.getRGB());
			} else {
				font.drawString(getCPSText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getCPSText())) / 2, pos.getAbsoluteY() + 1, color.getRGB());
			}
		}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		if (showBackground) {
			Gui.drawRect(
					pos.getAbsoluteX(),
					pos.getAbsoluteY(),
					pos.getAbsoluteX() + getWidth(),
					pos.getAbsoluteY() + getHeight(),
					new Color(0, 0, 0, 102).getRGB()
					);
			
			if (textShadow) {
				font.drawStringWithShadow(showRightCPS ? getCPSText(0) : getCPSText(0), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(showRightCPS ? getCPSText(0) : getCPSText(0))) / 2, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
			} else {
				font.drawString(showRightCPS ? getCPSText(0) : getCPSText(0), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(showRightCPS ? getCPSText(0) : getCPSText(0))) / 2, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
			}
		} else {
			if (textShadow) {
				font.drawStringWithShadow(showRightCPS ? getCPSText(0) : getCPSText(0), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color.getRGB());
			} else {
				font.drawString(showRightCPS ? getCPSText(0) : getCPSText(0), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color.getRGB());
			}
		}
	}
	
	private int getCPS(List<Long> clicks) {
        final long time = System.currentTimeMillis();

        clicks.removeIf((aLong) -> aLong + 1000 < time);

        return clicks.size();
    }
	
	private String getCPSText() {
		String cpsString = showRightCPS ? getCPS(leftClicks) + " " + EnumChatFormatting.GRAY + "⎟" + " " + EnumChatFormatting.RESET + getCPS(rightClicks) : "" + getCPS(leftClicks);
		
		return showBackground ? cpsString + " CPS" : "[" + cpsString + " CPS]";
	}
	
	private String getCPSText(int cps) {
		String cpsString = showRightCPS ? cps + " " + EnumChatFormatting.GRAY + "⎟" + " " + EnumChatFormatting.RESET + cps : "" + cps;
		
		return showBackground ? cpsString + " CPS" : "[" + cpsString + " CPS]";
	}
	
	public void setShowBackground(boolean enabled) {
		showBackground = enabled;
	}
	
	public boolean isShowBackgroundEnabled() {
		return showBackground;
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
	
	public void setShowRightCPS(boolean enabled) {
		showRightCPS = enabled;
	}
	
	public boolean isShowRightCPSEnabled() {
		return showRightCPS;
	}
}
