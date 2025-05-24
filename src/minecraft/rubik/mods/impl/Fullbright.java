package rubik.mods.impl;

import rubik.events.EventTarget;
import rubik.events.impl.TickEvent;
import rubik.mods.Mod;
import rubik.mods.ModInstances;

public class Fullbright extends Mod {
	private static final float fullbrightGamma = 10.0F;
	
	public static float getFullbrightGamma() {
		return fullbrightGamma;
	}
}
