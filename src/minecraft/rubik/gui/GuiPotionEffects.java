package rubik.gui;

import java.awt.Color;
import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.Client;
import rubik.events.EventTarget;
import rubik.mods.ModInstances;
import rubik.mods.impl.PotionEffects;

public class GuiPotionEffects extends GuiDropClientScreen {
	private final GuiScreen previousGuiScreen;
	private final PotionEffects mod = ModInstances.getPotionEffectsMod();
	
	public GuiPotionEffects(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	drawRect((this.width - 300) / 2, (this.height - 200) / 2, (this.width - 300) / 2 + 300, (this.height - 200) / 2 + 200, new Color(0, 0, 0, 127).getRGB());
        
        this.drawScaledText("Potion Effects", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 15, 2.0D, 0xFFFFFFFF, false, false);
        this.drawText("Blink", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15, -1, false, false);
        this.drawText("Duration Text Shadow", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 1 + 15, -1, false, false);
        this.drawText("Show Name", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 3 + 15, -1, false, false);
        this.drawText("Name Text Shadow", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 4 + 15, -1, false, false);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
            	this.mc.displayGuiScreen(this.previousGuiScreen);
            	break;
            case 1:
            	mod.setBlink(!mod.isBlinkEnabled());
            	this.initGui();
            	break;
            case 2:
            	mod.setDurationTextShadow(!mod.isDurationTextShadowEnabled());
            	this.initGui();
            	break;
            case 3:
            	mc.displayGuiScreen(new GuiPotionEffectsDurationTextColor(this));
            	break;
            case 4:
            	mod.setShowName(!mod.isShowNameEnabled());
            	this.initGui();
            	break;
            case 5:
            	mod.setNameTextShadow(!mod.isNameTextShadowEnabled());
            	this.initGui();
            	break;
            case 6:
            	mc.displayGuiScreen(new GuiPotionEffectsNameTextColor(this));
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
		
        this.buttonList.clear();
        
    	this.buttonList.add(new GuiButtonToggled(1, mod.isBlinkEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15 - 2));
    	this.buttonList.add(new GuiButtonToggled(2, mod.isDurationTextShadowEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 1 + 15 - 2));
        this.buttonList.add(new GuiButtonText(3, (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 2 + 15, "Duration Text Color"));
    	this.buttonList.add(new GuiButtonToggled(4, mod.isShowNameEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 3 + 15 - 2));
    	this.buttonList.add(new GuiButtonToggled(5, mod.isNameTextShadowEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 4 + 15 - 2));
        this.buttonList.add(new GuiButtonText(6, (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 5 + 15, "Name Text Color"));
        this.buttonList.add(new GuiButton(0, (this.width + 300) / 2 - 50 - 15, (this.height - 200) / 2 + 15, 50, 20, I18n.format("gui.done", new Object[0])));
    }
}
