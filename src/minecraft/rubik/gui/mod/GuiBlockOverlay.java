package rubik.gui.mod;

import java.awt.Color;
import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.Client;
import rubik.gui.GuiButtonToggled;
import rubik.gui.GuiDropClientScreen;
import rubik.gui.GuiSlider;
import rubik.gui.GuiText;
import rubik.mods.ModInstances;
import rubik.mods.impl.BlockOverlay;

public class GuiBlockOverlay extends GuiDropClientScreen {
	private final GuiScreen previousGuiScreen;
	private final BlockOverlay mod = ModInstances.getBlockOverlayMod();

	private GuiSlider sliderOutlineWidth;
	
	public GuiBlockOverlay(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}

	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		
    	drawRect((this.width - 300) / 2, (this.height - 200) / 2, (this.width - 300) / 2 + 300, (this.height - 200) / 2 + 200, new Color(0, 0, 0, 127).getRGB());
        
        this.drawScaledText("Block Overlay", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 15, 2.0D, 0xFFFFFFFF, false, false);
        this.drawText("Outline", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15, -1, false, false);
        this.drawText("Outline Width", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 2 + 15, -1, false, false);
        this.drawText(String.format("%.1f", mod.getOutlineWidth()), (this.width + 300) / 2 - mc.fontRendererObj.getStringWidth(String.format("%.1f", mod.getOutlineWidth())) - 15, (this.height - 200) / 2 + 30 + 15 * 2 + 15, -1, false, false);
        this.drawText("Overlay", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 3 + 15, -1, false, false);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
    	mod.setOutlineWidth(sliderOutlineWidth.getSliderPosition() * 5.0F);
    	
    	if (mod.getOutlineWidth() < 1.0F) {
    		mod.setOutlineWidth(1.0F);
    	}
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
            	this.mc.displayGuiScreen(this.previousGuiScreen);
            	break;
            case 1:
            	mod.setOutline(!mod.isOutlineEnabled());
            	this.initGui();
            	break;
            case 2:
            	mc.displayGuiScreen(new GuiBlockOverlayOutlineColor(this));
            	break;
            case 3:
            	mod.setOutlineWidth(sliderOutlineWidth.getSliderPosition() * 5.0F);
            	
            	if (mod.getOutlineWidth() < 1.0F) {
            		mod.setOutlineWidth(1.0F);
            	}
            	break;
            case 4:
            	mod.setOverlay(!mod.isOverlayEnabled());
            	this.initGui();
            	break;
            case 5:
            	mc.displayGuiScreen(new GuiBlockOverlayOverlayColor(this));
            	break;
        }
    }
	
	@Override
    public void initGui() {
        this.buttonList.clear();
        
    	this.buttonList.add(new GuiButtonToggled(1, mod.isOutlineEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15 - 2));
        this.buttonList.add(new GuiText(2, (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 1 + 15, "Outline Color"));
    	this.buttonList.add(sliderOutlineWidth = new GuiSlider(3, (this.width - 300) / 2 + 100, (this.height - 200) / 2 + 30 + 15 * 2 + 15 + 1, 100, 5, 0, 5, mod.getOutlineWidth()));
    	this.buttonList.add(new GuiButtonToggled(4, mod.isOverlayEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 3 + 15 - 2));
        this.buttonList.add(new GuiText(5, (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 4 + 15, "Overlay Color"));
        this.buttonList.add(new GuiButton(0, (this.width + 300) / 2 - 50 - 15, (this.height - 200) / 2 + 15, 50, 20, I18n.format("gui.done", new Object[0])));
    }
}
