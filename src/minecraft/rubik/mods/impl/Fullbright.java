package rubik.mods.impl;

import org.lwjgl.input.Keyboard;

import rubik.event.EventTarget;
import rubik.event.impl.KeyEvent;
import rubik.mods.Mod;

public class Fullbright extends Mod {
	public Fullbright() {
		if (isEnabled()) {
			mc.gameSettings.gammaSetting = 10.0F;
		} else {
			mc.gameSettings.gammaSetting = 1.0F;
		}
	}
}
