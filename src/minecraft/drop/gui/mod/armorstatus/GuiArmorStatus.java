package drop.gui.mod.armorstatus;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.mod.GuiModOptions;
import drop.mod.ModHandler;
import drop.mod.impl.ArmorStatus;

public class GuiArmorStatus extends GuiModOptions {	
	public GuiArmorStatus(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, ModHandler.get(ArmorStatus.class));
	}

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {    	
    	if (button.id == ModHandler.get(ArmorStatus.class).getColorOption("textColor").getGuiSettings().getButtonId()) {
    		mc.displayGuiScreen(new GuiArmorStatusTextColor(this));
    	} else {
        	super.actionPerformed(button);
    	}
    }
}
