package drop.mods.option;

import drop.gui.GuiSettings;
import drop.mods.Mod;

public class ModOption {
	protected Mod mod;
	
	private String key;
	private Object value;
	
	private ParentOption parentOption;
	private GuiSettings guiSettings;
	
	public ModOption(Mod mod, String key, Object value, ParentOption parentOption, GuiSettings guiSettings) {
		this.mod = mod;
		
		this.key = key;
		this.value = value;
		
		this.parentOption = parentOption;
		this.guiSettings = guiSettings;
	}
	
	public ModOption(Mod mod, String key, Object value, GuiSettings guiSettings) {
		this(mod, key, value, null, guiSettings);
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public void saveValue(Object value) {
		setValue(value);
		
		mod.setToFile(key, value);
	}
	
	public Object getValue() {
		return value;
	}
	
	public String getKey() {
		return key;
	}

	public ParentOption getParentOption() {
		return parentOption;
	}
	
	public GuiSettings getGuiSettings() {
		return guiSettings;
	}
}
