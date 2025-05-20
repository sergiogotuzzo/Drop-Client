package rubik.gui;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.input.Mouse;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonLanguage;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import rubik.Client;
import rubik.gui.GuiMods;
import rubik.gui.hud.IRenderer;

public class GuiDropClientMainMenu extends GuiScreen implements GuiYesNoCallback
{
	private ResourceLocation backgroundResourceLocation = new ResourceLocation("rubik/background.png");
    private ResourceLocation iconResourceLocation = new ResourceLocation("rubik/icon.png");
    private int iconSide = 98;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	this.mc.getTextureManager().bindTexture(backgroundResourceLocation);
    	drawModalRectWithCustomSizedTexture(-21 + Mouse.getX() / 90, Mouse.getY() * -1 / 90, 0.0f, 0.0f, this.width + 20, this.height + 20, (float)(this.width + 21), (float)(this.height + 20));
    	
    	drawRect((this.width - 200) / 2, (this.height - 200) / 2, (this.width - 200) / 2 + 200, (this.height - 200) / 2 + 200, new Color(0, 0, 0, 127).getRGB());
    	
        this.drawString(this.fontRendererObj, Client.nameVersion, 2, this.height - 10, 0xFFFFFF);
        this.drawString(this.fontRendererObj, "Minecraft 1.8.9", this.width - this.fontRendererObj.getStringWidth("Minecraft 1.8.9") - 2, this.height - 10, 0xFFFFFF);
        
        this.mc.getTextureManager().bindTexture(iconResourceLocation);
        this.drawModalRectWithCustomSizedTexture(this.width / 2 - iconSide / 2, this.height / 2 - iconSide + 16, 0.0f, 0.0f, iconSide, iconSide, (float)(iconSide), (float)(iconSide));

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	switch (button.id) {
    		case 0:
    			this.mc.shutdown();
    			break;
    		case 1:
    			this.mc.displayGuiScreen(new GuiSelectWorld(this));
    			break;
    		case 2:
    			this.mc.displayGuiScreen(new GuiMultiplayer(this, true));
    			break;
    		case 3:
    			this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
    			break;
    		case 4:
    			this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager()));
    			break;
    		case 5:
    			this.mc.displayGuiScreen(new GuiMods(this));
    			break;
    	}
    }
	
	@Override
    public void initGui() {
		Client.getInstance().getDiscordRichPresence().update("In Main Menu", "Idle");
				
		this.buttonList.add(new GuiButton(1, this.width / 2 - 150 / 2, this.height / 2 + 24 * 1 + 8, 150, 20, I18n.format("menu.singleplayer", new Object[0])));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 150 / 2, this.height / 2 + 24 * 2 + 8, 150, 20, I18n.format("menu.multiplayer", new Object[0])));
        this.buttonList.add(new GuiButtonIcon(5, new ResourceLocation("rubik/icon.png"), this.width / 2 - 2 * 3 - 40, this.height - 20 - fontRendererObj.FONT_HEIGHT - 14));
    	this.buttonList.add(new GuiButtonIcon(3, new ResourceLocation("rubik/options.png"), this.width / 2 + 2, this.height - 20 - fontRendererObj.FONT_HEIGHT - 14, 14, 14));
        this.buttonList.add(new GuiButtonLanguage(4, this.width / 2 - 2 - 20, this.height - 20 - fontRendererObj.FONT_HEIGHT - 14));
        this.buttonList.add(new GuiButtonIcon(0, new ResourceLocation("rubik/quit.png"), this.width / 2 + 2 * 3 + 20, this.height - 20 - fontRendererObj.FONT_HEIGHT - 14));
    }
}
