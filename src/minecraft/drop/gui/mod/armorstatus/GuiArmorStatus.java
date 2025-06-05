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

        this.writeOptionText("Text Color", 1);
        this.writeOptionText("Text Shadow", 2);
    	this.writeOptionText("Show Percentage", 3);
        this.writeOptionText("Show Damage", 4);
        this.writeOptionText("Show Max Damage", 5);
        this.writeOptionText("Equipped Item", 6);
        this.writeOptionText("Damage Overlays", 7);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
	        case 1:
	        	mc.displayGuiScreen(new GuiArmorStatusTextColor(this));
	        	break;
	        case 2:
	        	mod.setTextShadow(!mod.isTextShadowEnabled());
	        	this.initGui();
	        	break;
            case 3:
            	mod.setShowPercentage(!mod.isShowPercentageEnabled());
            	this.initGui();
            	break;
            case 4:
            	mod.setShowDamage(!mod.isShowDamageEnabled());
            	this.initGui();
            	break;
            case 5:
            	mod.setShowMaxDamage(!mod.isShowMaxDamageEnabled());
            	this.initGui();
            	break;
            case 6:
            	mod.setEquippedItem(!mod.isEquippedItemEnabled());
            	this.initGui();
            	break;
            case 7:
            	mod.setDamageOverlays(!mod.isDamageOverlaysEnabled());
            	this.initGui();
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();

		this.buttonList.add(this.createGuiRect(1, mod.getTextColor().getRGB(), 1));
		this.buttonList.add(this.createGuiButtonToggled(2, mod.isTextShadowEnabled(), 2));
		this.buttonList.add(this.createGuiButtonToggled(3, mod.isShowPercentageEnabled(), 3));
		this.buttonList.add(this.createGuiButtonToggled(4, mod.isShowDamageEnabled(), 4));
		this.buttonList.add(this.createGuiButtonToggled(5, mod.isShowMaxDamageEnabled(), 5));
		this.buttonList.add(this.createGuiButtonToggled(6, mod.isEquippedItemEnabled(), 6));
		this.buttonList.add(this.createGuiButtonToggled(7, mod.isDamageOverlaysEnabled(), 7));
    }
}
