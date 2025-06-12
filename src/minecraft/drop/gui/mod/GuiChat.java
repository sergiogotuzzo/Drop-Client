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
		this.writeOptionText("Compact Chat", 2);
		this.writeOptionText("Text Shadow", 3);
		this.writeOptionText("Background Opacity", 4);
		this.writeOptionValue(String.valueOf(mod.getBackgroundOpacity()), 4);
    }
    
    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
    	mod.setBackgroundOpacity((int) (sliderBackgroundOpacity.getSliderPosition() * 255.0F));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
	        case 1:
	        	mod.setChatHeightFix(!mod.isChatHeightFixToggled());
	        	this.initGui();
	        	break;
	        case 2:
	        	mod.setCompactChat(!mod.isCompactChatToggled());
	        	this.initGui();
	        	break;
            case 3:
            	mod.setTextShadow(!mod.isTextShadowToggled());
            	this.initGui();
            	break;
            case 4:
            	mod.setBackgroundOpacity((int) (sliderBackgroundOpacity.getSliderPosition() * 255.0F));
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
		
		this.buttonList.add(this.createGuiCheckBox(1, mod.isChatHeightFixToggled(), 1));
		this.buttonList.add(this.createGuiCheckBox(2, mod.isCompactChatToggled(), 2));
		this.buttonList.add(this.createGuiCheckBox(3, mod.isTextShadowToggled(), 3));
		this.buttonList.add(sliderBackgroundOpacity = this.createGuiSlider(4, 255.0F, mod.getBackgroundOpacity(), 5));
    }
}
