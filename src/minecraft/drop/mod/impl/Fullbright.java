package drop.mod.impl;

import drop.event.EventTarget;
import drop.event.impl.TickEvent;
import drop.gui.GuiScreenDC;
import drop.mod.Mod;

public class Fullbright extends Mod {	
	public Fullbright() {
		super(true);
	}
	
	@Override
	public GuiScreenDC getGui(GuiScreenDC previousGuiScreen) {
		return null;
	}
	
	public float getGamma() {
		return enabled ? 10.0F : mc.gameSettings.gammaSetting;
	}
}
