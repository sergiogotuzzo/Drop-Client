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
		return font.getStringWidth(brackets.wrap(getCurrentServerData().serverIP)) + (showBackground ? 20 : 0);
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
		drawServerAddress(pos, getCurrentServerData());
	}
	
	private void drawServerAddress(ScreenPosition pos, ServerData serverData) {
		if (showBackground) {
    		int rectLeft;
    		int rectRight;
    		int textX;
    		
    		if (pos.getRelativeX() < 1.0 / 3.0) {
    			rectLeft = pos.getAbsoluteX();
    			rectRight = pos.getAbsoluteX() + font.getStringWidth(brackets.wrap(serverData.serverIP)) + 20;
    			textX = pos.getAbsoluteX() + 20 / 2;
    		} else if (pos.getRelativeX() > 2.0 / 3.0) {
    			rectLeft = pos.getAbsoluteX() - font.getStringWidth(brackets.wrap(serverData.serverIP)) + getWidth() - 20;
    			rectRight = pos.getAbsoluteX() + getWidth();
    			textX = pos.getAbsoluteX() + getWidth() - font.getStringWidth(brackets.wrap(serverData.serverIP)) - 20 / 2;
    		} else {
    			rectLeft = pos.getAbsoluteX() - (font.getStringWidth(brackets.wrap(serverData.serverIP)) + 20) / 2 + getWidth() / 2;
    			rectRight = pos.getAbsoluteX() + (font.getStringWidth(brackets.wrap(serverData.serverIP)) + 20) / 2 + getWidth() / 2;
    			textX = pos.getAbsoluteX() + (getWidth() - font.getStringWidth(brackets.wrap(serverData.serverIP))) / 2;
    		}
    		
	    	drawRect(rectLeft, pos.getAbsoluteY(), rectRight, pos.getAbsoluteY() + getHeight());
			drawText(brackets.wrap(serverData.serverIP), textX, pos.getAbsoluteY() + getHeight() / 2 - 4, textColor, textShadow, textChroma);
    	} else {
		    drawAlignedText(brackets.wrap(serverData.serverIP), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, textColor, textShadow, textChroma);
    	}
	}
	
	private ServerData getCurrentServerData() {
		return mc.getCurrentServerData() != null ? mc.getCurrentServerData() : dummyServerData;
	}
}
