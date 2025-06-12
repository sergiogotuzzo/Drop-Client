package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.GuiSlider;
import drop.mods.ModInstances;
import drop.mods.impl.Nametags;

public class GuiNametags extends GuiMod {
	private static final Nametags mod = ModInstances.getNametagsMod();
	
	private GuiSlider sliderBackgroundOpacity;
	
	public GuiNametags(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	super.drawScreen(mouseX, mouseY, partialTicks);

        this.writeOptionText("Text Shadow", 1);
        this.writeOptionText("Background Opacity", 2);
        this.writeOptionValue(String.valueOf(mod.getBackgroundOpacity()), 2);
        this.writeOptionText("Show In Third Person", 4);
    }
    
    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
    	mod.setBackgroundOpacity((int) (sliderBackgroundOpacity.getSliderPosition() * 255.0F));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
	        case 1:
	        	mod.setTextShadow(!mod.isTextShadowToggled());
	        	this.initGui();
	        	break;
	        case 2:
	        	mod.setBackgroundOpacity((int) (sliderBackgroundOpacity.getSliderPosition() * 255.0F));
	        	break;
            case 3:
            	mod.setShowInThirdPerson(!mod.isShowInThirdPersonToggled());
            	this.initGui();
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();

    	this.buttonList.add(this.createGuiCheckBox(1, mod.isTextShadowToggled(), 1));
    	this.buttonList.add(sliderBackgroundOpacity = this.createGuiSlider(2, 255.0F, mod.getBackgroundOpacity(), 3));
    	this.buttonList.add(this.createGuiCheckBox(3, mod.isShowInThirdPersonToggled(), 4));
    }
}
