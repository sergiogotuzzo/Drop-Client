package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.mods.ModInstances;
import drop.mods.impl.Zoom;

public class GuiZoom extends GuiMod {
	private static final Zoom mod = ModInstances.getZoomMod();
	
	public GuiZoom(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	super.drawScreen(mouseX, mouseY, partialTicks);
    	
        this.writeOptionText("Scroll To Zoom", 1);
        this.writeOptionText("Cinematic Camera", 2);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
            case 1:
            	mod.setScrollToZoom(!mod.isScrollToZoomToggled());
            	this.initGui();
            	break;
            case 2:
            	mod.setSmoothCamera(!mod.isSmoothCameraToggled());
            	this.initGui();
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
        
    	this.buttonList.add(this.createGuiButtonToggled(1, mod.isScrollToZoomToggled(), 1));
    	this.buttonList.add(this.createGuiButtonToggled(2, mod.isSmoothCameraToggled(), 2));
    }
}
