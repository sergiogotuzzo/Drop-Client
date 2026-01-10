package drop.gui.mod.armorstatus;

import java.awt.Color;
import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import drop.gui.GuiScreenDC;
import drop.gui.mod.GuiModMenu;
import drop.mod.ModHandler;
import drop.mod.impl.ArmorStatus;
import drop.gui.mod.GuiModColor;

public class GuiArmorStatusDynamicColors extends GuiModMenu {
	private static final ArmorStatus mod = ModHandler.get(ArmorStatus.class);
	
	public GuiArmorStatusDynamicColors(GuiScreenDC previousGuiScreen) {
		super(previousGuiScreen, mod.getName());
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
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getColorOption("veryHighTextColor")));
	        	break;
	        case 2:
	        	mod.getBooleanOption("veryHighTextShadow").toggle();
	        	this.initGui();
	        	break;
	        case 3:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getColorOption("highTextColor")));
	        	break;
	        case 4:
	        	mod.getBooleanOption("highTextShadow").toggle();
	        	this.initGui();
	        	break;
	        case 5:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getColorOption("mediumTextColor")));
	        	break;
	        case 6:
	        	mod.getBooleanOption("mediumTextShadow").toggle();
	        	this.initGui();
	        	break;
	        case 7:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getColorOption("lowTextColor")));
	        	break;
	        case 8:
	        	mod.getBooleanOption("lowTextShadow").toggle();
	        	this.initGui();
	        	break;
	        case 9:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getColorOption("veryLowTextColor")));
	        	break;
	        case 10:
	        	mod.getBooleanOption("veryLowTextShadow").toggle();
	        	this.initGui();
	        	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();

		this.buttonList.add(this.createGuiRect(1, mod.getColorOption("veryHighTextColor").getColor().getRGB(), mod.getColorOption("veryHighTextColor").getColor().isChromaToggled(), 1));
		this.buttonList.add(this.createGuiCheckBox(2, mod.getBooleanOption("veryHighTextShadow").isToggled(), 2));
		this.buttonList.add(this.createGuiRect(3, mod.getColorOption("highTextColor").getColor().getRGB(), mod.getColorOption("highTextColor").getColor().isChromaToggled(), 3));
		this.buttonList.add(this.createGuiCheckBox(4, mod.getBooleanOption("highTextShadow").isToggled(), 4));
		this.buttonList.add(this.createGuiRect(5, mod.getColorOption("mediumTextColor").getColor().getRGB(), mod.getColorOption("mediumTextColor").getColor().isChromaToggled(), 5));
		this.buttonList.add(this.createGuiCheckBox(6, mod.getBooleanOption("mediumTextShadow").isToggled(), 6));
		this.buttonList.add(this.createGuiRect(7, mod.getColorOption("lowTextColor").getColor().getRGB(), mod.getColorOption("lowTextColor").getColor().isChromaToggled(), 7));
		this.buttonList.add(this.createGuiCheckBox(8, mod.getBooleanOption("lowTextShadow").isToggled(), 8));
		this.buttonList.add(this.createGuiRect(9, mod.getColorOption("veryLowTextColor").getColor().getRGB(), mod.getColorOption("veryLowTextColor").getColor().isChromaToggled(), 9));
		this.buttonList.add(this.createGuiCheckBox(10, mod.getBooleanOption("veryLowTextShadow").isToggled(), 10));
    }
}
