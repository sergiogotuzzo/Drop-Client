package drop.mods.impl;

import drop.mods.hud.ScreenPosition;
import drop.mods.ModDraggableDisplayText;

public class FPSDisplay extends ModDraggableDisplayText {
	public FPSDisplay() {
		super(true, 0.5, 0.5, "60 FPS");
	}

	@Override
	public void render(ScreenPosition pos) {	
		drawTextToRender(pos, mc.getDebugFPS() + " FPS");
	}
}
