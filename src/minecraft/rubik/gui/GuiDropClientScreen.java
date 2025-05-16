package rubik.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.Client;

public abstract class GuiDropClientScreen extends GuiScreen {
	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawString(this.fontRendererObj, "Drop Client (" + Client.version + ")", 2, this.height - 10, 0x808080);
        this.drawString(this.fontRendererObj, "Minecraft 1.8.9", this.width - this.fontRendererObj.getStringWidth("Minecraft 1.8.9") - 2, this.height - 10, 0x808080);
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    public void drawHollowRect(int x, int y, int width, int height, int color) {
		this.drawHorizontalLine(x, x + width, y, color);
		this.drawHorizontalLine(x, x + width, y + height, color);
		this.drawVerticalLine(x, y + height, y, color);
		this.drawVerticalLine(x + width, y + height, y, color);
	}
}
