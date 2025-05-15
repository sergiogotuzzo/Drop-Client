package rubik.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiButtonQuit extends GuiButton {
    private static final ResourceLocation quitIcon = new ResourceLocation("rubik/quit.png");

    public GuiButtonQuit(int buttonId, int x, int y) {
        super(buttonId, x, y, 20, 20, "");
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        super.drawButton(mc, mouseX, mouseY);
        
        if (this.visible) {
            mc.getTextureManager().bindTexture(quitIcon);
            
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            
            int iconX = this.xPosition + (this.width - 16) / 2;
            int iconY = this.yPosition + (this.height - 16) / 2;

            drawModalRectWithCustomSizedTexture(iconX, iconY, 0.0F, 0.0F, 16, 16, 16, 16);
        }
    }
}
