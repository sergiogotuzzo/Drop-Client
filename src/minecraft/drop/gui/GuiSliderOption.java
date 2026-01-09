package drop.gui;

import java.awt.Color;

import drop.mod.Mod;
import drop.mod.option.ModOption;
import drop.mod.option.type.FloatOption;
import drop.mod.option.type.IntOption;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class GuiSliderOption extends GuiButton {
    public boolean isMouseDown;
	private final float min;
	private final float max;
	private float sliderPosition;
	private final ModOption option;
	
	public GuiSliderOption(int buttonId, int x, int y, int widthIn, int heightIn, float min, float max, float defaultValue, ModOption option) {
		super(buttonId, x, y, widthIn, heightIn, "");
		
		this.min = min;
		this.max = max;
		this.sliderPosition = (defaultValue - min) / (max - min);
		this.option = option;
	}

	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;

            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);

            this.mouseDragged(mc, mouseX, mouseY);
            
            drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, new Color(0, 0, 0, 100).getRGB());
        	drawRect(this.xPosition + (int)(this.sliderPosition * (float)(this.width - 5)), this.yPosition, this.xPosition + (int)(this.sliderPosition * (float)(this.width - 5)) + 5, this.yPosition + this.height, new Color(255, 255, 255, this.hovered ? 150 : 100).getRGB());
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
            
            setValue();

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
    
    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        this.isMouseDown = false;
        
        if (option.getValue() instanceof Float) {
        	this.option.saveValue(option.getValue());
        } else if (option.getValue() instanceof Integer) {
        	this.option.saveValue(option.getValue());
        }
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
            
            setValue();
            
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
	
	private void setValue() {
		if (option.getValue() instanceof Float) {
        	this.option.setValue(this.getSliderPosition() * max);
    		
    		if ((float) option.getValue() < ((FloatOption) option).getMin()) {
            	option.setValue(((FloatOption) option).getMin());
            }
        } else if (option.getValue() instanceof Integer) {
        	this.option.setValue((int) (this.getSliderPosition() * max));
    		
    		if ((int) ((IntOption) option).getValue() < ((IntOption) option).getMin()) {
            	option.setValue(((IntOption) option).getMin());
            }
        }
	}
}