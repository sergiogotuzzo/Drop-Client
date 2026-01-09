package drop.mod.option.type;

import drop.gui.GuiSettings;
import drop.mod.Mod;
import drop.mod.option.ModOption;
import drop.mod.option.ParentOption;

public class FloatOption extends ModOption {
	private float min;
	private float max;
	
	public FloatOption(Mod mod, String key, float min, float max, float value, ParentOption parentOption, GuiSettings guiSettings) {
		super(mod, key, value, parentOption, guiSettings);
		
		this.min = min;
		this.max = max;
	}
	
	public FloatOption(Mod mod, String key, float min, float max, float value, GuiSettings guiSettings) {
		super(mod, key, value, guiSettings);
		
		this.min = min;
		this.max = max;
	}

	public float getMin() {
		return min;
	}
	
	public float getMax() {
		return max;
	}
}
