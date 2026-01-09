package drop.gui;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.util.IChatComponent;

public class GuiReconnect extends GuiDisconnected {
	public static String ip;
	public static int port;
	
	public GuiReconnect(String reasonLocalizationKey, IChatComponent chatComp) {
		super(new GuiMultiplayer(new GuiMainMenuDC()), reasonLocalizationKey, chatComp);
	}
	
	@Override
	public void initGui() {
		super.initGui();
		
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 2 + super.field_175353_i / 2 + this.fontRendererObj.FONT_HEIGHT + 20 + 5, "Try to reconnect"));
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		switch (button.id) {
			case 0:
				this.mc.displayGuiScreen(super.parentScreen);
				break;
			case 1:
				this.mc.displayGuiScreen(new GuiConnecting(super.parentScreen, mc, GuiReconnect.ip, GuiReconnect.port));
				break;
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
}
