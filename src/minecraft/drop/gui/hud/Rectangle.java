package drop.gui.hud;

import java.awt.Color;

import drop.gui.GuiDropClient;
import drop.mod.ModDraggable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class Rectangle {
	private final ModDraggable mod;
	private final Minecraft mc;

	private ScreenPosition pos;
	private int width;
	private int height;
	
	private Rectangle(ModDraggable mod) {
		this.mod = mod;
		this.mc = mod.getMinecraft();
		this.pos = mod.getPosition();
		this.width = mod.getWidth();
		this.height = mod.getHeight();
	}
	
	public static Rectangle getBounds(ModDraggable mod) {
		return new Rectangle(mod);
	}
	
	public void fill(int color) {
		GuiDropClient.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY(), pos.getAbsoluteX() + width, pos.getAbsoluteY() + height, color);
	}
	
	public void fill(int color, String text, int gap) {
		int rectLeft;
		int rectRight;
		
		if (pos.getRelativeX() < 1.0 / 3.0) {
			rectLeft = pos.getAbsoluteX();
			rectRight = pos.getAbsoluteX() + mc.fontRendererObj.getStringWidth(text) + gap;
		} else if (pos.getRelativeX() > 2.0 / 3.0) {
			rectLeft = pos.getAbsoluteX() - mc.fontRendererObj.getStringWidth(text) + width - gap;
			rectRight = pos.getAbsoluteX() + width;
		} else {
			rectLeft = pos.getAbsoluteX() - (mc.fontRendererObj.getStringWidth(text) + gap) / 2 + width / 2;
			rectRight = pos.getAbsoluteX() + (mc.fontRendererObj.getStringWidth(text) + gap) / 2 + width / 2;
		}
		
		GuiDropClient.drawRect(rectLeft, pos.getAbsoluteY(), rectRight, pos.getAbsoluteY() + height, color);
	}
	
	public void stroke(int color) {
		GuiDropClient.drawHollowRect(pos.getAbsoluteX(), pos.getAbsoluteY(), width - 1, height - 1, color);
	}
	
	public void stroke(int color, String text, int gap) {
		int rectLeft;
	    int rectRight;

	    if (pos.getRelativeX() < 1.0 / 3.0) {
	        rectLeft = pos.getAbsoluteX();
	        rectRight = rectLeft + mc.fontRendererObj.getStringWidth(text) + gap;
	    } else if (pos.getRelativeX() > 2.0 / 3.0) {
	        rectRight = pos.getAbsoluteX() + width;
	        rectLeft = rectRight - mc.fontRendererObj.getStringWidth(text) - gap;
	    } else {
	        rectLeft = pos.getAbsoluteX() - (mc.fontRendererObj.getStringWidth(text) + gap) / 2 + width / 2;
	        rectRight = pos.getAbsoluteX() + (mc.fontRendererObj.getStringWidth(text) + gap) / 2 + width / 2;
	    }

	    GuiDropClient.drawRect(rectLeft + 1, pos.getAbsoluteY() + 1, rectRight - 1, pos.getAbsoluteY(), color);
	    GuiDropClient.drawRect(rectLeft + 1, pos.getAbsoluteY() + height, rectRight - 1, pos.getAbsoluteY() + height - 1, color);
	    GuiDropClient.drawRect(rectLeft + 1, pos.getAbsoluteY(), rectLeft, pos.getAbsoluteY() + height, color);
	    GuiDropClient.drawRect(rectRight, pos.getAbsoluteY(), rectRight - 1, pos.getAbsoluteY() + height, color);
	}
}
