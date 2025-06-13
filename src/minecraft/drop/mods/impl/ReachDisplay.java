package drop.mods.impl;

import drop.mods.hud.ScreenPosition;
import net.minecraft.entity.Entity;
import drop.mods.ModDraggableDisplayText;

public class ReachDisplay extends ModDraggableDisplayText {
	public ReachDisplay() {
		super(false, 0.5, 0.5);
	}
	
	float range = 0.0F;
	private long lastHit;
	
	@Override
	public int getWidth() {
		return showBackground ? 58 : font.getStringWidth(brackets.wrap(String.format("%.1f", range) + " blocks"));
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
		
		if (System.currentTimeMillis() - lastHit >= 3000) {
			this.range = 0.0F;
		}
		
		String text = String.format("%.1f", range) + " blocks";
		
		drawCenteredText(showBackground ? text : brackets.wrap(text), pos.getAbsoluteX(), pos.getAbsoluteY(), textColor, textShadow);
	}
	
	public void onEntityHit(Entity entity) {
		this.range = mc.thePlayer.getDistanceToEntity(entity);
		this.lastHit = System.currentTimeMillis();
	}
}
