package drop.mods.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import drop.ColorManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumChatFormatting;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiKeystrokes;
import drop.mods.hud.ScreenPosition;
import drop.mods.ModDraggableText;

public class Keystrokes extends ModDraggableText {
	private ColorManager pressedTextColor = ColorManager.fromColor(Color.WHITE, false);
	private boolean pressedTextShadow = true;
	private boolean showMovementKeys = true;
	private boolean showMouse = true;
	private boolean showSpacebar = true;
	private boolean useArrows = false;
	
	private List<Long> leftClicks = new ArrayList<>();
    private boolean wasLeftPressed;
    private long lastLeftPressed;
    
    private List<Long> rightClicks = new ArrayList<>();
    private boolean wasRightPressed;
    private long lastRightPressed;
	
	public Keystrokes() {
		super(true, 0.5, 0.5);
		
		setPressedTextColor((int) ((long) getFromFile("pressedTextColor", pressedTextColor.getRGB())));
		setPressedTextChroma((boolean) getFromFile("pressedTextChroma", pressedTextColor.isChromaToggled()));
		setPressedTextShadow((boolean) getFromFile("pressedTextShadow", pressedTextShadow));
		setShowMovementKeys((boolean) getFromFile("showMovementKeys", showMovementKeys));
		setShowMouse((boolean) getFromFile("showMouse", showMouse));
		setShowSpacebar((boolean) getFromFile("showSpacebar", showSpacebar));
		setUseArrows((boolean) getFromFile("useArrows", useArrows));
	}
	
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
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiKeystrokes(previousGuiScreen);
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
	    for (Key key : mode.getKeys()) {
	        int textWidth = font.getStringWidth(key.getName());
	        
	        if (useArrows) {
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
	                key.isDown() ? new Color(255, 255, 255, 102) : new Color(0, 0, 0, 102)
	                );
	        
	        drawText(
	        		key.getName(),
	                pos.getAbsoluteX() + key.getX() + key.getWidth() / 2 - textWidth / 2,
	                pos.getAbsoluteY() + key.getY() + key.getHeight() / 2 - 4,
	                key.isDown() ? pressedTextColor : textColor,
	                key.isDown() ? pressedTextShadow : textShadow
	        		);
	    }
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
	
	public void setPressedTextColor(int rgb) {
		pressedTextColor.setRGB(rgb);
		
		setToFile("pressedTextColor", rgb);
	}
	
	public ColorManager getPressedTextColor() {
		return pressedTextColor;
	}
	
	public void setPressedTextShadow(boolean enabled) {
		pressedTextShadow = enabled;
		
		setToFile("pressedTextShadow", enabled);
	}
	
	public boolean isPressedTextShadowToggled() {
		return pressedTextShadow;
	}
	
	public void setShowMovementKeys(boolean enabled) {
		showMovementKeys = enabled;
		
		setToFile("showMovementKeys", enabled);
		
		updateMode();
	}
	
	public boolean isShowMovementKeysToggled() {
		return showMovementKeys;
	}
	
	public void setShowMouse(boolean enabled) {
		showMouse = enabled;
		
		setToFile("showMouse", enabled);
		
		updateMode();
	}
	
	public boolean isShowMouseToggled() {
		return showMouse;
	}
	
	public void setShowSpacebar(boolean enabled) {
		showSpacebar = enabled;
		
		setToFile("showSpacebar", enabled);
		
		updateMode();
	}
	
	public boolean isShowSpacebarToggled() {
		return showSpacebar;
	}
	
	public void setUseArrows(boolean enabled) {
		useArrows = enabled;
		
		setToFile("useArrows", enabled);
	}
	
	public boolean isUseArrowsToggled() {
		return useArrows;
	}
	
	public void setPressedTextChroma(boolean enabled) {
		pressedTextColor.setChromaToggled(enabled);
		
		setToFile("pressedTextChroma", enabled);
	}
	
	public boolean isPressedTextChromaToggled() {
		return pressedTextColor.isChromaToggled();
	}
}
