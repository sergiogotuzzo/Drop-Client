package drop.gui.mod.pingdisplay;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.mod.GuiModColor;
import drop.mod.ModHandler;
import drop.mod.impl.PingDisplay;
import drop.mod.option.type.BooleanOption;
import drop.mod.option.type.ColorOption;

public class GuiPingDisplayTextColor extends GuiModColor {
	private static final PingDisplay mod = ModHandler.get(PingDisplay.class);
	
	public GuiPingDisplayTextColor(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod, (ColorOption) mod.getColorOption("textColor"));
	}

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
    	switch (button.id) {
	    	case 6:
	    		mod.getBooleanOption("dynamicColors").toggle();
	        	this.initGui();
	        	break;
	    	case 7:
	    		mc.displayGuiScreen(new GuiPingDisplayDynamicColors(this));
	    		break;
		}
    }
	
	@Override
    public void initGui() {
		super.initGui();
		
		BooleanOption dynamicColors = mod.getBooleanOption("dynamicColors");
		
		this.buttonList.add(this.createGuiText(7, dynamicColors.getGuiSettings().getOptionName(), false, 9));
    	this.buttonList.add(this.createGuiCheckBox(6, dynamicColors.isToggled(), 9));
    }
}
