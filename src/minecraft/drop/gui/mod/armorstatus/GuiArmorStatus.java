package drop.gui.mod.armorstatus;

import java.awt.Color;
import java.io.IOException;

import drop.Client;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import drop.events.EventTarget;
import drop.gui.GuiButtonToggled;
import drop.gui.GuiDropClientScreen;
import drop.gui.GuiRect;
import drop.mods.ModInstances;
import drop.mods.impl.ArmorStatus;

public class GuiArmorStatus extends GuiDropClientScreen {
	private final GuiScreen previousGuiScreen;
	private final ArmorStatus mod = ModInstances.getArmorStatusMod();
	
	public GuiArmorStatus(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
    	
    	drawRect((this.width - 300) / 2, (this.height - 200) / 2, (this.width - 300) / 2 + 300, (this.height - 200) / 2 + 200, new Color(0, 0, 0, 127).getRGB());
        
        this.drawScaledText("Armor Status", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 15, 2.0D, 0xFFFFFFFF, false, false);
        this.drawText("Show Percentage", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15, -1, false, false);
        this.drawText("Show Damage", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 1 + 15, -1, false, false);
        this.drawText("Show Max Damage", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 2 + 15, -1, false, false);
        this.drawText("Equipped Item", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 3 + 15, -1, false, false);
        this.drawText("Damage Overlays", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 4 + 15, -1, false, false);
        this.drawText("Text Shadow", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 5 + 15, -1, false, false);
        this.drawText("Text Color", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 6 + 15, -1, false, false);
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
            	this.mc.displayGuiScreen(this.previousGuiScreen);
            	break;
            case 1:
            	mod.setShowPercentage(!mod.isShowPercentageEnabled());
            	this.initGui();
            	break;
            case 4:
            	mod.setEquippedItem(!mod.isEquippedItemEnabled());
            	this.initGui();
            	break;
            case 2:
            	mod.setShowDamage(!mod.isShowDamageEnabled());
            	this.initGui();
            	break;
            case 5:
            	mod.setDamageOverlays(!mod.isDamageOverlaysEnabled());
            	this.initGui();
            	break;
            case 3:
            	mod.setShowMaxDamage(!mod.isShowMaxDamageEnabled());
            	this.initGui();
            	break;
            case 6:
            	mod.setTextShadow(!mod.isTextShadowEnabled());
            	this.initGui();
            	break;
            case 7:
            	mc.displayGuiScreen(new GuiArmorStatusTextColor(this));
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
		
        this.buttonList.clear();
        
    	this.buttonList.add(new GuiButtonToggled(1, mod.isShowPercentageEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15 - 2));
    	this.buttonList.add(new GuiButtonToggled(2, mod.isShowDamageEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 1 + 15 - 2));
    	this.buttonList.add(new GuiButtonToggled(3, mod.isShowMaxDamageEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 2 + 15 - 2));
    	this.buttonList.add(new GuiButtonToggled(4, mod.isEquippedItemEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 3 + 15 - 2));
    	this.buttonList.add(new GuiButtonToggled(5, mod.isDamageOverlaysEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 4 + 15 - 2));
    	this.buttonList.add(new GuiButtonToggled(6, mod.isTextShadowEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 5 + 15 - 2));
        this.buttonList.add(new GuiRect(7, (this.width + 300) / 2 - 15 - 13, (this.height - 200) / 2 + 30 + 15 * 6 + 15 - 2 * 2, mod.getTextColor().getRGB()));
        this.buttonList.add(new GuiButton(0, (this.width + 300) / 2 - 50 - 15, (this.height - 200) / 2 + 15 - 3, 50, 20, I18n.format("gui.done", new Object[0])));
    }
}
