package rubik.gui;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.Client;
import rubik.mods.ModInstances;

public class GuiMods extends GuiRubikClientScreen {
	private final GuiScreen previousScreen;
	
	private GuiButton buttonReset;
	
	public GuiMods(GuiScreen previousScreen) {
		this.previousScreen = previousScreen;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        
        this.drawCenteredString(this.fontRendererObj, "Mods", this.width / 2, 20, 0xFFFFFFFF);
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
            	this.mc.displayGuiScreen(this.previousScreen);
            	break;
            case 1:
            	this.mc.displayGuiScreen(new GuiArmorStatus(this));
            	break;
            case 2:
            	this.mc.displayGuiScreen(new GuiBlockOverlay(this));
            	break;
            case 4:
            	this.mc.displayGuiScreen(new GuiCPSDisplay(this));
            	break;
            case 5:
            	this.mc.displayGuiScreen(new GuiFPSDisplay(this));
            	break;
            case 6:
            	this.mc.displayGuiScreen(new GuiFreelook(this));
            	break;
            case 8:
            	this.mc.displayGuiScreen(new GuiKeystrokes(this));
                break;
            case 9:
            	this.mc.displayGuiScreen(new GuiOldAnimations(this));
            	break;
            case 10:
            	this.mc.displayGuiScreen(new GuiPingDisplay(this));
            	break;
            case 11:
            	this.mc.displayGuiScreen(new GuiPotionEffects(this));
            	break;
            case 13:
            	this.mc.displayGuiScreen(new GuiCoordinatesDisplay(this));
            	break;
            case 14:
            	this.mc.displayGuiScreen(new GuiToggleSprintSneak(this));
            	break;
        }
    }
	
    @Override
    public void initGui() {
        this.buttonList.clear();
        
        int i = -16;
        int k = 24;
        
        this.buttonList.add(new GuiButton(8, this.width / 2 - 155, this.height / 6 + i + k * 1, 150, 20, I18n.format("Keystrokes", new Object[0])));
        this.buttonList.add(new GuiButton(13, this.width / 2 - 155 + 160, this.height / 6 + i + k * 5, 150, 20, I18n.format("Coordinates Display", new Object[0])));
        this.buttonList.add(new GuiButton(4, this.width / 2 - 155, this.height / 6 + i + k * 2, 150, 20, I18n.format("CPS Display", new Object[0])));
        this.buttonList.add(new GuiButton(10, this.width / 2 - 155, this.height / 6 + i + k * 4, 150, 20, I18n.format("Ping Display", new Object[0])));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 155 + 160, this.height / 6 + i + k * 4, 150, 20, I18n.format("Block Overlay", new Object[0])));
        this.buttonList.add(new GuiButton(5, this.width / 2 - 155, this.height / 6 + i + k * 3, 150, 20, I18n.format("FPS Display", new Object[0])));
        this.buttonList.add(new GuiButton(6, this.width / 2 - 155 + 160, this.height / 6 + i + k * 2, 150, 20, I18n.format("Freelook", new Object[0])));
        this.buttonList.add(new GuiButton(9, this.width / 2 - 155 + 160, this.height / 6 + i + k * 3, 150, 20, I18n.format("Old Animations", new Object[0])));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 155, this.height / 6 + i + k * 5, 150, 20, I18n.format("Armor Status", new Object[0])));
        this.buttonList.add(new GuiButton(14, this.width / 2 - 155 + 160, this.height / 6 + i + k * 1, 150, 20, I18n.format("Toggle Sprint / Sneak", new Object[0])));
        this.buttonList.add(new GuiButton(11, this.width / 2 - 155, this.height / 6 + i + k * 6, 150, 20, I18n.format("Potion Effects", new Object[0])));

        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
    }
}
