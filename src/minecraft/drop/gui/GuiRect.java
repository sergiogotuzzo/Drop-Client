package drop.gui;

import java.awt.Color;

import drop.ColorManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class GuiRect extends GuiButton {
    private int backgroundColor;
    private int borderColor;
    private boolean backgroundRainbow;
	
	public GuiRect(int buttonId, int x, int y, int widthIn, int heightIn, int backgroundColor, int borderColor, boolean backgroundRainbow) {
		super(buttonId, x, y, widthIn, heightIn, "");
		
		this.backgroundColor = backgroundColor;
		this.borderColor = borderColor;
		this.backgroundRainbow = backgroundRainbow;
	}

	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            
            ColorManager rectColor = new ColorManager(backgroundColor);
            
            if (this.backgroundRainbow) {
            	long t = System.currentTimeMillis() - (long) (this.xPosition * 10 - this.yPosition * 10);
	            ColorManager c = new ColorManager(Color.HSBtoRGB(t % (int) 2000.0F / 2000.0F, 0.8F, 0.8F));
	            
	            rectColor.setRed(c.getRed());
	            rectColor.setGreen(c.getGreen());
	            rectColor.setBlue(c.getBlue());
            }
            
            if (this.hovered) {
            	int alpha = rectColor.getAlpha();
            	
            	if (alpha >= 0 && alpha <= 127) {
            		alpha += 50;
            	} else if (alpha >= 127 && alpha <= 255) {
            		alpha -= 50;
            	}
            	
            	rectColor.setAlpha(alpha);
            }
            
            drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, rectColor.getRGB());
            GuiDropClient.drawHollowRect(this.xPosition, this.yPosition, this.width, this.height, borderColor);
        }
    }
}