package rubik.gui.mods.coordinatesdisplay;

import java.awt.Color;
import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.gui.GuiSlider;
import rubik.mods.ModInstances;
import rubik.mods.impl.CoordinatesDisplay;

public class GuiColor extends GuiScreen {
	private final GuiScreen previousGuiScreen;
	private CoordinatesDisplay mod = ModInstances.getCoordinatesDisplayMod();
	
	private GuiSlider sliderRed;
    private GuiSlider sliderGreen;
    private GuiSlider sliderBlue;
    private GuiSlider sliderAlpha;
	
	public GuiColor(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}
	
	@Override
    public void initGui()
    {
        this.buttonList.clear();
        
        int i = -16;
        int j = 50;
        
		this.buttonList.add(sliderRed = new GuiSlider(1, this.width / 2 - 100 + j, this.height / 4 + 24 + i, 100, 20, "Red", 0, 255, mod.getColor().getRed()));
        this.buttonList.add(sliderGreen = new GuiSlider(2, this.width / 2 - 100 + j, this.height / 4 + 48 + i, 100, 20, "Green", 0, 255, mod.getColor().getGreen()));
        this.buttonList.add(sliderBlue = new GuiSlider(3, this.width / 2 - 100 + j, this.height / 4 + 72 + i, 100, 20, "Blue", 0, 255, mod.getColor().getBlue()));
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
            case 1:
            	mod.getColorManager().setRed((int) (sliderRed.func_175217_d() * 255.0F));
            	break;
            case 2:
            	mod.getColorManager().setGreen((int) (sliderGreen.func_175217_d() * 255.0F));
            	break;
            case 3:
            	mod.getColorManager().setBlue((int) (sliderBlue.func_175217_d() * 255.0F));
            	break;
        }
    }
    
    @Override
    public void mouseClickMove(final int mouseX, final int mouseY, final int clickedMouseButton, final long timeSinceLastClick) {
    	mod.getColorManager().setRed((int) (sliderRed.func_175217_d() * 255.0F));
    	mod.getColorManager().setGreen((int) (sliderGreen.func_175217_d() * 255.0F));
    	mod.getColorManager().setBlue((int) (sliderBlue.func_175217_d() * 255.0F));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, I18n.format("Coordinates Display Mod Color", new Object[0]), this.width / 2, 40, 0xFFFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
