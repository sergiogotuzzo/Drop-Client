package drop.mods.impl;

import drop.events.EventTarget;
import drop.events.impl.TickEvent;
import drop.mods.Mod;
import drop.mods.ModInstances;

public class Fullbright extends Mod {
	private static final float fullbrightGamma = 10.0F;
	
	public Fullbright() {
		super(true);
	}
	
	public static float getFullbrightGamma() {
		return fullbrightGamma;
	}
}
