package drop.mod.impl;

import drop.event.EventTarget;
import drop.event.impl.TickEvent;
import drop.gui.GuiBlurredScreen;
import drop.mod.Mod;

public class Fullbright extends Mod {	
	public Fullbright() {
		super(true);
	}
	
	@Override
	public GuiBlurredScreen getGui(GuiBlurredScreen previousGuiScreen) {
		return null;
	}
	
	public float getGamma() {
		return enabled ? 10.0F : mc.gameSettings.gammaSetting;
	}
}
