package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.GuiButtonToggled;
import drop.gui.GuiSlider;
import drop.mods.ModInstances;
import drop.mods.impl.togglesprintsneak.ToggleSprintSneak;

public class GuiToggleSprintSneak extends GuiModDraggableText {
	private final ToggleSprintSneak mod = ModInstances.getToggleSprintSneakMod();

	private GuiSlider sliderFlyBoostFactor;
	
	public GuiToggleSprintSneak(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, ModInstances.getToggleSprintSneakMod());
	}

	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
        
        this.drawText("Toggle Sprint", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 4 + 15, -1, false, false);
        this.drawText("Toggle Sneak", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 5 + 15, -1, false, false);
        this.drawText("Fly Boost", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 6 + 15, -1, false, false);
        this.drawText("Fly Boost Factor", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 7 + 15, -1, false, false);
        this.drawText(String.format("%.1f", mod.getFlyBoostFactor()), (this.width + 300) / 2 - mc.fontRendererObj.getStringWidth(String.format("%.1f", mod.getFlyBoostFactor())) - 15, (this.height - 200) / 2 + 30 + 15 * 7 + 15, -1, false, false);
        this.drawText("Show Text", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 8 + 15, -1, false, false);
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
        
    	this.buttonList.add(new GuiButtonToggled(5, mod.isToggleSprintEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 4 + 15 - 2));
    	this.buttonList.add(new GuiButtonToggled(6, mod.isToggleSneakEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 5 + 15 - 2));
    	this.buttonList.add(new GuiButtonToggled(7, mod.isFlyBoostEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 6 + 15 - 2));
    	this.buttonList.add(sliderFlyBoostFactor = new GuiSlider(8, (this.width - 300) / 2 + 130, (this.height - 200) / 2 + 30 + 15 * 7 + 15 + 1, 100, 5, 2.0F, 8.0F, mod.getFlyBoostFactor()));
    	this.buttonList.add(new GuiButtonToggled(9, mod.isShowTextEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 8 + 15 - 2));
    }
}
