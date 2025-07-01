package drop.mods.option.type;

import drop.gui.GuiSettings;
import drop.mods.Mod;
import drop.mods.option.ModOption;
import drop.mods.option.ParentOption;

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
