package drop.gui.mod.armorstatus;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.mod.GuiModSettings;
import drop.mods.ModInstances;
import drop.mods.impl.ArmorStatus;

public class GuiArmorStatus extends GuiModSettings {	
	public GuiArmorStatus(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, ModInstances.getArmorStatusMod());
	}

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {    	
    	if (button.id == ModInstances.getArmorStatusMod().getOptions().getColorOption("textColor").getGuiSettings().getButtonId()) {
    		mc.displayGuiScreen(new GuiArmorStatusTextColor(this));
    	} else {
        	super.actionPerformed(button);
    	}
    }
}
