package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.GuiSlider;
import drop.mods.ModInstances;
import drop.mods.impl.Zoom;

public class GuiZoom extends GuiMod {
	private static final Zoom mod = ModInstances.getZoomMod();
	
	private GuiSlider sliderZoomLevel;
	private GuiSlider sliderZoomLevelMin;
	private GuiSlider sliderZoomLevelMax;

	public GuiZoom(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	super.drawScreen(mouseX, mouseY, partialTicks);
    	
        this.writeOptionText("Scroll To Zoom", 1);
        this.writeOptionText("Cinematic Camera", 2);
        this.writeOptionText("Zoom Level", 3);
		this.writeOptionValue(String.valueOf(mod.getZoomLevel()), 3);
		this.writeOptionText("Zoom Level Min", 5);
		this.writeOptionValue(String.valueOf(mod.getZoomLevelMin()), 5);
		this.writeOptionText("Zoom Level Max", 7);
		this.writeOptionValue(String.valueOf(mod.getZoomLevelMax()), 7);
    }
    
    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
    	mod.setZoomLevel((int) (sliderZoomLevel.getSliderPosition() * 64.0F));
    	
    	if (mod.getZoomLevel() < 4) {
    		mod.setZoomLevel(4);
    	}
    	
    	mod.setZoomLevelMin((int) (sliderZoomLevelMin.getSliderPosition() * 64.0F));
    	
    	if (mod.getZoomLevelMin() < 1) {
    		mod.setZoomLevelMin(1);
    	}
    	
    	mod.setZoomLevelMax((int) (sliderZoomLevelMax.getSliderPosition() * 64.0F));
    	
    	if (mod.getZoomLevelMax() < 4) {
    		mod.setZoomLevelMax(4);
    	}
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
            case 1:
            	mod.setScrollToZoom(!mod.isScrollToZoomToggled());
            	this.initGui();
            	break;
            case 2:
            	mod.setSmoothCamera(!mod.isSmoothCameraToggled());
            	this.initGui();
            	break;
            case 3:
            	mod.setZoomLevel((int) (sliderZoomLevel.getSliderPosition() * 64.0F));
            	
            	if (mod.getZoomLevel() < 4) {
            		mod.setZoomLevel(4);
            	}
            	break;
            case 4:
            	mod.setZoomLevelMin((int) (sliderZoomLevelMin.getSliderPosition() * 64.0F));
            	
            	if (mod.getZoomLevelMin() < 4) {
            		mod.setZoomLevelMin(4);
            	}
            	break;
            case 5:
            	mod.setZoomLevelMax((int) (sliderZoomLevelMax.getSliderPosition() * 64.0F));
            	
            	if (mod.getZoomLevelMax() < 4) {
            		mod.setZoomLevelMax(4);
            	}
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
        
    	this.buttonList.add(this.createGuiCheckBox(1, mod.isScrollToZoomToggled(), 1));
    	this.buttonList.add(this.createGuiCheckBox(2, mod.isSmoothCameraToggled(), 2));
		this.buttonList.add(sliderZoomLevel = this.createGuiSlider(3, 64.0F, mod.getZoomLevel(), 4));
		this.buttonList.add(sliderZoomLevelMin = this.createGuiSlider(4, 64.0F, mod.getZoomLevelMin(), 6));
		this.buttonList.add(sliderZoomLevelMax = this.createGuiSlider(5, 64.0F, mod.getZoomLevelMax(), 8));
    }
}
