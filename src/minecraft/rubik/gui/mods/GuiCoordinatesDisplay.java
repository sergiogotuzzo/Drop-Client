package rubik.gui.mods;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.Client;
import rubik.gui.GuiModColor;
import rubik.gui.GuiRubikClientScreen;
import rubik.mods.ModInstances;
import rubik.mods.impl.CoordinatesDisplay;

public class GuiCoordinatesDisplay extends GuiRubikClientScreen {
	private final GuiScreen previousGuiScreen;
	private final CoordinatesDisplay mod = ModInstances.getCoordinatesDisplayMod();
	
	private GuiButton backgroundColorButton;
	
	public GuiCoordinatesDisplay(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        
        this.drawCenteredString(this.fontRendererObj, "Coordinates Display Settings", this.width / 2, 30, 0xFFFFFFFF);

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
            	mod.setShowBackground(!mod.isShowBackgroundEnabled());
            	this.initGui();
            	break;
            case 3:
            	mod.setTextShadow(!mod.isTextShadowEnabled());
            	this.initGui();
            	break;
            case 4:
            	this.mc.displayGuiScreen(new GuiModColor(this, mod.getTextColor(), this.mod));
            	break;
            case 5:
            	mod.setShowBiome(!mod.isShowBiomeEnabled());
            	this.initGui();
            	break;
            case 6:
            	mod.setShowTowards(!mod.isShowTowardsEnabled());
            	this.initGui();
            	break;
            case 8:
            	mod.setTextChroma(!mod.isTextChromaEnabled());
            	this.initGui();
            	break;
        }
    }
	
	@Override
    public void initGui() {
        this.buttonList.clear();
        
        int i = -12;
        int j = -155;
        
        this.buttonList.add(new GuiButton(1, this.width / 2 + j, this.height / 6 + i + 24, 150, 20, mod.isEnabled() ? "Enabled" : "Disabled"));
        this.buttonList.add(new GuiButton(2, this.width / 2 + j + 160, this.height / 6 + i + 24, 150, 20, "Show Background: " + (mod.isShowBackgroundEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(3, this.width / 2 + j, this.height / 6 + i + 48, 150, 20, "Text Shadow: " + (mod.isTextShadowEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(4, this.width / 2 + j + 160, this.height / 6 + i + 48, 150, 20, "Text Color"));
        this.buttonList.add(new GuiButton(5, this.width / 2 + j, this.height / 6 + i + 72, 150, 20, "Show Biome: " + (mod.isShowBiomeEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(6, this.width / 2 + j + 160, this.height / 6 + i + 72, 150, 20, "Show Towards: " + (mod.isShowTowardsEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(8, this.width / 2 + j + 160, this.height / 6 + i + 96, 150, 20, "Text Chroma: " + (mod.isTextChromaEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
        
        backgroundColorButton.enabled = mod.isShowBackgroundEnabled();
    }
}
