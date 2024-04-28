package rubik.mods.impl;

import java.awt.Color;

import net.minecraft.client.gui.Gui;
import net.minecraft.server.MinecraftServer;
import rubik.ColorManager;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggable;

public class PingDisplay extends ModDraggable {
	private boolean showBackground = false;
	private boolean textShadow = true;
	private ColorManager color = new ColorManager(Color.WHITE);
	
	@Override
	public int getWidth() {
		return showBackground ? 58 : font.getStringWidth("[27 ms]");
	}

	@Override
	public int getHeight() {
		return showBackground ? 16 : font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (showBackground) {
			Gui.drawRect(
					pos.getAbsoluteX(),
					pos.getAbsoluteY(),
					pos.getAbsoluteX() + getWidth(),
					pos.getAbsoluteY() + getHeight(),
					new Color(0, 0, 0, 102).getRGB()
					);
			
			if (textShadow) {
				font.drawStringWithShadow(getPingText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getPingText())) / 2, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
			} else {
				font.drawString(getPingText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getPingText())) / 2, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
			}
		} else {
			if (textShadow) {
				font.drawStringWithShadow(getPingText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getPingText())) / 2, pos.getAbsoluteY() + 1, color.getRGB());
			} else {
				font.drawString(getPingText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getPingText())) / 2, pos.getAbsoluteY() + 1, color.getRGB());
			}
		}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		if (showBackground) {
			Gui.drawRect(
					pos.getAbsoluteX(),
					pos.getAbsoluteY(),
					pos.getAbsoluteX() + getWidth(),
					pos.getAbsoluteY() + getHeight(),
					new Color(0, 0, 0, 102).getRGB()
					);
			
			if (textShadow) {
				font.drawStringWithShadow("27 ms", pos.getAbsoluteX() + (getWidth() - font.getStringWidth("27 ms")) / 2, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
			} else {
				font.drawString("27 ms", pos.getAbsoluteX() + (getWidth() - font.getStringWidth("27 ms")) / 2, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
			}
		} else {
			if (textShadow) {
				font.drawStringWithShadow("[27 ms]", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color.getRGB());
			} else {
				font.drawString("[27 ms]", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color.getRGB());
			}
		}
	}
	
	private String getPingText() {
		int ping = MinecraftServer.getServer().isSinglePlayer() ? 0 : mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime();
		
		return showBackground ? ping + " ms" : "[" + ping + " ms]";
	}
	
	public void setShowBackground(boolean enabled) {
		showBackground = enabled;
	}
	
	public boolean isShowBackgroundEnabled() {
		return showBackground;
	}
	
	public void setTextShadow(boolean enabled) {
		textShadow = enabled;
	}
	
	public boolean isTextShadowEnabled() {
		return textShadow;
	}
	
	public ColorManager getColor() {
		return color;
	}
}
