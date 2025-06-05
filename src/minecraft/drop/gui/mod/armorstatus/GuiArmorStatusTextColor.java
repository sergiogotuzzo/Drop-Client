package drop.gui.mod.armorstatus;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.mod.GuiModColor;
import drop.mods.ModInstances;
import drop.mods.impl.ArmorStatus;

public class GuiArmorStatusTextColor extends GuiModColor {
	private static final ArmorStatus mod = ModInstances.getArmorStatusMod();
	
	public GuiArmorStatusTextColor(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod, mod.getTextColor());
	}

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
    	switch (button.id) {
	    	case 6:
	    		mc.displayGuiScreen(new GuiArmorStatusDynamicColors(this));
	    		break;
	    	case 7:
	    		mod.setDynamicColors(!mod.isDynamicColorsEnabled());
	        	this.initGui();
	        	break;
    	}
    }
	
	@Override
    public void initGui() {
		super.initGui();
		
		this.buttonList.add(this.createGuiText(6, (this.width - 300) / 2 + 15, "Dynamic Colors", 6));
    	this.buttonList.add(this.createGuiButtonToggled(7, mod.isDynamicColorsEnabled(), 6));
    }
}
