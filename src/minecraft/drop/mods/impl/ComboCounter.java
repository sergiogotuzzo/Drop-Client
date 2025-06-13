package drop.mods.impl;

import drop.mods.hud.ScreenPosition;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import drop.mods.ModDraggableDisplayText;

public class ComboCounter extends ModDraggableDisplayText {
	public ComboCounter() {
		super(false, 0.5, 0.5);
	}

	private boolean attacked = false;
	private int combo = 0;
	private long lastCombo;
	
	@Override
	public int getWidth() {
		return showBackground ? 58 : font.getStringWidth(brackets.wrap(combo + " combo"));
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
		
		if (mc.thePlayer.hurtTime > 3 || System.currentTimeMillis() - lastCombo >= 5000) {
			combo = 0;
		}
		
		String text = combo + " combo";
		
		drawCenteredText(showBackground ? text : brackets.wrap(text), pos.getAbsoluteX(), pos.getAbsoluteY(), textColor, textShadow);
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
