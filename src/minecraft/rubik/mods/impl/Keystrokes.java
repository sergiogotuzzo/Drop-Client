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
		WASD_JUMP(Key.W, Key.A, Key.S, Key.D, new Key(EnumChatFormatting.STRIKETHROUGH + "----", Minecraft.getMinecraft().gameSettings.keyBindJump, 1, 41, 58, 12)),
		WASD_MOUSE_JUMP(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB, new Key(EnumChatFormatting.STRIKETHROUGH + "----", Minecraft.getMinecraft().gameSettings.keyBindJump, 1, 61, 58, 12));
	
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
		private static final Key W = new Key("W", Minecraft.getMinecraft().gameSettings.keyBindForward, 21, 1, 18, 18);
		private static final Key A = new Key("A", Minecraft.getMinecraft().gameSettings.keyBindLeft, 1, 21, 18, 18);
		private static final Key S = new Key("S", Minecraft.getMinecraft().gameSettings.keyBindBack, 21, 21, 18, 18);
		private static final Key D = new Key("D", Minecraft.getMinecraft().gameSettings.keyBindRight, 41, 21, 18, 18);
		private static final Key LMB = new Key("LMB", Minecraft.getMinecraft().gameSettings.keyBindAttack, 1, 41, 28, 18);
		private static final Key RMB = new Key("RMB", Minecraft.getMinecraft().gameSettings.keyBindUseItem, 31, 41, 28, 18);
		
		private final String name;
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
	
	private ColorManager pressedColor = new ColorManager(Color.BLACK);
	private ColorManager releasedColor = new ColorManager(Color.WHITE);
	private boolean shadow = true;
	private KeystrokesMode mode = KeystrokesMode.WASD_MOUSE_JUMP;

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
	        
	        Gui.drawRect(
	                pos.getAbsoluteX() + key.getX(),
	                pos.getAbsoluteY() + key.getY(),
	                pos.getAbsoluteX() + key.getX() + key.getWidth(),
	                pos.getAbsoluteY() + key.getY() + key.getHeight(),
	                key.isDown() ? new Color(255, 255, 255, 102).getRGB() : new Color(0, 0, 0, 102).getRGB()
	                );
	        
	        if (shadow) {
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
	
	public ColorManager getPressedColorManager() {
		return pressedColor;
	}
	
	public Color getPressedColor() {
		return pressedColor.getColor();
	}
	
	public ColorManager getReleasedColorManager() {
		return releasedColor;
	}
	
	public Color getReleasedColor() {
		return releasedColor.getColor();
	}
	
	public void setShadowEnabled(boolean enabled) {
		shadow = enabled;
	}
	
	public boolean isShadowEnabled() {
		return shadow;
	}
	
	public void setMode(KeystrokesMode mode) {
		this.mode = mode;
	}
	
	public KeystrokesMode getMode() {
		return mode;
	}
}
