package drop.gui.mod.armorstatus;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.mod.GuiMod;
import drop.gui.mod.GuiModColor;
import drop.mods.ModInstances;
import drop.mods.impl.ArmorStatus;

public class GuiArmorStatusDynamicColors extends GuiMod {
	private static final ArmorStatus mod = ModInstances.getArmorStatusMod();
	
	public GuiArmorStatusDynamicColors(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	super.drawScreen(mouseX, mouseY, partialTicks);

        this.writeOptionText("Very High Text Color", 1);
        this.writeOptionText("Very High Text Shadow", 2);
        this.writeOptionText("High Text Color", 3);
        this.writeOptionText("High Text Shadow", 4);
        this.writeOptionText("Medium Text Color", 5);
        this.writeOptionText("Medium Text Shadow", 6);
        this.writeOptionText("Low Text Color", 7);
        this.writeOptionText("Low Text Shadow", 8);
        this.writeOptionText("Very Low Text Color", 9);
        this.writeOptionText("Very Low Text Shadow", 10);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
	        case 1:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getVeryHighTextColor(), "veryHighTextColor", "veryHighTextChroma", "Very High Text Color"));
	        	break;
	        case 2:
	        	mod.setVeryHighTextShadow(!mod.isVeryHighTextShadowToggled());
	        	this.initGui();
	        	break;
	        case 3:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getHighTextColor(), "highTextColor", "highTextChroma", "High Text Color"));
	        	break;
	        case 4:
	        	mod.setHighTextShadow(!mod.isHighTextShadowToggled());
	        	this.initGui();
	        	break;
	        case 5:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getMediumTextColor(), "mediumTextColor", "mediumTextChroma", "Medium Text Color"));
	        	break;
	        case 6:
	        	mod.setMediumTextShadow(!mod.isMediumTextShadowToggled());
	        	this.initGui();
	        	break;
	        case 7:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getLowTextColor(), "lowTextColor", "lowTextChroma", "Low Text Color"));
	        	break;
	        case 8:
	        	mod.setLowTextShadow(!mod.isLowTextShadowToggled());
	        	this.initGui();
	        	break;
	        case 9:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getVeryLowTextColor(), "veryLowTextColor", "veryLowTextChroma", "Very Low Text Color"));
	        	break;
	        case 10:
	        	mod.setVeryLowTextShadow(!mod.isVeryLowTextShadowToggled());
	        	this.initGui();
	        	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();

		this.buttonList.add(this.createGuiRect(1, mod.getVeryHighTextColor().getRGB(), 1));
		this.buttonList.add(this.createGuiCheckBox(2, mod.isVeryHighTextShadowToggled(), 2));
		this.buttonList.add(this.createGuiRect(3, mod.getHighTextColor().getRGB(), 3));
		this.buttonList.add(this.createGuiCheckBox(4, mod.isHighTextShadowToggled(), 4));
		this.buttonList.add(this.createGuiRect(5, mod.getMediumTextColor().getRGB(), 5));
		this.buttonList.add(this.createGuiCheckBox(6, mod.isMediumTextShadowToggled(), 6));
		this.buttonList.add(this.createGuiRect(7, mod.getLowTextColor().getRGB(), 7));
		this.buttonList.add(this.createGuiCheckBox(8, mod.isLowTextShadowToggled(), 8));
		this.buttonList.add(this.createGuiRect(9, mod.getVeryLowTextColor().getRGB(), 9));
		this.buttonList.add(this.createGuiCheckBox(10, mod.isVeryLowTextShadowToggled(), 10));
    }
}
