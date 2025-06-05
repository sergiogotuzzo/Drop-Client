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
        
        this.writeOptionText("Show Background", 1);
        this.writeOptionText("Name Text Shadow", 2);
        this.writeOptionText("Name Text Color", 3);
        this.writeOptionText("Description Text Shadow", 4);
        this.writeOptionText("Description Text Color", 5);
        this.writeOptionText("Show Icon", 6);
        this.writeOptionText("Show Description", 7);
        this.writeOptionText("Show All Selected Packs", 8);
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
            	mod.setNameTextShadow(!mod.isNameTextShadowEnabled());
            	this.initGui();
            	break;
            case 3:
            	mc.displayGuiScreen(new GuiModColor(this, mod.getNameTextColor(), mod, "nameTextColor", "nameTextChroma", "Pack Display", "Name Text Color", false));
            	break;
            case 4:
            	mod.setDescriptionTextShadow(!mod.isDescriptionTextShadowEnabled());
            	this.initGui();
            	break;
            case 5:
            	mc.displayGuiScreen(new GuiModColor(this, mod.getDescriptionTextColor(), mod, "descriptionTextColor", "descriptionTextChroma", "Pack Display", "Description Text Color", false));
            	break;
            case 6:
            	mod.setShowIcon(!mod.isShowIconToggled());
            	this.initGui();
            	break;
            case 7:
            	mod.setShowDescription(!mod.isShowDescriptionToggled());
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
        
    	this.buttonList.add(this.createGuiButtonToggled(1, mod.isShowBackgroundEnabled(), 1));
    	this.buttonList.add(this.createGuiButtonToggled(2, mod.isNameTextShadowEnabled(), 2));
        this.buttonList.add(this.createGuiRect(3, mod.getNameTextColor().getRGB(), 3));
        this.buttonList.add(this.createGuiButtonToggled(4, mod.isDescriptionTextShadowEnabled(), 4));
        this.buttonList.add(this.createGuiRect(5, mod.getDescriptionTextColor().getRGB(), 5));
    	this.buttonList.add(this.createGuiButtonToggled(6, mod.isShowIconToggled(), 6));
    	this.buttonList.add(this.createGuiButtonToggled(7, mod.isShowDescriptionToggled(), 7));
    	this.buttonList.add(this.createGuiButtonToggled(8, mod.isShowAllSelectedPacksToggled(), 8));
    }
}
