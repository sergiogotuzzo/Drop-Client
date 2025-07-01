package drop.mods.impl;

import drop.gui.GuiSettings;
import drop.mods.Mod;
import drop.mods.ModOptions;
import drop.mods.option.ParentOption;
import drop.mods.option.type.BooleanOption;
import drop.mods.option.type.FloatOption;

public class HurtCam extends Mod {
	public HurtCam() {
		super(false);
		
		this.options = new ModOptions(
				new BooleanOption(this, "hurtShake", true, new GuiSettings(1, "Hurt Shake")),
				new FloatOption(this, "hurtShakeIntensity", 5.0F, 35.0F, 14.0F, new ParentOption("hurtShake"), new GuiSettings(2, "Hurt Shake Intensity"))
				);
		
		saveOptions();
	}
}