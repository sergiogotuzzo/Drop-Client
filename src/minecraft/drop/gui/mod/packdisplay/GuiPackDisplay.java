package drop.gui.mod.packdisplay;

import java.awt.Color;
import java.io.IOException;

import drop.Client;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import drop.gui.GuiButtonToggled;
import drop.gui.GuiDropClientScreen;
import drop.gui.GuiRect;
import drop.gui.mod.GuiModDraggableTextColor;
import drop.mods.ModInstances;
import drop.mods.impl.PackDisplay;

public class GuiPackDisplay extends GuiDropClientScreen {
	private final GuiScreen previousGuiScreen;
	private final PackDisplay mod = ModInstances.getPackDisplayMod();
	
	public GuiPackDisplay(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	this.drawDefaultBackground();
    	
    	drawRect((this.width - 300) / 2, (this.height - 200) / 2, (this.width - 300) / 2 + 300, (this.height - 200) / 2 + 200, new Color(0, 0, 0, 127).getRGB());
        
        this.drawScaledText("Pack Display", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 15, 2.0D, 0xFFFFFFFF, false, false);
        this.drawText("Show Background", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15, -1, false, false);
        this.drawText("Name Text Shadow", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 1 + 15, -1, false, false);
        this.drawText("Name Text Color", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 2 + 15, -1, false, false);
        this.drawText("Description Text Shadow", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 3 + 15, -1, false, false);
        this.drawText("Description Text Color", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 4 + 15, -1, false, false);
        this.drawText("Show Icon", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 5 + 15, -1, false, false);
        this.drawText("Show Description", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 6 + 15, -1, false, false);

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
            	mod.setNameTextShadow(!mod.isNameTextShadowEnabled());
            	this.initGui();
            	break;
            case 3:
            	mc.displayGuiScreen(new GuiPackDisplayNameTextColor(this));
            	break;
            case 4:
            	mod.setDescriptionTextShadow(!mod.isDescriptionTextShadowEnabled());
            	this.initGui();
            	break;
            case 5:
            	mc.displayGuiScreen(new GuiPackDisplayDescriptionTextColor(this));
            	break;
            case 6:
            	mod.setShowIcon(!mod.isShowIconToggled());
            	this.initGui();
            	break;
            case 7:
            	mod.setShowDescription(!mod.isShowDescriptionToggled());
            	this.initGui();
            	break;
        }
    }
	
	@Override
    public void initGui() {
        this.buttonList.clear();
        
    	this.buttonList.add(new GuiButtonToggled(1, mod.isShowBackgroundEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15 - 2));
    	this.buttonList.add(new GuiButtonToggled(2, mod.isNameTextShadowEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 1 + 15 - 2));
        this.buttonList.add(new GuiRect(3, (this.width + 300) / 2 - 15 - 13, (this.height - 200) / 2 + 30 + 15 * 2 + 15 - 2 * 2, mod.getNameTextColor().getRGB()));
        this.buttonList.add(new GuiButtonToggled(4, mod.isDescriptionTextShadowEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 3 + 15 - 2));
        this.buttonList.add(new GuiRect(5, (this.width + 300) / 2 - 15 - 13, (this.height - 200) / 2 + 30 + 15 * 4 + 15 - 2 * 2, mod.getDescriptionTextColor().getRGB()));
    	this.buttonList.add(new GuiButtonToggled(6, mod.isShowIconToggled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 5 + 15 - 2));
    	this.buttonList.add(new GuiButtonToggled(7, mod.isShowDescriptionToggled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 6 + 15 - 2));
        this.buttonList.add(new GuiButton(0, (this.width + 300) / 2 - 50 - 15, (this.height - 200) / 2 + 15, 50, 20, I18n.format("gui.done", new Object[0])));
    }
}
