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

        this.writeOptionText("Text Shadow", 1);
        this.writeOptionText("Background Color", 2);
        this.writeOptionText("Show In Third Person", 3);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
	        case 1:
	        	mod.setTextShadow(!mod.isTextShadowToggled());
	        	this.initGui();
	        	break;
	        case 2:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getBackgroundColor(), "backgroundColor", "Background Color", true));
	        	break;
            case 3:
            	mod.setShowInThirdPerson(!mod.isShowInThirdPersonToggled());
            	this.initGui();
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();

    	this.buttonList.add(this.createGuiCheckBox(1, mod.isTextShadowToggled(), 1));
		this.buttonList.add(this.createGuiRect(2, mod.getBackgroundColor().getRGB(), 2));
    	this.buttonList.add(this.createGuiCheckBox(3, mod.isShowInThirdPersonToggled(), 3));
    }
}
