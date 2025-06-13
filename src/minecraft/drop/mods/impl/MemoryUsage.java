package drop.mods.impl;

import drop.mods.hud.ScreenPosition;
import drop.mods.ModDraggableDisplayText;

public class MemoryUsage extends ModDraggableDisplayText {
	public MemoryUsage() {
		super(false, 0.5, 0.5, "Mem: 1%");
	}

	@Override
	public void render(ScreenPosition pos) {				
		drawTextToRender(pos, String.format("Mem: %1d%%", Long.valueOf((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) * 100L / Runtime.getRuntime().maxMemory())));
	}
}
