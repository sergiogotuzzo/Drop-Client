package rubik.gui.mods.impl;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.Client;
import rubik.gui.GuiRubikClientScreen;
import rubik.mods.ModInstances;
import rubik.mods.impl.Freelook;

public class GuiFreelook extends GuiRubikClientScreen {
	private final GuiScreen previousGuiScreen;
	private final Freelook mod = ModInstances.getFreelookMod();
	
	public GuiFreelook(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        
        this.drawCenteredString(this.fontRendererObj, "Freelook", this.width / 2, 15, 0xFFFFFFFF);
        this.drawCenteredString(this.fontRendererObj, "Settings", this.width / 2, 30, 0xFFFFFFFF);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
            	this.mc.displayGuiScreen(this.previousGuiScreen);
            	break;
            case 1:
            	mod.setEnabled(!mod.isEnabled());
            	this.initGui();
                break;
            case 2:
            	mod.setReturnOnRelease(!mod.isReturnOnReleaseEnabled());
            	this.initGui();
            	break;
            case 3:
            	mod.setInvertYaw(!mod.isInvertYawEnabled());
            	this.initGui();
            	break;
            case 4:
            	mod.setInvertPitch(!mod.isInvertPitchEnabled());
            	this.initGui();
            	break;
        }
    }
	
	@Override
    public void initGui() {
        this.buttonList.clear();
        
        int i = -12;
        int j = -155;
        
        this.buttonList.add(new GuiButton(1, this.width / 2 - 75, this.height / 6 + i + 24, 150, 20, "Toggled: " + (mod.isEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(2, this.width / 2 + j + 160, this.height / 6 + i + 48, 150, 20, "Mode: " + (mod.isReturnOnReleaseEnabled() ? "Key Held" : "Toggle")));
        this.buttonList.add(new GuiButton(3, this.width / 2 + j, this.height / 6 + i + 48, 150, 20, "Invert Yaw: " + (mod.isInvertYawEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(4, this.width / 2 + j, this.height / 6 + i + 72, 150, 20, "Invert Pitch: " + (mod.isInvertPitchEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
    }
}
