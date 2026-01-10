package drop.mod.impl;

import drop.gui.GuiSettings;
import drop.mod.Mod;
import drop.mod.option.ParentOption;
import drop.mod.option.type.BooleanOption;
import drop.mod.option.type.FloatOption;

public class HurtCam extends Mod {
	public HurtCam() {
		super(false);
		
		saveOptions(
				new BooleanOption(this, "hurtShake", true, new GuiSettings(1, "Hurt Shake")),
				new FloatOption(this, "hurtShakeIntensity", 5.0F, 35.0F, 14.0F, new ParentOption("hurtShake"), new GuiSettings(2, "Hurt Shake Intensity"))
				);
	}
}