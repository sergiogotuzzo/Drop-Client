package drop.gui;

import java.awt.Color;

import drop.ColorManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class GuiRect extends GuiButton {
    public int color;
    
    public GuiRect(int buttonId, int x, int y) {
		this(buttonId, x, y, 13, 13, Color.BLACK.getRGB());
	}
    
    public GuiRect(int buttonId, int x, int y, int color) {
		this(buttonId, x, y, 13, 13, color);
	}
    
    public GuiRect(int buttonId, int x, int y, int widthIn, int heightIn) {
		this(buttonId, x, y, widthIn, heightIn, Color.BLACK.getRGB());
	}
	
	public GuiRect(int buttonId, int x, int y, int widthIn, int heightIn, int color) {
		super(buttonId, x, y, widthIn, heightIn, "");
		
		this.color = color;
	}

	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;

            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);
            
            ColorManager rectColor = ColorManager.fromRGB(color, false);
            
            if (this.hovered) {
            	int alpha = ColorManager.fromRGB(color, false).getAlpha();
            	
            	if (alpha >= 0 && alpha <= 127) {
            		alpha += 50;
            	} else if (alpha >= 127 && alpha <= 255) {
            		alpha -= 50;
            	}
            	
            	rectColor.setAlpha(alpha);
            }
            
            drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, rectColor.getRGB());
            GuiDropClient.drawHollowRect(this.xPosition, this.yPosition, this.width, this.height, Color.BLACK.getRGB());
        }
    }
}