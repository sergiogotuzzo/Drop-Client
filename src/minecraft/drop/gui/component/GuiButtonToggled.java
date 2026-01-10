package drop.gui.component;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class GuiButtonToggled extends GuiButton {
	public boolean toggled;
	
	public GuiButtonToggled(int buttonId, boolean toggled, int x, int y) {
		super(buttonId, x, y, 18, 11, "");
		
		this.toggled = toggled;
	}

	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;

            this.mouseDragged(mc, mouseX, mouseY);

        	if (this.toggled) {
            	drawRect(this.xPosition, this.yPosition + 1, this.xPosition + this.width - 5, this.yPosition + this.height - 1, new Color(0, 0, 0, this.hovered ? 50 : 100).getRGB());
            	drawRect(this.xPosition + this.width - 5, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, new Color(0, 255, 0, this.hovered ? 150 : 100).getRGB());
        	} else {
            	drawRect(this.xPosition + 5, this.yPosition + 1, this.xPosition + this.width, this.yPosition + this.height - 1, new Color(0, 0, 0, this.hovered ? 50 : 100).getRGB());
            	drawRect(this.xPosition, this.yPosition, this.xPosition + 5, this.yPosition + this.height, new Color(255, 0, 0, this.hovered ? 150 : 100).getRGB());
        	}
        }
    }
}