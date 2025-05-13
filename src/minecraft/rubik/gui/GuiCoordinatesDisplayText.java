package rubik.gui;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.Client;
import rubik.mods.ModInstances;
import rubik.mods.impl.CoordinatesDisplay;

public class GuiCoordinatesDisplayText extends GuiRubikClientScreen {
	private final GuiScreen previousGuiScreen;
	private final CoordinatesDisplay mod = ModInstances.getCoordinatesDisplayMod();
	
	private GuiButton buttonTextColor;
	
	public GuiCoordinatesDisplayText(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        
        this.drawCenteredString(this.fontRendererObj, "Coordinates Display", this.width / 2, 15, 0xFFFFFFFF);
        this.drawCenteredString(this.fontRendererObj, "Text Settings", this.width / 2, 30, 0xFFFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
            	this.mc.displayGuiScreen(this.previousGuiScreen);
            	break;
            case 1:
            	mod.setTextShadow(!mod.isTextShadowEnabled());
            	this.initGui();
                break;
            case 2:
            	mc.displayGuiScreen(new GuiModColor(this, mod.getTextColor(), mod));
            	break;
            case 3:
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
        
        this.buttonList.add(new GuiButton(1, this.width / 2 + j, this.height / 6 + i + 24, 150, 20, "Shadow: " + (mod.isTextShadowEnabled() ? "ON" : "OFF")));
        this.buttonList.add(buttonTextColor = new GuiButton(2, this.width / 2 + j + 160, this.height / 6 + i + 24, 150, 20, "Color"));
        this.buttonList.add(new GuiButton(3, this.width / 2 + j, this.height / 6 + i + 48, 150, 20, "Chroma: " + (mod.isTextChromaEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
        
        buttonTextColor.enabled = !mod.isTextChromaEnabled();
    }
}
