package rubik.mods;

import java.awt.Color;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import rubik.ColorManager;
import rubik.gui.hud.IRenderer;
import rubik.gui.hud.ScreenPosition;

public abstract class ModDraggableText extends ModDraggable {
	protected ColorManager textColor = ColorManager.fromColor(Color.WHITE);
	protected boolean textShadow = true;
	protected boolean textChroma = false;
	
	public ModDraggableText() {
		setTextColor((int) ((long) getFromFile("textColor", textColor.getRGB())));
		setTextShadow((boolean) getFromFile("textShadow", textShadow));
		setTextChroma((boolean) getFromFile("textChroma", textChroma));
	}
	
	public void setTextColor(int rgb) {
		this.textColor = ColorManager.fromRGB(rgb);
		
		setToFile("textColor", rgb);
	}
	
	public ColorManager getTextColor() {
		return textColor;
	}
	
	public void setTextShadow(boolean enabled) {
		textShadow = enabled;
		
		setToFile("textShadow", enabled);
	}
	
	public boolean isTextShadowEnabled() {
		return textShadow;
	}
	
	public void setTextChroma(boolean enabled) {
		this.textChroma = enabled;
		
		setToFile("textChroma", enabled);
	}
	
	public boolean isTextChromaEnabled() {
		return textChroma;
	}
}
