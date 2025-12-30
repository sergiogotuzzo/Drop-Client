package drop.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiButtonIcon extends GuiButton {
    private final ResourceLocation icon;
    private final int iconWidth;
    private final int iconHeight;
    private final boolean draw;

    public GuiButtonIcon(int buttonId, ResourceLocation iconResourceLocation, int x, int y, int iconWidth, int iconHeight, int width, int height, boolean draw) {
        super(buttonId, x, y, width, height, "");
        
        this.icon = iconResourceLocation;
        this.iconWidth = iconWidth;
        this.iconHeight = iconHeight;
        this.draw = draw;
    }

    public GuiButtonIcon(int buttonId, ResourceLocation iconResourceLocation, int x, int y, int iconWidth, int iconHeight) {
        this(buttonId, iconResourceLocation, x, y, iconWidth, iconHeight, 20, 20, true);
    }
    
    public GuiButtonIcon(int buttonId, ResourceLocation iconResourceLocation, int x, int y) {
        this(buttonId, iconResourceLocation, x, y, 16, 16, 20, 20, true);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
        	if (this.draw) {
        		super.drawButton(mc, mouseX, mouseY);
        	}
        	
            mc.getTextureManager().bindTexture(icon);
            
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            
            int iconX = this.xPosition + (this.width - iconWidth) / 2;
            int iconY = this.yPosition + (this.height - iconHeight) / 2;

            drawModalRectWithCustomSizedTexture(iconX, iconY, 0.0F, 0.0F, iconWidth, iconHeight, iconWidth, iconHeight);
        }
    }
}
