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

public abstract class ModDraggable extends Mod implements IRenderer {
	protected ScreenPosition pos;
	protected float zLevel;
	
	public ModDraggable() {
		save(ScreenPosition.fromRelativePosition((double) getFromFile("posX", 0.5), (double) getFromFile("posY", 0.5)));
	}
	
	public void save(ScreenPosition pos) {
		this.pos = pos;
		
		setToFile("posX", pos.getRelativeX());
		setToFile("posY", pos.getRelativeY());
	}
	
	public ScreenPosition load() {
		return pos;
	}
	
	public void drawRect(int left, int top, int right, int bottom, int color) {
		Gui.drawRect(left, top, right, bottom, color);
	}

	public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos((double)(x + 0), (double)(y + height), (double)this.zLevel).tex((double)((float)(textureX + 0) * f), (double)((float)(textureY + height) * f1)).endVertex();
        worldrenderer.pos((double)(x + width), (double)(y + height), (double)this.zLevel).tex((double)((float)(textureX + width) * f), (double)((float)(textureY + height) * f1)).endVertex();
        worldrenderer.pos((double)(x + width), (double)(y + 0), (double)this.zLevel).tex((double)((float)(textureX + width) * f), (double)((float)(textureY + 0) * f1)).endVertex();
        worldrenderer.pos((double)(x + 0), (double)(y + 0), (double)this.zLevel).tex((double)((float)(textureX + 0) * f), (double)((float)(textureY + 0) * f1)).endVertex();
        
        tessellator.draw();
    }
	
	public void drawText(String text, int x, int y, int color, boolean textShadow, boolean chroma) {
		if (chroma) {
			int textCharX = x;
			
	        for (char textChar : text.toCharArray()) {
	            long t = System.currentTimeMillis() - (textCharX * 10 - y * 10);
	            int c = Color.HSBtoRGB(t % (int) 2000.0F / 2000.0F, 0.8F, 0.8F);
	            
	            if (text.startsWith("§m")) {
	            	drawText(text, x, y, c, textShadow, false);
	            } else {
		            drawText(String.valueOf(textChar), textCharX, y, c, textShadow, false);
	            }
	            
	            textCharX += font.getCharWidth(textChar);
	        }
		} else {
			font.drawString(text, x, y, color, textShadow);
		}
	}
	
	public void drawCenteredText(String text, int x, int y, int color, boolean textShadow, boolean chroma) {
		drawText(text, x + (getWidth() - font.getStringWidth(text)) / 2, y + getHeight() / 2 - 4, color, textShadow, chroma);
	}
	
	public void drawAlignedText(String text, int x, int y, int color, boolean textShadow, boolean chroma) {
		drawText(text, getAlignedAbsoluteX(text), y, color, textShadow, chroma);
	}
	
	public void drawScaledText(String text, int x, int y, double scale, int color, boolean textShadow, boolean chroma) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, 0);
		GlStateManager.scale(scale, scale, 1);
		GlStateManager.translate(-x, -y, 0);
		
		drawText(text, x, y, color, textShadow, chroma);
		
		GlStateManager.popMatrix();
	}
	
	public int getAlignedAbsoluteX(String text) {
		int positionX;
		
		if (pos.getRelativeX() < 1.0 / 3.0) {
			positionX = pos.getAbsoluteX();
		} else if (pos.getRelativeX() > 2.0 / 3.0) {
			positionX = pos.getAbsoluteX() + getWidth() - font.getStringWidth(text);
		} else {
			positionX = pos.getAbsoluteX() + (getWidth() - font.getStringWidth(text)) / 2;
		}
	    
	    return positionX;
	}
	
	public final int getLineOffset(ScreenPosition pos, int lineNum) {
		return pos.getAbsoluteY() + getLineOffset(lineNum);
	}
	
	private int getLineOffset(int lineNum) {
		return (font.FONT_HEIGHT + 3) * lineNum;
	}
}
