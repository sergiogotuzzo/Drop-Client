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
import rubik.mods.impl.Keystrokes.KeystrokesMode;

public class GuiKeystrokes extends GuiRubikClientScreen {
	private final GuiScreen previousGuiScreen;
	private final Keystrokes mod = ModInstances.getKeystrokesMod();
	
	private GuiButton arrowsButton;
	
	public GuiKeystrokes(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        
        this.drawCenteredString(this.fontRendererObj, "Keystrokes Settings", this.width / 2, 30, 0xFFFFFFFF);

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
            	mod.setTextShadow(!mod.isTextShadowEnabled());
            	this.initGui();
            	break;
            case 3:
            	this.mc.displayGuiScreen(new GuiModColor(this, mod.getPressedTextColor(), this.mod, "pressedColor"));
            	break;
            case 4:
            	this.mc.displayGuiScreen(new GuiModColor(this, mod.getReleasedTextColor(), this.mod, "releasedColor"));
            	break;
            case 5:
            	mod.setShowMouse(!mod.isShowMouseEnabled());
            	this.initGui();
            	break;
            case 6:
            	mod.setShowSpacebar(!mod.isShowSpacebarEnabled());
            	this.initGui();
            	break;
            case 7:
            	mod.setShowMovementKeys(!mod.isShowMovementKeysEnabled());
            	this.initGui();
            	break;
            case 8:
            	mod.setArrows(!mod.isArrowsEnabled());
            	this.initGui();
            	break;
            case 9:
            	this.mc.displayGuiScreen(new GuiModColor(this, mod.getPressedBackgroundColor(), this.mod, "pressedBackgroundColor"));
            	break;
            case 10:
            	this.mc.displayGuiScreen(new GuiModColor(this, mod.getReleasedBackgroundColor(), this.mod, "releasedBackgroundColor"));
            	break;
            case 11:
            	mod.setShowLeftCPS(!mod.isShowLeftCPSEnabled());
            	this.initGui();
            	break;
            case 12:
            	mod.setShowRightCPS(!mod.isShowRightCPSEnabled());
            	this.initGui();
            	break;
            case 13:
            	mod.setPressedTextChroma(!mod.isPressedTextChromaEnabled());
            	this.initGui();
            	break;
            case 14:
            	mod.setReleasedTextChroma(!mod.isReleasedTextChromaEnabled());
            	this.initGui();
            	break;
        }
    }
	
	@Override
    public void initGui() {
        this.buttonList.clear();
        
        int i = -12;
        int j = -155;
        int k = -3;
        
        this.buttonList.add(new GuiButton(1, this.width / 2 + j, this.height / 6 + i + 24, 150, 20, mod.isEnabled() ? "Enabled" : "Disabled"));
        this.buttonList.add(new GuiButton(2, this.width / 2 + j + 160, this.height / 6 + i + 24, 150, 20, "Text Shadow: " + (mod.isTextShadowEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(3, this.width / 2 + j, this.height / 6 + i + 48 + k, 150, 20, "Pressed Text Color"));
        this.buttonList.add(new GuiButton(4, this.width / 2 + j + 160, this.height / 6 + i + 48 + k, 150, 20, "Released Text Color"));
        this.buttonList.add(new GuiButton(5, this.width / 2 + j, this.height / 6 + i + 72 + k * 2, 150, 20, "Show Mouse: " + (mod.isShowMouseEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(6, this.width / 2 + j + 160, this.height / 6 + i + 72 + k * 2, 150, 20, "Show Spacebar: " + (mod.isShowSpacebarEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(7, this.width / 2 + j, this.height / 6 + i + 96 + k * 3, 150, 20, "Show Movement Keys: " + (mod.isShowMovementKeysEnabled() ? "ON" : "OFF")));
        this.buttonList.add(arrowsButton = new GuiButton(8, this.width / 2 + j + 160, this.height / 6 + i + 96 + k * 3, 150, 20, "Arrows: " + (mod.isArrowsEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(9, this.width / 2 + j, this.height / 6 + i + 120 + k * 4, 150, 20, "Pressed Background Color"));
        this.buttonList.add(new GuiButton(10, this.width / 2 + j + 160, this.height / 6 + i + 120 + k * 4, 150, 20, "Released Background Color"));
        this.buttonList.add(new GuiButton(11, this.width / 2 + j, this.height / 6 + i + 144 + k * 5, 150, 20, "Show Left CPS: " + (mod.isShowLeftCPSEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(12, this.width / 2 + j + 160, this.height / 6 + i + 144 + k * 5, 150, 20, "Show Right CPS: " + (mod.isShowRightCPSEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(13, this.width / 2 + j, this.height / 6 + i + 168 + k * 6, 150, 20, "Pressed Text Chroma: " + (mod.isPressedTextChromaEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(14, this.width / 2 + j + 160, this.height / 6 + i + 168 + k * 6, 150, 20, "Released Text Chroma: " + (mod.isReleasedTextChromaEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
        
        arrowsButton.enabled = mod.isShowMovementKeysEnabled();
    }
}
