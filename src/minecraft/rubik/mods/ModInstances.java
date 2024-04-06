package rubik.mods;

import rubik.gui.hud.HUDManager;
import rubik.mods.impl.CPSDisplay;
import rubik.mods.impl.FPSDisplay;
import rubik.mods.impl.Keystrokes;

public class ModInstances {
	private static FPSDisplay fpsDisplayMod;
	private static CPSDisplay cpsDisplayMod;
	private static Keystrokes keystrokesMod;
	
	public static void register(HUDManager manager) {
		manager.register(fpsDisplayMod = new FPSDisplay());
		manager.register(cpsDisplayMod = new CPSDisplay());
		manager.register(keystrokesMod = new Keystrokes());
	}
	
	public static FPSDisplay getFPSDisplayMod() {
		return fpsDisplayMod;
	}
	
	public static CPSDisplay getCPSDisplayMod() {
		return cpsDisplayMod;
	}
	
	public static Keystrokes getKeystrokesMod() {
		return keystrokesMod;
	}
}
