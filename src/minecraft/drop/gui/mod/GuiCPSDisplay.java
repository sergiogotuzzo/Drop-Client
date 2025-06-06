package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.GuiButtonToggled;
import drop.mods.ModInstances;
import drop.mods.impl.CPSDisplay;

public class GuiCPSDisplay extends GuiModDraggableDisplayText {
	private final CPSDisplay mod = ModInstances.getCPSDisplayMod();
	
	public GuiCPSDisplay(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, ModInstances.getCPSDisplayMod());
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        
        this.writeOptionText("Show Right CPS", 5);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        if (button.id == 5) {
        	mod.setShowRightCPS(!mod.isShowRightCPSToggled());
        	this.initGui();
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
        
    	this.buttonList.add(this.createGuiButtonToggled(5, mod.isShowRightCPSToggled(), 5));
    }
}
