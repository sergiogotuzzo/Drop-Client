package rubik.mods.impl;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import rubik.events.EventTarget;
import rubik.events.impl.KeyEvent;
import rubik.mods.Mod;

public class Freelook extends Mod {
	private boolean hold = true;
	private boolean invertYaw = false;
	private boolean invertPitch = false;
	
	private boolean perspectiveToggled = false;
	
	private float cameraYaw = 0F;
	private float cameraPitch = 0F;
	
	private int previousPerspective = 0;
	
	public Freelook() {
		setHold((boolean) getFromFile("hold", hold));
		setInvertYaw((boolean) getFromFile("invertYaw", invertYaw));
		setInvertPitch((boolean) getFromFile("invertPitch", invertPitch));
	}
	
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
			} else if (hold) {
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
			
			cameraYaw = invertYaw ? cameraYaw - (f3 * 0.15F) : cameraYaw + (f3 * 0.15F);
			cameraPitch = invertPitch ? cameraPitch + (f4 * 0.15F) : cameraPitch - (f4 * 0.15F);
			
			if (cameraPitch > 90) {
				cameraPitch = 90;
			}
			
			if (cameraPitch < -90) {
				cameraPitch = -90;
			}
		}
		
		return false;
	}
	
	public void setHold(boolean enabled) {
		this.hold = enabled;
		
		setToFile("hold", enabled);
	}
	
	public boolean isHoldEnabled() {
		return hold;
	}
	
	public void setInvertYaw(boolean enabled) {
		this.invertYaw = enabled;
		
		setToFile("invertYaw", enabled);
	}
	
	public boolean isInvertYawEnabled() {
		return invertYaw;
	}
	
	public void setInvertPitch(boolean enabled) {
		this.invertPitch = enabled;
		
		setToFile("invertPitch", enabled);
	}
	
	public boolean isInvertPitchEnabled() {
		return invertPitch;
	}
}
