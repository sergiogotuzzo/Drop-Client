package rubik.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiButtonIcon extends GuiButton {
    private final ResourceLocation icon;
    private final int iconWidth;
    private final int iconHenght;
    
    public GuiButtonIcon(int buttonId, ResourceLocation iconResourceLocation, int x, int y) {
        this(buttonId, iconResourceLocation, x, y, 16, 16);
    }

    public GuiButtonIcon(int buttonId, ResourceLocation iconResourceLocation, int x, int y, int width, int henght) {
        super(buttonId, x, y, 20, 20, "");
        
        this.icon = iconResourceLocation;
        this.iconWidth = width;
        this.iconHenght = henght;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        super.drawButton(mc, mouseX, mouseY);
        
        if (this.visible) {
            mc.getTextureManager().bindTexture(icon);
            
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            
            int iconX = this.xPosition + (this.width - iconWidth) / 2;
            int iconY = this.yPosition + (this.height - iconHenght) / 2;

            drawModalRectWithCustomSizedTexture(iconX, iconY, 0.0F, 0.0F, iconWidth, iconHenght, iconWidth, iconHenght);
        }
    }
}
