package rubik.gui.mods;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.Client;
import rubik.gui.GuiModColor;
import rubik.gui.GuiRubikClientScreen;
import rubik.mods.ModInstances;
import rubik.mods.impl.ArmorStatus;
import rubik.mods.impl.ArmorStatus.ArmorStatusMode;

public class GuiArmorStatus extends GuiRubikClientScreen {
	private final GuiScreen previousGuiScreen;
	private final ArmorStatus mod = ModInstances.getArmorStatusMod();
	
	private GuiButton buttonTextColor;
	private GuiButton buttonTextChroma;
	
	public GuiArmorStatus(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        
        this.drawCenteredString(this.fontRendererObj, "Armor Status Settings", this.width / 2, 30, 0xFFFFFFFF);

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
            	mod.setShowEquippedItem(!mod.isShowEquippedItemEnabled());
            	this.initGui();
            	break;
            case 3:
            	mod.setRight(!mod.isRightEnabled());
            	this.initGui();
            	break;
            case 4:
	            if (mod.getMode() == ArmorStatusMode.PERCENTAGE) {
	            	mod.setMode(ArmorStatusMode.DAMAGE.getIndex());
	            } else if (mod.getMode() == ArmorStatusMode.DAMAGE) {
	            	mod.setMode(ArmorStatusMode.DAMAGE_MAX_DAMAGE.getIndex());
	            } else if (mod.getMode() == ArmorStatusMode.DAMAGE_MAX_DAMAGE) {
	            	mod.setMode(ArmorStatusMode.PERCENTAGE.getIndex());
	            }
	            this.initGui();
            	break;
            case 5:
            	mod.setDynamicColors(!mod.isDynamicColorsEnabled());
            	this.initGui();
            	break;
            case 6:
            	this.mc.displayGuiScreen(new GuiModColor(this, mod.getTextColor(), this.mod));
            	break;
            case 7:
            	mod.setTextShadow(!mod.isTextShadowEnabled());
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
        this.buttonList.add(new GuiButton(2, this.width / 2 + j + 160, this.height / 6 + i + 24, 150, 20, "Show Equipped Item: " + (mod.isShowEquippedItemEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(3, this.width / 2 + j, this.height / 6 + i + 48, 150, 20, "Side: " + (mod.isRightEnabled() ? "RIGHT" : "LEFT")));
        this.buttonList.add(new GuiButton(4, this.width / 2 + j + 160, this.height / 6 + i + 48, 150, 20, "Mode: " + mod.getMode().toString().replace("DAMAGE_MAX_DAMAGE", "DAMAGE/MAX DAMAGE")));
        this.buttonList.add(new GuiButton(5, this.width / 2 + j, this.height / 6 + i + 72, 150, 20, "Dynamic Colors: " + (mod.isDynamicColorsEnabled() ? "ON" : "OFF")));
        this.buttonList.add(buttonTextColor = new GuiButton(6, this.width / 2 + j + 160, this.height / 6 + i + 72, 150, 20, "Text Color"));
        this.buttonList.add(new GuiButton(7, this.width / 2 + j, this.height / 6 + i + 96, 150, 20, "Text Shadow: " + (mod.isTextShadowEnabled() ? "ON" : "OFF")));
        this.buttonList.add(buttonTextChroma = new GuiButton(8, this.width / 2 + j + 160, this.height / 6 + i + 96, 150, 20, "Text Chroma: " + (mod.isTextChromaEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
        
        buttonTextColor.enabled = !mod.isDynamicColorsEnabled();
        buttonTextChroma.enabled = !mod.isDynamicColorsEnabled();
    }
}
