package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.mods.ModInstances;
import drop.mods.impl.Bossbar;

public class GuiBossbar extends GuiMod {
	private static final Bossbar mod = ModInstances.getBossbarMod();
	
	public GuiBossbar(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	super.drawScreen(mouseX, mouseY, partialTicks);
    	
    	this.writeOptionText("Text Color", 1);
    	this.writeOptionText("Text Shadow", 2);
    	this.writeOptionText("Show Name", 3);
    	this.writeOptionText("Show Health", 4);
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
    			mod.setShowName(!mod.isShowNameToggled());
            	this.initGui();
            	break;
    		case 4:
    			mod.setShowHealth(!mod.isShowHealthToggled());
            	this.initGui();
            	break;
    	}
    }
	
	@Override
    public void initGui() {
		super.initGui();
        
		this.buttonList.add(this.createGuiRect(1, mod.getTextColor().getRGB(), 1));
		this.buttonList.add(this.createGuiButtonToggled(2, mod.isTextShadowToggled(), 2));
    	this.buttonList.add(this.createGuiButtonToggled(3, mod.isShowNameToggled(), 3));
    	this.buttonList.add(this.createGuiButtonToggled(4, mod.isShowHealthToggled(), 4));
    }
}