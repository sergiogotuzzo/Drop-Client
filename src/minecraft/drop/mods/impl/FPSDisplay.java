package drop.mods.impl;

import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiFPSDisplay;
import drop.gui.mod.hud.ScreenPosition;
import drop.mods.ModDraggableText;

public class FPSDisplay extends ModDraggableText {
	private boolean showBackground = false;
	
	public FPSDisplay() {
		setShowBackground((boolean) getFromFile("showBackground", showBackground));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiFPSDisplay(previousGuiScreen);
	}
	
	@Override
	public int getWidth() {
		return 58;
	}

	@Override
	public int getHeight() {
		return 18;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (showBackground) {
			drawRect(pos);
		}
		
		String text = mc.getDebugFPS() + " FPS";
		
		drawCenteredText(brackets.wrap(text), pos.getAbsoluteX(), pos.getAbsoluteY(), textColor, textShadow, textChroma);
	}
	
	public void setShowBackground(boolean enabled) {
		showBackground = enabled;
		
		setToFile("showBackground", enabled);
	}
	
	public boolean isShowBackgroundEnabled() {
		return showBackground;
	}
}
