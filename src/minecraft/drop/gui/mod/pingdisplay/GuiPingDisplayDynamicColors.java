package drop.gui.mod.pingdisplay;

import java.awt.Color;
import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiModMenu;
import drop.gui.mod.GuiModColor;
import drop.mods.ModInstances;
import drop.mods.impl.PingDisplay;

public class GuiPingDisplayDynamicColors extends GuiModMenu {
	private static final PingDisplay mod = ModInstances.getPingDisplayMod();
	
	public GuiPingDisplayDynamicColors(GuiDropClientScreen previousGuiScreen) {
		super(previousGuiScreen, mod.getName());
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	super.drawScreen(mouseX, mouseY, partialTicks);

        this.writeOptionText("Excellent Text Color", 1);
        this.writeOptionText("Excellent Text Shadow", 2);
        this.writeOptionText("Good Text Color", 3);
        this.writeOptionText("Good Text Shadow", 4);
        this.writeOptionText("Moderate Text Color", 5);
        this.writeOptionText("Moderate Text Shadow", 6);
        this.writeOptionText("Weak Text Color", 7);
        this.writeOptionText("Weak Text Shadow", 8);
        this.writeOptionText("Unstable Text Color", 9);
        this.writeOptionText("Unstable Text Shadow", 10);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
	        case 1:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getOptions().getColorOption("excellentTextColor")));
	        	break;
	        case 2:
	        	mod.getOptions().getBooleanOption("excellentTextShadow").toggle();
	        	this.initGui();
	        	break;
	        case 3:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getOptions().getColorOption("goodTextColor")));
	        	break;
	        case 4:
	        	mod.getOptions().getBooleanOption("goodTextShadow").toggle();
	        	this.initGui();
	        	break;
	        case 5:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getOptions().getColorOption("moderateTextColor")));
	        	break;
	        case 6:
	        	mod.getOptions().getBooleanOption("moderateTextShadow").toggle();
	        	this.initGui();
	        	break;
	        case 7:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getOptions().getColorOption("weakTextColor")));
	        	break;
	        case 8:
	        	mod.getOptions().getBooleanOption("weakTextShadow").toggle();
	        	this.initGui();
	        	break;
	        case 9:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getOptions().getColorOption("unstableTextColor")));
	        	break;
	        case 10:
	        	mod.getOptions().getBooleanOption("unstableTextShadow").toggle();
	        	this.initGui();
	        	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();

		this.buttonList.add(this.createGuiRect(1, mod.getOptions().getColorOption("excellentTextColor").getColor().getRGB(), 1));
		this.buttonList.add(this.createGuiCheckBox(2, mod.getOptions().getBooleanOption("excellentTextShadow").isToggled(), 2));
		this.buttonList.add(this.createGuiRect(3, mod.getOptions().getColorOption("goodTextColor").getColor().getRGB(), 3));
		this.buttonList.add(this.createGuiCheckBox(4, mod.getOptions().getBooleanOption("goodTextShadow").isToggled(), 4));
		this.buttonList.add(this.createGuiRect(5, mod.getOptions().getColorOption("moderateTextColor").getColor().getRGB(), 5));
		this.buttonList.add(this.createGuiCheckBox(6, mod.getOptions().getBooleanOption("moderateTextShadow").isToggled(), 6));
		this.buttonList.add(this.createGuiRect(7, mod.getOptions().getColorOption("weakTextColor").getColor().getRGB(), 7));
		this.buttonList.add(this.createGuiCheckBox(8, mod.getOptions().getBooleanOption("weakTextShadow").isToggled(), 8));
		this.buttonList.add(this.createGuiRect(9, mod.getOptions().getColorOption("unstableTextColor").getColor().getRGB(), 9));
		this.buttonList.add(this.createGuiCheckBox(10, mod.getOptions().getBooleanOption("unstableTextShadow").isToggled(), 10));
    }
}
