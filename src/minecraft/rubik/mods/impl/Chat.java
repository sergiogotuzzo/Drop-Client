package rubik.mods.impl;

import rubik.FileManager;
import rubik.mods.Mod;

public class Chat extends Mod {
	private boolean textShadow = true;
	private boolean transparentBackground = false;
	private boolean chatHeightFix = true;
	
	public Chat() {
		setTextShadow((boolean) getFromFile("textShadow", textShadow));
		setTransparentBackground((boolean) getFromFile("transparentBackground", transparentBackground));
	}
	
	public void setTextShadow(boolean enabled) {
		textShadow = enabled;
		
		setToFile("textShadow", enabled);
	}
	
	public boolean isTextShadowEnabled() {
		return textShadow;
	}
	
	public void setTransparentBackground(boolean enabled) {
		transparentBackground = enabled;
		
		setToFile("transparentBackground", enabled);
	}
	
	public boolean isTransparentBackgroundEnabled() {
		return transparentBackground;
	}
	
	public void setChatHeightFix(boolean enabled) {
		chatHeightFix = enabled;
		
		setToFile("chatHeightFix", enabled);
	}
	
	public boolean isChatHeightFixEnabled() {
		return chatHeightFix;
	}
}
