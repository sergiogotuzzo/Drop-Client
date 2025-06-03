package drop.gui.mod;

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
import drop.mods.impl.OldVisuals;

public class GuiOldVisuals extends GuiDropClientScreen {
	private final GuiScreen previousGuiScreen;
	private final OldVisuals mod = ModInstances.getOldVisualsMod();
	
	public GuiOldVisuals(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	this.drawDefaultBackground();
    	
    	drawRect((this.width - 300) / 2, (this.height - 200) / 2, (this.width - 300) / 2 + 300, (this.height - 200) / 2 + 200, new Color(0, 0, 0, 127).getRGB());
        
        this.drawScaledText("Old Visuals", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 15, 2.0D, 0xFFFFFFFF, false, false);
        this.drawText("Fishing Rod", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15, -1, false, false);
        this.drawText("Bow", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 1 + 15, -1, false, false);
        this.drawText("Block Hitting", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 2 + 15, -1, false, false);
        this.drawText("Armor Hit Animation", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 3 + 15, -1, false, false);
        this.drawText("Hit Armor Color", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 4 + 15, -1, false, false);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
            	this.mc.displayGuiScreen(this.previousGuiScreen);
            	break;
            case 1:
            	mod.setFishingRod(!mod.isFishingRodEnabled());
            	this.initGui();
            	break;
            case 2:
            	mod.setBow(!mod.isBowEnabled());
            	this.initGui();
            	break;
            case 3:
            	mod.setBlockHitting(!mod.isBlockHittingEnabled());
            	this.initGui();
            	break;
            case 4:
            	mod.setArmorHitAnimation(!mod.isArmorHitAnimationEnabled());
            	this.initGui();
            	break;
            case 5:
            	mc.displayGuiScreen(new GuiModColor(this, mod.getHitArmorColor(), mod, "hitArmorColor", "hitArmorChroma", "Old Visuals", "Hit Armor Color", true));
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();

        this.buttonList.clear();
        
    	this.buttonList.add(new GuiButtonToggled(1, mod.isFishingRodEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15 - 2));
    	this.buttonList.add(new GuiButtonToggled(2, mod.isBowEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 1 + 15 - 2));
    	this.buttonList.add(new GuiButtonToggled(3, mod.isBlockHittingEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 2 + 15 - 2));
    	this.buttonList.add(new GuiButtonToggled(4, mod.isArmorHitAnimationEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 3 + 15 - 2));
        this.buttonList.add(new GuiRect(5, (this.width + 300) / 2 - 15 - 13, (this.height - 200) / 2 + 30 + 15 * 4 + 15 - 2 * 2, mod.getHitArmorColor().getRGB()));
        this.buttonList.add(new GuiButton(0, (this.width + 300) / 2 - 50 - 15, (this.height - 200) / 2 + 15, 50, 20, I18n.format("gui.done", new Object[0])));
    }
}
