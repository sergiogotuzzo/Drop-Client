package drop.gui.mod.pingdisplay;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.mod.GuiModOptions;
import drop.mod.ModHandler;
import drop.mod.impl.PingDisplay;

public class GuiPingDisplay extends GuiModOptions {	
	public GuiPingDisplay(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, ModHandler.get(PingDisplay.class));
	}

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {    	
    	if (button.id == ModHandler.get(PingDisplay.class).getColorOption("textColor").getGuiSettings().getButtonId()) {
    		mc.displayGuiScreen(new GuiPingDisplayTextColor(this));
    	} else {
        	super.actionPerformed(button);
    	}
    }
}
