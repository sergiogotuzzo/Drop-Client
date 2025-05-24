package rubik.gui.mod;

import java.awt.Color;
import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.Client;
import rubik.gui.GuiDropClientScreen;
import rubik.gui.GuiSlider;
import rubik.mods.ModInstances;
import rubik.mods.impl.TimeChanger;

public class GuiTimeChanger extends GuiDropClientScreen {
	private final GuiScreen previousGuiScreen;
	private final TimeChanger mod = ModInstances.getTimeChangerMod();

	private GuiSlider sliderTime;
	
	public GuiTimeChanger(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}

	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		
    	drawRect((this.width - 300) / 2, (this.height - 200) / 2, (this.width - 300) / 2 + 300, (this.height - 200) / 2 + 200, new Color(0, 0, 0, 127).getRGB());
        
        this.drawScaledText("Time Changer", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 15, 2.0D, 0xFFFFFFFF, false, false);
        this.drawText("Time", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15, -1, false, false);
        this.drawText(String.format("%.2f", mod.getTime()), (this.width + 300) / 2 - mc.fontRendererObj.getStringWidth(String.format("%.2f", mod.getTime())) - 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15, -1, false, false);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
    	mod.setTime(sliderTime.getSliderPosition());
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
            	this.mc.displayGuiScreen(this.previousGuiScreen);
            	break;
            case 1:
            	mod.setTime(sliderTime.getSliderPosition());
            	break;
        }
    }
	
	@Override
    public void initGui() {
        this.buttonList.clear();
        
    	this.buttonList.add(sliderTime = new GuiSlider(1, (this.width - 300) / 2 + 100, (this.height - 200) / 2 + 30 + 15 * 0 + 15 + 1, 100, 5, 0, 1, mod.getTime()));
        this.buttonList.add(new GuiButton(0, (this.width + 300) / 2 - 50 - 15, (this.height - 200) / 2 + 15, 50, 20, I18n.format("gui.done", new Object[0])));
    }
}
