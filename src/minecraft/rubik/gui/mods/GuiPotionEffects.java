package rubik.gui.mods;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.Client;
import rubik.gui.GuiModColor;
import rubik.gui.GuiRubikClientScreen;
import rubik.mods.ModInstances;
import rubik.mods.impl.PotionEffects;

public class GuiPotionEffects extends GuiRubikClientScreen {
	private final GuiScreen previousGuiScreen;
	private final PotionEffects mod = ModInstances.getPotionEffectsMod();
	
	public GuiPotionEffects(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        
        this.drawCenteredString(this.fontRendererObj, "Potion Effects Settings", this.width / 2, 30, 0xFFFFFFFF);

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
            	mod.setShowName(!mod.isShowNameEnabled());
            	this.initGui();
            	break;
            case 3:
            	mod.setTextShadow(!mod.isTextShadowEnabled());
            	this.initGui();
            	break;
            case 4:
            	this.mc.displayGuiScreen(new GuiModColor(this, mod.getNameTextColor(), this.mod, "effectNameColor"));
                break;
            case 5:
            	this.mc.displayGuiScreen(new GuiModColor(this, mod.getDurationTextColor(), this.mod, "effectDurationColor"));
                break;
            case 6:
            	mod.setNameTextChroma(!mod.isNameTextChromaEnabled());
            	this.initGui();
            	break;
            case 7:
            	mod.setDurationTextChroma(!mod.isDurationTextChromaEnabled());
            	this.initGui();
            	break;
            case 8:
            	mod.setRight(!mod.isRightEnabled());
            	this.initGui();
            	break;
            case 9:
            	mod.setBlink(!mod.isBlinkEnabled());
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
        this.buttonList.add(new GuiButton(2, this.width / 2 + j + 160, this.height / 6 + i + 24, 150, 20, "Show Name: " + (mod.isShowNameEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(3, this.width / 2 + j, this.height / 6 + i + 48, 150, 20, "Text Shadow: " + (mod.isTextShadowEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(4, this.width / 2 + j + 160, this.height / 6 + i + 48, 150, 20, "Name Text Color"));
        this.buttonList.add(new GuiButton(5, this.width / 2 + j, this.height / 6 + i + 72, 150, 20, "Duration Text Color"));
        this.buttonList.add(new GuiButton(6, this.width / 2 + j + 160, this.height / 6 + i + 72, 150, 20, "Name Text Chroma: " + (mod.isNameTextChromaEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(7, this.width / 2 + j, this.height / 6 + i + 96, 150, 20, "Duration Text Chroma: " + (mod.isDurationTextChromaEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(8, this.width / 2 + j + 160, this.height / 6 + i + 96, 150, 20, "Side: " + (mod.isRightEnabled() ? "RIGHT" : "LEFT")));
        this.buttonList.add(new GuiButton(9, this.width / 2 + j, this.height / 6 + i + 120, 150, 20, "Blink: " + (mod.isBlinkEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
    }
}
