package drop.mods.impl;

import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiModDraggableText;
import drop.mods.hud.ScreenPosition;
import drop.mods.ModDraggableText;

public class FPSDisplay extends ModDraggableText {
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiModDraggableText(previousGuiScreen, this);
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
