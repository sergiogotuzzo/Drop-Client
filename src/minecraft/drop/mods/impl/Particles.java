package drop.mods.impl;

import drop.gui.GuiSettings;
import drop.mods.Mod;
import drop.mods.ModOptions;
import drop.mods.option.ParentOption;
import drop.mods.option.type.BooleanOption;
import drop.mods.option.type.ScrollOption_INT;

public class Particles extends Mod {
	public Particles() {
		super(false);
		
		this.options = new ModOptions(
				new BooleanOption(this, "affectCriticals", true, new GuiSettings(1, "Affect Criticals")),
				new BooleanOption(this, "affectSharpness", true, new GuiSettings(2, "Affect Sharpness")),
				new BooleanOption(this, "alwaysSharpness", false, new ParentOption("affectSharpness"), new GuiSettings(3, "Always Sharpness")),
				new ScrollOption_INT(this, "multiplierFactor", 1, 10, 1, new GuiSettings(4, "Multiplier Factor"))
				);
		
		saveOptions();
	}
}
