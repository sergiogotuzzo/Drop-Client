package drop.mods.impl;

import drop.mods.hud.ScreenPosition;
import drop.mods.ModDraggableDisplayText;

public class DayCounter extends ModDraggableDisplayText {
	public DayCounter() {
		super(false, 0.5, 0.5, "365 days");
	}

	@Override
	public void render(ScreenPosition pos) {
		drawTextToRender(pos, Long.valueOf(this.mc.theWorld.getWorldTime() / 24000L) + " days");
	}
}
