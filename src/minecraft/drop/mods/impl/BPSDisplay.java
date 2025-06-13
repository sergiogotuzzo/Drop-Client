package drop.mods.impl;

import drop.mods.hud.ScreenPosition;
import drop.mods.ModDraggableDisplayText;

public class BPSDisplay extends ModDraggableDisplayText {
	public BPSDisplay() {
		super(false, 0.5, 0.5);
	}

	float blocks;
	
	@Override
	public int getWidth() {
		return showBackground ? 58 : font.getStringWidth(brackets.wrap(String.format("%.1f", blocks) + " m/s"));
	}

	@Override
	public int getHeight() {
		return showBackground ? 18 : font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		float ticks = mc.timer.ticksPerSecond * mc.timer.timerSpeed;
		
        this.blocks = (float) (mc.thePlayer.getDistance(mc.thePlayer.lastTickPosX, mc.thePlayer.posY, mc.thePlayer.lastTickPosZ) * ticks);
        
		if (showBackground) {
			drawRect(pos);
		}
		
		String text = String.format("%.1f", blocks) + " m/s";
		
		drawCenteredText(showBackground ? text : brackets.wrap(text), pos.getAbsoluteX(), pos.getAbsoluteY(), textColor, textShadow);
	}
}
