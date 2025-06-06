package drop.gui.mod.pingdisplay;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.mod.GuiModColor;
import drop.mods.ModInstances;
import drop.mods.impl.PingDisplay;

public class GuiPingDisplayTextColor extends GuiModColor {
	private static final PingDisplay mod = ModInstances.getPingDisplayMod();
	
	public GuiPingDisplayTextColor(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod, mod.getTextColor());
	}

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
    	switch (button.id) {
	    	case 6:
	    		mc.displayGuiScreen(new GuiPingDisplayDynamicColors(this));
	    		break;
	    	case 7:
	    		mod.setDynamicColors(!mod.isDynamicColorsToggled());
	        	this.initGui();
	        	break;
		}
    }
	
	@Override
    public void initGui() {
		super.initGui();
		
		this.buttonList.add(this.createGuiText(6, (this.width - 300) / 2 + 15, "Dynamic Colors", 6));
    	this.buttonList.add(this.createGuiButtonToggled(7, mod.isDynamicColorsToggled(), 6));
    }
}
