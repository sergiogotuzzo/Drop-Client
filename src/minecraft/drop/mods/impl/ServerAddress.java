package drop.mods.impl;

import drop.mods.hud.ScreenPosition;
import net.minecraft.client.multiplayer.ServerData;
import drop.mods.ModDraggableDisplayText;

public class ServerAddress extends ModDraggableDisplayText {
	public ServerAddress() {
		super(false, 0.5, 0.5);
	}
	
	private ServerData dummyServerData = new ServerData("Example", "mc.example.org", false);

	@Override
	public int getWidth() {
		return showBackground ? font.getStringWidth(dummyServerData.serverIP) + 8 : font.getStringWidth(brackets.wrap(dummyServerData.serverIP));
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
			drawAlignedRect(pos, serverData.serverIP, 8);
			drawCenteredAlignedText(pos, serverData.serverIP, 8, pos.getAbsoluteX(), pos.getAbsoluteY(), textColor, textShadow);
    	} else {
		    drawAlignedText(brackets.wrap(serverData.serverIP), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, textColor, textShadow);
    	}
	}
}
