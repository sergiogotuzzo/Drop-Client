package drop.mods.impl;

import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiModDraggableDisplayText;
import drop.mods.hud.ScreenPosition;
import drop.mods.ModDraggableDisplayText;

public class FPSDisplay extends ModDraggableDisplayText {
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
		
		String text = mc.getDebugFPS() + " FPS";
		
		drawCenteredText(brackets.wrap(text), pos.getAbsoluteX(), pos.getAbsoluteY(), textColor, textShadow, textChroma);
	}
}
