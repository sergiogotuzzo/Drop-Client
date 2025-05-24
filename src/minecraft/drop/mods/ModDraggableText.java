package drop.mods;

import java.awt.Color;

import drop.ColorManager;

public abstract class ModDraggableText extends ModDraggable {
	protected ColorManager textColor = ColorManager.fromColor(Color.WHITE);
	protected boolean textShadow = true;
	protected boolean textChroma = false;
	
	public ModDraggableText() {
		setTextColor((int) ((long) getFromFile("textColor", textColor.getRGB())));
		setTextShadow((boolean) getFromFile("textShadow", textShadow));
		setTextChroma((boolean) getFromFile("textChroma", textChroma));
	}
	
	public void setTextColor(int rgb) {
		this.textColor = ColorManager.fromRGB(rgb);
		
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
		this.textChroma = enabled;
		
		setToFile("textChroma", enabled);
	}
	
	public boolean isTextChromaEnabled() {
		return textChroma;
	}
}
