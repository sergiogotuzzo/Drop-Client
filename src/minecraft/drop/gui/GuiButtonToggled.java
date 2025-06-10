package drop.gui;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class GuiButtonToggled extends GuiButton {
	public boolean toggled;
	
	public GuiButtonToggled(int buttonId, boolean toggled, int x, int y, int widthIn, int heightIn) {
		super(buttonId, x, y, widthIn, heightIn, "");
		
		this.toggled = toggled;
	}
	
	public GuiButtonToggled(int buttonId, boolean toggled, int x, int y) {
		super(buttonId, x, y, 20, 11, "");
		
		this.toggled = toggled;
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
            
        	
        	if (this.toggled) {
            	drawRect(this.xPosition, this.yPosition, this.xPosition + this.width - 5, this.yPosition + this.height, new Color(0, 0, 0, this.hovered ? 50 : 100).getRGB());
            	drawRect(this.xPosition + this.width - 5, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, new Color(0, 255, 0, this.hovered ? 110 : 100).getRGB());
        	} else {
            	drawRect(this.xPosition + 5, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, new Color(0, 0, 0, this.hovered ? 50 : 100).getRGB());
            	drawRect(this.xPosition, this.yPosition, this.xPosition + 5, this.yPosition + this.height, new Color(255, 0, 0, this.hovered ? 110 : 100).getRGB());
        	}
        }
    }
}