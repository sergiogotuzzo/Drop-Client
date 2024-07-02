package rubik.gui;

import java.text.DecimalFormat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;

public class GuiSlider extends GuiButton
{
    private float sliderPosition = 1.0F;
    public boolean isMouseDown;
    private String name;
    private final float min;
    private final float max;

    public GuiSlider(int idIn, int x, int y, int width, int height, String name, float min, float max, float defaultValue)
    {
        super(idIn, x, y, width, height, name);
        
        this.name = name;
        this.min = min;
        this.max = max;
        this.sliderPosition = (defaultValue - min) / (max - min);
        this.displayString = this.getDisplayString();
    }
    
    @Override
    protected int getHoverState(boolean mouseOver)
    {
        return 0;
    }
    
    @Override
    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY)
    {
        if (this.visible) {
            if (this.isMouseDown) {
                this.sliderPosition = (float)(mouseX - (this.xPosition + 4)) / (float)(this.width - 8);

                if (this.sliderPosition < 0.0F) {
                    this.sliderPosition = 0.0F;
                }

                if (this.sliderPosition > 1.0F) {
                    this.sliderPosition = 1.0F;
                }

                this.displayString = this.getDisplayString();
            }

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderPosition * (float)(this.width - 8)), this.yPosition, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderPosition * (float)(this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
        }
    }
    
    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        this.isMouseDown = false;
    }
    
    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        if (super.mousePressed(mc, mouseX, mouseY)) {
            this.sliderPosition = (float)(mouseX - (this.xPosition + 4)) / (float)(this.width - 8);

            if (this.sliderPosition < 0.0F) {
                this.sliderPosition = 0.0F;
            }

            if (this.sliderPosition > 1.0F) {
                this.sliderPosition = 1.0F;
            }

            this.displayString = this.getDisplayString();
            this.isMouseDown = true;
            
            return true;
        } else {
            return false;
        }
    }

    public float func_175220_c() {
        return this.min + (this.max - this.min) * this.sliderPosition;
    }

    public void func_175218_a(float p_175218_1_) {
        this.sliderPosition = (p_175218_1_ - this.min) / (this.max - this.min);
        this.displayString = this.getDisplayString();
    }

    public float func_175217_d() {
        return this.sliderPosition;
    }

    private String getDisplayString() {
        int value = Math.round(func_175220_c());

        return I18n.format(this.name, new Object[0]) + ": " + value;
    }

    public void func_175219_a(float p_175219_1_) {
        this.sliderPosition = p_175219_1_;
        this.displayString = this.getDisplayString();
    }
}
