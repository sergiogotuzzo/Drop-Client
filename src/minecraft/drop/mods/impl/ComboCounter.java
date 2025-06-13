package drop.mods.impl;

import drop.mods.hud.ScreenPosition;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.server.S19PacketEntityStatus;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import drop.mods.ModDraggableDisplayText;

public class ComboCounter extends ModDraggableDisplayText {
	public ComboCounter() {
		super(false, 0.5, 0.5, "0 combo");
	}

	private boolean attacked = false;
	private int combo = 0;
	private long lastCombo;

	@Override
	public void render(ScreenPosition pos) {
		if (mc.thePlayer.hurtTime > 3 || System.currentTimeMillis() - lastCombo >= 5000) {
			combo = 0;
		}
		
		drawTextToRender(pos, combo + " combo");
	}
	
	public void onAttack() {
		attacked = true;
	}
	
	public void onEntityHit(S19PacketEntityStatus event) {
		if (attacked && event.getOpCode() == 2) {
			combo++;
			lastCombo = System.currentTimeMillis();
			attacked = false;
		}
	}
}
