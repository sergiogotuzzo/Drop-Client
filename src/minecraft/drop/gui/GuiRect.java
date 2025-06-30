package drop.gui;

import drop.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class GuiRect extends GuiButton {
    private int backgroundColor;
    private int borderColor;
	
	public GuiRect(int buttonId, int x, int y, int widthIn, int heightIn, int backgroundColor, int borderColor) {
		super(buttonId, x, y, widthIn, heightIn, "");
		
		this.backgroundColor = backgroundColor;
		this.borderColor = borderColor;
	}

	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            
            Color rectColor = new Color(backgroundColor);
            
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