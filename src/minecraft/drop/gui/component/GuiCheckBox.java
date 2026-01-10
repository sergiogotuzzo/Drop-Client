package drop.gui.component;

import java.awt.Color;

import net.minecraft.client.Minecraft;

public class GuiCheckBox extends GuiRect {
	private boolean toggled;
	
	public GuiCheckBox(int buttonId, int x, int y, boolean toggled) {
		super(buttonId, x, y, 13, 13, new Color(0, 0, 0, 100).getRGB(), Color.BLACK.getRGB(), false);
		
		this.toggled = toggled;
	}
	
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
        	super.drawButton(mc, mouseX, mouseY);
        	        	
        	if (toggled) {
            	mc.fontRendererObj.drawStringWithShadow("✔", this.xPosition - this.width / 2 + mc.fontRendererObj.getStringWidth("✔"), this.yPosition - this.height / 2 + mc.fontRendererObj.FONT_HEIGHT, Color.WHITE.getRGB());
        	}
        }
    }
}
