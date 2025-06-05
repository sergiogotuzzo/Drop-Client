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
            	mod.setTextShadow(!mod.isTextShadowEnabled());
            	this.initGui();
            	break;
            case 3:
            	mod.setShowBackground(!mod.isShowBackgroundEnabled());
            	this.initGui();
            	break;
            case 4:
            	mod.setShowBiome(!mod.isShowBiomeEnabled());
            	this.initGui();
            	break;
            case 5:
            	mod.setShowFacing(!mod.isShowFacingEnabled());
            	this.initGui();
            	break;
            case 6:
            	mod.setShowFacingTowards(!mod.isShowFacingTowardsEnabled());
            	this.initGui();
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
        
        this.buttonList.add(this.createGuiRect(1, mod.getTextColor().getRGB(), 1));
        this.buttonList.add(this.createGuiButtonToggled(2, mod.isTextShadowEnabled(), 2));
        this.buttonList.add(this.createGuiButtonToggled(3, mod.isShowBackgroundEnabled(), 3));
        this.buttonList.add(this.createGuiButtonToggled(4, mod.isShowBiomeEnabled(), 4));
        this.buttonList.add(this.createGuiButtonToggled(5, mod.isShowFacingEnabled(), 5));
        this.buttonList.add(this.createGuiButtonToggled(6, mod.isShowFacingTowardsEnabled(), 6));
    }
}
