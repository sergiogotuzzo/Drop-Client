package drop.mod.option.type;

import drop.gui.mod.GuiSettings;
import drop.mod.Mod;
import drop.mod.option.ModOption;
import drop.mod.option.ParentOption;

public class BooleanOption extends ModOption {
	public BooleanOption(Mod mod, String key, boolean value, ParentOption parentOption, GuiSettings guiSettings) {
		super(mod, key, value, parentOption, guiSettings);
	}
	
	public BooleanOption(Mod mod, String key, boolean value, GuiSettings guiSettings) {
		super(mod, key, value, guiSettings);
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
	
	public boolean isEnabled() {
		return mod.isEnabled() && isToggled();
	}
}
