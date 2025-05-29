package drop.gui.mod;

import java.awt.Color;
import java.io.IOException;

import drop.Client;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import drop.gui.GuiButtonToggled;
import drop.gui.GuiDropClientScreen;
import drop.gui.GuiRect;
import drop.gui.GuiSlider;
import drop.gui.GuiText;
import drop.mods.ModInstances;
import drop.mods.impl.togglesprintsneak.ToggleSprintSneak;

public class GuiToggleSprintSneak extends GuiDropClientScreen {
	private final GuiScreen previousGuiScreen;
	private final ToggleSprintSneak mod = ModInstances.getToggleSprintSneakMod();

	private GuiSlider sliderFlyBoostFactor;
	
	public GuiToggleSprintSneak(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}

	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		
    	drawRect((this.width - 300) / 2, (this.height - 200) / 2, (this.width - 300) / 2 + 300, (this.height - 200) / 2 + 200, new Color(0, 0, 0, 127).getRGB());
        
        this.drawScaledText("Toggle Sprint Sneak", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 15, 2.0D, 0xFFFFFFFF, false, false);
        this.drawText("Toggle Sprint", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15, -1, false, false);
        this.drawText("Toggle Sneak", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 1 + 15, -1, false, false);
        this.drawText("Fly Boost", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 2 + 15, -1, false, false);
        this.drawText("Fly Boost Factor", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 3 + 15, -1, false, false);
        this.drawText(String.format("%.1f", mod.getFlyBoostFactor()), (this.width + 300) / 2 - mc.fontRendererObj.getStringWidth(String.format("%.1f", mod.getFlyBoostFactor())) - 15, (this.height - 200) / 2 + 30 + 15 * 3 + 15, -1, false, false);
        this.drawText("Show Text", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 4 + 15, -1, false, false);
        this.drawText("Show Background", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 5 + 15, -1, false, false);
        this.drawText("Text Shadow", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 6 + 15, -1, false, false);
        this.drawText("Text Color", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 7 + 15, -1, false, false);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
    	mod.setFlyBoostFactor(sliderFlyBoostFactor.getSliderPosition() * 8.0F);
    	
    	if (mod.getFlyBoostFactor() < 2.0F) {
    		mod.setFlyBoostFactor(2.0F);
    	}
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
            	this.mc.displayGuiScreen(this.previousGuiScreen);
            	break;
            case 1:
            	mod.setToggleSprint(!mod.isToggleSprintEnabled());
            	this.initGui();
            	break;
            case 2:
            	mod.setToggleSneak(!mod.isToggleSneakEnabled());
            	this.initGui();
            	break;
            case 3:
            	mod.setFlyBoost(!mod.isFlyBoostEnabled());
            	this.initGui();
            	break;
            case 4:
            	mod.setFlyBoostFactor(sliderFlyBoostFactor.getSliderPosition() * 8.0F);
            	
            	if (mod.getFlyBoostFactor() < 2.0F) {
            		mod.setFlyBoostFactor(2.0F);
            	}
            	break;
            case 5:
            	mod.setShowText(!mod.isShowTextEnabled());
            	this.initGui();
            	break;
            case 6:
            	mod.setShowBackground(!mod.isShowBackgroundEnabled());
            	this.initGui();
            	break;
            case 7:
            	mod.setTextShadow(!mod.isTextShadowEnabled());
            	this.initGui();
            	break;
            case 8:
            	mc.displayGuiScreen(new GuiModDraggableTextColor(this, mod, "Toggle Sprint Sneak"));
            	break;
        }
    }
	
	@Override
    public void initGui() {
        this.buttonList.clear();
        
    	this.buttonList.add(new GuiButtonToggled(1, mod.isToggleSprintEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15 - 2));
    	this.buttonList.add(new GuiButtonToggled(2, mod.isToggleSneakEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 1 + 15 - 2));
    	this.buttonList.add(new GuiButtonToggled(3, mod.isFlyBoostEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 2 + 15 - 2));
    	this.buttonList.add(sliderFlyBoostFactor = new GuiSlider(4, (this.width - 300) / 2 + 130, (this.height - 200) / 2 + 30 + 15 * 3 + 15 + 1, 100, 5, 0, 8, mod.getFlyBoostFactor()));
    	this.buttonList.add(new GuiButtonToggled(5, mod.isShowTextEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 4 + 15 - 2));
    	this.buttonList.add(new GuiButtonToggled(6, mod.isShowBackgroundEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 5 + 15 - 2));
    	this.buttonList.add(new GuiButtonToggled(7, mod.isTextShadowEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 6 + 15 - 2));
        this.buttonList.add(new GuiRect(8, (this.width + 300) / 2 - 15 - 13, (this.height - 200) / 2 + 30 + 15 * 7 + 15 - 2 * 2, mod.getTextColor().getRGB()));
        this.buttonList.add(new GuiButton(0, (this.width + 300) / 2 - 50 - 15, (this.height - 200) / 2 + 15, 50, 20, I18n.format("gui.done", new Object[0])));
    }
}
