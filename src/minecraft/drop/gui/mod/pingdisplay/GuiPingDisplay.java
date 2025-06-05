package drop.gui.mod.pingdisplay;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.mod.GuiModDraggableDisplayText;
import drop.mods.ModInstances;
import drop.mods.impl.PingDisplay;

public class GuiPingDisplay extends GuiModDraggableDisplayText {
	private static final PingDisplay mod = ModInstances.getPingDisplayMod();
	
	public GuiPingDisplay(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
	}

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {    	
        switch (button.id) {
        	case 0:
            case 1:
            case 2:
            case 4:
            	super.actionPerformed(button);
            	break;
            case 3:
            	mc.displayGuiScreen(new GuiPingDisplayTextColor(this));
            	break;
        }
    }
}
