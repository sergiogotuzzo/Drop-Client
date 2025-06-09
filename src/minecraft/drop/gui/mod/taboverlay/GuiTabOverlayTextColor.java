package drop.gui.mod.taboverlay;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.mod.GuiModColor;
import drop.mods.ModInstances;
import drop.mods.impl.TabOverlay;

public class GuiTabOverlayTextColor extends GuiModColor {
	private static final TabOverlay mod = ModInstances.getTabOverlayMod();
	
	public GuiTabOverlayTextColor(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod, mod.getTextColor());
	}

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
    	switch (button.id) {
	    	case 6:
	    		mc.displayGuiScreen(new GuiTabOverlayDynamicColors(this));
	    		break;
	    	case 7:
	    		mod.setDynamicColors(!mod.isDynamicColorsToggled());
	        	this.initGui();
	        	break;
		}
    }
	
	@Override
    public void initGui() {
		super.initGui();
		
		this.buttonList.add(this.createGuiText(6, (this.width - 300) / 2 + 15, "Dynamic Colors", 9));
    	this.buttonList.add(this.createGuiButtonToggled(7, mod.isDynamicColorsToggled(), 9));
    }
}
