package rubik.gui;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.Client;
import rubik.mods.ModInstances;
import rubik.mods.impl.Scoreboard;

public class GuiScoreboardSettings extends GuiDropClientScreen {
	private final GuiScreen previousGuiScreen;
	private final Scoreboard mod = ModInstances.getScoreboardMod();
	
	private GuiSlider sliderBackgroundOpacity;
	
	public GuiScoreboardSettings(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        
        this.drawCenteredString(this.fontRendererObj, "Scoreboard Settings...", this.width / 2, 20, 0xFFFFFFFF);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
    	mod.setBackgroundOpacity((int) (sliderBackgroundOpacity.func_175217_d() * 127.0F));
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
            	mod.setHideNumbers(!mod.isHideNumbersEnabled());
            	this.initGui();
            	break;
            case 3:
            	mod.setTextShadow(!mod.isTextShadowEnabled());
            	this.initGui();
            	break;
            case 4:
            	mod.setBackgroundOpacity((int) (sliderBackgroundOpacity.func_175217_d() * 127.0F));
            	break;
        }
    }
	
	@Override
    public void initGui() {
        this.buttonList.clear();
        
        int i = 0;
        int j = -155;
        
        this.buttonList.add(new GuiButton(1, this.width / 2 + j, this.height / 6 + i, 150, 20, "Scoreboard: " + (mod.isEnabled() ? "Shown" : "Hidden")));
        this.buttonList.add(new GuiButton(2, this.width / 2 + j + 160, this.height / 6 + i, 150, 20, "Numbers: " + (mod.isHideNumbersEnabled() ? "Hidden" : "Shown")));
        this.buttonList.add(new GuiButton(3, this.width / 2 + j, this.height / 6 + i + 24, 150, 20, "Text Shadow: " + (mod.isTextShadowEnabled() ? "ON" : "OFF")));
        this.buttonList.add(sliderBackgroundOpacity = new GuiSlider(4, this.width / 2 + j + 160, this.height / 6 + i + 24, 150, 20, "Background Opacity", 0, 127, mod.getBackgroundOpacity()));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
    }
}
