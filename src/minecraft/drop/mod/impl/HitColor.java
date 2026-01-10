package drop.mod.impl;

import java.awt.Color;

import drop.gui.GuiSettings;
import drop.mod.Mod;
import drop.mod.ModColor;
import drop.mod.option.type.ColorOption;

public class HitColor extends Mod {
	public HitColor() {
		super(false);
				
		saveOptions(
				new ColorOption(this, "hitColor", ModColor.fromColor(Color.RED, false).setAlpha(76), new GuiSettings(1, "Hit Color", true, true))
				);
	}
}
