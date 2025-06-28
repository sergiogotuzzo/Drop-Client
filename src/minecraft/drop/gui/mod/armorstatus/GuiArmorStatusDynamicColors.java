package drop.gui.mod.armorstatus;

import java.awt.Color;
import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiModColor;
import drop.mods.ModInstances;
import drop.mods.impl.ArmorStatus;

public class GuiArmorStatusDynamicColors extends GuiDropClientScreen {
	private static final ArmorStatus mod = ModInstances.getArmorStatusMod();
	private GuiDropClientScreen previousGuiScreen;
	
	public GuiArmorStatusDynamicColors(GuiDropClientScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	this.drawDefaultBackground();
    	
    	this.drawRect((this.width - 350) / 2, (this.height - 250) / 2, (this.width - 350) / 2 + 350, (this.height - 250) / 2 + 250, new Color(0, 0, 0, 127).getRGB());
        
        this.drawScaledText(mod.getName(), (this.width - 350) / 2 + 15, (this.height - 250) / 2 + 15, 2.0D, 0xFFFFFFFF, true, false);

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
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
        	case 0:
        		mc.displayGuiScreen(this.previousGuiScreen);
        		break;
	        case 1:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getOptions().getColorOption("veryHighTextColor")));
	        	break;
	        case 2:
	        	mod.getOptions().getBooleanOption("veryHighTextShadow").toggle();
	        	this.initGui();
	        	break;
	        case 3:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getOptions().getColorOption("highTextColor")));
	        	break;
	        case 4:
	        	mod.getOptions().getBooleanOption("highTextShadow").toggle();
	        	this.initGui();
	        	break;
	        case 5:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getOptions().getColorOption("mediumTextColor")));
	        	break;
	        case 6:
	        	mod.getOptions().getBooleanOption("mediumTextShadow").toggle();
	        	this.initGui();
	        	break;
	        case 7:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getOptions().getColorOption("lowTextColor")));
	        	break;
	        case 8:
	        	mod.getOptions().getBooleanOption("lowTextShadow").toggle();
	        	this.initGui();
	        	break;
	        case 9:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getOptions().getColorOption("veryLowTextColor")));
	        	break;
	        case 10:
	        	mod.getOptions().getBooleanOption("veryLowTextShadow").toggle();
	        	this.initGui();
	        	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();

        this.buttonList.clear();
        
        this.buttonList.add(new GuiButton(0, (this.width + 350) / 2 - 50 - 15, (this.height - 250) / 2 + 15 - 3, 50, 20, I18n.format("gui.done", new Object[0])));

		this.buttonList.add(this.createGuiRect(1, mod.getOptions().getColorOption("veryHighTextColor").getColor().getRGB(), 1));
		this.buttonList.add(this.createGuiCheckBox(2, mod.getOptions().getBooleanOption("veryHighTextShadow").isToggled(), 2));
		this.buttonList.add(this.createGuiRect(3, mod.getOptions().getColorOption("highTextColor").getColor().getRGB(), 3));
		this.buttonList.add(this.createGuiCheckBox(4, mod.getOptions().getBooleanOption("highTextShadow").isToggled(), 4));
		this.buttonList.add(this.createGuiRect(5, mod.getOptions().getColorOption("mediumTextColor").getColor().getRGB(), 5));
		this.buttonList.add(this.createGuiCheckBox(6, mod.getOptions().getBooleanOption("mediumTextShadow").isToggled(), 6));
		this.buttonList.add(this.createGuiRect(7, mod.getOptions().getColorOption("lowTextColor").getColor().getRGB(), 7));
		this.buttonList.add(this.createGuiCheckBox(8, mod.getOptions().getBooleanOption("lowTextShadow").isToggled(), 8));
		this.buttonList.add(this.createGuiRect(9, mod.getOptions().getColorOption("veryLowTextColor").getColor().getRGB(), 9));
		this.buttonList.add(this.createGuiCheckBox(10, mod.getOptions().getBooleanOption("veryLowTextShadow").isToggled(), 10));
    }
}
