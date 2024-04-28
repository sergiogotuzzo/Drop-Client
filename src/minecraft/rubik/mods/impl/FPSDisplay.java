package rubik.mods.impl;

import java.awt.Color;

import net.minecraft.client.gui.Gui;
import rubik.ColorManager;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggable;

public class FPSDisplay extends ModDraggable {
	private boolean showBackground = false;
	private boolean textShadow = true;
	private ColorManager color = new ColorManager(Color.WHITE);
	
	@Override
	public int getWidth() {
		return showBackground ? 58 : font.getStringWidth("[99 FPS]");
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
				font.drawStringWithShadow(getFPSText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getFPSText())) / 2, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
			} else {
				font.drawString(getFPSText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getFPSText())) / 2, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
			}
		} else {
			if (textShadow) {
				font.drawStringWithShadow(getFPSText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getFPSText())) / 2, pos.getAbsoluteY() + 1, color.getRGB());
			} else {
				font.drawString(getFPSText(), pos.getAbsoluteX() + (getWidth() - font.getStringWidth(getFPSText())) / 2, pos.getAbsoluteY() + 1, color.getRGB());
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
				font.drawStringWithShadow("99 FPS", pos.getAbsoluteX() + (getWidth() - font.getStringWidth("99 FPS")) / 2, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
			} else {
				font.drawString("99 FPS", pos.getAbsoluteX() + (getWidth() - font.getStringWidth("99 FPS")) / 2, pos.getAbsoluteY() + (getHeight() - (getHeight() / 2)) / 2, color.getRGB());
			}
		} else {
			if (textShadow) {
				font.drawStringWithShadow("[99 FPS]", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color.getRGB());
			} else {
				font.drawString("[99 FPS]", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, color.getRGB());
			}
		}
	}
	
	private String getFPSText() {
		return showBackground ? mc.getDebugFPS() + " FPS" : "[" + mc.getDebugFPS() + " FPS]";
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
