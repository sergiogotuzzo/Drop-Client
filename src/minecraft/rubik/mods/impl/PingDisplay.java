package rubik.mods.impl;

import java.awt.Color;

import net.minecraft.client.gui.Gui;
import net.minecraft.server.MinecraftServer;
import rubik.ColorManager;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggable;

public class PingDisplay extends ModDraggable {
	private boolean background = false;
	private ColorManager color = new ColorManager(new Color(255, 255, 255, 255));
	private boolean shadow = true;
	
	@Override
	public int getWidth() {
		return background ? 58 : font.getStringWidth("[27 ms]");
	}

	@Override
	public int getHeight() {
		return background ? 16 : font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (background) {
			Gui.drawRect(
					pos.getAbsoluteX(),
					pos.getAbsoluteY(),
					pos.getAbsoluteX() + getWidth(),
					pos.getAbsoluteY() + getHeight(),
					new Color(0, 0, 0, 102).getRGB()
					);
			
			if (shadow) {
				font.drawStringWithShadow(getPingText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getPingText())) / 2, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
			} else {
				font.drawString(getPingText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getPingText())) / 2, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
			}
		} else {
			if (shadow) {
				font.drawStringWithShadow(getPingText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getPingText())) / 2, pos.getAbsoluteY() + 1, color.getRGB());
			} else {
				font.drawString(getPingText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getPingText())) / 2, pos.getAbsoluteY() + 1, color.getRGB());
			}
		}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		if (background) {
			Gui.drawRect(
					pos.getAbsoluteX(),
					pos.getAbsoluteY(),
					pos.getAbsoluteX() + getWidth(),
					pos.getAbsoluteY() + getHeight(),
					new Color(0, 0, 0, 102).getRGB()
					);
			
			if (shadow) {
				font.drawStringWithShadow("27 ms", pos.getAbsoluteX() + (getWidth() - font.getStringWidth("27 ms")) / 2, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
			} else {
				font.drawString("27 ms", pos.getAbsoluteX() + (getWidth() - font.getStringWidth("27 ms")) / 2, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
			}
		} else {
			if (shadow) {
				font.drawStringWithShadow("[27 ms]", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color.getRGB());
			} else {
				font.drawString("[27 ms]", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color.getRGB());
			}
		}
	}
	
	public String getPingText() {
		int ping = MinecraftServer.getServer().isSinglePlayer() ? 0 : mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime();
		
		return background ? ping + " ms" : "[" + ping + " ms]";
	}
	
	public void setBackgroundEnabled(boolean enabled) {
		background = enabled;
	}
	
	public boolean isBackgroundEnabled() {
		return background;
	}
	
	public ColorManager getColorManager() {
		return color;
	}
	
	public Color getColor() {
		return color.getColor();
	}
	
	public void setShadowEnabled(boolean enabled) {
		shadow = enabled;
	}
	
	public boolean isShadowEnabled() {
		return shadow;
	}
}
