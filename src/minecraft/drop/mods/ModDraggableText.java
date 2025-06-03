package drop.mods;

import java.awt.Color;

import drop.ColorManager;
import drop.gui.GuiDropClientScreen;

public abstract class ModDraggableText extends ModDraggable {
	protected ColorManager textColor = ColorManager.fromColor(Color.WHITE, false);
	protected boolean textShadow = true;
	
	public ModDraggableText() {
		setTextColor((int) ((long) getFromFile("textColor", textColor.getRGB())));
		setTextChroma((boolean) getFromFile("textChroma", textColor.isChromaToggled()));
		setTextShadow((boolean) getFromFile("textShadow", textShadow));
	}
	
	public void setTextColor(int rgb) {
		this.textColor = ColorManager.fromRGB(rgb, textColor.isChromaToggled());
		
		setToFile("textColor", rgb);
	}
	
	public ColorManager getTextColor() {
		return textColor;
	}
	
	public void setTextShadow(boolean enabled) {
		textShadow = enabled;
		
		setToFile("textShadow", enabled);
	}
	
	public boolean isTextShadowEnabled() {
		return textShadow;
	}
	
	public void setTextChroma(boolean enabled) {
		textColor.setChromaToggled(enabled);
		
		setToFile("textChroma", enabled);
	}
	
	public boolean isTextChromaEnabled() {
		return textColor.isChromaToggled();
	}
}
