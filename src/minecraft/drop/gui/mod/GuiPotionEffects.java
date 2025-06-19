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
        
        if (mod.isShowNameToggled()) {
        	this.writeOptionText("Name Text Color", 4);
            this.writeOptionText("Name Text Shadow", 5);
        }
        
        this.writeOptionText("Show Icon", mod.isShowNameToggled() ? 6 : 4);
        this.writeOptionText("Blink", mod.isShowNameToggled() ? 7 : 5);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
	        case 1:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getDurationTextColor(), "durationTextColor", "durationTextChroma", "Duration Text Color"));
	        	break;
            case 2:
            	mod.setDurationTextShadow(!mod.isDurationTextShadowToggled());
            	this.initGui();
            	break;
            case 3:
            	mod.setShowName(!mod.isShowNameToggled());
            	this.initGui();
            	break;
            case 4:
            	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getNameTextColor(), "nameTextColor", "nameTextChroma", "Name Text Color"));
            	break;
            case 5:
            	mod.setNameTextShadow(!mod.isNameTextShadowToggled());
            	this.initGui();
            	break;
            case 6:
            	mod.setShowIcon(!mod.isShowIconToggled());
            	this.initGui();
            	break;
            case 7:
            	mod.setBlink(!mod.isBlinkToggled());
            	this.initGui();
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
        
        this.buttonList.add(this.createGuiRect(1, mod.getDurationTextColor().getRGB(), 1));
    	this.buttonList.add(this.createGuiCheckBox(2, mod.isDurationTextShadowToggled(), 2));
    	this.buttonList.add(this.createGuiCheckBox(3, mod.isShowNameToggled(), 3));
        
    	if (mod.isShowNameToggled()) {
    		this.buttonList.add(this.createGuiRect(4, mod.getNameTextColor().getRGB(), 4));
        	this.buttonList.add(this.createGuiCheckBox(5, mod.isNameTextShadowToggled(), 5));
    	}
    	
    	this.buttonList.add(this.createGuiCheckBox(6, mod.isShowIconToggled(), mod.isShowNameToggled() ? 6 : 4));
    	this.buttonList.add(this.createGuiCheckBox(7, mod.isBlinkToggled(), mod.isShowNameToggled() ? 7 : 5));
    }
}
