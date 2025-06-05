package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.mods.ModInstances;
import drop.mods.impl.PotionEffects;

public class GuiPotionEffects extends GuiMod {
	private static final PotionEffects mod = ModInstances.getPotionEffectsMod();
	
	public GuiPotionEffects(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	super.drawScreen(mouseX, mouseY, partialTicks);
    	
        this.writeOptionText("Duration Text Color", 1);
        this.writeOptionText("Duration Text Shadow", 2);
        this.writeOptionText("Show Name", 3);
        this.writeOptionText("Name Text Color", 4);
        this.writeOptionText("Name Text Shadow", 5);
        this.writeOptionText("Show Icon", 6);
        this.writeOptionText("Blink", 7);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
	        case 1:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getDurationTextColor(), "durationTextColor", "durationTextChroma", "Duration Text Color"));
	        	break;
            case 2:
            	mod.setDurationTextShadow(!mod.isDurationTextShadowEnabled());
            	this.initGui();
            	break;
            case 3:
            	mod.setShowName(!mod.isShowNameEnabled());
            	this.initGui();
            	break;
            case 4:
            	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getNameTextColor(), "nameTextColor", "nameTextChroma", "Name Text Color"));
            	break;
            case 5:
            	mod.setNameTextShadow(!mod.isNameTextShadowEnabled());
            	this.initGui();
            	break;
            case 6:
            	mod.setShowIcon(!mod.isShowIconEnabled());
            	this.initGui();
            	break;
            case 7:
            	mod.setBlink(!mod.isBlinkEnabled());
            	this.initGui();
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
        
        this.buttonList.add(this.createGuiRect(1, mod.getDurationTextColor().getRGB(), 1));
    	this.buttonList.add(this.createGuiButtonToggled(2, mod.isDurationTextShadowEnabled(), 2));
    	this.buttonList.add(this.createGuiButtonToggled(3, mod.isShowNameEnabled(), 3));
        this.buttonList.add(this.createGuiRect(4, mod.getNameTextColor().getRGB(), 4));
    	this.buttonList.add(this.createGuiButtonToggled(5, mod.isNameTextShadowEnabled(), 5));
    	this.buttonList.add(this.createGuiButtonToggled(6, mod.isShowIconEnabled(), 6));
    	this.buttonList.add(this.createGuiButtonToggled(7, mod.isBlinkEnabled(), 7));
    }
}
