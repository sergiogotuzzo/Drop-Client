package rubik.mods.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumChatFormatting;
import rubik.ColorManager;
import rubik.gui.hud.ScreenPosition;
import rubik.mods.ModDraggable;

public class Keystrokes extends ModDraggable {
	public static enum KeystrokesMode {
		WASD(Key.W, Key.A, Key.S, Key.D),
		WASD_MOUSE(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB),
		WASD_JUMP(Key.W, Key.A, Key.S, Key.D, new Key(EnumChatFormatting.STRIKETHROUGH + "---", Minecraft.getMinecraft().gameSettings.keyBindJump, 0, 40, 58, 10)),
		WASD_MOUSE_JUMP(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB, new Key(EnumChatFormatting.STRIKETHROUGH + "---", Minecraft.getMinecraft().gameSettings.keyBindJump, 0, 60, 58, 10)),
		MOUSE(new Key("LMB", Minecraft.getMinecraft().gameSettings.keyBindAttack, 0, 0, 28, 18), new Key("RMB", Minecraft.getMinecraft().gameSettings.keyBindUseItem, 30, 0, 28, 18)),
		JUMP(new Key(EnumChatFormatting.STRIKETHROUGH + "---", Minecraft.getMinecraft().gameSettings.keyBindJump, 0, 0, 58, 10)),
		MOUSE_JUMP(new Key("LMB", Minecraft.getMinecraft().gameSettings.keyBindAttack, 0, 0, 28, 18), new Key("RMB", Minecraft.getMinecraft().gameSettings.keyBindUseItem, 30, 0, 28, 18), new Key(EnumChatFormatting.STRIKETHROUGH + "---", Minecraft.getMinecraft().gameSettings.keyBindJump, 0, 20, 58, 10)),
		NONE();
	
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

	private boolean pressedTextShadow = true;
	private boolean releasedTextShadow = true;
	private ColorManager pressedTextColor = ColorManager.fromColor(Color.BLACK);
	private ColorManager releasedTextColor = ColorManager.fromColor(Color.WHITE);
	private boolean showMovementKeys = true;
	private boolean showMouse = true;
	private boolean showSpacebar = true;
	private boolean arrows = false;
	private boolean pressedTextChroma = false;
	private boolean releasedTextChroma = false;
	
	private List<Long> leftClicks = new ArrayList<>();
    private boolean wasLeftPressed;
    private long lastLeftPressed;
    
    private List<Long> rightClicks = new ArrayList<>();
    private boolean wasRightPressed;
    private long lastRightPressed;
	
	public Keystrokes() {
		setPressedTextShadow((boolean) getFromFile("pressedTextShadow", pressedTextShadow));
		setReleasedTextShadow((boolean) getFromFile("releasedTextShadow", releasedTextShadow));
		setPressedTextColor((int) ((long) getFromFile("pressedTextColor", pressedTextColor.getRGB())));
		setReleasedTextColor((int) ((long) getFromFile("releasedTextColor", releasedTextColor.getRGB())));
		setShowMovementKeys((boolean) getFromFile("showMovementKeys", showMovementKeys));
		setShowMouse((boolean) getFromFile("showMouse", showMouse));
		setShowSpacebar((boolean) getFromFile("showSpacebar", showSpacebar));
		setArrows((boolean) getFromFile("arrows", arrows));
		setPressedTextChroma((boolean) getFromFile("pressedTextChroma", pressedTextChroma));
		setReleasedTextChroma((boolean) getFromFile("releasedTextChroma", releasedTextChroma));
	}

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
		final boolean leftPressed = Mouse.isButtonDown(0);
        final boolean rightPressed = Mouse.isButtonDown(1);

        if (leftPressed != this.wasLeftPressed) {
            this.lastLeftPressed = System.currentTimeMillis();
            this.wasLeftPressed = leftPressed;

            if (leftPressed) {
                this.leftClicks.add(this.lastLeftPressed);
            }
        }

        if (rightPressed != this.wasRightPressed) {
            this.lastRightPressed = System.currentTimeMillis();
            this.wasRightPressed = rightPressed;

            if (rightPressed) {
                this.rightClicks.add(this.lastRightPressed);
            }
        }
        
