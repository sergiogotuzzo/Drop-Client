package drop.mods.impl;

import drop.mods.hud.ScreenPosition;
import net.minecraft.entity.Entity;
import drop.events.EventTarget;
import drop.events.impl.EntityDamageEvent;
import drop.mods.ModDraggableDisplayText;

public class ReachDisplay extends ModDraggableDisplayText {
	public ReachDisplay() {
		super(false, 0.5, 0.5, "0,00 blocks");
	}
	
	float range = 0.0F;
	private long lastHit;

	@Override
	public void render(ScreenPosition pos) {
		if (System.currentTimeMillis() - lastHit >= 3000) {
			this.range = 0.0F;
		}
				
		drawTextToRender(pos, String.format("%.2f", range) + " blocks");
	}
	
	@EventTarget
	public void onEntityDamage(EntityDamageEvent event) {
		range = mc.thePlayer.getDistanceToEntity(event.getEntity());
		lastHit = System.currentTimeMillis();
	}
}
