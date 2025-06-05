package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.GuiButtonToggled;
import drop.gui.GuiSlider;
import drop.mods.ModInstances;
import drop.mods.impl.togglesprintsneak.ToggleSprintSneak;

public class GuiToggleSprintSneak extends GuiModDraggableDisplayText {
	private static final ToggleSprintSneak mod = ModInstances.getToggleSprintSneakMod();

	private GuiSlider sliderFlyBoostFactor;
	
	public GuiToggleSprintSneak(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
	}

	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
        
        this.writeOptionText("Toggle Sprint", 5);
        this.writeOptionText("Toggle Sneak", 6);
        this.writeOptionText("Fly Boost", 7);
        this.writeOptionText("Fly Boost Factor", 8);
        this.writeOptionValue(String.format("%.1f", mod.getFlyBoostFactor()), 8);
        this.writeOptionText("Show Text", 9);
    }
    
    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
    	mod.setFlyBoostFactor(sliderFlyBoostFactor.getSliderPosition() * 8.0F);
    	
    	if (mod.getFlyBoostFactor() < 2.0F) {
    		mod.setFlyBoostFactor(2.0F);
    	}
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
            case 5:
            	mod.setToggleSprint(!mod.isToggleSprintEnabled());
            	this.initGui();
            	break;
            case 6:
            	mod.setToggleSneak(!mod.isToggleSneakEnabled());
            	this.initGui();
            	break;
            case 7:
            	mod.setFlyBoost(!mod.isFlyBoostEnabled());
            	this.initGui();
            	break;
            case 8:
            	mod.setFlyBoostFactor(sliderFlyBoostFactor.getSliderPosition() * 8.0F);
            	
            	if (mod.getFlyBoostFactor() < 2.0F) {
            		mod.setFlyBoostFactor(2.0F);
            	}
            	break;
            case 9:
            	mod.setShowText(!mod.isShowTextEnabled());
            	this.initGui();
            	break;
        }
    }
	
	@Override
    public void initGui() {
        super.initGui();
        
    	this.buttonList.add(this.createGuiButtonToggled(5, mod.isToggleSprintEnabled(), 5));
    	this.buttonList.add(this.createGuiButtonToggled(6, mod.isToggleSneakEnabled(), 6));
    	this.buttonList.add(this.createGuiButtonToggled(7, mod.isFlyBoostEnabled(), 7));
    	this.buttonList.add(sliderFlyBoostFactor = this.createGuiSlider(8, 2.0F, 8.0F, mod.getFlyBoostFactor(), 30, 8));
    	this.buttonList.add(this.createGuiButtonToggled(9, mod.isShowTextEnabled(), 9));
    }
}
