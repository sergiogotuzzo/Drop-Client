package drop.mods.impl;

import net.minecraft.entity.Entity;

import java.awt.Color;

import drop.ColorManager;
import drop.gui.GuiSettings;
import drop.mods.Mod;
import drop.mods.ModOptions;
import drop.mods.option.ParentOption;
import drop.mods.option.type.BooleanOption;
import drop.mods.option.type.IntOption;

public class OldVisuals extends Mod {
	private boolean fishingRod = true;
	private boolean bow = true;
	private boolean blockHitting = true;
	private boolean armorHitAnimation = true;
	
	public OldVisuals() {
		super(true);
		
		this.options = new ModOptions(
				new BooleanOption(this, "fishingRod", true, new GuiSettings(1, "Fishing Rod")),
				new BooleanOption(this, "bow", true, new GuiSettings(2, "Bow")),
				new BooleanOption(this, "blockHitting", true, new GuiSettings(3, "Block Hitting")),
				new BooleanOption(this, "armorHitAnimation", true, new GuiSettings(4, "Armor Hit Animation"))
				);
		
		saveOptions();
	}
}
