package drop.gui.mod.taboverlay;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.mod.GuiMod;
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

        this.writeOptionText("Hide Ping", 1);
    	this.writeOptionText("Ping Text Color", 2);
        this.writeOptionText("Ping Text Shadow", 3);
        this.writeOptionText("Ping Numbers", 4);
        this.writeOptionText("Show Player Heads", 5);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
	        case 1:
	        	mod.setHidePing(!mod.isHidePingToggled());
	        	this.initGui();
	        	break;
        	case 2:
        		mc.displayGuiScreen(new GuiTabOverlayTextColor(this));
        		break;
        	case 3:
        		mod.setTextShadow(!mod.isTextShadowToggled());
        		break;
            case 4:
            	mod.setPingNumbers(!mod.isPingNumbersToggled());
            	this.initGui();
            	break;
            case 5:
            	mod.setShowPlayerHeads(!mod.isShowPlayerHeadsToggled());
            	this.initGui();
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();

    	this.buttonList.add(this.createGuiCheckBox(1, mod.isHidePingToggled(), 1));
		this.buttonList.add(this.createGuiRect(2, mod.getTextColor().getRGB(), 2));
		this.buttonList.add(this.createGuiCheckBox(3, mod.isTextShadowToggled(), 3));
    	this.buttonList.add(this.createGuiCheckBox(4, mod.isPingNumbersToggled(), 4));
    	this.buttonList.add(this.createGuiCheckBox(5, mod.isShowPlayerHeadsToggled(), 5));
    }
}
