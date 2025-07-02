package drop.gui.hud;

import java.awt.Color;

import drop.gui.GuiDropClient;
import drop.mods.ModDraggable;
import net.minecraft.client.gui.Gui;

public class Rectangle {
	private ScreenPosition pos;
	private int width;
	private int height;
	
	private Rectangle(ScreenPosition pos, int width, int height) {
		this.pos = pos;
		this.width = width;
		this.height = height;
	}
	
	public static Rectangle getBounds(ModDraggable mod) {
		return new Rectangle(mod.getPosition(), mod.getWidth(), mod.getHeight());
	}
	
	public void fill(int color) {
		GuiDropClient.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + width, pos.getAbsoluteY() + height, color);
	}
	
	public void stroke(int color) {
		GuiDropClient.drawHollowRect(pos.getAbsoluteX(), pos.getAbsoluteY(), width - 1, height - 1, color);
	}
}
