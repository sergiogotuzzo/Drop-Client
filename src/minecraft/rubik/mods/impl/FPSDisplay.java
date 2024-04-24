package rubik.mods.impl;

import java.awt.Color;

import net.minecraft.client.gui.Gui;
import rubik.ColorManager;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggable;

public class FPSDisplay extends ModDraggable {
	private boolean background = false;
	private ColorManager color = new ColorManager(new Color(255, 255, 255, 255));
	private boolean shadow = true;
	
	@Override
	public int getWidth() {
		return background ? 58 : font.getStringWidth("[99 FPS]");
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
				font.drawStringWithShadow(getFPSText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getFPSText())) / 2, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
			} else {
				font.drawString(getFPSText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getFPSText())) / 2, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
			}
		} else {
			if (shadow) {
				font.drawStringWithShadow(getFPSText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getFPSText())) / 2, pos.getAbsoluteY() + 1, color.getRGB());
			} else {
				font.drawString(getFPSText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getFPSText())) / 2, pos.getAbsoluteY() + 1, color.getRGB());
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
				font.drawStringWithShadow("99 FPS", pos.getAbsoluteX() + (getWidth() - font.getStringWidth("99 FPS")) / 2, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
			} else {
				font.drawString("99 FPS", pos.getAbsoluteX() + (getWidth() - font.getStringWidth("99 FPS")) / 2, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
			}
		} else {
			if (shadow) {
				font.drawStringWithShadow("[99 FPS]", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color.getRGB());
			} else {
				font.drawString("[99 FPS]", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color.getRGB());
			}
		}
	}
	
	private String getFPSText() {
		return background ? mc.getDebugFPS() + " FPS" : "[" + mc.getDebugFPS() + " FPS]";
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
