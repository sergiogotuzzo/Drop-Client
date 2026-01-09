package drop.gui;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.input.Mouse;

import drop.Drop;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import drop.gui.mod.GuiModList;

public class GuiDropClientMainMenu extends GuiScreen implements GuiYesNoCallback
{
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	this.mc.getTextureManager().bindTexture(new ResourceLocation("drop/background.png"));
    	drawModalRectWithCustomSizedTexture(-21 + Mouse.getX() / 90, Mouse.getY() * -1 / 90, 0.0f, 0.0f, this.width + 20, this.height + 20, (float)(this.width + 21), (float)(this.height + 20));
    	
    	drawRect((this.width - 140) / 2, (this.height - 150) / 2, (this.width - 140) / 2 + 140, (this.height - 150) / 2 + 150, new Color(0, 0, 0, 127).getRGB());
    	
        this.drawString(this.fontRendererObj, Drop.getNameVersion(), 2, this.height - 10, 0xFFFFFF);
        this.drawString(this.fontRendererObj, Drop.MENU_SUBTITLE, this.width - this.fontRendererObj.getStringWidth(Drop.MENU_SUBTITLE) - 2, this.height - 10, 0xFFFFFF);
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	switch (button.id) {
    		case 1:
    			this.mc.displayGuiScreen(new GuiSelectWorld(this));
    			break;
    		case 2:
    			this.mc.displayGuiScreen(new GuiMultiplayer(this));
    			break;
    		case 3:
    			this.mc.displayGuiScreen(new GuiModList(this));
    			break;
    		case 4:
    			this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager()));
    			break;
    		case 5:
    			this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
    			break;
    		case 6:
    			this.mc.shutdown();
    			break;
    	}
    }
	
	@Override
    public void initGui() {
		Drop.getDropClient().getDiscordRichPresence().update("In Main Menu", "Idle");
		
        this.buttonList.add(new GuiButtonIcon(3, new ResourceLocation("drop/icon.png"), this.width / 2 - 84 / 2 + 19, this.height / 2 - 84 + 24, 84, 84, 84 - 38, 84 - 24, false));

		this.buttonList.add(new GuiButton(1, this.width / 2 - 120 / 2, this.height / 2 + 24 - 4, 120, 20, I18n.format("menu.singleplayer", new Object[0])));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 120 / 2, this.height / 2 + 24 * 2 - 4, 120, 20, I18n.format("menu.multiplayer", new Object[0])));
        
        this.buttonList.add(new GuiButtonIcon(3, new ResourceLocation("drop/icon.png"), this.width / 2 - 2 * 3 - 40, this.height - 20 - fontRendererObj.FONT_HEIGHT - 8));
        this.buttonList.add(new GuiButtonIcon(4, new ResourceLocation("drop/language.png"), this.width / 2 - 2 - 20, this.height - 20 - fontRendererObj.FONT_HEIGHT - 8, 14, 14));
    	this.buttonList.add(new GuiButtonIcon(5, new ResourceLocation("drop/options.png"), this.width / 2 + 2, this.height - 20 - fontRendererObj.FONT_HEIGHT - 8, 14, 14));
        this.buttonList.add(new GuiButtonIcon(6, new ResourceLocation("drop/quit.png"), this.width / 2 + 2 * 3 + 20, this.height - 20 - fontRendererObj.FONT_HEIGHT - 8));
    }
}
