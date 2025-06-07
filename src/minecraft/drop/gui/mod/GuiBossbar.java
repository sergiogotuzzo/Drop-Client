package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.mods.ModInstances;
import drop.mods.impl.Bossbar;

public class GuiBossbar extends GuiMod {
	private static final Bossbar mod = ModInstances.getBossbarMod();
	
	public GuiBossbar(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	super.drawScreen(mouseX, mouseY, partialTicks);
    	
    	this.writeOptionText("Show Name", 1);
    	this.writeOptionText("Show Health", 2);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
    	switch (button.id) {
    		case 1:
    			mod.setShowName(!mod.isShowNameToggled());
            	this.initGui();
            	break;
    		case 2:
    			mod.setShowHealth(!mod.isShowHealthToggled());
            	this.initGui();
            	break;
    	}
    }
	
	@Override
    public void initGui() {
		super.initGui();
        
    	this.buttonList.add(this.createGuiButtonToggled(1, mod.isShowNameToggled(), 1));
    	this.buttonList.add(this.createGuiButtonToggled(2, mod.isShowHealthToggled(), 2));
    }
}