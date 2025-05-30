package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.GuiButtonToggled;
import drop.mods.ModInstances;
import drop.mods.impl.PingDisplay;

public class GuiPingDisplay extends GuiModDraggableText {
	private final PingDisplay mod = ModInstances.getPingDisplayMod();
	
	public GuiPingDisplay(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, ModInstances.getPingDisplayMod());
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    	
        this.drawText("Dynamic Colors", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 4 + 15, -1, false, false);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        if (button.id == 5) {
        	mod.setDynamicColors(!mod.isDynamicColorsEnabled());
        	this.initGui();
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
        
    	this.buttonList.add(new GuiButtonToggled(5, mod.isDynamicColorsEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 4 + 15 - 2));
    }
}
