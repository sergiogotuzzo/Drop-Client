package drop.mods.option.type;

import drop.ColorManager;
import drop.gui.GuiSettings;
import drop.mods.Mod;
import drop.mods.impl.togglesprintsneak.ToggleSprintSneak;
import drop.mods.option.ModOption;
import drop.mods.option.ParentOption;

public class FloatOption extends ModOption {
	private float min;
	private float max;
	
	public FloatOption(Mod mod, String key, float min, float max, float value, ParentOption parentOption, GuiSettings guiSettings) {
		super(mod, key, value, parentOption, guiSettings);
		
		this.min = min;
		this.max = max;
	}
	
	public FloatOption(Mod mod, String key, float min, float max, float value, GuiSettings guiSettings) {
		this(mod, key, min, max, value, null, guiSettings);
	}

	public float getMin() {
		return min;
	}
	
	public float getMax() {
		return max;
	}
}
