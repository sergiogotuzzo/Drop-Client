package drop.gui.mod.pingdisplay;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.mod.GuiModColor;
import drop.mods.ModInstances;
import drop.mods.impl.PingDisplay;

public class GuiPingDisplayTextColor extends GuiModColor {
	private static final PingDisplay mod = ModInstances.getPingDisplayMod();
	
	public GuiPingDisplayTextColor(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod, mod.getTextColor());
	}
	
	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        this.writeOptionText("Dynamic Colors", 6);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        if (button.id == 6) {
        	mod.setDynamicColors(!mod.isDynamicColorsEnabled());
        	this.initGui();
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
		
    	this.buttonList.add(this.createGuiButtonToggled(6, mod.isDynamicColorsEnabled(), 6));
    }
}
