package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.mods.ModInstances;
import drop.mods.impl.TabOverlay;

public class GuiTabOverlay extends GuiMod {
	private static final TabOverlay mod = ModInstances.getTabOverlayMod();
	
	public GuiTabOverlay(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	super.drawScreen(mouseX, mouseY, partialTicks);
    	
        this.writeOptionText("Show Player Heads", 1);
        this.writeOptionText("Hide Ping", 2);
        this.writeOptionText("Ping Numbers", 3);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
            case 1:
            	mod.setShowPlayerHeads(!mod.isShowPlayerHeadsToggled());
            	this.initGui();
            	break;
            case 2:
            	mod.setHidePing(!mod.isHidePingToggled());
            	this.initGui();
            	break;
            case 3:
            	mod.setPingNumbers(!mod.isPingNumbersToggled());
            	this.initGui();
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
        
    	this.buttonList.add(this.createGuiButtonToggled(1, mod.isShowPlayerHeadsToggled(), 1));
    	this.buttonList.add(this.createGuiButtonToggled(2, mod.isHidePingToggled(), 2));
    	this.buttonList.add(this.createGuiButtonToggled(3, mod.isPingNumbersToggled(), 3));
    }
}
