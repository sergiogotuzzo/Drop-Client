package rubik.gui.mods;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import rubik.gui.GuiModColor;
import rubik.mods.ModInstances;
import rubik.mods.impl.ArmorStatus;
import rubik.mods.impl.ArmorStatus.ArmorStatusMode;

public class GuiArmorStatus extends GuiScreen {
	private final GuiScreen previousGuiScreen;
	private ArmorStatus mod = ModInstances.getArmorStatusMod();
	private GuiButton buttonColor;
	
	public GuiArmorStatus(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}
	
	@Override
    public void initGui()
    {
        this.buttonList.clear();
        
        int i = -16;
 
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 24 + i, 98, 20, I18n.format(mod.isEnabled() ? EnumChatFormatting.GREEN + "Enabled" : EnumChatFormatting.RED + "Disabled", new Object[0])));
        this.buttonList.add(new GuiButton(2, this.width / 2 + 2, this.height / 4 + 24 + i, 98, 20, I18n.format((mod.isShowCurrentItemEnabled() ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) + "Show Current Item", new Object[0])));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, this.height / 4 + 48 + i, 98, 20, I18n.format(mod.isRightEnabled() ? "Right" : "Left", new Object[0])));
        this.buttonList.add(new GuiButton(4, this.width / 2 + 2, this.height / 4 + 48 + i, 98, 20, I18n.format(mod.getMode().toString().replace("DAMAGE_MAX_DAMAGE", "Damage/Max Damage").replace("DAMAGE", "Damage").replace("PERCENTAGE", "Percentage"), new Object[0])));
        this.buttonList.add(new GuiButton(5, this.width / 2 - 100, this.height / 4 + 72 + i, 98, 20, I18n.format((mod.isDynamicColorsEnabled() ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) + "Dynamic Colors", new Object[0])));
        this.buttonList.add(buttonColor = new GuiButton(6, this.width / 2 + 2, this.height / 4 + 72 + i, 98, 20, I18n.format("Color", new Object[0])));
        this.buttonList.add(new GuiButton(7, this.width / 2 - 100 + 50, this.height / 4 + 96 + i, 98, 20, I18n.format((mod.isShadowEnabled() ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) + "Text Shadow", new Object[0])));
        
        buttonColor.enabled = !mod.isDynamicColorsEnabled();
        
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
            	mod.setShowCurrentItemEnabled(!mod.isShowCurrentItemEnabled());
            	this.initGui();
            	break;
            case 3:
            	mod.setRight(!mod.isRightEnabled());
            	this.initGui();
            	break;
            case 4:
	            {
	            	if (mod.getMode() == ArmorStatusMode.PERCENTAGE) {
	            		mod.setMode(ArmorStatusMode.DAMAGE);
	            	} else if (mod.getMode() == ArmorStatusMode.DAMAGE) {
	            		mod.setMode(ArmorStatusMode.DAMAGE_MAX_DAMAGE);
	            	} else if (mod.getMode() == ArmorStatusMode.DAMAGE_MAX_DAMAGE) {
	            		mod.setMode(ArmorStatusMode.PERCENTAGE);
	            	}
	            	
	            	this.initGui();
	            }
            	break;
            case 5:
            	mod.setDynamicColorsEnabled(!mod.isDynamicColorsEnabled());
            	this.initGui();
            	break;
            case 6:
            	this.mc.displayGuiScreen(new GuiModColor(this, mod.getColorManager()));
            	break;
            case 7:
            	mod.setShadowEnabled(!mod.isShadowEnabled());
            	this.initGui();
            	break;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, I18n.format("Armor Status Settings", new Object[0]), this.width / 2, 40, 0xFFFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
