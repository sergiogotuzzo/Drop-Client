package rubik.gui;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class GuiSlider extends GuiButton {
    public boolean isMouseDown;
	private final float min;
	private final float max;
	private float sliderPosition;
	
	public GuiSlider(int buttonId, int x, int y, int widthIn, int heightIn, float min, float max, float defaultValue) {
		super(buttonId, x, y, widthIn, heightIn, "");
		
		this.min = min;
		this.max = max;
		this.sliderPosition = (defaultValue - min) / (max - min);
	}

	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;

            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);

            this.mouseDragged(mc, mouseX, mouseY);
            
            drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, new Color(0, 0, 0, this.hovered ? 50 : 100).getRGB());
        	drawRect(this.xPosition + (int)(this.sliderPosition * (float)(this.width - 5)), this.yPosition, this.xPosition + (int)(this.sliderPosition * (float)(this.width - 5)) + 5, this.yPosition + this.height, new Color(255, 255, 255, this.hovered ? 110 : 100).getRGB());
        }
    }
	
	@Override
    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY)
    {
        if (this.visible) {
            if (this.isMouseDown) {
            	this.sliderPosition = (float)(mouseX - (this.xPosition)) / (float)(this.width - 5);
                
                if (this.sliderPosition < 0.0F) {
                    this.sliderPosition = 0.0F;
                }

                if (this.sliderPosition > 1.0F) {
                    this.sliderPosition = 1.0F;
                }
            }

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
    
    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        this.isMouseDown = false;
    }
    
    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        if (super.mousePressed(mc, mouseX, mouseY)) {
            this.sliderPosition = (float)(mouseX - (this.xPosition)) / (float)(this.width - 5);

            if (this.sliderPosition < 0.0F) {
                this.sliderPosition = 0.0F;
            }

            if (this.sliderPosition > 1.0F) {
                this.sliderPosition = 1.0F;
            }

            this.isMouseDown = true;
            
            return true;
        } else {
            return false;
        }
    }
    
    public void setSliderPosition(float position) {
    	this.sliderPosition = position;
    }
	
	public float getSliderPosition() {
		return sliderPosition;
	}
}