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
        this.writeOptionText("Show Mouse", 5);
        this.writeOptionText("Show Spacebar", 6);
        this.writeOptionText("Show Movement Keys", 7);
        this.writeOptionText("Use Arrows", 8);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
	        case 1:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getTextColor()));
	        	break;
	        case 2:
	        	mod.setTextShadow(!mod.isTextShadowEnabled());
	        	this.initGui();
	        	break;
	        case 3:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getPressedTextColor(), "pressedTextColor", "pressedTextChroma", "Pressed Text Color"));
	        	break;
	        case 4:
	        	mod.setPressedTextShadow(!mod.isPressedTextShadowEnabled());
	        	this.initGui();
	        	break;
            case 5:
            	mod.setShowMouse(!mod.isShowMouseEnabled());
            	this.initGui();
            	break;
            case 6:
            	mod.setShowSpacebar(!mod.isShowSpacebarEnabled());
            	this.initGui();
            	break;
            case 7:
            	mod.setShowMovementKeys(!mod.isShowMovementKeysEnabled());
            	this.initGui();
            	break;
            case 8:
            	mod.setUseArrows(!mod.isUseArrowsEnabled());
            	this.initGui();
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();

        this.buttonList.add(this.createGuiRect(1, mod.getTextColor().getRGB(), 1));
    	this.buttonList.add(this.createGuiButtonToggled(2, mod.isTextShadowEnabled(), 2));
        this.buttonList.add(this.createGuiRect(3, mod.getPressedTextColor().getRGB(), 3));
    	this.buttonList.add(this.createGuiButtonToggled(4, mod.isPressedTextShadowEnabled(), 4));
    	this.buttonList.add(this.createGuiButtonToggled(5, mod.isShowMouseEnabled(), 5));
    	this.buttonList.add(this.createGuiButtonToggled(6, mod.isShowSpacebarEnabled(), 6));
    	this.buttonList.add(this.createGuiButtonToggled(7, mod.isShowMovementKeysEnabled(), 7));
    	this.buttonList.add(this.createGuiButtonToggled(8, mod.isUseArrowsEnabled(), 8));
    }
}
