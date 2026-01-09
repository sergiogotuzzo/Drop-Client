package drop.mod;

import java.util.Arrays;

import drop.mod.option.ModOption;
import drop.mod.option.type.BooleanOption;
import drop.mod.option.type.ColorOption;
import drop.mod.option.type.EnumOption;
import drop.mod.option.type.FloatOption;
import drop.mod.option.type.IntOption;

public class ModOptions {
	private ModOption[] options;
	
	public ModOptions(ModOption... optionsIn) {
		this.options = optionsIn;
	}
	
	public ModOption[] getOptions() {
		return options;
	}
	
	public ModOption getOption(String key) {		
		for (ModOption option : options) {
			if (option.getKey() == key) {
				return option;
			}
		}
		
		return null;
	}
	
	public BooleanOption getBooleanOption(String key) {		
		return (BooleanOption) getOption(key);
	}
	
	public ColorOption getColorOption(String key) {		
		return (ColorOption) getOption(key);
	}
	
	public IntOption getIntOption(String key) {
		return (IntOption) getOption(key);
	}
	
	public FloatOption getFloatOption(String key) {
		return (FloatOption) getOption(key);
	}
	
	public EnumOption getEnumOption(String key) {
		return (EnumOption) getOption(key);
	}
}
