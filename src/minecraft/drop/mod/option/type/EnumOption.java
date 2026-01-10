package drop.mod.option.type;

import drop.gui.mod.GuiSettings;
import drop.mod.Mod;
import drop.mod.option.ModOption;
import drop.mod.option.ParentOption;

public class EnumOption extends ModOption {
	private int min;
	private int max;
	private Enum enumIn;
	
	public EnumOption(Mod mod, String key, int min, int max, int value, Enum enumIn, ParentOption parentOption, GuiSettings guiSettings) {
		super(mod, key, value, parentOption, guiSettings);
		
		this.min = min;
		this.max = max;
		this.enumIn = enumIn;
	}
	
	public EnumOption(Mod mod, String key, int min, int max, int value, Enum enumIn, GuiSettings guiSettings) {
		super(mod, key, value, guiSettings);
		
		this.min = min;
		this.max = max;
		this.enumIn = enumIn;
	}
	
	public int getMin() {
		return min;
	}
	
	public int getMax() {
		return max;
	}
	
	public Enum getEnum() {
		return enumIn;
	}
	
	public int getId() {
		return (int) getValue();
	}
}
