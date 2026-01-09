package drop.mod.impl;

import drop.gui.GuiSettings;
import drop.mod.Mod;
import drop.mod.ModOptions;
import drop.mod.option.type.IntOption;

public class ItemPhysics extends Mod {
	public ItemPhysics() {
		super(false);
		
		this.options = new ModOptions(
				new IntOption(this, "rotationSpeed", 1, 10, 2, new GuiSettings(1, "Rotation Speed"))
				);
		
		saveOptions();
	}
}
