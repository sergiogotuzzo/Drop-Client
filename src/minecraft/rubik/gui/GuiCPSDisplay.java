package rubik.gui;

import java.awt.Color;
import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.Client;
import rubik.events.EventTarget;
import rubik.mods.ModInstances;
import rubik.mods.impl.CPSDisplay;
import rubik.mods.impl.CPSDisplay.CPSMode;

public class GuiCPSDisplay extends GuiDropClientScreen {
	private final GuiScreen previousGuiScreen;
	private final CPSDisplay mod = ModInstances.getCPSDisplayMod();
	
	public GuiCPSDisplay(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	this.drawDefaultBackground();
    	
    	drawRect((this.width - 300) / 2, (this.height - 200) / 2, (this.width - 300) / 2 + 300, (this.height - 200) / 2 + 200, new Color(0, 0, 0, 127).getRGB());
        
        this.drawScaledText("CPS Display", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 15, 2.0D, 0xFFFFFFFF, false, false);
        this.drawText("Show Background", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15, -1, false, false);
        this.drawText("Mode", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 1 + 15, -1, false, false);
        this.drawText("Text Shadow", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 2 + 15, -1, false, false);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
            	this.mc.displayGuiScreen(this.previousGuiScreen);
            	break;
            case 1:
            	mod.setShowBackground(!mod.isShowBackgroundEnabled());
            	this.initGui();
            	break;
            case 2:
            	if (mod.getMode() == CPSMode.LEFT) {
            		mod.setMode(CPSMode.RIGHT.getIndex());
            	} else if (mod.getMode() == CPSMode.RIGHT) {
            		mod.setMode(CPSMode.LEFT_RIGHT.getIndex());
            	} else if (mod.getMode() == CPSMode.LEFT_RIGHT) {
            		mod.setMode(CPSMode.HIGHER.getIndex());
            	} else if (mod.getMode() == CPSMode.HIGHER) {
            		mod.setMode(CPSMode.LEFT.getIndex());
            	}
            	this.initGui();
            	break;
            case 3:
            	mod.setTextShadow(!mod.isTextShadowEnabled());
            	this.initGui();
            	break;
            case 4:
            	mc.displayGuiScreen(new GuiModDraggableTextColor(this, mod, "CPS Display"));
            	break;
        }
    }
	
	@Override
    public void initGui() {
        this.buttonList.clear();
        
    	this.buttonList.add(new GuiButtonToggled(1, mod.isShowBackgroundEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15 - 2));
        this.buttonList.add(new GuiButtonText(2, (this.width + 300) / 2 - mc.fontRendererObj.getStringWidth(mod.getMode().name().replace("LEFT_RIGHT", "LEFT / RIGHT")) - 15, (this.height - 200) / 2 + 30 + 15 * 1 + 15, mod.getMode().name().replace("LEFT_RIGHT", "LEFT / RIGHT")));
        this.buttonList.add(new GuiButtonToggled(3, mod.isTextShadowEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 2 + 15 - 2));
        this.buttonList.add(new GuiButtonText(4, (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 3 + 15, "Text Color"));
        this.buttonList.add(new GuiButton(0, (this.width + 300) / 2 - 50 - 15, (this.height - 200) / 2 + 15, 50, 20, I18n.format("gui.done", new Object[0])));
    }
}
