package rubik.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiButtonOptions extends GuiButton {
    private static final ResourceLocation optionsIcon = new ResourceLocation("rubik/options.png");

    public GuiButtonOptions(int buttonId, int x, int y) {
        super(buttonId, x, y, 20, 20, "");
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        super.drawButton(mc, mouseX, mouseY);
        
        if (this.visible) {
            mc.getTextureManager().bindTexture(optionsIcon);
            
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            
            int iconX = this.xPosition + (this.width - 14) / 2;
            int iconY = this.yPosition + (this.height - 14) / 2;

            drawModalRectWithCustomSizedTexture(iconX, iconY, 0.0F, 0.0F, 14, 14, 14, 14);
        }
    }
}
