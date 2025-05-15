package rubik.mods.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import net.minecraft.util.EnumChatFormatting;
import rubik.ColorManager;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggable;

public class CPSDisplay extends ModDraggable {
	public enum CPSMode {
		LEFT(1),
		RIGHT(2),
		LEFT_RIGHT(3),
		HIGHER(4);
		
		private int index;
		
		CPSMode(int index) {
			this.index = index;
		}
		
		public int getIndex() {
			return index;
		}
	}
	
	private boolean showBackground = false;
	private boolean textShadow = true;
	private ColorManager textColor = ColorManager.fromColor(Color.WHITE);
	private CPSMode mode = CPSMode.LEFT_RIGHT;
	private boolean textChroma = false;
	
	private List<Long> leftClicks = new ArrayList<>();
    private boolean wasLeftPressed;
    private long lastLeftPressed;
    
    private List<Long> rightClicks = new ArrayList<>();
    private boolean wasRightPressed;
    private long lastRightPressed;
    
    public CPSDisplay() {
		setShowBackground((boolean) getFromFile("showBackground", showBackground));
		setTextShadow((boolean) getFromFile("textShadow", textShadow));
		setTextColor((int) ((long) getFromFile("textColor", textColor.getRGB())));
		setMode((int) ((long) getFromFile("mode", mode.getIndex())));
		setTextChroma((boolean) getFromFile("textChroma", textChroma));
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
		String cpsText = "";
		String line = textChroma ? " ⎟ " : " " + EnumChatFormatting.GRAY + "⎟" + " " + EnumChatFormatting.RESET;
		
		switch (mode.getIndex()) {
			case 1:
				cpsText = getCPS(leftClicks) + " CPS";
				break;
			case 2:
				cpsText = getCPS(rightClicks) + " CPS";
				break;
			case 3:
				cpsText = getCPS(leftClicks) + line + getCPS(rightClicks) + " CPS";
				break;
			case 4:
				cpsText = (getCPS(leftClicks) > getCPS(rightClicks) ? getCPS(leftClicks) : getCPS(rightClicks)) + " CPS";
				break;
		}
		
		return showBackground ? cpsText : "[" + cpsText + "]";
	}
	
	public void setShowBackground(boolean enabled) {
		showBackground = enabled;
		
		setToFile("showBackground", enabled);
	}
	
	public boolean isShowBackgroundEnabled() {
		return showBackground;
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
	
	public void setMode(int modeIndex) {
		switch (modeIndex) {
			case 1:
				this.mode = CPSMode.LEFT;
				break;
			case 2:
				this.mode = CPSMode.RIGHT;
				break;
			case 3:
				this.mode = CPSMode.LEFT_RIGHT;
				break;
			case 4:
				this.mode = CPSMode.HIGHER;
				break;
		}
		
		setToFile("mode", modeIndex);
	}
	
	public CPSMode getMode() {
		return mode;
	}
	
	public void setTextChroma(boolean enabled) {
		this.textChroma = enabled;
		
		setToFile("textChroma", enabled);
	}
	
	public boolean isTextChromaEnabled() {
		return textChroma;
	}
}
