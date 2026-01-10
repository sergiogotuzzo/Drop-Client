package drop.mod.impl;

import drop.gui.mod.GuiSettings;
import drop.mod.Mod;
import drop.mod.option.type.IntOption;

public class ItemPhysics extends Mod {
	public ItemPhysics() {
		super(false);
		
		saveOptions(
				new IntOption(this, "rotationSpeed", 1, 10, 2, new GuiSettings(1, "Rotation Speed"))
				);
	}
}
