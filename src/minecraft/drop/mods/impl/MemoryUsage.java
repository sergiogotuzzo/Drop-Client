package drop.mods.impl;

import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiMemoryUsage;
import drop.gui.mod.hud.ScreenPosition;
import drop.mods.ModDraggableText;

public class MemoryUsage extends ModDraggableText {
	private boolean showBackground = false;
	
	public MemoryUsage() {
		setShowBackground((boolean) getFromFile("showBackground", showBackground));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiMemoryUsage(previousGuiScreen);
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
			drawRect(pos);
		}
		
		drawCenteredText(getMemoryText(), pos.getAbsoluteX(), pos.getAbsoluteY(), textColor, textShadow, textChroma);
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
