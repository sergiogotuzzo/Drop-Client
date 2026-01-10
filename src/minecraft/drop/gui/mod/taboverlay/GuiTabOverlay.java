package drop.gui.mod.taboverlay;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.mod.GuiModOptions;
import drop.mod.ModHandler;
import drop.mod.impl.TabOverlay;

public class GuiTabOverlay extends GuiModOptions {	
	public GuiTabOverlay(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, ModHandler.get(TabOverlay.class));
	}

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	if (button.id == ModHandler.get(TabOverlay.class).getColorOption("textColor").getGuiSettings().getButtonId()) {
    		mc.displayGuiScreen(new GuiTabOverlayTextColor(this));
    	} else {
        	super.actionPerformed(button);
    	}
    }
}
