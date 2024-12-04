package rubik.gui.mods;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.Client;
import rubik.gui.GuiModColor;
import rubik.gui.GuiRubikClientScreen;
import rubik.mods.ModInstances;
import rubik.mods.impl.Keystrokes;

public class GuiKeystrokesPressedText extends GuiRubikClientScreen {
	private final GuiScreen previousGuiScreen;
	private final Keystrokes mod = ModInstances.getKeystrokesMod();
	
	private GuiButton buttonTextColor;
	
	public GuiKeystrokesPressedText(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        
        this.drawCenteredString(this.fontRendererObj, "Keystrokes", this.width / 2, 15, 0xFFFFFFFF);
        this.drawCenteredString(this.fontRendererObj, "Pressed Text Settings", this.width / 2, 30, 0xFFFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
            	this.mc.displayGuiScreen(this.previousGuiScreen);
            	break;
            case 1:
            	mod.setPressedTextShadow(!mod.isPressedTextShadowEnabled());
            	this.initGui();
                break;
            case 2:
            	mc.displayGuiScreen(new GuiModColor(this, mod.getPressedTextColor(), mod, "pressedTextColor"));
            	break;
            case 3:
            	mod.setPressedTextChroma(!mod.isPressedTextChromaEnabled());
            	this.initGui();
            	break;
        }
    }
	
	@Override
    public void initGui() {
        this.buttonList.clear();
        
        int i = -12;
        int j = -155;
        
        this.buttonList.add(new GuiButton(1, this.width / 2 + j, this.height / 6 + i + 24, 150, 20, "Shadow: " + (mod.isPressedTextShadowEnabled() ? "ON" : "OFF")));
        this.buttonList.add(buttonTextColor = new GuiButton(2, this.width / 2 + j + 160, this.height / 6 + i + 24, 150, 20, "Color"));
        this.buttonList.add(new GuiButton(3, this.width / 2 + j, this.height / 6 + i + 48, 150, 20, "Chroma: " + (mod.isPressedTextChromaEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
        
        buttonTextColor.enabled = !mod.isPressedTextChromaEnabled();
    }
}
