package drop.mods;

import java.util.Arrays;

import drop.mods.option.ModOption;
import drop.mods.option.type.BooleanOption;
import drop.mods.option.type.ColorOption;
import drop.mods.option.type.ScrollOption_FLOAT;
import drop.mods.option.type.ScrollOption_INT;
import drop.mods.option.type.StepOption;

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
	
	public ScrollOption_INT getIntOption(String key) {
		return (ScrollOption_INT) getOption(key);
	}
	
	public ScrollOption_FLOAT getFloatOption(String key) {
		return (ScrollOption_FLOAT) getOption(key);
	}
	
	public StepOption getStepOption(String key) {
		return (StepOption) getOption(key);
	}
}
