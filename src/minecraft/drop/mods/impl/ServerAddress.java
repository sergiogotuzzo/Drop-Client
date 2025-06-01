package drop.mods.impl;

import java.awt.Color;

import drop.ColorManager;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiModDraggableDisplayText;
import drop.gui.mod.GuiToggleSprintSneak;
import drop.mods.hud.ScreenPosition;
import net.minecraft.client.multiplayer.ServerData;
import drop.mods.ModDraggableDisplayText;

public class ServerAddress extends ModDraggableDisplayText {
	private ServerData dummyServerData = new ServerData("Example", "mc.example.org", false);
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiModDraggableDisplayText(previousGuiScreen, this);
	}

	@Override
	public int getWidth() {
		return font.getStringWidth(brackets.wrap(dummyServerData.serverIP)) + (showBackground ? 20 : 0);
	}

	@Override
	public int getHeight() {
		return showBackground ? 18 : font.FONT_HEIGHT;
	}

	@Override
	public void render(ScreenPosition pos) {
    	if (mc.getCurrentServerData() != null) {
    		drawServerAddress(pos, mc.getCurrentServerData());
    	}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		drawServerAddress(pos, dummyServerData);
	}
	
	private void drawServerAddress(ScreenPosition pos, ServerData serverData) {
		if (showBackground) {
			drawAlignedRect(pos, brackets.wrap(serverData.serverIP));
			drawCenteredAlignedText(pos, brackets.wrap(serverData.serverIP), pos.getAbsoluteX(), pos.getAbsoluteY(), textColor, textShadow, textChroma);
    	} else {
		    drawAlignedText(brackets.wrap(serverData.serverIP), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, textColor, textShadow, textChroma);
    	}
	}
}
