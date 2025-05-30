package drop.gui.mod;

import java.awt.Color;
import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import drop.events.EventTarget;
import drop.gui.GuiButtonToggled;
import drop.gui.GuiDropClientScreen;
import drop.gui.GuiSlider;
import drop.mods.ModInstances;
import drop.mods.impl.VisualTweaks;

public class GuiVisualTweaks extends GuiDropClientScreen {
	private final GuiScreen previousGuiScreen;
	private final VisualTweaks mod = ModInstances.getVisualTweaksMod();
	
	private GuiSlider sliderHurtShakeIntensity;
	
	public GuiVisualTweaks(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	this.drawDefaultBackground();
    	
    	drawRect((this.width - 300) / 2, (this.height - 200) / 2, (this.width - 300) / 2 + 300, (this.height - 200) / 2 + 200, new Color(0, 0, 0, 127).getRGB());
        
        this.drawScaledText("Visual Tweaks", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 15, 2.0D, 0xFFFFFFFF, false, false);
        this.drawText("Minimal View Bobbing", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15, -1, false, false);
        this.drawText("Left Hand", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 1 + 15, -1, false, false);
        this.drawText("Hurt Shake", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 2 + 15, -1, false, false);
        this.drawText("Hurt Shake Intensity", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 3 + 15, -1, false, false);
        this.drawText(String.format("%.1f", mod.getHurtShakeIntensity()), (this.width + 300) / 2 - mc.fontRendererObj.getStringWidth(String.format("%.1f", mod.getHurtShakeIntensity())) - 15, (this.height - 200) / 2 + 30 + 15 * 3 + 15, -1, false, false);
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
    	mod.setHurtShakeIntensity(sliderHurtShakeIntensity.getSliderPosition() * 35.0F);
    	
    	if (mod.getHurtShakeIntensity() < 5.0F) {
    		mod.setHurtShakeIntensity(5.0F);
    	}
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
            	this.mc.displayGuiScreen(this.previousGuiScreen);
            	break;
            case 1:
            	mod.setMinimalViewBobbing(!mod.isMinimalViewBobbingToggled());
            	this.initGui();
            	break;
            case 2:
            	mod.setLeftHand(!mod.isLeftHandToggled());
            	this.initGui();
            	break;
            case 3:
            	mod.setHurtShake(!mod.isHurtShakeToggled());
            	this.initGui();
            	break;
            case 4:
            	mod.setHurtShakeIntensity(sliderHurtShakeIntensity.getSliderPosition() * 35.0F);
            	
            	if (mod.getHurtShakeIntensity() < 5.0F) {
            		mod.setHurtShakeIntensity(5.0F);
            	}
            	break;
        }
    }
	
	@Override
    public void initGui() {
        this.buttonList.clear();
        
    	this.buttonList.add(new GuiButtonToggled(1, mod.isMinimalViewBobbingToggled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15 - 2));
    	this.buttonList.add(new GuiButtonToggled(2, mod.isLeftHandToggled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 1 + 15 - 2));
    	this.buttonList.add(new GuiButtonToggled(3, mod.isHurtShakeToggled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 2 + 15 - 2));
    	this.buttonList.add(sliderHurtShakeIntensity = new GuiSlider(4, (this.width - 300) / 2 + 140, (this.height - 200) / 2 + 30 + 15 * 3 + 15 + 1, 100, 5, 5, 35, mod.getHurtShakeIntensity()));
        this.buttonList.add(new GuiButton(0, (this.width + 300) / 2 - 50 - 15, (this.height - 200) / 2 + 15, 50, 20, I18n.format("gui.done", new Object[0])));
    }
}
