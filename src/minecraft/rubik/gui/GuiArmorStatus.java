package rubik.gui;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.Client;
import rubik.mods.ModInstances;
import rubik.mods.impl.ArmorStatus;

public class GuiArmorStatus extends GuiRubikClientScreen {
	private final GuiScreen previousGuiScreen;
	private final ArmorStatus mod = ModInstances.getArmorStatusMod();
	
	private GuiButton percentageBtn;
	private GuiButton damageBtn;
	private GuiButton maxDamageBtn;
	
	public GuiArmorStatus(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        
        this.drawCenteredString(this.fontRendererObj, "Armor Status", this.width / 2, 15, 0xFFFFFFFF);
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
            	mod.setShowPercentage(!mod.isShowPercentageEnabled());
            	this.initGui();
            	break;
            case 3:
            	mod.setEquippedItem(!mod.isEquippedItemEnabled());
            	this.initGui();
            	break;
            case 4:
            	mod.setShowDamage(!mod.isShowDamageEnabled());
            	this.initGui();
            	break;
            case 5:
            	mod.setDamageOverlays(!mod.isDamageOverlaysEnabled());
            	this.initGui();
            	break;
            case 6:
            	mod.setShowMaxDamage(!mod.isShowMaxDamageEnabled());
            	this.initGui();
            	break;
            case 7:
            	mc.displayGuiScreen(new GuiArmorStatusText(this));
            	break;
        }
    }
	
	@Override
    public void initGui() {
        this.buttonList.clear();
        
        int i = -12;
        int j = -155;
        
        this.buttonList.add(new GuiButton(1, this.width / 2 - 75, this.height / 6 + i + 24, 150, 20, "Toggled: " + (mod.isEnabled() ? "ON" : "OFF")));
        this.buttonList.add(percentageBtn = new GuiButton(2, this.width / 2 + j, this.height / 6 + i + 48, 150, 20, "Show Percentage: " + (mod.isShowPercentageEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(3, this.width / 2 + j + 160, this.height / 6 + i + 48, 150, 20, "Equipped Item: " + (mod.isEquippedItemEnabled() ? "ON" : "OFF")));
        this.buttonList.add(damageBtn = new GuiButton(4, this.width / 2 + j, this.height / 6 + i + 72, 150, 20, "Show Damage: " + (mod.isShowDamageEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(5, this.width / 2 + j + 160, this.height / 6 + i + 72, 150, 20, "Damage Overlays: " + (mod.isDamageOverlaysEnabled() ? "ON" : "OFF")));
        this.buttonList.add(maxDamageBtn = new GuiButton(6, this.width / 2 + j, this.height / 6 + i + 96, 150, 20, "Show Max Damage: " + (mod.isShowMaxDamageEnabled() ? "ON" : "OFF")));
        this.buttonList.add(new GuiButton(7, this.width / 2 + j + 160, this.height / 6 + i + 96, 150, 20, "Text"));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
        
        damageBtn.enabled = !mod.isShowPercentageEnabled();
        maxDamageBtn.enabled = !mod.isShowPercentageEnabled() && mod.isShowDamageEnabled();
        percentageBtn.enabled = !mod.isShowDamageEnabled();
    }
}
