package rubik.gui.mods;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import rubik.mods.ModInstances;
import rubik.mods.impl.OldAnimations;

public class GuiOldAnimations extends GuiScreen {
	private final GuiScreen previousGuiScreen;
	private OldAnimations mod = ModInstances.getOldAnimationsMod();
	
	public GuiOldAnimations(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}
	
	@Override
    public void initGui()
    {
        this.buttonList.clear();
        
        int i = -16;
 
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 24 + i, 98, 20, I18n.format(mod.isEnabled() ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled", new Object[0])));
        this.buttonList.add(new GuiButton(2, this.width / 2 + 2, this.height / 4 + 24 + i, 98, 20, I18n.format((mod.isOldFishingRodEnabled() ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) + "Old Fishing Rod", new Object[0])));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, this.height / 4 + 48 + i, 98, 20, I18n.format((mod.isOldBowEnabled() ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) + "Old Bow", new Object[0])));
        this.buttonList.add(new GuiButton(4, this.width / 2 + 2, this.height / 4 + 48 + i, 98, 20, I18n.format((mod.isBlockHitEnabled() ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) + "Block Hit", new Object[0])));
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
            	mod.setOldFishingRod(!mod.isOldFishingRodEnabled());
            	this.initGui();
                break;
            case 3:
            	mod.setOldBow(!mod.isOldBowEnabled());
            	this.initGui();
                break;
            case 4:
            	mod.setBlockHit(!mod.isBlockHitEnabled());
            	this.initGui();
                break;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, I18n.format("Old Animations Settings", new Object[0]), this.width / 2, 40, 0xFFFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
