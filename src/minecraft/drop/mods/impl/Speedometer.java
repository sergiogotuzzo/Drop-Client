package drop.mods.impl;

import drop.mods.hud.ScreenPosition;
import drop.mods.ModDraggableDisplayText;

public class Speedometer extends ModDraggableDisplayText {
	public Speedometer() {
		super(false, 0.5, 0.5, "0,00 m/s");
	}

	float blocks;

	@Override
	public void render(ScreenPosition pos) {
		float ticks = mc.timer.ticksPerSecond * mc.timer.timerSpeed;
		
        this.blocks = (float) (mc.thePlayer.getDistance(mc.thePlayer.lastTickPosX, mc.thePlayer.posY, mc.thePlayer.lastTickPosZ) * ticks);
		
		drawTextToRender(pos, String.format("%.2f", blocks) + " m/s");
	}
}
