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
    	
        this.writeOptionText("Show Mouse", 1);
        this.writeOptionText("Show Spacebar", 2);
        this.writeOptionText("Show Movement Keys", 3);
        this.writeOptionText("Use Arrows", 4);
        this.writeOptionText("Text Shadow", 5);
        this.writeOptionText("Text Color", 6);
        this.writeOptionText("Text Shadow (Pressed)", 7);
        this.writeOptionText("Text Color (Pressed)", 8);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
            case 1:
            	mod.setShowMouse(!mod.isShowMouseEnabled());
            	this.initGui();
            	break;
            case 2:
            	mod.setShowSpacebar(!mod.isShowSpacebarEnabled());
            	this.initGui();
            	break;
            case 3:
            	mod.setShowMovementKeys(!mod.isShowMovementKeysEnabled());
            	this.initGui();
            	break;
            case 4:
            	mod.setUseArrows(!mod.isUseArrowsEnabled());
            	this.initGui();
            	break;
            case 5:
            	mod.setTextShadow(!mod.isTextShadowEnabled());
            	this.initGui();
            	break;
            case 6:
            	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getTextColor()));
            	break;
            case 7:
            	mod.setPressedTextShadow(!mod.isPressedTextShadowEnabled());
            	this.initGui();
            	break;
            case 8:
            	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getPressedTextColor(), "pressedTextColor", "pressedTextChroma", "Pressed Text Color"));
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
        
    	this.buttonList.add(this.createGuiButtonToggled(1, mod.isShowMouseEnabled(), 1));
    	this.buttonList.add(this.createGuiButtonToggled(2, mod.isShowSpacebarEnabled(), 2));
    	this.buttonList.add(this.createGuiButtonToggled(3, mod.isShowMovementKeysEnabled(), 3));
    	this.buttonList.add(this.createGuiButtonToggled(4, mod.isUseArrowsEnabled(), 4));
    	this.buttonList.add(this.createGuiButtonToggled(5, mod.isTextShadowEnabled(), 5));
        this.buttonList.add(this.createGuiRect(6, mod.getTextColor().getRGB(), 6));
    	this.buttonList.add(this.createGuiButtonToggled(7, mod.isPressedTextShadowEnabled(), 7));
        this.buttonList.add(this.createGuiRect(8, mod.getPressedTextColor().getRGB(), 8));
    }
}
