package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.mods.ModInstances;
import drop.mods.impl.Freelook;

public class GuiFreelook extends GuiMod {
	private static final Freelook mod = ModInstances.getFreelookMod();
	
	public GuiFreelook(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	super.drawScreen(mouseX, mouseY, partialTicks);
    	
    	this.writeOptionText("Hold", 1);
    	this.writeOptionText("Invert Yaw", 2);
    	this.writeOptionText("Invert Pitch", 3);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
            case 1:
            	mod.setHold(!mod.isHoldToggled());
            	this.initGui();
            	break;
            case 2:
            	mod.setInvertYaw(!mod.isInvertYawToggled());
            	this.initGui();
            	break;
            case 3:
            	mod.setInvertPitch(!mod.isInvertPitchToggled());
            	this.initGui();
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
		
		this.buttonList.add(this.createGuiCheckBox(1, mod.isHoldToggled(), 1));
		this.buttonList.add(this.createGuiCheckBox(2, mod.isInvertYawToggled(), 2));
		this.buttonList.add(this.createGuiCheckBox(3, mod.isInvertPitchToggled(), 3));
    }
}
