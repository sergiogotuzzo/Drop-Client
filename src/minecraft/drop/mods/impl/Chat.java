package drop.mods.impl;

import java.awt.Color;

import drop.ColorManager;
import drop.FileManager;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiChat;
import drop.mods.Mod;

public class Chat extends Mod {
	private boolean chatHeightFix = true;
	private boolean textShadow = false;
	private boolean compactChat = true;
	private ColorManager backgroundColor = ColorManager.fromRGB(0, 0, 0, 127, false);
	
	public Chat() {
		super(true);
		
		setChatHeightFix(getBooleanFromFile("chatHeightFix", chatHeightFix));
		setCompactChat(getBooleanFromFile("compactChat", compactChat));
		setTextShadow(getBooleanFromFile("textShadow", textShadow));
		setBackgroundColor(getIntFromFile("backgroundColor", backgroundColor.getRGB()));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiChat(previousGuiScreen);
	}
	
	public void setChatHeightFix(boolean toggled) {
		chatHeightFix = toggled;
		
		setToFile("chatHeightFix", toggled);
	}
	
	public boolean isChatHeightFixToggled() {
		return chatHeightFix;
	}
	
	public void setCompactChat(boolean toggled) {
		compactChat = toggled;
		
		setToFile("compactChat", toggled);
	}
	
	public boolean isCompactChatToggled() {
		return compactChat;
	}
	
	public void setTextShadow(boolean toggled) {
		textShadow = toggled;
		
		setToFile("textShadow", toggled);
	}
	
	public boolean isTextShadowToggled() {
		return textShadow;
	}
	
	public void setBackgroundColor(int rgb) {
		backgroundColor = backgroundColor.fromRGB(rgb, false);
		
		setToFile("backgroundColor", rgb);
	}
	
	public ColorManager getBackgroundColor() {
		return backgroundColor;
	}
}
