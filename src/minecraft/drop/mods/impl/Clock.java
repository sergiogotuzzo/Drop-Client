package drop.mods.impl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import drop.mods.hud.ScreenPosition;
import drop.mods.ModDraggableDisplayText;

public class Clock extends ModDraggableDisplayText {    
	public Clock() {
		super(false, 0.5, 0.5, "12:00");
	}

	@Override
	public void render(ScreenPosition pos) {
		drawTextToRender(pos, LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
	}
}
