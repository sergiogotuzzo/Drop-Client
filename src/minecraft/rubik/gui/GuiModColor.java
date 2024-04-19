package rubik.gui;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.mods.ModDraggable;
import rubik.mods.ModInstances;

public class GuiModColor extends GuiScreen {
	private final GuiScreen previousGuiScreen;
	private ModDraggable mod;
	
	private GuiSlider sliderRed;
    private GuiSlider sliderGreen;
    private GuiSlider sliderBlue;
    private GuiSlider sliderAlpha;
	
	public GuiModColor(GuiScreen previousGuiScreen, ModDraggable mod) {
		this.previousGuiScreen = previousGuiScreen;
		this.mod = mod;
	}
	
	@Override
    public void initGui()
    {
        this.buttonList.clear();
        
        int i = -16;
        int j = 50;
        
		this.buttonList.add(sliderRed = new GuiSlider(1, this.width / 2 - 100 + j, this.height / 4 + 24 + i, 100, 20, "Red", 0, 255, 255));
        this.buttonList.add(sliderGreen = new GuiSlider(2, this.width / 2 - 100 + j, this.height / 4 + 48 + i, 100, 20, "Green", 0, 255, 255));
        this.buttonList.add(sliderBlue = new GuiSlider(3, this.width / 2 - 100 + j, this.height / 4 + 72 + i, 100, 20, "Blue", 0, 255, 255));
        this.buttonList.add(sliderAlpha = new GuiSlider(4, this.width / 2 - 100 + j, this.height / 4 + 96 + i, 100, 20, "Alpha" , 0, 255, 255));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
        switch (button.id)
        {
            case 0:
            	this.mc.displayGuiScreen(this.previousGuiScreen);
            	break;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, I18n.format("Mod Color", new Object[0]), this.width / 2, 40, 0xFFFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
