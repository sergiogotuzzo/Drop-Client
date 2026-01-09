package drop.gui.mod.armorstatus;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.mod.GuiModColor;
import drop.mod.ModHandler;
import drop.mod.impl.ArmorStatus;
import drop.mod.option.type.BooleanOption;
import drop.mod.option.type.ColorOption;

public class GuiArmorStatusTextColor extends GuiModColor {
	private static final ArmorStatus mod = ModHandler.get(ArmorStatus.class);
	
	public GuiArmorStatusTextColor(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod, (ColorOption) mod.getOptions().getColorOption("textColor"));
	}

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
    	switch (button.id) {
	    	case 6:
	    		mod.getOptions().getBooleanOption("dynamicColors").toggle();
	        	this.initGui();
	        	break;
	    	case 7:
	    		mc.displayGuiScreen(new GuiArmorStatusDynamicColors(this));
	    		break;
		}
    }
	
	@Override
    public void initGui() {
		super.initGui();
		
		BooleanOption dynamicColors = mod.getOptions().getBooleanOption("dynamicColors");
		
		this.buttonList.add(this.createGuiText(7, dynamicColors.getGuiSettings().getOptionName(), false, 9));
    	this.buttonList.add(this.createGuiCheckBox(6, dynamicColors.isToggled(), 9));
    }
}
