package drop.mods.impl;

import java.awt.Color;

import drop.ColorManager;
import drop.FileManager;
import drop.mods.Mod;

public class Chat extends Mod {
	private boolean chatHeightFix = true;
	private boolean textShadow = true;
	private int backgroundOpacity = 127;
	
	public Chat() {
		setChatHeightFix((boolean) getFromFile("chatHeightFix", chatHeightFix));
		setTextShadow((boolean) getFromFile("textShadow", textShadow));
		setBackgroundOpacity((int) ((long) getFromFile("backgroundOpacity", backgroundOpacity)));
	}
	
	public void setChatHeightFix(boolean enabled) {
		chatHeightFix = enabled;
		
		setToFile("chatHeightFix", enabled);
	}
	
	public boolean isChatHeightFixEnabled() {
		return chatHeightFix;
	}
	
	public void setTextShadow(boolean enabled) {
		textShadow = enabled;
		
		setToFile("textShadow", enabled);
	}
	
	public boolean isTextShadowEnabled() {
		return textShadow;
	}
	
	public void setBackgroundOpacity(int opacity) {
		backgroundOpacity = opacity;
		
		setToFile("backgroundOpacity", opacity);
	}
	
	public int getBackgroundOpacity() {
		return backgroundOpacity;
	}
}
