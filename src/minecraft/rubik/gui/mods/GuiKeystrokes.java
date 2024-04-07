package rubik.gui.mods;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.mods.ModInstances;
import rubik.mods.impl.Keystrokes;
import rubik.mods.impl.Keystrokes.KeystrokesMode;

public class GuiKeystrokes extends GuiScreen {
	private final GuiScreen previousScreen;
	private Keystrokes mod = ModInstances.getKeystrokesMod();
	
	public GuiKeystrokes(GuiScreen previousScreen) {
		this.previousScreen = previousScreen;
	}
	
	@Override
    public void initGui()
    {
        this.buttonList.clear();
        
        int i = -16;
 
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 24 + i, 98, 20, I18n.format(mod.isEnabled() ? "§aEnabled" : "§cDisabled", new Object[0])));
        this.buttonList.add(new GuiButton(2, this.width / 2 + 2, this.height / 4 + 24 + i, 98, 20, I18n.format(mod.getMode().toString(), new Object[0])));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100 + 50, this.height / 4 + 48 + i, 98, 20, I18n.format((mod.isShadowEnabled() ? "§a" : "§c") + "Text Shadow", new Object[0])));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
        switch (button.id)
        {
            case 0:
            	this.mc.displayGuiScreen(this.previousScreen);
            	break;
            case 1:
            	mod.setEnabled(!mod.isEnabled());
            	this.initGui();
                break;
            case 2:
	            {
	            	if (mod.getMode() == KeystrokesMode.WASD) {
	            		mod.setMode(KeystrokesMode.WASD_MOUSE);
	            	} else if (mod.getMode() == KeystrokesMode.WASD_MOUSE) {
	            		mod.setMode(KeystrokesMode.WASD_JUMP);
	            	} else if (mod.getMode() == KeystrokesMode.WASD_JUMP) {
	            		mod.setMode(KeystrokesMode.WASD_JUMP_MOUSE);
	            	} else if (mod.getMode() == KeystrokesMode.WASD_JUMP_MOUSE) {
	            		mod.setMode(KeystrokesMode.WASD);
	            	}
	            	
	            	this.initGui();
	            }
            	break;
            case 3:
            	mod.setShadowEnabled(!mod.isShadowEnabled());
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
