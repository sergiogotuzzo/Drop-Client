package drop.mod.impl;

import drop.gui.mod.GuiSettings;
import drop.mod.Mod;
import drop.mod.option.ParentOption;
import drop.mod.option.type.BooleanOption;
import drop.mod.option.type.IntOption;

public class Particles extends Mod {
	public Particles() {
		super(false);
		
		saveOptions(
				new BooleanOption(this, "affectCriticals", true, new GuiSettings(1, "Affect Criticals")),
				new BooleanOption(this, "affectSharpness", true, new GuiSettings(2, "Affect Sharpness")),
				new BooleanOption(this, "alwaysSharpness", false, new ParentOption("affectSharpness"), new GuiSettings(3, "Always Sharpness")),
				new IntOption(this, "multiplierFactor", 1, 10, 1, new GuiSettings(4, "Multiplier Factor"))
				);
	}
}
