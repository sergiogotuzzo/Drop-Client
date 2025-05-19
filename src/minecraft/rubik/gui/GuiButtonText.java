package rubik.gui;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class GuiButtonText extends GuiButton {
	public GuiButtonText(int buttonId, int x, int y, String text) {
		super(buttonId, x, y, 0, 0, text);
		
		this.width = Minecraft.getMinecraft().fontRendererObj.getStringWidth(text);
		this.height = Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT;
	}

	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int i = this.getHoverState(this.hovered);
            
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);

            this.mouseDragged(mc, mouseX, mouseY);
                               
            int textColor = -1;
            
        	if (this.hovered) {
        		textColor = 16777120;
        	}
        	
        	mc.fontRendererObj.drawString(displayString, this.xPosition, this.yPosition, textColor);
        }
    }
}