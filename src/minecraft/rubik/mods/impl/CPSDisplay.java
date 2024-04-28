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
	private boolean background = false;
	private ColorManager color = new ColorManager(Color.WHITE);
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
		return background ? 58 : font.getStringWidth(right ? "[0 ⎟ 0 CPS]" : "[0 CPS]");
	}

	@Override
	public int getHeight() {
		return background ? 16 : font.FONT_HEIGHT;
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
			Gui.drawRect(
					pos.getAbsoluteX(),
					pos.getAbsoluteY(),
					pos.getAbsoluteX() + getWidth(),
					pos.getAbsoluteY() + getHeight(),
					new Color(0, 0, 0, 102).getRGB()
					);
			
			if (shadow) {
				font.drawStringWithShadow(getCPSText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getCPSText())) / 2, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
			} else {
				font.drawString(getCPSText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getCPSText())) / 2, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
			}
		} else {
			if (shadow) {
				font.drawStringWithShadow(getCPSText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getCPSText())) / 2, pos.getAbsoluteY() + 1, color.getRGB());
			} else {
				font.drawString(getCPSText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getCPSText())) / 2, pos.getAbsoluteY() + 1, color.getRGB());
			}
		}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		if (background) {
			Gui.drawRect(
					pos.getAbsoluteX(),
					pos.getAbsoluteY(),
					pos.getAbsoluteX() + getWidth(),
					pos.getAbsoluteY() + getHeight(),
					new Color(0, 0, 0, 102).getRGB()
					);
			
			if (shadow) {
				font.drawStringWithShadow(right ? getCPSText(0) : getCPSText(0), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(right ? getCPSText(0) : getCPSText(0))) / 2, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
			} else {
				font.drawString(right ? getCPSText(0) : getCPSText(0), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(right ? getCPSText(0) : getCPSText(0))) / 2, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
			}
		} else {
			if (shadow) {
				font.drawStringWithShadow(right ? getCPSText(0) : getCPSText(0), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color.getRGB());
			} else {
				font.drawString(right ? getCPSText(0) : getCPSText(0), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color.getRGB());
			}
		}
	}
	
	private int getCPS(List<Long> clicks) {
        final long time = System.currentTimeMillis();

        clicks.removeIf((aLong) -> aLong + 1000 < time);

        return clicks.size();
    }
	
	private String getCPSText() {
		String cpsString = right ? getCPS(leftClicks) + " " + EnumChatFormatting.GRAY + "⎟" + " " + EnumChatFormatting.RESET + getCPS(rightClicks) : "" + getCPS(leftClicks);
		
		return background ? cpsString + " CPS" : "[" + cpsString + " CPS]";
	}
	
	private String getCPSText(int cps) {
		String cpsString = right ? cps + " " + EnumChatFormatting.GRAY + "⎟" + " " + EnumChatFormatting.RESET + cps : "" + cps;
		
		return background ? cpsString + " CPS" : "[" + cpsString + " CPS]";
	}
	
	public void setBackgroundEnabled(boolean enabled) {
		background = enabled;
	}
	
	public boolean isBackgroundEnabled() {
		return background;
	}
	
	public ColorManager getColorManager() {
		return color;
	}
	
	public Color getColor() {
		return color.getColor();
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
