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

    	this.writeOptionText("Text Color", 1);
    	this.writeOptionText("Text Shadow", 2);
    	this.writeOptionText("Show Background", 3);
    	this.writeOptionText("Brackets", 4);
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
            	mod.setShowBackground(!mod.isShowBackgroundToggled());
            	this.initGui();
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
		
		this.buttonList.add(this.createGuiRect(1, mod.getTextColor().getRGB(), 1));
		this.buttonList.add(this.createGuiButtonToggled(2, mod.isTextShadowToggled(), 2));
		this.buttonList.add(this.createGuiButtonToggled(3, mod.isShowBackgroundToggled(), 3));
		this.buttonList.add(this.createGuiText(4, mod.getBrackets().getName(), true, 4));
    }
}
