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
    	
    	this.writeOptionText("Show Name", 1);
    	
    	if (mod.isShowNameToggled()) {
    		this.writeOptionText("Text Color", 2);
        	this.writeOptionText("Text Shadow", 3);
    	}
    	
    	this.writeOptionText("Show Health", mod.isShowNameToggled() ? 4 : 2);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
    	switch (button.id) {
			case 1:
				mod.setShowName(!mod.isShowNameToggled());
	        	this.initGui();
	        	break;
	    	case 2:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getTextColor()));
	        	break;
	        case 3:
	        	mod.setTextShadow(!mod.isTextShadowToggled());
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
        
    	this.buttonList.add(this.createGuiCheckBox(1, mod.isShowNameToggled(), 1));

		if (mod.isShowNameToggled()) {
			this.buttonList.add(this.createGuiRect(2, mod.getTextColor().getRGB(), 2));
			this.buttonList.add(this.createGuiCheckBox(3, mod.isTextShadowToggled(), 3));
		}
    	
    	this.buttonList.add(this.createGuiCheckBox(4, mod.isShowHealthToggled(), mod.isShowNameToggled() ? 4 : 2));
    }
}