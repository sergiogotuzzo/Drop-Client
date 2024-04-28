package rubik.gui.mods;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import rubik.gui.GuiModColor;
import rubik.mods.ModInstances;
import rubik.mods.impl.CPSDisplay;

public class GuiCPSDisplay extends GuiScreen {
	private final GuiScreen previousGuiScreen;
	private CPSDisplay mod = ModInstances.getCPSDisplayMod();
	
	public GuiCPSDisplay(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}
	
	@Override
    public void initGui()
    {
        this.buttonList.clear();
        
        int i = -16;
 
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 24 + i, 98, 20, I18n.format(mod.isEnabled() ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled", new Object[0])));
        this.buttonList.add(new GuiButton(2, this.width / 2 + 2, this.height / 4 + 24 + i, 98, 20, I18n.format((mod.isShowBackgroundEnabled() ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) + "Show Background", new Object[0])));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, this.height / 4 + 48 + i, 98, 20, I18n.format((mod.isTextShadowEnabled() ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) + "Text Shadow", new Object[0])));
        this.buttonList.add(new GuiButton(4, this.width / 2 + 2, this.height / 4 + 48 + i, 98, 20, I18n.format("Color", new Object[0])));
        this.buttonList.add(new GuiButton(5, this.width / 2 - 100 + 50, this.height / 4 + 72 + i, 98, 20, I18n.format((mod.isShowRightCPSEnabled() ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) + "Show Right CPS", new Object[0])));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
        switch (button.id)
        {
            case 0:
            	this.mc.displayGuiScreen(this.previousGuiScreen);
            	break;
            case 1:
            	mod.setEnabled(!mod.isEnabled());
            	this.initGui();
                break;
            case 2:
            	mod.setShowBackground(!mod.isShowBackgroundEnabled());
            	this.initGui();
            	break;
            case 3:
            	mod.setTextShadow(!mod.isTextShadowEnabled());
            	this.initGui();
            	break;
            case 4:
            	this.mc.displayGuiScreen(new GuiModColor(this, mod.getColorManager()));
            	break;
            case 5:
            	mod.setShowRightCPS(!mod.isShowRightCPSEnabled());
            	this.initGui();
            	break;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, I18n.format("CPS Display Settings", new Object[0]), this.width / 2, 40, 0xFFFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
