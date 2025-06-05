package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.GuiSlider;
import drop.mods.ModInstances;
import drop.mods.impl.BlockOverlay;

public class GuiBlockOverlay extends GuiMod {
	private static final BlockOverlay mod = ModInstances.getBlockOverlayMod();

	private GuiSlider sliderOutlineWidth;
	
	public GuiBlockOverlay(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
	}

	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		this.writeOptionText("Outline", 1);
		this.writeOptionText("Outline Color", 2);
		this.writeOptionText("Outline Width", 3);
		this.writeOptionValue(String.format("%.1f", mod.getOutlineWidth()), 3);
		this.writeOptionText("Overlay", 4);
		this.writeOptionText("Overlay Color", 5);
    }
    
    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
    	mod.setOutlineWidth(sliderOutlineWidth.getSliderPosition() * 5.0F);
    	
    	if (mod.getOutlineWidth() < 0.4F) {
    		mod.setOutlineWidth(0.4F);
    	}
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
            case 1:
            	mod.setOutline(!mod.isOutlineEnabled());
            	this.initGui();
            	break;
            case 2:
            	mc.displayGuiScreen(new GuiModColor(this, mod.getOutlineColor(), mod, "outlineColor", "outlineChroma", "Block Overlay", "Outline Color", false));
            	break;
            case 3:
            	mod.setOutlineWidth(sliderOutlineWidth.getSliderPosition() * 5.0F);
            	
            	if (mod.getOutlineWidth() < 0.4F) {
            		mod.setOutlineWidth(0.4F);
            	}
            	break;
            case 4:
            	mod.setOverlay(!mod.isOverlayEnabled());
            	this.initGui();
            	break;
            case 5:
            	mc.displayGuiScreen(new GuiModColor(this, mod.getOverlayColor(), mod, "overlayColor", "overlayChroma", "Block Overlay", "Overlay Color", true));
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
		
    	this.buttonList.add(this.createGuiButtonToggled(1, mod.isOutlineEnabled(), 1));
        this.buttonList.add(this.createGuiRect(2, mod.getOutlineColor().getRGB(), 2));
    	this.buttonList.add(sliderOutlineWidth = this.createGuiSlider(3, 0.4F, 5.0F, mod.getOutlineWidth(), 3));
    	this.buttonList.add(this.createGuiButtonToggled(4, mod.isOverlayEnabled(), 4));
    	this.buttonList.add(this.createGuiRect(5, mod.getOverlayColor().getRGB(), 5));
    }
}
