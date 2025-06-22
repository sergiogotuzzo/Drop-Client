package drop.mods.impl;

import drop.mods.hud.ScreenPosition;
import net.minecraft.client.multiplayer.ServerData;
import drop.mods.ModDraggableDisplayText;

public class ServerAddress extends ModDraggableDisplayText {
	public ServerAddress() {
		super(false, 0.5, 0.5, "mc.example.org");
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
    		drawTextToRender(pos, mc.getCurrentServerData().serverIP);
    	}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		drawTextToRender(pos, dummyServerData.serverIP);
	}
	
	@Override
	public void drawTextToRender(ScreenPosition pos, String textToRender) {
		if (showBackground) {
			drawAlignedRect(pos, textToRender, 8, backgroundColor.getRGB());
			drawCenteredAlignedText(pos, textToRender, 8, pos.getAbsoluteX(), pos.getAbsoluteY(), textColor, textShadow);
    	} else {
		    drawAlignedText(brackets.wrap(textToRender), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, textColor, textShadow);
    	}
	}
}
