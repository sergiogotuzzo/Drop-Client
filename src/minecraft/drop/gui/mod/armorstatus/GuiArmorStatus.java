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
    	
    	this.writeOptionText("Show Percentage", 1);
        this.writeOptionText("Show Damage", 2);
        this.writeOptionText("Show Max Damage", 3);
        this.writeOptionText("Equipped Item", 4);
        this.writeOptionText("Damage Overlays", 5);
        this.writeOptionText("Text Shadow", 6);
        this.writeOptionText("Text Color", 7);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
            case 1:
            	mod.setShowPercentage(!mod.isShowPercentageEnabled());
            	this.initGui();
            	break;
            case 4:
            	mod.setEquippedItem(!mod.isEquippedItemEnabled());
            	this.initGui();
            	break;
            case 2:
            	mod.setShowDamage(!mod.isShowDamageEnabled());
            	this.initGui();
            	break;
            case 5:
            	mod.setDamageOverlays(!mod.isDamageOverlaysEnabled());
            	this.initGui();
            	break;
            case 3:
            	mod.setShowMaxDamage(!mod.isShowMaxDamageEnabled());
            	this.initGui();
            	break;
            case 6:
            	mod.setTextShadow(!mod.isTextShadowEnabled());
            	this.initGui();
            	break;
            case 7:
            	mc.displayGuiScreen(new GuiArmorStatusTextColor(this));
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
		
		this.buttonList.add(this.createGuiButtonToggled(1, mod.isShowPercentageEnabled(), 1));
		this.buttonList.add(this.createGuiButtonToggled(2, mod.isShowDamageEnabled(), 2));
		this.buttonList.add(this.createGuiButtonToggled(3, mod.isShowMaxDamageEnabled(), 3));
		this.buttonList.add(this.createGuiButtonToggled(4, mod.isEquippedItemEnabled(), 4));
		this.buttonList.add(this.createGuiButtonToggled(5, mod.isDamageOverlaysEnabled(), 5));
		this.buttonList.add(this.createGuiButtonToggled(6, mod.isTextShadowEnabled(), 6));
		this.buttonList.add(this.createGuiRect(7, mod.getTextColor().getRGB(), 7));
    }
}
