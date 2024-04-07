package rubik.mods.impl;

import java.awt.Color;

import net.minecraft.client.gui.Gui;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggable;

public class FPSDisplay extends ModDraggable {
	private boolean background = false;
	private int color = 0xFFFFFFFF;
	private boolean shadow = true;
	
	@Override
	public int getWidth() {
		return font.getStringWidth(background ? "99 FPS" : "[99 FPS]");
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (background) {
			int paddingX = 10;
	        int paddingY = 4;
	        
			Gui.drawRect(
					pos.getAbsoluteX() - paddingX,
					pos.getAbsoluteY() - paddingY,
					pos.getAbsoluteX() + getWidth() + paddingX + 2,
					pos.getAbsoluteY() + getHeight() + paddingY,
					new Color(0, 0, 0, 102).getRGB()
					);
		}
		
		if (shadow) {
			font.drawStringWithShadow(getFPSText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getFPSText())) / 2, pos.getAbsoluteY() + 1, color);
		} else {
			font.drawString(getFPSText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getFPSText())) / 2, pos.getAbsoluteY() + 1, color);
		}
	}
	
	public String getFPSText() {
		return background ? mc.getDebugFPS() + " FPS" : "[" + mc.getDebugFPS() + " FPS]";
	}
	
	public void setBackgroundEnabled(boolean enabled) {
		background = enabled;
	}
	
	public boolean isBackgroundEnabled() {
		return background;
	}
	
	public void setShadowEnabled(boolean enabled) {
		shadow = enabled;
	}
	
	public boolean isShadowEnabled() {
		return shadow;
	}
}