	    for (Key key : mode.getKeys()) {
	        int textWidth = font.getStringWidth(key.getName());
	        
	        if (arrows) {
	        	switch (key.getName()) {
		        	case "W":
		        		key.setName("▲");
		        		break;
		        	case "A":
		        		key.setName("◄");
		        		break;
		        	case "S":
		        		key.setName("▼");
		        		break;
		        	case "D":
		        		key.setName("►");
		        		break;
	        	}
	        } else {
	        	switch (key.getName()) {
		        	case "▲":
		        		key.setName("W");
		        		break;
		        	case "◄":
		        		key.setName("A");
		        		break;
		        	case "▼":
		        		key.setName("S");
		        		break;
		        	case "►":
		        		key.setName("D");
		        		break;
	        	}
	        }
	        
	        drawRect(
	                pos.getAbsoluteX() + key.getX(),
	                pos.getAbsoluteY() + key.getY(),
	                pos.getAbsoluteX() + key.getX() + key.getWidth(),
	                pos.getAbsoluteY() + key.getY() + key.getHeight(),
	                key.isDown() ? ColorManager.fromColor(Color.WHITE).setAlpha(102).getRGB() : ColorManager.fromColor(Color.BLACK).setAlpha(102).getRGB()
	                );
	        
	        drawText(
	        		key.getName(),
	                pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2,
	                pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 - 4,
	                key.isDown() ? pressedTextColor.getRGB() : releasedTextColor.getRGB(),
	                key.isDown() ? pressedTextShadow : releasedTextShadow,
	                key.isDown() ? pressedTextChroma : releasedTextChroma
	        		);
	    }
	}
	
	private int getCPS(List<Long> clicks) {
        final long time = System.currentTimeMillis();

        clicks.removeIf((aLong) -> aLong + 1000 < time);

        return clicks.size();
    }
	
	private void updateMode() {
		if (showMovementKeys && showMouse && showSpacebar) {
			mode = KeystrokesMode.WASD_MOUSE_JUMP;
		} else if (showMovementKeys && showMouse && !showSpacebar) {
			mode = KeystrokesMode.WASD_MOUSE;
		} else if (showMovementKeys && !showMouse && showSpacebar) {
			mode = KeystrokesMode.WASD_JUMP;
		} else if (showMovementKeys && !showMouse && !showSpacebar) {
			mode = KeystrokesMode.WASD;
		} else if (!showMovementKeys && showMouse && !showSpacebar) {
			mode = KeystrokesMode.MOUSE;
		} else if (!showMovementKeys && !showMouse && showSpacebar) {
			mode = KeystrokesMode.JUMP;
		} else if (!showMovementKeys && showMouse && showSpacebar) {
			mode = KeystrokesMode.MOUSE_JUMP;
		} else if (!showMovementKeys && !showMouse && !showSpacebar) {
			mode = KeystrokesMode.NONE;
		}
	}
	
	public void setPressedTextShadow(boolean enabled) {
		pressedTextShadow = enabled;
		
		setToFile("pressedTextShadow", enabled);
	}
	
	public boolean isPressedTextShadowEnabled() {
		return pressedTextShadow;
	}
	
	public void setReleasedTextShadow(boolean enabled) {
		releasedTextShadow = enabled;
		
		setToFile("releasedTextShadow", enabled);
	}
	
	public boolean isReleasedTextShadowEnabled() {
		return releasedTextShadow;
	}
	
	public void setPressedTextColor(int rgb) {
		this.pressedTextColor = ColorManager.fromRGB(rgb);
		
		setToFile("pressedTextColor", rgb);
	}
	
	public ColorManager getPressedTextColor() {
		return pressedTextColor;
	}
	
	public void setReleasedTextColor(int rgb) {
		this.releasedTextColor = ColorManager.fromRGB(rgb);
		
		setToFile("releasedTextColor", rgb);
	}
	
	public ColorManager getReleasedTextColor() {
		return releasedTextColor;
	}
	
	public void setShowMovementKeys(boolean enabled) {
		showMovementKeys = enabled;
		
		setToFile("showMovementKeys", enabled);
		
		updateMode();
	}
	
	public boolean isShowMovementKeysEnabled() {
		return showMovementKeys;
	}
	
	public void setShowMouse(boolean enabled) {
		showMouse = enabled;
		
		setToFile("showMouse", enabled);
		
		updateMode();
	}
	
	public boolean isShowMouseEnabled() {
		return showMouse;
	}
	
	public void setShowSpacebar(boolean enabled) {
		showSpacebar = enabled;
		
		setToFile("showSpacebar", enabled);
		
		updateMode();
	}
	
	public boolean isShowSpacebarEnabled() {
		return showSpacebar;
	}
	
	public void setArrows(boolean enabled) {
		arrows = enabled;
		
		setToFile("arrows", enabled);
	}
	
	public boolean isArrowsEnabled() {
		return arrows;
	}
	
	public void setPressedTextChroma(boolean enabled) {
		this.pressedTextChroma = enabled;
		
		setToFile("pressedTextChroma", enabled);
	}
	
	public boolean isPressedTextChromaEnabled() {
		return pressedTextChroma;
	}
	
	public void setReleasedTextChroma(boolean enabled) {
		this.releasedTextChroma = enabled;
		
		setToFile("releasedTextChroma", enabled);
	}
	
	public boolean isReleasedTextChromaEnabled() {
		return releasedTextChroma;
	}
}
