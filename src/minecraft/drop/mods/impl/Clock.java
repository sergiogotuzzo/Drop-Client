package drop.mods.impl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import net.minecraft.item.ItemStack;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiClock;
import drop.gui.mod.hud.ScreenPosition;
import drop.mods.ModDraggableText;

public class Clock extends ModDraggableText {
	private boolean showBackground = false;
	
	public Clock() {
		setShowBackground((boolean) getFromFile("showBackground", showBackground));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiClock(previousGuiScreen);
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
	
	public void setShowBackground(boolean enabled) {
		showBackground = enabled;
		
		setToFile("showBackground", enabled);
	}
	
	public boolean isShowBackgroundEnabled() {
		return showBackground;
	}
}
