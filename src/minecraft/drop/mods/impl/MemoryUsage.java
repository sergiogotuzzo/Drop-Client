package drop.mods.impl;

import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiModDraggableDisplayText;
import drop.mods.hud.ScreenPosition;
import drop.mods.ModDraggableDisplayText;

public class MemoryUsage extends ModDraggableDisplayText {
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiModDraggableDisplayText(previousGuiScreen, this);
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

		long maxMemory = Runtime.getRuntime().maxMemory();
		long totalMemory = Runtime.getRuntime().totalMemory();
		long freeMemory = Runtime.getRuntime().freeMemory();
		long usingMemory = totalMemory - freeMemory;
		String text = String.format("Mem: %1d%%", Long.valueOf(usingMemory * 100L / maxMemory));
		
		drawCenteredText(brackets.wrap(text), pos.getAbsoluteX(), pos.getAbsoluteY(), textColor, textShadow, textChroma);
	}
}
