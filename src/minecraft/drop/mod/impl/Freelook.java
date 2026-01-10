package drop.mod.impl;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import drop.event.EventTarget;
import drop.event.impl.KeyEvent;
import drop.gui.mod.GuiSettings;
import drop.mod.Mod;
import drop.mod.option.type.BooleanOption;

public class Freelook extends Mod {
	public Freelook() {
		super(true);
				
		saveOptions(
				new BooleanOption(this, "hold", true, new GuiSettings(1, "Hold")),
				new BooleanOption(this, "invertYaw", false, new GuiSettings(2, "Invert Yaw")),
				new BooleanOption(this, "invertPitch", false, new GuiSettings(3, "Invert Pitch"))
				);
	}
	
	private boolean perspectiveToggled = false;
	private float cameraYaw = 0F;
	private float cameraPitch = 0F;
	private int previousPerspective = 0;
	
	@EventTarget
	public void onKey(KeyEvent e) {
		if (Keyboard.getEventKey() == mc.gameSettings.keyBindFreelook.getKeyCode()) {
			if (Keyboard.getEventKeyState()) {
				perspectiveToggled = !perspectiveToggled;
				
				cameraYaw = mc.thePlayer.rotationYaw;
				cameraPitch = mc.thePlayer.rotationPitch;
				
				if (perspectiveToggled) {
					previousPerspective = mc.gameSettings.thirdPersonView;
					mc.gameSettings.thirdPersonView = 1;
				} else {
					mc.gameSettings.thirdPersonView = previousPerspective;
				}
			} else if (getBooleanOption("hold").isToggled()) {
				perspectiveToggled = false;
				mc.gameSettings.thirdPersonView = previousPerspective;
			}
		}
		
		if (Keyboard.getEventKey() == mc.gameSettings.keyBindTogglePerspective.getKeyCode()) {
			perspectiveToggled = false;
		}
	}
	
	public float getCameraYaw() {
		return perspectiveToggled ? cameraYaw : mc.thePlayer.rotationYaw;
	}
	
	public float getCameraPitch() {
		return perspectiveToggled ? cameraPitch : mc.thePlayer.rotationPitch;
	}
	
	public boolean overrideMouse() {
		if (mc.inGameHasFocus && Display.isActive()) {
			if (!perspectiveToggled) {
				return true;
			}
			
			mc.mouseHelper.mouseXYChange();
			
			float sensitivityScale = 0.1F;
			float f1 = mc.gameSettings.mouseSensitivity * sensitivityScale;
			float f2 = f1 * 3 * 8.0F;
			float f3 = mc.mouseHelper.deltaX * f2;
			float f4 = mc.mouseHelper.deltaY * f2;
			
			cameraYaw = getBooleanOption("invertYaw").isToggled() ? cameraYaw - (f3 * 0.15F) : cameraYaw + (f3 * 0.15F);
			cameraPitch = getBooleanOption("invertPitch").isToggled() ? cameraPitch + (f4 * 0.15F) : cameraPitch - (f4 * 0.15F);
			
			if (cameraPitch > 90) {
				cameraPitch = 90;
			}
			
			if (cameraPitch < -90) {
				cameraPitch = -90;
			}
		}
		
		return false;
	}
}
