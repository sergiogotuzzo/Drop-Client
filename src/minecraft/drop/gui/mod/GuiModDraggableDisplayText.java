package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.mods.ModDraggableDisplayText;
import drop.mods.ModDraggableDisplayText.Brackets;
import drop.mods.ModInstances;

public class GuiModDraggableDisplayText extends GuiMod {
	private static ModDraggableDisplayText mod;
	
	public GuiModDraggableDisplayText(GuiScreen previousGuiScreen, ModDraggableDisplayText mod) {
		super(previousGuiScreen, mod);
		
		this.mod = mod;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	super.drawScreen(mouseX, mouseY, partialTicks);
    	
    	this.writeOptionText("Show Background", 1);
    	this.writeOptionText("Text Shadow", 2);
    	this.writeOptionText("Text Color", 3);
    	this.writeOptionText("Brackets", 4);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
            case 1:
            	mod.setShowBackground(!mod.isShowBackgroundEnabled());
            	this.initGui();
            	break;
            case 2:
            	mod.setTextShadow(!mod.isTextShadowEnabled());
            	this.initGui();
            	break;
            case 3:
            	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getTextColor()));
            	break;
            case 4:
            	mod.setBrackets(Brackets.fromId(mod.getBrackets() == Brackets.ANGULAR ? Brackets.NONE.getId() : mod.getBrackets().getId() + 1));
            	this.initGui();
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
		
		this.buttonList.add(this.createGuiButtonToggled(1, mod.isShowBackgroundEnabled(), 1));
		this.buttonList.add(this.createGuiButtonToggled(2, mod.isTextShadowEnabled(), 2));
		this.buttonList.add(this.createGuiRect(3, mod.getTextColor().getRGB(), 3));
		this.buttonList.add(this.createGuiText(4, mod.getBrackets().getName(), 4));
    }
}
