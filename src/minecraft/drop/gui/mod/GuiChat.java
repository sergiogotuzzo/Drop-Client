package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.GuiDropClientScreen;
import drop.gui.GuiSlider;
import drop.mods.ModInstances;
import drop.mods.impl.Chat;

public class GuiChat extends GuiMod {
	private static final Chat mod = ModInstances.getChatMod();

	private GuiSlider sliderBackgroundOpacity;
	
	public GuiChat(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
	}

	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		this.writeOptionText("Chat Height Fix", 1);
		this.writeOptionText("Text Shadow", 2);
		this.writeOptionText("Background Opacity", 3);
		this.writeOptionValue(String.valueOf(mod.getBackgroundOpacity()), 3);
    }
    
    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
    	mod.setBackgroundOpacity((int) (sliderBackgroundOpacity.getSliderPosition() * 127.0F));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
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
		
		this.buttonList.add(this.createGuiButtonToggled(1, mod.isChatHeightFixEnabled(), 1));
		this.buttonList.add(this.createGuiButtonToggled(2, mod.isTextShadowEnabled(), 2));
		this.buttonList.add(sliderBackgroundOpacity = this.createGuiSlider(3, 0.0F, 127.0F, mod.getBackgroundOpacity(), 40, 3));
    }
}
