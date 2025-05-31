package drop.mods.impl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import net.minecraft.item.ItemStack;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiModDraggableDisplayText;
import drop.mods.hud.ScreenPosition;
import drop.mods.ModDraggableDisplayText;

public class Clock extends ModDraggableDisplayText {
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
		
		LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String text = currentTime.format(formatter);
		
		drawCenteredText(brackets.wrap(text), pos.getAbsoluteX(), pos.getAbsoluteY(), textColor, textShadow, textChroma);
	}
}
