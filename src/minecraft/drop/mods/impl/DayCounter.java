package drop.mods.impl;

import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiModDraggableDisplayText;
import drop.mods.hud.ScreenPosition;
import drop.mods.ModDraggableDisplayText;

public class DayCounter extends ModDraggableDisplayText {
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiModDraggableDisplayText(previousGuiScreen, this);
	}
	
	@Override
	public int getWidth() {
		return showBackground ? font.getStringWidth(Long.valueOf(this.mc.theWorld.getWorldTime() / 24000L) + " days") + 20 : font.getStringWidth(brackets.wrap(Long.valueOf(this.mc.theWorld.getWorldTime() / 24000L) + " days"));
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
		
		String text = Long.valueOf(this.mc.theWorld.getWorldTime() / 24000L) + " days";
		
		drawCenteredText(showBackground ? text : brackets.wrap(text), pos.getAbsoluteX(), pos.getAbsoluteY(), textColor, textShadow, textChroma);
	}
}
