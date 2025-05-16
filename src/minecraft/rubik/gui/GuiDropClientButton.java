package rubik.gui;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class GuiDropClientButton extends GuiButton {
	public GuiDropClientButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
	}
	
	public GuiDropClientButton(int buttonId, int x, int y, String buttonText) {
		super(buttonId, x, y, 200, 20, buttonText);
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
            
            int textColor = new Color(255, 255, 255).getRGB();

            if (!this.enabled) {
            	textColor = 10526880;
            } else if (this.hovered) {
            	drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, new Color(0, 0, 0, 110).getRGB());
            } else {
            	drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, new Color(0, 0, 0, 100).getRGB());
            }

            this.drawCenteredString(mc.fontRendererObj, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - mc.fontRendererObj.FONT_HEIGHT) / 2 + 1, textColor);
        }
    }
}
