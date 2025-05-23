package rubik.mods.impl;

import java.awt.Color;

import rubik.ColorManager;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggableText;

public class MemoryUsage extends ModDraggableText {
	private boolean showBackground = false;
	
	public MemoryUsage() {
		setShowBackground((boolean) getFromFile("showBackground", showBackground));
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
		
		drawCenteredText(getMemoryText(), pos.getAbsoluteX(), pos.getAbsoluteY(), textColor.getRGB(), textShadow, textChroma);
	}
	
	private String getMemoryText() {
		long maxMemory = Runtime.getRuntime().maxMemory();
		long totalMemory = Runtime.getRuntime().totalMemory();
		long freeMemory = Runtime.getRuntime().freeMemory();
		long usingMemory = totalMemory - freeMemory;
		
		String text = String.format("Mem: %1d%%", Long.valueOf(usingMemory * 100L / maxMemory));
		
		return showBackground ? text : "[" + text + "]";
	}
	
	public void setShowBackground(boolean enabled) {
		showBackground = enabled;
		
		setToFile("showBackground", enabled);
	}
	
	public boolean isShowBackgroundEnabled() {
		return showBackground;
	}
}
