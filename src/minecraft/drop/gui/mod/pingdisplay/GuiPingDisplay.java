package drop.gui.mod.pingdisplay;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.mod.GuiModSettings;
import drop.mods.ModInstances;
import drop.mods.impl.PingDisplay;

public class GuiPingDisplay extends GuiModSettings {	
	public GuiPingDisplay(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, ModInstances.getPingDisplayMod());
	}

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {    	
    	if (button.id == ModInstances.getPingDisplayMod().getOptions().getColorOption("textColor").getGuiSettings().getButtonId()) {
    		mc.displayGuiScreen(new GuiPingDisplayTextColor(this));
    	} else {
        	super.actionPerformed(button);
    	}
    }
}
