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
		return showBackground ? 58 : font.getStringWidth(brackets.wrap(mc.getDebugFPS() + " FPS"));
	}

	@Override
	public int getHeight() {
		return showBackground ? 18 : font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (showBackground) {
			drawRect(pos);
		}
		
		String text = mc.getDebugFPS() + " FPS";
		
		drawCenteredText(showBackground ? text : brackets.wrap(text), pos.getAbsoluteX(), pos.getAbsoluteY(), textColor, textShadow, textColor.isChromaToggled());
	}
}
