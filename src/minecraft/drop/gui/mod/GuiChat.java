package drop.gui.mod;

import java.awt.Color;
import java.io.IOException;

import drop.Client;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import drop.gui.GuiButtonToggled;
import drop.gui.GuiDropClientScreen;
import drop.gui.GuiSlider;
import drop.mods.ModInstances;
import drop.mods.impl.Chat;

public class GuiChat extends GuiDropClientScreen {
	private final GuiScreen previousGuiScreen;
	private final Chat mod = ModInstances.getChatMod();

	private GuiSlider sliderBackgroundOpacity;
	
	public GuiChat(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}

	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		
    	drawRect((this.width - 300) / 2, (this.height - 200) / 2, (this.width - 300) / 2 + 300, (this.height - 200) / 2 + 200, new Color(0, 0, 0, 127).getRGB());
        
        this.drawScaledText("Chat", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 15, 2.0D, 0xFFFFFFFF, false, false);
        this.drawText("Chat Height Fix", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15, -1, false, false);
        this.drawText("Text Shadow", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 1 + 15, -1, false, false);
        this.drawText("Background Opacity", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 2 + 15, -1, false, false);
        this.drawText(String.valueOf(mod.getBackgroundOpacity()), (this.width + 300) / 2 - mc.fontRendererObj.getStringWidth(String.valueOf(mod.getBackgroundOpacity())) - 15, (this.height - 200) / 2 + 30 + 15 * 2 + 15, -1, false, false);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
    	mod.setBackgroundOpacity((int) (sliderBackgroundOpacity.getSliderPosition() * 127.0F));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
            	this.mc.displayGuiScreen(this.previousGuiScreen);
            	break;
            case 1:
            	mod.setChatHeightFix(!mod.isChatHeightFixEnabled());
            	this.initGui();
            	break;
            case 2:
            	mod.setTextShadow(!mod.isTextShadowEnabled());
            	this.initGui();
            	break;
            case 3:
            	mod.setBackgroundOpacity((int) (sliderBackgroundOpacity.getSliderPosition() * 127.0F));
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();

        this.buttonList.clear();
        
    	this.buttonList.add(new GuiButtonToggled(1, mod.isChatHeightFixEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15 - 2));
    	this.buttonList.add(new GuiButtonToggled(2, mod.isTextShadowEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 1 + 15 - 2));
    	this.buttonList.add(sliderBackgroundOpacity = new GuiSlider(3, (this.width - 300) / 2 + 140, (this.height - 200) / 2 + 30 + 15 * 2 + 15 + 1, 100, 5, 0, 127, mod.getBackgroundOpacity()));
        this.buttonList.add(new GuiButton(0, (this.width + 300) / 2 - 50 - 15, (this.height - 200) / 2 + 15, 50, 20, I18n.format("gui.done", new Object[0])));
    }
}
