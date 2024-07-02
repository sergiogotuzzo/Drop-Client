package rubik.gui.mods;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.Client;
import rubik.gui.GuiModColor;
import rubik.gui.GuiModColorWithAlpha;
import rubik.gui.GuiRubikClientScreen;
import rubik.mods.ModInstances;
import rubik.mods.impl.BlockOverlay;

public class GuiBlockOverlay extends GuiRubikClientScreen {
	private final GuiScreen previousGuiScreen;
	private final BlockOverlay mod = ModInstances.getBlockOverlayMod();
	
	public GuiBlockOverlay(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        
        this.drawCenteredString(this.fontRendererObj, I18n.format("Block Overlay Settings", new Object[0]), this.width / 2, 30, 0xFFFFFFFF);
        
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
            	mod.setOutline(!mod.isOutlineEnabled());
            	this.initGui();
            	break;
            case 3:
            	mod.setOutlineWidth((mod.getOutlineWidth() < 5 && mod.getOutlineWidth() >= 1) ? mod.getOutlineWidth() + 1 : 1);
            	this.initGui();
            	break;
            case 4:
            	this.mc.displayGuiScreen(new GuiModColor(this, mod.getOutlineColor(), this.mod, "outlineColor"));
            	break;
            case 5:
            	mod.setOverlay(!mod.isOverlayEnabled());
            	this.initGui();
            	break;
            case 6:
            	this.mc.displayGuiScreen(new GuiModColorWithAlpha(this, mod.getOverlayColor(), this.mod, "overlayColor"));
            	break;
        }
    }
	
	@Override
    public void initGui() {
        this.buttonList.clear();
        
        int i = -12;
        int j = -155;
        
        this.buttonList.add(new GuiButton(1, this.width / 2 + j, this.height / 6 + i + 24, 150, 20, I18n.format(mod.isEnabled() ? "Enabled" : "Disabled", new Object[0])));
        this.buttonList.add(new GuiButton(2, this.width / 2 + j + 160, this.height / 6 + i + 24, 150, 20, I18n.format("Outline: " + (mod.isOutlineEnabled() ? "ON" : "OFF"), new Object[0])));
        this.buttonList.add(new GuiButton(3, this.width / 2 + j, this.height / 6 + i + 48, 150, 20, I18n.format("Outline Width: " + mod.getOutlineWidth() + "x", new Object[0])));
        this.buttonList.add(new GuiButton(4, this.width / 2 + j + 160, this.height / 6 + i + 48, 150, 20, I18n.format("Outline Color", new Object[0])));
        this.buttonList.add(new GuiButton(5, this.width / 2 + j, this.height / 6 + i + 72, 150, 20, I18n.format("Overlay: " + (mod.isOverlayEnabled() ? "ON" : "OFF"), new Object[0])));
        this.buttonList.add(new GuiButton(6, this.width / 2 + j + 160, this.height / 6 + i + 72, 150, 20, I18n.format("Overlay Color", new Object[0])));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
    }
}
