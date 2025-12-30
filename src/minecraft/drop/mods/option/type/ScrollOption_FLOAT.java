package drop.mods.option.type;

import drop.gui.GuiSettings;
import drop.mods.Mod;
import drop.mods.option.ModOption;
import drop.mods.option.ParentOption;

public class ScrollOption_FLOAT extends ModOption {
	private float min;
	private float max;
	
	public ScrollOption_FLOAT(Mod mod, String key, float min, float max, float value, ParentOption parentOption, GuiSettings guiSettings) {
		super(mod, key, value, parentOption, guiSettings);
		
		this.min = min;
		this.max = max;
	}
	
	public ScrollOption_FLOAT(Mod mod, String key, float min, float max, float value, GuiSettings guiSettings) {
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
