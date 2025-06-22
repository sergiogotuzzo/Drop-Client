package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.mods.ModInstances;
import drop.mods.impl.Keystrokes;

public class GuiKeystrokes extends GuiMod {
	private static final Keystrokes mod = ModInstances.getKeystrokesMod();
	
	public GuiKeystrokes(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	super.drawScreen(mouseX, mouseY, partialTicks);
    	
        this.writeOptionText("Text Color", 1);
        this.writeOptionText("Text Shadow", 2);
        this.writeOptionText("Text Color (Pressed)", 3);
        this.writeOptionText("Text Shadow (Pressed)", 4);
        this.writeOptionText("Background Color", 5);
        this.writeOptionText("Background Color (Pressed)", 6);
        this.writeOptionText("Show Mouse", 7);
        this.writeOptionText("Show Spacebar", 8);
        this.writeOptionText("Show Movement Keys", 9);
        
        if (mod.isShowMovementKeysToggled()) {
        	this.writeOptionText("Use Arrows", 10);
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
	        case 1:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getTextColor()));
	        	break;
	        case 2:
	        	mod.setTextShadow(!mod.isTextShadowToggled());
	        	this.initGui();
	        	break;
	        case 3:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getPressedTextColor(), "pressedTextColor", "pressedTextChroma", "Pressed Text Color"));
	        	break;
	        case 4:
	        	mod.setPressedTextShadow(!mod.isPressedTextShadowToggled());
	        	this.initGui();
	        	break;
            case 5:
            	mod.setShowMouse(!mod.isShowMouseToggled());
            	this.initGui();
            	break;
            case 6:
            	mod.setShowSpacebar(!mod.isShowSpacebarToggled());
            	this.initGui();
            	break;
            case 7:
            	mod.setShowMovementKeys(!mod.isShowMovementKeysToggled());
            	this.initGui();
            	break;
            case 8:
            	mod.setUseArrows(!mod.isUseArrowsToggled());
            	this.initGui();
            	break;
            case 9:
            	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getBackgroundColor(), "backgroundColor", "Background Color", true));
            	break;
            case 10:
            	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getPressedBackgroundColor(), "pressedBackgroundColor", "Background Color (Pressed)", true));
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();

        this.buttonList.add(this.createGuiRect(1, mod.getTextColor().getRGB(), 1));
    	this.buttonList.add(this.createGuiCheckBox(2, mod.isTextShadowToggled(), 2));
        this.buttonList.add(this.createGuiRect(3, mod.getPressedTextColor().getRGB(), 3));
    	this.buttonList.add(this.createGuiCheckBox(4, mod.isPressedTextShadowToggled(), 4));
        this.buttonList.add(this.createGuiRect(9, mod.getBackgroundColor().getRGB(), 5));
        this.buttonList.add(this.createGuiRect(10, mod.getPressedBackgroundColor().getRGB(), 6));
    	this.buttonList.add(this.createGuiCheckBox(5, mod.isShowMouseToggled(), 7));
    	this.buttonList.add(this.createGuiCheckBox(6, mod.isShowSpacebarToggled(), 8));
    	this.buttonList.add(this.createGuiCheckBox(7, mod.isShowMovementKeysToggled(), 9));

    	if (mod.isShowMovementKeysToggled()) {
        	this.buttonList.add(this.createGuiCheckBox(8, mod.isUseArrowsToggled(), 10));
    	}
	}
}
