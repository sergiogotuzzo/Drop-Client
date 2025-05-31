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
    	
        this.drawText("Show Right CPS", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 4 + 15, -1, false, false);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        if (button.id == 5) {
        	mod.setShowRightCPS(!mod.isShowRightCPSEnabled());
        	this.initGui();
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
        
    	this.buttonList.add(new GuiButtonToggled(5, mod.isShowRightCPSEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 4 + 15 - 2));
    }
}
