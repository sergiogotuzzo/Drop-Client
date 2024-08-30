package rubik.mods.impl;

import java.awt.Color;

import rubik.ColorManager;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggable;

public class FPSDisplay extends ModDraggable {
	private boolean showBackground = false;
	private boolean textShadow = true;
	private ColorManager textColor = ColorManager.fromColor(Color.WHITE);
	private boolean textChroma = false;
	
	public FPSDisplay() {
		setShowBackground((boolean) getFromFile("showBackground", showBackground));
		setTextShadow((boolean) getFromFile("textShadow", textShadow));
		setTextColor((int) ((long) getFromFile("textColor", textColor.getRGB())));
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
		if (showBackground) {
			drawRect(
					pos.getAbsoluteX(),
					pos.getAbsoluteY(),
					pos.getAbsoluteX() + getWidth(),
					pos.getAbsoluteY() + getHeight(),
					ColorManager.fromColor(Color.BLACK).setAlpha(102).getRGB()
					);
		}
		
		drawCenteredText(getFPSText(), pos.getAbsoluteX(), pos.getAbsoluteY(), textColor.getRGB(), textShadow, textChroma);
	}
	
	private String getFPSText() {
		String fpsText = mc.getDebugFPS() + " FPS";
		
		return showBackground ? fpsText : "[" + fpsText + "]";
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
	
	public void setTextChroma(boolean enabled) {
		this.textChroma = enabled;
		
		setToFile("textChroma", enabled);
	}
	
	public boolean isTextChromaEnabled() {
		return textChroma;
	}
}
