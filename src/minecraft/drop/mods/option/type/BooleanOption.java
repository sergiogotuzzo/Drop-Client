package drop.mods.option.type;

import drop.gui.GuiSettings;
import drop.mods.Mod;
import drop.mods.option.ModOption;
import drop.mods.option.ParentOption;

public class BooleanOption extends ModOption {
	public BooleanOption(Mod mod, String key, boolean value, ParentOption parentOption, GuiSettings guiSettings) {
		super(mod, key, value, parentOption, guiSettings);
	}
	
	public BooleanOption(Mod mod, String key, boolean value, GuiSettings guiSettings) {
		this(mod, key, value, null, guiSettings);
	}
	
	public void toggle(boolean toggled) {
		saveValue(toggled);
	}
	
	public void toggle() {
		toggle(!isToggled());
	}
	
	public boolean isToggled() {
		return (boolean) getValue();
	}
}
