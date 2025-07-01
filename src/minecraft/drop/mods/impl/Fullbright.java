package drop.mods.impl;

import drop.event.EventTarget;
import drop.event.impl.TickEvent;
import drop.gui.GuiDropClientScreen;
import drop.mods.Mod;
import drop.mods.ModInstances;

public class Fullbright extends Mod {	
	public Fullbright() {
		super(true);
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return null;
	}
	
	public float getGamma() {
		return enabled ? 10.0F : mc.gameSettings.gammaSetting;
	}
}
