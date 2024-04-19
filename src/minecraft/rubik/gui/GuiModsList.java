package rubik.gui;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.gui.mods.GuiCPSDisplay;
import rubik.gui.mods.GuiCoordinatesDisplay;
import rubik.gui.mods.GuiFPSDisplay;
import rubik.gui.mods.GuiFreelook;
import rubik.gui.mods.GuiFullbright;
import rubik.gui.mods.GuiKeystrokes;
import rubik.gui.mods.GuiPingDisplay;
import rubik.gui.mods.GuiPotionEffects;
import rubik.gui.mods.GuiToggleSprintSneak;
import rubik.gui.mods.armorstatus.GuiArmorStatus;

public class GuiModsList extends GuiScreen {
	private final GuiScreen previousScreen;
	
	public GuiModsList(GuiScreen previousScreen) {
		this.previousScreen = previousScreen;
	}
	
    @Override
    public void initGui()
    {
        this.buttonList.clear();
        
        int i = -16;
        int j = 50;
        
        this.buttonList.add(new GuiButton(1, this.width / 2 - 200 - 2 + j, this.height / 4 + 24 + i, 98, 20, I18n.format("Keystrokes", new Object[0])));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100 + j, this.height / 4 + 24 + i, 98, 20, I18n.format("Armor Status", new Object[0])));
        this.buttonList.add(new GuiButton(3, this.width / 2 + 2 + j, this.height / 4 + 24 + i, 98, 20, I18n.format("Potion Effects", new Object[0])));
        this.buttonList.add(new GuiButton(4, this.width / 2 - 200 - 2 + j, this.height / 4 + 48 + i, 98, 20, I18n.format("CPS Display", new Object[0])));
        this.buttonList.add(new GuiButton(5, this.width / 2 - 100 + j, this.height / 4 + 48 + i, 98, 20, I18n.format("FPS Display", new Object[0])));
        this.buttonList.add(new GuiButton(6, this.width / 2 + 2 + j, this.height / 4 + 48 + i, 98, 20, I18n.format("Ping Display", new Object[0])));
        this.buttonList.add(new GuiButton(7, this.width / 2 - 200 - 2 + j, this.height / 4 + 72 + i, 98, 20, I18n.format("Coming Soon...", new Object[0])));
        this.buttonList.add(new GuiButton(8, this.width / 2 - 100 + j, this.height / 4 + 72 + i, 98, 20, I18n.format("Fullbright", new Object[0])));
        this.buttonList.add(new GuiButton(9, this.width / 2 + 2 + j, this.height / 4 + 72 + i, 98, 20, I18n.format("Freelook", new Object[0])));
        this.buttonList.add(new GuiButton(10, this.width / 2 - 100 - 26, this.height / 4 + 96 + i, 124, 20, I18n.format("Coordinates Display", new Object[0])));
        this.buttonList.add(new GuiButton(11, this.width / 2 + 2 + 0, this.height / 4 + 96 + i, 124, 20, I18n.format("Toggle Sprint / Sneak", new Object[0])));
        this.buttonList.add(new GuiButton(0, this.width / 2 - j * 2, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
        switch (button.id)
        {
            case 0:
            	this.mc.displayGuiScreen(this.previousScreen);
            	break;
            case 1:
            	this.mc.displayGuiScreen(new GuiKeystrokes(this));
                break;
            case 2:
            	this.mc.displayGuiScreen(new GuiArmorStatus(this));
            	break;
            case 3:
            	this.mc.displayGuiScreen(new GuiPotionEffects(this));
            	break;
            case 4:
            	this.mc.displayGuiScreen(new GuiCPSDisplay(this));
            	break;
            case 5:
            	this.mc.displayGuiScreen(new GuiFPSDisplay(this));
            	break;
            case 6:
            	this.mc.displayGuiScreen(new GuiPingDisplay(this));
            	break;
            case 7:
            	break;
            case 8:
            	this.mc.displayGuiScreen(new GuiFullbright(this));
            	break;
            case 9:
            	this.mc.displayGuiScreen(new GuiFreelook(this));
            	break;
            case 10:
            	this.mc.displayGuiScreen(new GuiCoordinatesDisplay(this));
            	break;
            case 11:
            	this.mc.displayGuiScreen(new GuiToggleSprintSneak(this));
            	break;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, I18n.format("Mods List", new Object[0]), this.width / 2, 40, 0xFFFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
