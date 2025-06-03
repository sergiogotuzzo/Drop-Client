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
import drop.gui.GuiText;
import drop.mods.ModInstances;
import drop.mods.impl.Keystrokes;

public class GuiKeystrokes extends GuiDropClientScreen {
	private final GuiScreen previousGuiScreen;
	private final Keystrokes mod = ModInstances.getKeystrokesMod();
	
	public GuiKeystrokes(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	this.drawDefaultBackground();
    	
    	drawRect((this.width - 300) / 2, (this.height - 200) / 2, (this.width - 300) / 2 + 300, (this.height - 200) / 2 + 200, new Color(0, 0, 0, 127).getRGB());
        
        this.drawScaledText("Keystrokes", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 15, 2.0D, 0xFFFFFFFF, false, false);
        this.drawText("Show Mouse", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15, -1, false, false);
        this.drawText("Show Spacebar", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 1 + 15, -1, false, false);
        this.drawText("Show Movement Keys", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 2 + 15, -1, false, false);
        this.drawText("Use Arrows", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 3 + 15, -1, false, false);
        this.drawText("Text Shadow", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 4 + 15, -1, false, false);
        this.drawText("Text Color", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 5 + 15, -1, false, false);
        this.drawText("Text Shadow (Pressed)", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 6 + 15, -1, false, false);
        this.drawText("Text Color (Pressed)", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 7 + 15, -1, false, false);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
            	this.mc.displayGuiScreen(this.previousGuiScreen);
            	break;
            case 1:
            	mod.setShowMouse(!mod.isShowMouseEnabled());
            	this.initGui();
            	break;
            case 2:
            	mod.setShowSpacebar(!mod.isShowSpacebarEnabled());
            	this.initGui();
            	break;
            case 3:
            	mod.setShowMovementKeys(!mod.isShowMovementKeysEnabled());
            	this.initGui();
            	break;
            case 4:
            	mod.setUseArrows(!mod.isUseArrowsEnabled());
            	this.initGui();
            	break;
            case 5:
            	mod.setTextShadow(!mod.isTextShadowEnabled());
            	this.initGui();
            	break;
            case 6:
            	mc.displayGuiScreen(new GuiModColor(this, mod.getTextColor(), mod, "Keystrokes"));
            	break;
            case 7:
            	mod.setPressedTextShadow(!mod.isPressedTextShadowEnabled());
            	this.initGui();
            	break;
            case 8:
            	mc.displayGuiScreen(new GuiModColor(this, mod.getPressedTextColor(), mod, "pressedTextColor", "pressedTextChroma", "Keystrokes", "Pressed Text Color", false));
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();

        this.buttonList.clear();
        
    	this.buttonList.add(new GuiButtonToggled(1, mod.isShowMouseEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15 - 2));
    	this.buttonList.add(new GuiButtonToggled(2, mod.isShowSpacebarEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 1 + 15 - 2));
    	this.buttonList.add(new GuiButtonToggled(3, mod.isShowMovementKeysEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 2 + 15 - 2));
    	this.buttonList.add(new GuiButtonToggled(4, mod.isUseArrowsEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 3 + 15 - 2));
    	this.buttonList.add(new GuiButtonToggled(5, mod.isTextShadowEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 4 + 15 - 2));
        this.buttonList.add(new GuiRect(6, (this.width + 300) / 2 - 15 - 13, (this.height - 200) / 2 + 30 + 15 * 5 + 15 - 2 * 2, mod.getTextColor().getRGB()));
    	this.buttonList.add(new GuiButtonToggled(7, mod.isPressedTextShadowEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 6 + 15 - 2));
        this.buttonList.add(new GuiRect(8, (this.width + 300) / 2 - 15 - 13, (this.height - 200) / 2 + 30 + 15 * 7 + 15 - 2 * 2, mod.getPressedTextColor().getRGB()));
        this.buttonList.add(new GuiButton(0, (this.width + 300) / 2 - 50 - 15, (this.height - 200) / 2 + 15, 50, 20, I18n.format("gui.done", new Object[0])));
    }
}
