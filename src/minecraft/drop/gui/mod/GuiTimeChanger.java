package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.GuiSlider;
import drop.mods.ModInstances;
import drop.mods.impl.TimeChanger;

public class GuiTimeChanger extends GuiMod {
	private static final TimeChanger mod = ModInstances.getTimeChangerMod();

	private GuiSlider sliderTime;
	
	public GuiTimeChanger(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
	}

	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		
        this.writeOptionText("Time", 1);
        this.writeOptionValue(String.format("%.2f", mod.getTime()), 1);
        this.writeOptionText("Use Real Current Time", 3);
    }
    
    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
    	mod.setTime(sliderTime.getSliderPosition());
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
            case 1:
            	mod.setTime(sliderTime.getSliderPosition());
            	break;
            case 2:
            	mod.setUseRealCurrentTime(!mod.isUseRealCurrentTimeToggled());
            	this.initGui();
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
        
    	this.buttonList.add(sliderTime = this.createGuiSlider(1, 0.0F, 1.0F, mod.getTime(), 2));
    	this.buttonList.add(this.createGuiButtonToggled(2, mod.isUseRealCurrentTimeToggled(), 3));
    }
}
