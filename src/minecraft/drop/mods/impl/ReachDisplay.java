package drop.mods.impl;

import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiModDraggableDisplayText;
import drop.mods.hud.ScreenPosition;
import net.minecraft.entity.Entity;
import drop.mods.ModDraggableDisplayText;

public class ReachDisplay extends ModDraggableDisplayText {
	public ReachDisplay() {
		super(false, 0.5, 0.5);
	}
	
	float range = 0.0F;
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiModDraggableDisplayText(previousGuiScreen, this);
	}
	
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
		
		String text = String.format("%.1f", range) + " blocks";
		
		drawCenteredText(showBackground ? text : brackets.wrap(text), pos.getAbsoluteX(), pos.getAbsoluteY(), textColor, textShadow);
	}
	
	public void onEntityHit(Entity entity) {
		System.out.println(entity.getName());
		
		this.range = mc.thePlayer.getDistanceToEntity(entity);
	}
}
