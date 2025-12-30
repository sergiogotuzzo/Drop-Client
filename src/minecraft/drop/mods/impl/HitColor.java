package drop.mods.impl;

import net.minecraft.entity.Entity;

import java.awt.Color;

import drop.gui.GuiSettings;
import drop.mods.Mod;
import drop.mods.ModColor;
import drop.mods.ModOptions;
import drop.mods.option.ParentOption;
import drop.mods.option.type.BooleanOption;
import drop.mods.option.type.ColorOption;
import drop.mods.option.type.ScrollOption_FLOAT;

public class HitColor extends Mod {
	public HitColor() {
		super(false);

		this.options = new ModOptions(
				new ColorOption(this, "hitColor", ModColor.fromColor(Color.RED, false).setAlpha(76), new GuiSettings(1, "Hit Color", true, true))
				);
				
		saveOptions();
	}
}
