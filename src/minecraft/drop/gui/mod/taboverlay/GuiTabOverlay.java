package drop.gui.mod.taboverlay;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.mod.GuiModSettings;
import drop.mods.ModInstances;
import drop.mods.impl.TabOverlay;

public class GuiTabOverlay extends GuiModSettings {	
	public GuiTabOverlay(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, ModInstances.getTabOverlayMod());
	}

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	if (button.id == ModInstances.getTabOverlayMod().getOptions().getColorOption("textColor").getGuiSettings().getButtonId()) {
    		mc.displayGuiScreen(new GuiTabOverlayTextColor(this));
    	} else {
        	super.actionPerformed(button);
    	}
    }
}
