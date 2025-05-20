package rubik.mods.impl;

import rubik.events.EventTarget;
import rubik.events.impl.TickEvent;
import rubik.mods.Mod;
import rubik.mods.ModInstances;

public class Fullbright extends Mod {
	private static final float fullbrightGamma = 10.0F;
	
	private boolean toggled = true;
	
	public Fullbright() {
		setToFile("toggled", (boolean) getFromFile("toggled", toggled));
	}
	
	@EventTarget
	public void onTick(TickEvent event) {
		if (isEnabled() && mc.gameSettings.keyBindFullbright.isPressed()) {
			Fullbright mod = ModInstances.getFullbrightMod();
			
			mod.setToggled(!mod.isToggled());
		}
	}
	
	public void setToggled(boolean toggled) {
		this.toggled = toggled;
		
		setToFile("toggled", toggled);
	}
	
	public boolean isToggled() {
		return toggled;
	}
	
	public static float getFullbrightGamma() {
		return fullbrightGamma;
	}
}
