package drop.mods.impl;

import drop.mods.hud.ScreenPosition;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.server.S19PacketEntityStatus;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import drop.events.EventTarget;
import drop.events.impl.EntityAttackEvent;
import drop.events.impl.EntityDamageEvent;
import drop.mods.ModDraggableDisplayText;

public class ComboCounter extends ModDraggableDisplayText {
	public ComboCounter() {
		super(false, 0.5, 0.5, "0 combo");
	}

	private int targetId;
	private int combo = 0;
	private long lastCombo;

	@Override
	public void render(ScreenPosition pos) {
		if (mc.thePlayer.hurtTime > 3 || System.currentTimeMillis() - lastCombo >= 5000) {
			combo = 0;
		}
		
		drawTextToRender(pos, combo + " combo");
	}

	@EventTarget
	public void onEntityAttack(EntityAttackEvent event) {
		this.targetId = event.getEntity().getEntityId();
	}
	
	@EventTarget
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getEntity().getEntityId() == targetId) {
			dealHit();
		} else if (event.getEntity() == mc.thePlayer) {
			takeHit();
		}
	}
	
	private void dealHit() {
		targetId = -1;
		combo++;
		lastCombo = System.currentTimeMillis();
	}
	
	private void takeHit() {
		combo = 0;
	}
}
