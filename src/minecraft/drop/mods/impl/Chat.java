package drop.mods.impl;

import java.awt.Color;

import drop.ColorManager;
import drop.FileManager;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiChat;
import drop.mods.Mod;

public class Chat extends Mod {
	private boolean chatHeightFix = true;
	private boolean textShadow = true;
	private int backgroundOpacity = 127;
	
	public Chat() {
		super(true);
		
		setChatHeightFix(getBooleanFromFile("chatHeightFix", chatHeightFix));
		setTextShadow(getBooleanFromFile("textShadow", textShadow));
		setBackgroundOpacity(getIntFromFile("backgroundOpacity", backgroundOpacity));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiChat(previousGuiScreen);
	}
	
	public void setChatHeightFix(boolean enabled) {
		chatHeightFix = enabled;
		
		setToFile("chatHeightFix", enabled);
	}
	
	public boolean isChatHeightFixToggled() {
		return chatHeightFix;
	}
	
	public void setTextShadow(boolean enabled) {
		textShadow = enabled;
		
		setToFile("textShadow", enabled);
	}
	
	public boolean isTextShadowToggled() {
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
