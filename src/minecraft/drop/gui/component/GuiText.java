package drop.gui.component;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiText extends GuiButton {
	public final boolean hovering;
	
	public GuiText(int buttonId, int x, int y, String text) {
		this(buttonId, x, y, text, true);
	}
	
	public GuiText(int buttonId, int x, int y, String text, boolean hovering) {
		super(buttonId, x, y, 0, 0, text);
		
		this.width = Minecraft.getMinecraft().fontRendererObj.getStringWidth(text);
		this.height = Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT;
		this.hovering = hovering;
	}

	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int i = this.getHoverState(this.hovered);

            this.mouseDragged(mc, mouseX, mouseY);
                               
            int textColor = -1;
            
        	if (this.hovering && this.hovered) {
        		textColor = 16777120;
        	}
        	
        	mc.fontRendererObj.drawStringWithShadow(displayString, this.xPosition, this.yPosition, textColor);
        }
    }
	
	public void playPressSound(SoundHandler soundHandlerIn) {
        if (this.hovering) {
        	soundHandlerIn.playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
        }
    }
}