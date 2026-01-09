package drop.mod.option.type;

import drop.gui.GuiSettings;
import drop.mod.Mod;
import drop.mod.ModColor;
import drop.mod.option.ModOption;
import drop.mod.option.ParentOption;

public class ColorOption extends ModOption {
	public ColorOption(Mod mod, String key, ModColor value, ParentOption parentOption, GuiSettings guiSettings) {
		super(mod, key, value, parentOption, guiSettings);
	}
	
	public ColorOption(Mod mod, String key, ModColor value, GuiSettings guiSettings) {
		super(mod, key, value, guiSettings);
	}
	
	public void saveValue(ModColor color) {
		super.setValue(color);
		
		mod.setToFile(getKeyRGB(), color.getRGB());

		if (getGuiSettings().shouldBeChromaCheckBoxShown()) {
			mod.setToFile(getKeyChroma(), color.isChromaToggled());
		}
	}
	
	public String getKeyRGB() {
		return this.getKey() + "RGB";
	}
	
	public String getKeyChroma() {
		return this.getKey() + "Chroma";
	}
	
	public ModColor getColor() {
		return (ModColor) getValue();
	}
}
