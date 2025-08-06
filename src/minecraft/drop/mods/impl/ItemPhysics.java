package drop.mods.impl;

import drop.gui.GuiSettings;
import drop.mods.Mod;
import drop.mods.ModOptions;
import drop.mods.option.type.IntOption;

public class ItemPhysics extends Mod {
	public ItemPhysics() {
		super(false);
		
		this.options = new ModOptions(
				new IntOption(this, "rotationSpeed", 1, 10, 2, new GuiSettings(1, "Rotation Speed"))
				);
		
		saveOptions();
	}
}
