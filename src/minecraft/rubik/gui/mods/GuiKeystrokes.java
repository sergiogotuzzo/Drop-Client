package rubik.gui.mods;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import rubik.gui.GuiModColor;
import rubik.mods.ModInstances;
import rubik.mods.impl.Keystrokes;
import rubik.mods.impl.Keystrokes.KeystrokesMode;

public class GuiKeystrokes extends GuiScreen {
	private final GuiScreen previousGuiScreen;
	private Keystrokes mod = ModInstances.getKeystrokesMod();
	
	public GuiKeystrokes(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}
	
	@Override
    public void initGui()
    {
        this.buttonList.clear();
        
        int i = -16;
 
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 24 + i, 98, 20, I18n.format(mod.isEnabled() ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled", new Object[0])));
        this.buttonList.add(new GuiButton(2, this.width / 2 + 2, this.height / 4 + 24 + i, 98, 20, I18n.format((mod.isTextShadowEnabled() ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) + "Text Shadow", new Object[0])));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, this.height / 4 + 48 + i, 98, 20, I18n.format("Pressed Color", new Object[0])));
        this.buttonList.add(new GuiButton(4, this.width / 2 + 2, this.height / 4 + 48 + i, 98, 20, I18n.format("Released Color", new Object[0])));
        this.buttonList.add(new GuiButton(5, this.width / 2 - 100, this.height / 4 + 72 + i, 98, 20, I18n.format((mod.isShowMouseEnabled() ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) + "Show Mouse", new Object[0])));
        this.buttonList.add(new GuiButton(6, this.width / 2 + 2, this.height / 4 + 72 + i, 98, 20, I18n.format((mod.isShowSpacebarEnabled() ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) + "Show Spacebar", new Object[0])));       
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
            	mod.setTextShadow(!mod.isTextShadowEnabled());
            	this.initGui();
            	break;
            case 3:
            	this.mc.displayGuiScreen(new GuiModColor(this, mod.getPressedColorManager()));
            	break;
            case 4:
            	this.mc.displayGuiScreen(new GuiModColor(this, mod.getReleasedColorManager()));
            	break;
            case 5:
            	mod.setShowMouse(!mod.isShowMouseEnabled());
            	this.initGui();
            	break;
            case 6:
            	mod.setShowSpacebar(!mod.isShowSpacebarEnabled());
            	this.initGui();
            	break;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, I18n.format("Keystrokes Settings", new Object[0]), this.width / 2, 40, 0xFFFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
