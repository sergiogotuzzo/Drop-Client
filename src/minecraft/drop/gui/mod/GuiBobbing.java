package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.mods.ModInstances;
import drop.mods.impl.Bobbing;

public class GuiBobbing extends GuiMod {
	private static final Bobbing mod = ModInstances.getBobbingMod();
	
	public GuiBobbing(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	super.drawScreen(mouseX, mouseY, partialTicks);
    	
    	this.writeOptionText("Minimal View Bobbing", 1);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
    	if (button.id == 1) {
    		mod.setMinimalViewBobbing(!mod.isMinimalViewBobbingToggled());
        	this.initGui();
    	}
    }
	
	@Override
    public void initGui() {
		super.initGui();
        
    	this.buttonList.add(this.createGuiButtonToggled(1, mod.isMinimalViewBobbingToggled(), 1));
    }
}