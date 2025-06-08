package drop.mods;

import java.awt.Color;

import drop.ColorManager;
import drop.gui.GuiDropClientScreen;

public abstract class ModDraggableText extends ModDraggable {
	protected ColorManager textColor = ColorManager.fromColor(Color.WHITE, false);
	protected boolean textShadow = true;
	
	public ModDraggableText(boolean enabled, double relativeX, double relativeY) {
		super(enabled, relativeX, relativeY);
		
		setTextColor(getIntFromFile("textColor", textColor.getRGB()));
		setTextChroma(getBooleanFromFile("textChroma", textColor.isChromaToggled()));
		setTextShadow(getBooleanFromFile("textShadow", textShadow));
	}
	
	public void setTextColor(int rgb) {
		textColor.setRGB(rgb);
		
		setToFile("textColor", rgb);
	}
	
	public ColorManager getTextColor() {
		return textColor;
	}
	
	public void setTextChroma(boolean enabled) {
		textColor.setChromaToggled(enabled);
		
		setToFile("textChroma", enabled);
	}
	
	public boolean isTextChromaToggled() {
		return textColor.isChromaToggled();
	}
	
	public void setTextShadow(boolean enabled) {
		textShadow = enabled;
		
		setToFile("textShadow", enabled);
	}
	
	public boolean isTextShadowToggled() {
		return textShadow;
	}
}
