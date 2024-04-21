package rubik.mods.impl;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import rubik.event.EventTarget;
import rubik.event.impl.KeyEvent;
import rubik.mods.Mod;

public class Freelook extends Mod {
	private boolean returnOnRelease = true;
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
			} else if (returnOnRelease) {
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
			
			cameraYaw += f3 * 0.15F;
			cameraPitch -= f4 * 0.15F;
			
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
