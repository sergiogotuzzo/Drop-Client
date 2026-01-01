package drop.mods;

import java.awt.Color;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import drop.gui.GuiDropClient;
import drop.gui.GuiDropClientScreen;
import drop.gui.hud.IRenderer;
import drop.gui.hud.Rectangle;
import drop.gui.hud.ScreenPosition;

public abstract class ModDraggable extends Mod implements IRenderer {
	protected ScreenPosition pos;
	protected float zLevel;
	
	public ModDraggable(boolean enabled, double relativeX, double relativeY) {
		super(enabled);

		setPosition(ScreenPosition.fromRelativePosition((double) getFromFile("x", relativeX), (double) getFromFile("y", relativeY)));
	}
	
	public void setPosition(ScreenPosition pos) {
		this.pos = pos;
		
		setToFile("x", pos.getRelativeX());
		setToFile("y", pos.getRelativeY());
	}
	
	public ScreenPosition getPosition() {
		return pos;
	}
	
	public Rectangle getBounds() {
		return Rectangle.getBounds(this);
	}
	
	public void drawRect(int left, int top, int right, int bottom, int color) {
		GuiDropClient.drawRect(left, top, right, bottom, color);
	}
	
	public void drawHollowRect(int x, int y, int width, int height, int color) {
		GuiDropClient.drawHollowRect(x, y, width, height, color);
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
	
	public void drawText(String text, float x, float y, int color, boolean dropShadow, boolean chroma) {
		if (chroma) {
			float textCharX = x;
			
	        for (char textChar : text.toCharArray()) {
	            long t = System.currentTimeMillis() - (long) (textCharX * 10 - y * 10);
	            int c = Color.HSBtoRGB(t % (int) 2000.0F / 2000.0F, 0.8F, 0.8F);
	            
	            if (text.startsWith("§")) {
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
	
	public void drawText(String text, float x, float y, ModColor color, boolean dropShadow) {
		drawText(text, x, y, color.getRGB(), dropShadow, color.isChromaToggled());
	}
	
	public void drawCenteredText(String text, float x, float y, int color, boolean dropShadow, boolean chroma) {
		drawText(text, pos.getAbsoluteX() + (getWidth() - font.getStringWidth(text)) / 2.0F + 0.5F, pos.getAbsoluteY() + (getHeight() / 2.0F) - 4.0F, color, dropShadow, chroma);
	}
	
	public void drawCenteredText(String text, float x, float y, ModColor color, boolean dropShadow) {
		drawCenteredText(text, x, y, color.getRGB(), dropShadow, color.isChromaToggled());
	}
	
	public void drawCenteredText(String text, int gap, float x, float y, int color, boolean dropShadow, boolean chroma) {
		float textX;
		
		if (pos.getRelativeX() < 1.0 / 3.0) {
			textX = x + gap / 2.0F;
		} else if (pos.getRelativeX() > 2.0 / 3.0) {
			textX = x + getWidth() - font.getStringWidth(text) - gap / 2.0F;
		} else {
			textX = x + (getWidth() - font.getStringWidth(text)) / 2.0F;
		}
		
		drawText(text, textX, y + getHeight() / 2.0F - 4.0F, color, dropShadow, chroma);
	}
	
	public void drawCenteredText(String text, int gap, float x, float y, ModColor color, boolean dropShadow) {
		drawCenteredText(text, gap, x, y, color.getRGB(), dropShadow, color.isChromaToggled());
	}
	
	public void drawAlignedText(String text, float x, float y, int color, boolean dropShadow, boolean chroma) {
		float alignedX;
		
		if (pos.getRelativeX() < 1.0 / 3.0) {
			alignedX = x;
		} else if (pos.getRelativeX() > 2.0 / 3.0) {
			alignedX = x + getWidth() - font.getStringWidth(text);
		} else {
			alignedX = x + (getWidth() - font.getStringWidth(text)) / 2.0F;
		}
		
		drawText(text, alignedX, y, color, dropShadow, chroma);
	}
	
	public void drawAlignedText(String text, float x, float y, ModColor color, boolean dropShadow) {
		drawAlignedText(text, x, y, color.getRGB(), dropShadow, color.isChromaToggled());
	}
}
