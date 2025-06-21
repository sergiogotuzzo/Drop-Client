package drop.gui.mod.armorstatus;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.mod.GuiMod;
import drop.mods.ModInstances;
import drop.mods.impl.ArmorStatus;

public class GuiArmorStatus extends GuiMod {
	private static final ArmorStatus mod = ModInstances.getArmorStatusMod();
	
	public GuiArmorStatus(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	super.drawScreen(mouseX, mouseY, partialTicks);

        if (mod.isShowPercentageToggled() || mod.isShowDamageToggled()) {
        	this.writeOptionText("Text Color", 1);
            this.writeOptionText("Text Shadow", 2);
        }
    	
    	if (!mod.isShowPercentageToggled() && !mod.isShowDamageToggled()) {
        	this.writeOptionText("Show Percentage", 1);
            this.writeOptionText("Show Damage", 2);
    	} else if (mod.isShowPercentageToggled()) {
        	this.writeOptionText("Show Percentage", 3);
    	} else if (!mod.isShowPercentageToggled() && mod.isShowDamageToggled()) {
    		this.writeOptionText("Show Damage", 3);
            this.writeOptionText("Show Max Damage", 4);
    	}
    	
        this.writeOptionText("Equipped Item", mod.isShowPercentageToggled() ? 4 : mod.isShowDamageToggled() ? 5 : 3);
        this.writeOptionText("Damage Overlays", mod.isShowPercentageToggled() ? 5 : mod.isShowDamageToggled() ? 6 : 4);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
	        case 1:
	        	mc.displayGuiScreen(new GuiArmorStatusTextColor(this));
	        	break;
	        case 2:
	        	mod.setTextShadow(!mod.isTextShadowToggled());
	        	this.initGui();
	        	break;
            case 3:
            	mod.setShowPercentage(!mod.isShowPercentageToggled());
            	this.initGui();
            	break;
            case 4:
            	mod.setShowDamage(!mod.isShowDamageToggled());
            	this.initGui();
            	break;
            case 5:
            	mod.setShowMaxDamage(!mod.isShowMaxDamageToggled());
            	this.initGui();
            	break;
            case 6:
            	mod.setEquippedItem(!mod.isEquippedItemToggled());
            	this.initGui();
            	break;
            case 7:
            	mod.setDamageOverlays(!mod.isDamageOverlaysToggled());
            	this.initGui();
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();

		if (mod.isShowPercentageToggled() || mod.isShowDamageToggled()) {
			this.buttonList.add(this.createGuiRect(1, mod.getTextColor().getRGB(), 1));
			this.buttonList.add(this.createGuiCheckBox(2, mod.isTextShadowToggled(), 2));
		}
		
		if (!mod.isShowPercentageToggled() && !mod.isShowDamageToggled()) {
			this.buttonList.add(this.createGuiCheckBox(3, mod.isShowPercentageToggled(), 1));
			this.buttonList.add(this.createGuiCheckBox(4, mod.isShowDamageToggled(), 2));
    	} else if (mod.isShowPercentageToggled()) {
    		this.buttonList.add(this.createGuiCheckBox(3, mod.isShowPercentageToggled(), 3));
    	} else if (!mod.isShowPercentageToggled() && mod.isShowDamageToggled()) {
    		this.buttonList.add(this.createGuiCheckBox(4, mod.isShowDamageToggled(), 3));
			this.buttonList.add(this.createGuiCheckBox(5, mod.isShowMaxDamageToggled(), 4));
    	}
		
		this.buttonList.add(this.createGuiCheckBox(6, mod.isEquippedItemToggled(), mod.isShowPercentageToggled() ? 4 : mod.isShowDamageToggled() ? 5 : 3));
		this.buttonList.add(this.createGuiCheckBox(7, mod.isDamageOverlaysToggled(), mod.isShowPercentageToggled() ? 5 : mod.isShowDamageToggled() ? 6 : 4));
    }
}
