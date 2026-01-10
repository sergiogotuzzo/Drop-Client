package drop.mod.option.type;

import drop.gui.mod.GuiSettings;
import drop.mod.Mod;
import drop.mod.option.ModOption;
import drop.mod.option.ParentOption;

public class IntOption extends ModOption {
	private int min;
	private int max;
	
	public IntOption(Mod mod, String key, int min, int max, int value, ParentOption parentOption, GuiSettings guiSettings) {
		super(mod, key, value, parentOption, guiSettings);
		
		this.min = min;
		this.max = max;
	}
	
	public IntOption(Mod mod, String key, int min, int max, int value, GuiSettings guiSettings) {
		super(mod, key, value, guiSettings);
		
		this.min = min;
		this.max = max;
	}
	
	public int getMin() {
		return min;
	}
	
	public int getMax() {
		return max;
	}
}
