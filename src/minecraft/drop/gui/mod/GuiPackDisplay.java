package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.mods.ModInstances;
import drop.mods.impl.PackDisplay;

public class GuiPackDisplay extends GuiMod {
	private static final PackDisplay mod = ModInstances.getPackDisplayMod();
	
	public GuiPackDisplay(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	super.drawScreen(mouseX, mouseY, partialTicks);
        
        this.writeOptionText("Name Text Color", 1);
        this.writeOptionText("Name Text Shadow", 2);
        this.writeOptionText("Show Description", 3);
        this.writeOptionText("Description Text Color", 4);
        this.writeOptionText("Description Text Shadow", 5);
        this.writeOptionText("Show Background", 6);
        this.writeOptionText("Show Icon", 7);
        this.writeOptionText("Show All Selected Packs", 8);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
            case 1:
            	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getNameTextColor(), "nameTextColor", "nameTextChroma", "Name Text Color"));
            	break;
            case 2:
            	mod.setNameTextShadow(!mod.isNameTextShadowToggled());
            	this.initGui();
            	break;
            case 3:
            	mod.setShowDescription(!mod.isShowDescriptionToggled());
            	this.initGui();
            	break;
            case 4:
            	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getDescriptionTextColor(), "descriptionTextColor", "descriptionTextChroma", "Description Text Color"));
            	break;
            case 5:
            	mod.setDescriptionTextShadow(!mod.isDescriptionTextShadowToggled());
            	this.initGui();
            	break;
            case 6:
            	mod.setShowBackground(!mod.isShowBackgroundToggled());
            	this.initGui();
            	break;
            case 7:
            	mod.setShowIcon(!mod.isShowIconToggled());
            	this.initGui();
            	break;
            case 8:
            	mod.setShowAllSelectedPacks(!mod.isShowAllSelectedPacksToggled());
            	this.initGui();
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
        
        this.buttonList.add(this.createGuiRect(1, mod.getNameTextColor().getRGB(), 1));
    	this.buttonList.add(this.createGuiCheckBox(2, mod.isNameTextShadowToggled(), 2));
    	this.buttonList.add(this.createGuiCheckBox(3, mod.isShowDescriptionToggled(), 3));
        this.buttonList.add(this.createGuiRect(4, mod.getDescriptionTextColor().getRGB(), 4));
        this.buttonList.add(this.createGuiCheckBox(5, mod.isDescriptionTextShadowToggled(), 5));
    	this.buttonList.add(this.createGuiCheckBox(6, mod.isShowBackgroundToggled(), 6));
    	this.buttonList.add(this.createGuiCheckBox(7, mod.isShowIconToggled(), 7));
    	this.buttonList.add(this.createGuiCheckBox(8, mod.isShowAllSelectedPacksToggled(), 8));
    }
}
