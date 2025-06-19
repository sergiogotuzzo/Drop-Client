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
import drop.mods.impl.HurtCam;

public class GuiHurtCam extends GuiMod {
	private static final HurtCam mod = ModInstances.getHurtCamMod();
	
	private GuiSlider sliderHurtShakeIntensity;
	
	public GuiHurtCam(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	super.drawScreen(mouseX, mouseY, partialTicks);
    	
    	this.writeOptionText("Hurt Shake", 1);
    	
    	if (mod.isHurtShakeToggled()) {
    		this.writeOptionText("Hurt Shake Intensity", 2);
        	this.writeOptionValue(String.format("%.1f", mod.getHurtShakeIntensity()), 2);
    	}
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
    	super.actionPerformed(button);
    	
        switch (button.id) {
            case 1:
            	mod.setHurtShake(!mod.isHurtShakeToggled());
            	this.initGui();
            	break;
            case 2:
            	mod.setHurtShakeIntensity(sliderHurtShakeIntensity.getSliderPosition() * 35.0F);
            	
            	if (mod.getHurtShakeIntensity() < 5.0F) {
            		mod.setHurtShakeIntensity(5.0F);
            	}
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
		
		this.buttonList.add(this.createGuiCheckBox(1, mod.isHurtShakeToggled(), 1));
		
		if (mod.isHurtShakeToggled()) {
			this.buttonList.add(sliderHurtShakeIntensity = this.createGuiSlider(2, 35.0F, mod.getHurtShakeIntensity(), 3));
		}
    }
}