package drop.mods;

import java.awt.Color;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import drop.ColorManager;
import drop.gui.GuiDropClient;
import drop.gui.GuiDropClientScreen;
import drop.mods.hud.IRenderer;
import drop.mods.hud.Bounds;
import drop.mods.hud.ScreenPosition;

public abstract class ModDraggable extends Mod implements IRenderer {
	protected ScreenPosition pos;
	protected float zLevel;
	
	public ModDraggable(boolean enabled, double relativeX, double relativeY) {
		super(enabled);
		
		setPosition(ScreenPosition.fromRelativePosition(getDoubleFromFile("x", relativeX), getDoubleFromFile("y", relativeY)));
	}
	
	public void setPosition(ScreenPosition pos) {
		this.pos = pos;
		
		setToFile("x", pos.getRelativeX());
		setToFile("y", pos.getRelativeY());
	}
	
	public ScreenPosition getPosition() {
		return pos;
	}
	
	public Bounds getBounds() {
		return Bounds.getBounds(this);
	}
	
	public void drawRect(int left, int top, int right, int bottom, int color) {
		GuiDropClient.drawRect(left, top, right, bottom, color);
	}
	
	public void drawRect(int left, int top, int right, int bottom, Color color) {
		drawRect(left, top, right, bottom, color.getRGB());
	}
	
	public void drawRect(int left, int top, int right, int bottom, ColorManager color) {
		drawRect(left, top, right, bottom, color.getRGB());
	}
	
	public void drawAlignedRect(ScreenPosition pos, String text, int widthBackground, int color) {
		int rectLeft;
		int rectRight;
		
		if (pos.getRelativeX() < 1.0 / 3.0) {
			rectLeft = pos.getAbsoluteX();
			rectRight = pos.getAbsoluteX() + font.getStringWidth(text) + widthBackground;
		} else if (pos.getRelativeX() > 2.0 / 3.0) {
			rectLeft = pos.getAbsoluteX() - font.getStringWidth(text) + getWidth() - widthBackground;
			rectRight = pos.getAbsoluteX() + getWidth();
		} else {
			rectLeft = pos.getAbsoluteX() - (font.getStringWidth(text) + widthBackground) / 2 + getWidth() / 2;
			rectRight = pos.getAbsoluteX() + (font.getStringWidth(text) + widthBackground) / 2 + getWidth() / 2;
		}
		
    	drawRect(rectLeft, pos.getAbsoluteY(), rectRight, pos.getAbsoluteY() + getHeight(), color);
	}
	
	public void drawAlignedRect(ScreenPosition pos, String text, int widthBackground, Color color) {
		drawAlignedRect(pos, text, widthBackground, color.getRGB());
	}
	
	public void drawAlignedRect(ScreenPosition pos, String text, int widthBackground) {
		drawAlignedRect(pos, text, widthBackground, new Color(0, 0, 0, 102));
	}
	
	public void drawText(String text, int x, int y, int color, boolean dropShadow, boolean chroma) {
		if (chroma) {
			int textCharX = x;
			
	        for (char textChar : text.toCharArray()) {
	            long t = System.currentTimeMillis() - (textCharX * 10 - y * 10);
	            int c = Color.HSBtoRGB(t % (int) 2000.0F / 2000.0F, 0.8F, 0.8F);
	            
	            if (text.startsWith("§m")) {
	            	drawText(text, x, y, c, dropShadow, false);
	            } else {
		            drawText(String.valueOf(textChar), textCharX, y, c, dropShadow, false);
	            }
	            
	            textCharX += font.getCharWidth(textChar);
	        }
		} else {
			font.drawString(text, x, y, color, dropShadow);
		}
	}
	
	public void drawText(String text, int x, int y, ColorManager color, boolean dropShadow) {
		drawText(text, x, y, color.getRGB(), dropShadow, color.isChromaToggled());
	}
	
	public void drawCenteredText(String text, int x, int y, int color, boolean dropShadow, boolean chroma) {
		drawText(text, pos.getAbsoluteX() + (getWidth() - font.getStringWidth(text)) / 2, pos.getAbsoluteY() + getHeight() / 2 - 4, color, dropShadow, chroma);
	}
	
	public void drawCenteredText(String text, int x, int y, ColorManager color, boolean dropShadow) {
		drawCenteredText(text, x, y, color.getRGB(), dropShadow, color.isChromaToggled());
	}
	
	public void drawCenteredAlignedText(ScreenPosition pos, String text, int widthBackground, int x, int y, int color, boolean dropShadow, boolean chroma) {
		int textX;
		
		if (pos.getRelativeX() < 1.0 / 3.0) {
			textX = x + widthBackground / 2;
		} else if (pos.getRelativeX() > 2.0 / 3.0) {
			textX = x + getWidth() - font.getStringWidth(text) - widthBackground / 2;
		} else {
			textX = x + (getWidth() - font.getStringWidth(text)) / 2;
		}
		
		drawText(text, textX, y + getHeight() / 2 - 4, color, dropShadow, chroma);
	}
	
	public void drawCenteredAlignedText(ScreenPosition pos, String text, int widthBackground, int x, int y, ColorManager color, boolean dropShadow) {
		drawCenteredAlignedText(pos, text, widthBackground, x, y, color.getRGB(), dropShadow, color.isChromaToggled());
	}
	
	public void drawAlignedText(String text, int x, int y, int color, boolean dropShadow, boolean chroma) {
		int alignedX;
		
		if (pos.getRelativeX() < 1.0 / 3.0) {
			alignedX = x;
		} else if (pos.getRelativeX() > 2.0 / 3.0) {
			alignedX = x + getWidth() - font.getStringWidth(text);
		} else {
			alignedX = x + (getWidth() - font.getStringWidth(text)) / 2;
		}
		
		drawText(text, alignedX, y, color, dropShadow, chroma);
	}
	
	public void drawAlignedText(String text, int x, int y, ColorManager color, boolean dropShadow) {
		drawAlignedText(text, x, y, color.getRGB(), dropShadow, color.isChromaToggled());
	}
	
	public void drawScaledText(String text, int x, int y, double scale, int color, boolean dropShadow, boolean chroma) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, 0);
		GlStateManager.scale(scale, scale, 1);
		GlStateManager.translate(-x, -y, 0);
		
		drawText(text, x, y, color, dropShadow, chroma);
		
		GlStateManager.popMatrix();
	}
	
	public void drawScaledText(String text, int x, int y, double scale, ColorManager color, boolean dropShadow) {
		drawScaledText(text, x, y, scale, color.getRGB(), dropShadow, color.isChromaToggled());
	}
	
	public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height) {
		float f = 0.00390625F;
		float f1 = 0.00390625F;
        
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldRenderer = tessellator.getWorldRenderer();
		
		worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
		worldRenderer.pos((double)(x + 0), (double)(y + height), (double)this.zLevel).tex((double)((float)(textureX + 0) * f), (double)((float)(textureY + height) * f1)).endVertex();
		worldRenderer.pos((double)(x + width), (double)(y + height), (double)this.zLevel).tex((double)((float)(textureX + width) * f), (double)((float)(textureY + height) * f1)).endVertex();
		worldRenderer.pos((double)(x + width), (double)(y + 0), (double)this.zLevel).tex((double)((float)(textureX + width) * f), (double)((float)(textureY + 0) * f1)).endVertex();
		worldRenderer.pos((double)(x + 0), (double)(y + 0), (double)this.zLevel).tex((double)((float)(textureX + 0) * f), (double)((float)(textureY + 0) * f1)).endVertex();
		
		tessellator.draw();
	}
}
