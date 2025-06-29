package drop.mods.option.type;

import drop.gui.GuiSettings;
import drop.mods.Mod;
import drop.mods.option.Brackets;
import drop.mods.option.ModOption;
import drop.mods.option.ParentOption;

public class BracketsOption extends ModOption {
	public BracketsOption(Mod mod, String key, Brackets value, ParentOption parentOption, GuiSettings guiSettings) {
		super(mod, key, value, parentOption, guiSettings);
	}
	
	public BracketsOption(Mod mod, String key, Brackets value, GuiSettings guiSettings) {
		this(mod, key, value, null, guiSettings);
	}
	
	public void saveValue(Brackets brackets) {
		super.setValue(brackets);
		
		mod.setToFile(getKey(), brackets.getId());
	}
	
	public Brackets getBrackets() {
		return (Brackets) getValue();
	}
	
	public int getId() {
        return getBrackets().getId();
    }

    public String getOpen() {
        return getBrackets().getOpen();
    }

    public String getClose() {
        return getBrackets().getClose();
    }

    public String wrap(String text) {
        return getOpen() + text + getClose();
    }
}
