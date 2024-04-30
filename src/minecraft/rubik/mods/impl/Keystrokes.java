package rubik.mods.impl;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumChatFormatting;
import rubik.ColorManager;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggable;

public class Keystrokes extends ModDraggable {
	public static enum KeystrokesMode {
		WASD(Key.W, Key.A, Key.S, Key.D),
		WASD_MOUSE(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB),
		WASD_JUMP(Key.W, Key.A, Key.S, Key.D, new Key(EnumChatFormatting.STRIKETHROUGH + "----", Minecraft.getMinecraft().gameSettings.keyBindJump, 0, 40, 58, 12)),
		WASD_MOUSE_JUMP(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB, new Key(EnumChatFormatting.STRIKETHROUGH + "----", Minecraft.getMinecraft().gameSettings.keyBindJump, 0, 60, 58, 12));
	
		private final Key[] keys;
		private int width = 0;
		private int height = 0;
		
		private KeystrokesMode(Key... keysIn) {
			this.keys = keysIn;
			
			for (Key key : keys) {
				this.width = Math.max(this.width, key.getX() + key.getWidth());
				this.height = Math.max(this.height, key.getY() + key.getHeight());
			}
		}
		
		public Key[] getKeys() {
			return keys;
		}
		
		public int getWidth() {
			return width;
		}
		
		public int getHeight() {
			return height;
		}
	}
	
	private static class Key {
		private static final Key W = new Key("W", Minecraft.getMinecraft().gameSettings.keyBindForward, 20, 0, 18, 18);
		private static final Key A = new Key("A", Minecraft.getMinecraft().gameSettings.keyBindLeft, 0, 20, 18, 18);
		private static final Key S = new Key("S", Minecraft.getMinecraft().gameSettings.keyBindBack, 20, 20, 18, 18);
		private static final Key D = new Key("D", Minecraft.getMinecraft().gameSettings.keyBindRight, 40, 20, 18, 18);
		private static final Key LMB = new Key("LMB", Minecraft.getMinecraft().gameSettings.keyBindAttack, 0, 40, 28, 18);
		private static final Key RMB = new Key("RMB", Minecraft.getMinecraft().gameSettings.keyBindUseItem, 30, 40, 28, 18);
		
		private String name;
		
		private final KeyBinding keyBind;
		private final int x;
		private final int y;
		private final int width;
		private final int height;
		
		public Key(String name, KeyBinding keyBind, int x, int y, int width, int height) {
			this.name = name;
			this.keyBind = keyBind;
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}
		
		public boolean isDown() {
			return keyBind.isKeyDown();
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		public KeyBinding keyBind() {
			return keyBind;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public int getWidth() {
			return width;
		}
		
		public int getHeight() {
			return height;
		}
	}
	
	private KeystrokesMode mode = KeystrokesMode.WASD_MOUSE_JUMP;

	private boolean textShadow = true;
	private ColorManager pressedColor = new ColorManager(Color.BLACK);
	private ColorManager releasedColor = new ColorManager(Color.WHITE);
	private boolean showMouse = true;
	private boolean showSpacebar = true;
	private boolean arrows = false;

	@Override
	public int getWidth() {
		return mode.getWidth();
	}

	@Override
	public int getHeight() {
		return mode.getHeight();
	}

	@Override
	public void render(ScreenPosition pos) {
	    for (Key key : mode.getKeys()) {
	        int textWidth = font.getStringWidth(key.getName());
	        
	        if (arrows) {
	        	if (key.getName() == "W") {
	        		key.setName("▲");
	        	}
	        	
	        	if (key.getName() == "A") {
	        		key.setName("◄");
	        	}
	        	
	        	if (key.getName() == "S") {
	        		key.setName("▼");
	        	}
	        	
	        	if (key.getName() == "D") {
	        		key.setName("►");
	        	}
	        } else {
	        	if (key.getName() == "▲") {
	        		key.setName("W");
	        	}
	        	
	        	if (key.getName() == "◄") {
	        		key.setName("A");
	        	}
	        	
	        	if (key.getName() == "▼") {
	        		key.setName("S");
	        	}
	        	
	        	if (key.getName() == "►") {
	        		key.setName("D");
	        	}
	        }
	        
	        Gui.drawRect(
	                pos.getAbsoluteX() + key.getX(),
	                pos.getAbsoluteY() + key.getY(),
	                pos.getAbsoluteX() + key.getX() + key.getWidth(),
	                pos.getAbsoluteY() + key.getY() + key.getHeight(),
	                key.isDown() ? new Color(255, 255, 255, 102).getRGB() : new Color(0, 0, 0, 102).getRGB()
	                );
	        
	        if (textShadow) {
	        	font.drawStringWithShadow(
		                key.getName(),
		                pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2,
		                pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 - 4,
		                key.isDown() ? pressedColor.getRGB() : releasedColor.getRGB()
		                );
	        } else {
	        	font.drawString(
		                key.getName(),
		                pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2,
		                pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 - 4,
		                key.isDown() ? pressedColor.getRGB() : releasedColor.getRGB()
		                );
	        }
	    }
	}
	
	private void updateMode() {
		if (showMouse && showSpacebar) {
			mode = KeystrokesMode.WASD_MOUSE_JUMP;
		} else if (showMouse && !showSpacebar) {
			mode = KeystrokesMode.WASD_MOUSE;
		} else if (!showMouse && showSpacebar) {
			mode = KeystrokesMode.WASD_JUMP;
		} else if (!showMouse && !showSpacebar) {
			mode = KeystrokesMode.WASD;
		}
	}
	
	public void setTextShadow(boolean enabled) {
		textShadow = enabled;
	}
	
	public boolean isTextShadowEnabled() {
		return textShadow;
	}
	
	public ColorManager getPressedColor() {
		return pressedColor;
	}
	
	public ColorManager getReleasedColor() {
		return releasedColor;
	}
	
	public void setShowMouse(boolean enabled) {
		showMouse = enabled;
		
		updateMode();
	}
	
	public boolean isShowMouseEnabled() {
		return showMouse;
	}
	
	public void setShowSpacebar(boolean enabled) {
		showSpacebar = enabled;
		
		updateMode();
	}
	
	public boolean isShowSpacebarEnabled() {
		return showSpacebar;
	}
	
	public void setArrows(boolean enabled) {
		arrows = enabled;
	}
	
	public boolean isArrowsEnabled() {
		return arrows;
	}
}
