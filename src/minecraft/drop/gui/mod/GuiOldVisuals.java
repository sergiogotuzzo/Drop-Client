package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.mods.ModInstances;
import drop.mods.impl.OldVisuals;

public class GuiOldVisuals extends GuiMod {
	private static final OldVisuals mod = ModInstances.getOldVisualsMod();
	
	public GuiOldVisuals(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	super.drawScreen(mouseX, mouseY, partialTicks);
    	
        this.writeOptionText("Fishing Rod", 1);
        this.writeOptionText("Bow", 2);
        this.writeOptionText("Block Hitting", 3);
        this.writeOptionText("Armor Hit Animation", 4);
        this.writeOptionText("Hit Armor Color", 5);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
            case 1:
            	mod.setFishingRod(!mod.isFishingRodToggled());
            	this.initGui();
            	break;
            case 2:
            	mod.setBow(!mod.isBowToggled());
            	this.initGui();
            	break;
            case 3:
            	mod.setBlockHitting(!mod.isBlockHittingToggled());
            	this.initGui();
            	break;
            case 4:
            	mod.setArmorHitAnimation(!mod.isArmorHitAnimationToggled());
            	this.initGui();
            	break;
            case 5:
            	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getHitArmorColor(), "hitArmorColor", "hitArmorChroma", "Hit Armor Color", true));
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
        
    	this.buttonList.add(this.createGuiButtonToggled(1, mod.isFishingRodToggled(), 1));
    	this.buttonList.add(this.createGuiButtonToggled(2, mod.isBowToggled(), 2));
    	this.buttonList.add(this.createGuiButtonToggled(3, mod.isBlockHittingToggled(), 3));
    	this.buttonList.add(this.createGuiButtonToggled(4, mod.isArmorHitAnimationToggled(), 4));
        this.buttonList.add(this.createGuiRect(5, mod.getHitArmorColor().getRGB(), 5));
    }
}
