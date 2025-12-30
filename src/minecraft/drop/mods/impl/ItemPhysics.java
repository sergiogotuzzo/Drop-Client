package drop.mods.impl;

import drop.gui.GuiSettings;
import drop.mods.Mod;
import drop.mods.ModOptions;
import drop.mods.option.type.ScrollOption_INT;

public class ItemPhysics extends Mod {
	public ItemPhysics() {
		super(false);
		
		this.options = new ModOptions(
				new ScrollOption_INT(this, "rotationSpeed", 1, 10, 2, new GuiSettings(1, "Rotation Speed"))
				);
		
		saveOptions();
	}
}
