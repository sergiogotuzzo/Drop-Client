package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.GuiSlider;
import drop.mods.ModInstances;
import drop.mods.impl.Particles;

public class GuiParticles extends GuiMod {
	private static final Particles mod = ModInstances.getParticlesMod();

	private GuiSlider sliderMultiplierFactor;
	
	public GuiParticles(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
	}

	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		this.writeOptionText("Show Criticals", 1);
		this.writeOptionText("Always Sharpness", 2);
		this.writeOptionText("Multiplier Factor", 3);
		this.writeOptionValue(String.valueOf(mod.getMultiplierFactor()), 3);
    }
    
    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
    	mod.setMultiplierFactor((int) (sliderMultiplierFactor.getSliderPosition() * 10.0F));
    	
    	if (mod.getMultiplierFactor() < 1) {
    		mod.setMultiplierFactor(1);
    	}
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
            case 1:
            	mod.setShowCriticals(!mod.isShowCriticalsToggled());
            	this.initGui();
            	break;
            case 2:
            	mod.setAlwaysSharpness(!mod.isAlwaysSharpnessToggled());
            	this.initGui();
            	break;
            case 3:
            	mod.setMultiplierFactor((int) (sliderMultiplierFactor.getSliderPosition() * 10.0F));
            	
            	if (mod.getMultiplierFactor() < 1) {
            		mod.setMultiplierFactor(1);
            	}
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
		
    	this.buttonList.add(this.createGuiCheckBox(1, mod.isShowCriticalsToggled(), 1));
        this.buttonList.add(this.createGuiCheckBox(2, mod.isAlwaysSharpnessToggled(), 2));
    	this.buttonList.add(sliderMultiplierFactor = this.createGuiSlider(3, 10.0F, mod.getMultiplierFactor(), 4));
    }
}
