package drop.mods.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import drop.gui.GuiSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumChatFormatting;
import drop.gui.hud.ScreenPosition;
import drop.mods.option.ParentOption;
import drop.mods.option.type.BooleanOption;
import drop.mods.option.type.ColorOption;
import drop.mods.ModColor;
import drop.mods.ModDraggable;
import drop.mods.ModOptions;

public class Keystrokes extends ModDraggable {
	public Keystrokes() {
		super(true, 0.5, 0.5);
		
		this.options = new ModOptions(
				new ColorOption(this, "textColor", ModColor.fromColor(Color.WHITE, false), new GuiSettings(1, "Text Color", true, false)),
				new BooleanOption(this, "textShadow", true, new GuiSettings(2, "Text Shadow")),
				new ColorOption(this, "pressedTextColor", ModColor.fromColor(Color.WHITE, false), new GuiSettings(3, "Text Color (Pressed)", true, false)),
				new BooleanOption(this, "pressedTextShadow", true, new GuiSettings(4, "Text Shadow (Pressed)")),
				new ColorOption(this, "backgroundColor", ModColor.fromRGB(0, 0, 0, 102, false), new GuiSettings(5, "Background Color", false, true)),
				new ColorOption(this, "pressedBackgroundColor", ModColor.fromRGB(255, 255, 255, 102, false), new GuiSettings(6, "Background Color (Pressed)", false, true)),
				new BooleanOption(this, "showBorder", false, new GuiSettings(11, "Border")),
				new ColorOption(this, "borderColor", ModColor.fromRGB(0, 0, 0, 255, false), new ParentOption("showBorder"), new GuiSettings(12, "Border Color", false, true)),
				new ColorOption(this, "pressedBorderColor", ModColor.fromRGB(0, 0, 0, 255, false), new ParentOption("showBorder"), new GuiSettings(13, "Border Color (Pressed)", false, true)),
				new BooleanOption(this, "showMovementKeys", true, new GuiSettings(7, "Show Movement Keys")),
				new BooleanOption(this, "showMouse", true, new GuiSettings(8, "Show Mouse")),
				new BooleanOption(this, "showSpacebar", true, new GuiSettings(9, "Show Spacebar")),
				new BooleanOption(this, "useArrows", false, new ParentOption("showMovementKeys"), new GuiSettings(10, "Use Arrows"))
				);
				
		saveOptions();
		
		updateMode();
	}
	
	public static enum KeystrokesMode {
		WASD(Key.W, Key.A, Key.S, Key.D),
		WASD_MOUSE(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB),
		WASD_JUMP(Key.W, Key.A, Key.S, Key.D, new Key(EnumChatFormatting.STRIKETHROUGH + "---", Minecraft.getMinecraft().gameSettings.keyBindJump, 0, 36, 53, 8)),
		WASD_MOUSE_JUMP(Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB, new Key(EnumChatFormatting.STRIKETHROUGH + "---", Minecraft.getMinecraft().gameSettings.keyBindJump, 0, 54, 53, 8)),
		MOUSE(new Key("LMB", Minecraft.getMinecraft().gameSettings.keyBindAttack, 0, 0, 26, 17), new Key("RMB", Minecraft.getMinecraft().gameSettings.keyBindUseItem, 27, 0, 26, 17)),
		JUMP(new Key(EnumChatFormatting.STRIKETHROUGH + "---", Minecraft.getMinecraft().gameSettings.keyBindJump, 0, 0, 53, 8)),
		MOUSE_JUMP(new Key("LMB", Minecraft.getMinecraft().gameSettings.keyBindAttack, 0, 0, 26, 17), new Key("RMB", Minecraft.getMinecraft().gameSettings.keyBindUseItem, 27, 0, 26, 17), new Key(EnumChatFormatting.STRIKETHROUGH + "---", Minecraft.getMinecraft().gameSettings.keyBindJump, 0, 18, 53, 8)),
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
		private static final Key W = new Key("W", Minecraft.getMinecraft().gameSettings.keyBindForward, 18, 0, 17, 17);
		private static final Key A = new Key("A", Minecraft.getMinecraft().gameSettings.keyBindLeft, 0, 18, 17, 17);
		private static final Key S = new Key("S", Minecraft.getMinecraft().gameSettings.keyBindBack, 18, 18, 17, 17);
		private static final Key D = new Key("D", Minecraft.getMinecraft().gameSettings.keyBindRight, 36, 18, 17, 17);
		private static final Key LMB = new Key("LMB", Minecraft.getMinecraft().gameSettings.keyBindAttack, 0, 36, 26, 17);
		private static final Key RMB = new Key("RMB", Minecraft.getMinecraft().gameSettings.keyBindUseItem, 27, 36, 26, 17);
		
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
	public int getWidth() {
		return mode.getWidth();
	}

	@Override
	public int getHeight() {
		return mode.getHeight();
	}

	@Override
	public void render(ScreenPosition pos) {
		updateMode();

	    for (Key key : mode.getKeys()) {
	        int textWidth = font.getStringWidth(key.getName());
	        
	        if (options.getBooleanOption("useArrows").isToggled()) {
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
	                key.isDown() ? options.getColorOption("pressedBackgroundColor").getColor().getRGB() : options.getColorOption("backgroundColor").getColor().getRGB()
	                );
	        
	        if (options.getBooleanOption("showBorder").isToggled()) {
	        	drawHollowRect(
		                pos.getAbsoluteX() + key.getX(),
		                pos.getAbsoluteY() + key.getY(),
		                key.getWidth() - 1,
		                key.getHeight() - 1,
		                key.isDown() ? options.getColorOption("pressedBorderColor").getColor().getRGB() : options.getColorOption("borderColor").getColor().getRGB()
		                );
	        }
	        
	        drawText(
	        		key.getName(),
	                pos.getAbsoluteX() + key.getX() + (key.getWidth() / 2.0F) - (textWidth / 2.0F),
	                pos.getAbsoluteY() + key.getY() + (key.getHeight() / 2.0F) - 4.0F,
	                key.isDown() ? options.getColorOption("pressedTextColor").getColor() : options.getColorOption("textColor").getColor(),
	                key.isDown() ? options.getBooleanOption("pressedTextShadow").isToggled() : options.getBooleanOption("textShadow").isToggled()
	        		);
	    }
	}
	
	private void updateMode() {
		boolean showMovementKeys = options.getBooleanOption("showMovementKeys").isToggled();
		boolean showMouse = options.getBooleanOption("showMouse").isToggled();
		boolean showSpacebar = options.getBooleanOption("showSpacebar").isToggled();
		
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
}
