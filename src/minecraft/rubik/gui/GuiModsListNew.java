package rubik.gui;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import rubik.events.EventTarget;
import rubik.events.impl.ClickEvent;

public class GuiModsListNew extends GuiScreen {
	private GuiListMods scrollPanel;

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		
		this.scrollPanel.drawScreen(mouseX, mouseY, partialTicks);

		this.drawCenteredString(this.fontRendererObj, "Mods List", this.width / 2, 20, 0xFFFFFFFF);

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public void handleMouseInput() throws IOException {
		super.handleMouseInput();
		this.scrollPanel.handleMouseInput();
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		this.scrollPanel.mouseClicked(mouseX, mouseY, mouseButton);
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		this.scrollPanel.mouseReleased(mouseX, mouseY, state);
		super.mouseReleased(mouseX, mouseY, state);
	}

	@Override
	public void initGui() {
		scrollPanel = new GuiListMods(mc, this);
		
		this.buttonList.clear();
	}

	@Override
	public boolean doesGuiPauseGame() {
		return true;
	}
}