package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.mods.ModInstances;
import drop.mods.impl.Nametags;

public class GuiNametags extends GuiMod {
	private static final Nametags mod = ModInstances.getNametagsMod();
	
	public GuiNametags(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	super.drawScreen(mouseX, mouseY, partialTicks);
        
        this.writeOptionText("Show In Third Person", 1);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
            case 1:
            	mod.setShowInThirdPerson(!mod.isShowInThirdPersonToggled());
            	this.initGui();
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
        
    	this.buttonList.add(this.createGuiButtonToggled(1, mod.isShowInThirdPersonToggled(), 1));
    }
}
