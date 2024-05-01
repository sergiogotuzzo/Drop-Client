package rubik.gui.mods;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import rubik.mods.ModInstances;
import rubik.mods.impl.Chat;
import rubik.mods.impl.Scoreboard;

public class GuiChatSidebar extends GuiScreen {
	private final GuiScreen previousGuiScreen;
	private Chat chatMod = ModInstances.getChatMod();
	private Scoreboard sidebarMod = ModInstances.getScoreboardMod();
	
	public GuiChatSidebar(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}
	
	@Override
    public void initGui()
    {
        this.buttonList.clear();
        
        int i = -16;
        int j = 50;
 
        this.buttonList.add(new GuiButton(1, this.width / 2 - 200 - 2 + j, this.height / 4 + 24 + i, 98, 20, I18n.format((chatMod.isEnabled() ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) + "Chat", new Object[0])));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100 + j, this.height / 4 + 24 + i, 98, 20, I18n.format((chatMod.isTextShadowEnabled() ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) + "Text Shadow", new Object[0])));
        this.buttonList.add(new GuiButton(3, this.width / 2 + 2 + j, this.height / 4 + 24 + i, 98, 20, I18n.format((chatMod.isTransparentBackgroundEnabled() ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) + "Show Background", new Object[0])));
        this.buttonList.add(new GuiButton(4, this.width / 2 - 100, this.height / 4 + 72 + i, 98, 20, I18n.format((sidebarMod.isEnabled() ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) + "Sidebar", new Object[0])));
        this.buttonList.add(new GuiButton(5, this.width / 2 + 2, this.height / 4 + 72 + i, 98, 20, I18n.format((sidebarMod.isHideNumbersEnabled() ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) + "Hide Numbers", new Object[0])));
        this.buttonList.add(new GuiButton(6, this.width / 2 - 100, this.height / 4 + 96 + i, 98, 20, I18n.format((sidebarMod.isTextShadowEnabled() ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) + "Text Shadow", new Object[0])));
        this.buttonList.add(new GuiButton(7, this.width / 2 + 2, this.height / 4 + 96 + i, 98, 20, I18n.format((sidebarMod.isShowBackgroundEnabled() ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) + "Show Background", new Object[0])));
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
            	chatMod.setEnabled(!chatMod.isEnabled());
            	this.initGui();
                break;
            case 2:
            	chatMod.setTextShadow(!chatMod.isTextShadowEnabled());
            	this.initGui();
            	break;
            case 3:
            	chatMod.setTransparentBackground(!chatMod.isTransparentBackgroundEnabled());
            	this.initGui();
            	break;
            case 4:
            	sidebarMod.setEnabled(!sidebarMod.isEnabled());
            	this.initGui();
                break;
            case 5:
            	sidebarMod.setHideNumbers(!sidebarMod.isHideNumbersEnabled());
            	this.initGui();
            	break;
            case 6:
            	sidebarMod.setTextShadow(!sidebarMod.isTextShadowEnabled());
            	this.initGui();
            	break;
            case 7:
            	sidebarMod.setShowBackground(!sidebarMod.isShowBackgroundEnabled());
            	this.initGui();
            	break;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        
        this.drawCenteredString(this.fontRendererObj, I18n.format("Chat Settings", new Object[0]), this.width / 2, this.height / 4 - 10, 0xFFFFFFFF);
        this.drawCenteredString(this.fontRendererObj, I18n.format("Sidebar Settings", new Object[0]), this.width / 2, this.height / 4 + 48 - 10, 0xFFFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
