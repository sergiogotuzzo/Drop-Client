package drop.gui.mod.taboverlay;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.mod.GuiMod;
import drop.gui.mod.GuiModColor;
import drop.mods.ModInstances;
import drop.mods.impl.TabOverlay;

public class GuiTabOverlayDynamicColors extends GuiMod {
	private static final TabOverlay mod = ModInstances.getTabOverlayMod();
	
	public GuiTabOverlayDynamicColors(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
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
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getExcellentTextColor(), "excellentTextColor", "excellentTextChroma", "Excellent Text Color"));
	        	break;
	        case 2:
	        	mod.setExcellentTextShadow(!mod.isExcellentTextShadowToggled());
	        	this.initGui();
	        	break;
	        case 3:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getGoodTextColor(), "goodTextColor", "goodTextChroma", "Good Text Color"));
	        	break;
	        case 4:
	        	mod.setGoodTextShadow(!mod.isGoodTextShadowToggled());
	        	this.initGui();
	        	break;
	        case 5:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getModerateTextColor(), "moderateTextColor", "moderateTextChroma", "Moderate Text Color"));
	        	break;
	        case 6:
	        	mod.setModerateTextShadow(!mod.isModerateTextShadowToggled());
	        	this.initGui();
	        	break;
	        case 7:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getWeakTextColor(), "weakTextColor", "weakTextChroma", "Weak Text Color"));
	        	break;
	        case 8:
	        	mod.setWeakTextShadow(!mod.isWeakTextShadowToggled());
	        	this.initGui();
	        	break;
	        case 9:
	        	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getUnstableTextColor(), "unstableTextColor", "unstableTextChroma", "Unstable Text Color"));
	        	break;
	        case 10:
	        	mod.setUnstableTextShadow(!mod.isUnstableTextShadowToggled());
	        	this.initGui();
	        	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();

		this.buttonList.add(this.createGuiRect(1, mod.getExcellentTextColor().getRGB(), 1));
		this.buttonList.add(this.createGuiButtonToggled(2, mod.isExcellentTextShadowToggled(), 2));
		this.buttonList.add(this.createGuiRect(3, mod.getGoodTextColor().getRGB(), 3));
		this.buttonList.add(this.createGuiButtonToggled(4, mod.isGoodTextShadowToggled(), 4));
		this.buttonList.add(this.createGuiRect(5, mod.getModerateTextColor().getRGB(), 5));
		this.buttonList.add(this.createGuiButtonToggled(6, mod.isModerateTextShadowToggled(), 6));
		this.buttonList.add(this.createGuiRect(7, mod.getWeakTextColor().getRGB(), 7));
		this.buttonList.add(this.createGuiButtonToggled(8, mod.isWeakTextShadowToggled(), 8));
		this.buttonList.add(this.createGuiRect(9, mod.getUnstableTextColor().getRGB(), 9));
		this.buttonList.add(this.createGuiButtonToggled(10, mod.isUnstableTextShadowToggled(), 10));
    }
}
