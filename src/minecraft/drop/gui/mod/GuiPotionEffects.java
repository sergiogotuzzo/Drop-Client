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
    	
        this.writeOptionText("Blink", 1);
        this.writeOptionText("Show Icon", 2);
        this.writeOptionText("Duration Text Shadow", 3);
        this.writeOptionText("Duration Text Color", 4);
        this.writeOptionText("Show Name", 5);
        this.writeOptionText("Name Text Shadow", 6);
        this.writeOptionText("Name Text Color", 7);
        this.writeOptionText("Show Background", 8);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
            case 1:
            	mod.setBlink(!mod.isBlinkEnabled());
            	this.initGui();
            	break;
            case 2:
            	mod.setShowIcon(!mod.isShowIconEnabled());
            	this.initGui();
            	break;
            case 3:
            	mod.setDurationTextShadow(!mod.isDurationTextShadowEnabled());
            	this.initGui();
            	break;
            case 4:
            	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getDurationTextColor(), "durationTextColor", "durationTextChroma", "Duration Text Color"));
            	break;
            case 5:
            	mod.setShowName(!mod.isShowNameEnabled());
            	this.initGui();
            	break;
            case 6:
            	mod.setNameTextShadow(!mod.isNameTextShadowEnabled());
            	this.initGui();
            	break;
            case 7:
            	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getNameTextColor(), "nameTextColor", "nameTextChroma", "Name Text Color"));
            	break;
            case 8:
            	mod.setShowBackground(!mod.isShowBackgroundEnabled());
            	this.initGui();
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
        
    	this.buttonList.add(this.createGuiButtonToggled(1, mod.isBlinkEnabled(), 1));
    	this.buttonList.add(this.createGuiButtonToggled(2, mod.isShowIconEnabled(), 2));
    	this.buttonList.add(this.createGuiButtonToggled(3, mod.isDurationTextShadowEnabled(), 3));
        this.buttonList.add(this.createGuiRect(4, mod.getDurationTextColor().getRGB(), 4));
    	this.buttonList.add(this.createGuiButtonToggled(5, mod.isShowNameEnabled(), 5));
    	this.buttonList.add(this.createGuiButtonToggled(6, mod.isNameTextShadowEnabled(), 6));
        this.buttonList.add(this.createGuiRect(7, mod.getNameTextColor().getRGB(), 7));
    	this.buttonList.add(this.createGuiButtonToggled(8, mod.isShowBackgroundEnabled(), 8));
    }
}
