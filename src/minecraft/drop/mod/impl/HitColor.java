package drop.mod.impl;

import net.minecraft.entity.Entity;

import java.awt.Color;

import drop.gui.GuiSettings;
import drop.mod.Mod;
import drop.mod.ModColor;
import drop.mod.ModOptions;
import drop.mod.option.ParentOption;
import drop.mod.option.type.BooleanOption;
import drop.mod.option.type.ColorOption;
import drop.mod.option.type.FloatOption;

public class HitColor extends Mod {
	public HitColor() {
		super(false);

		this.options = new ModOptions(
				new ColorOption(this, "hitColor", ModColor.fromColor(Color.RED, false).setAlpha(76), new GuiSettings(1, "Hit Color", true, true))
				);
				
		saveOptions();
	}
}
