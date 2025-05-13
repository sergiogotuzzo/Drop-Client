package rubik.mods.impl;

import java.awt.Color;

import rubik.ColorManager;
import rubik.FileManager;
import rubik.mods.Mod;

public class Chat extends Mod {
	private boolean textShadow = true;
	private boolean transparentBackground = false;
	private boolean chatHeightFix = true;
	private int backgroundOpacity = 127;
	
	public Chat() {
		setTextShadow((boolean) getFromFile("textShadow", textShadow));
		setChatHeightFix((boolean) getFromFile("chatHeightFix", chatHeightFix));
		setBackgroundOpacity((int) ((long) getFromFile("backgroundOpacity", backgroundOpacity)));
	}
	
	public void setTextShadow(boolean enabled) {
		textShadow = enabled;
		
		setToFile("textShadow", enabled);
	}
	
	public boolean isTextShadowEnabled() {
		return textShadow;
	}
	
	public void setChatHeightFix(boolean enabled) {
		chatHeightFix = enabled;
		
		setToFile("chatHeightFix", enabled);
	}
	
	public boolean isChatHeightFixEnabled() {
		return chatHeightFix;
	}
	
	public void setBackgroundOpacity(int opacity) {
		backgroundOpacity = opacity;
		
		setToFile("backgroundOpacity", opacity);
	}
	
	public int getBackgroundOpacity() {
		return backgroundOpacity;
	}
}
