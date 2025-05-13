package rubik.gui.mods.impl.togglesprintsneak;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.Client;
import rubik.gui.GuiRubikClientScreen;
import rubik.mods.ModInstances;
import rubik.mods.impl.togglesprintsneak.ToggleSprintSneak;

public class GuiToggleSprintSneak extends GuiRubikClientScreen {
	private final GuiScreen previousGuiScreen;
	private final ToggleSprintSneak mod = ModInstances.getToggleSprintSneakMod();
	
	public GuiToggleSprintSneak(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        
        this.drawCenteredString(this.fontRendererObj, "Toggle Sprint / Sneak", this.width / 2, 15, 0xFFFFFFFF);
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
            	mod.setToggleSprint(!mod.isToggleSprintEnabled());
            	this.initGui();
            	break;
            case 3:
            	mod.setToggleSneak(!mod.isToggleSneakEnabled());
            	this.initGui();
            	break;
            case 4:
            	mod.setFlyBoost(!mod.isFlyBoostEnabled());
            	this.initGui();
            	break;
            case 5:
            	mod.setFlyBoostFactor((mod.getFlyBoostFactor() < 8 && mod.getFlyBoostFactor() >= 2) ? mod.getFlyBoostFactor() + 1 : 2);
            	this.initGui();
            	break;
            case 6:
            	this.mc.displayGuiScreen(new GuiToggleSprintSneakText(this));
            	break;
        }
    }
	
	@Override
    public void initGui() {
        this.buttonList.clear();
        
        int i = -12;
        int j = -155;
        
        this.buttonList.add(new GuiButton(1, this.width / 2 - 75, this.height / 6 + i + 24, 150, 20, "Toggled: " + (mod.isEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(2, this.width / 2 + j, this.height / 6 + i + 48, 150, 20, "Toggle Sprint: " + (mod.isToggleSprintEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(3, this.width / 2 + j + 160, this.height / 6 + i + 48, 150, 20, "Toggle Sneak: " + (mod.isToggleSneakEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(4, this.width / 2 + j, this.height / 6 + i + 72, 150, 20, "Fly Boost: " + (mod.isFlyBoostEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(5, this.width / 2 + j + 160, this.height / 6 + i + 72, 150, 20, "Fly Boost Factor: " + mod.getFlyBoostFactor() + "x"));
        this.buttonList.add(new GuiButton(6, this.width / 2 + j, this.height / 6 + i + 96, 150, 20, "Text"));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
    }
}
