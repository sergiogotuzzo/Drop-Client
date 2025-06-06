package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.mods.ModInstances;
import drop.mods.impl.CoordinatesDisplay;

public class GuiCoordinatesDisplay extends GuiMod {
	private static final CoordinatesDisplay mod = ModInstances.getCoordinatesDisplayMod();
	
	public GuiCoordinatesDisplay(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	super.drawScreen(mouseX, mouseY, partialTicks);

    	this.writeOptionText("Text Color", 1);
    	this.writeOptionText("Text Shadow", 2);
    	this.writeOptionText("Show Background", 3);
    	this.writeOptionText("Show Biome", 4);
    	this.writeOptionText("Show Facing", 5);
    	this.writeOptionText("Show Facing Towards", 6);
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
            	mod.setShowBiome(!mod.isShowBiomeToggled());
            	this.initGui();
            	break;
            case 5:
            	mod.setShowFacing(!mod.isShowFacingToggled());
            	this.initGui();
            	break;
            case 6:
            	mod.setShowFacingTowards(!mod.isShowFacingTowardsToggled());
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
        this.buttonList.add(this.createGuiButtonToggled(4, mod.isShowBiomeToggled(), 4));
        this.buttonList.add(this.createGuiButtonToggled(5, mod.isShowFacingToggled(), 5));
        this.buttonList.add(this.createGuiButtonToggled(6, mod.isShowFacingTowardsToggled(), 6));
    }
}
