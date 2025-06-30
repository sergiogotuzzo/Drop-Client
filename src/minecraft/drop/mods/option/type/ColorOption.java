package drop.mods.option.type;

import drop.gui.GuiSettings;
import drop.mods.Mod;
import drop.mods.ModColor;
import drop.mods.option.ModOption;
import drop.mods.option.ParentOption;

public class ColorOption extends ModOption {	
	private boolean alphaInGui;
	private boolean chromaInGui;
	
	public ColorOption(Mod mod, String key, ModColor value, ParentOption parentOption, GuiSettings guiSettings) {
		super(mod, key, value, parentOption, guiSettings);
	}
	
	public ColorOption(Mod mod, String key, ModColor value, GuiSettings guiSettings) {
		this(mod, key, value, null, guiSettings);
	}
	
	public void saveValue(ModColor color) {
		super.setValue(color);
		
		mod.setToFile(getKeyRGB(), color.getRGB());

		if (chromaInGui) {
			mod.setToFile(getKeyChroma(), color.isChromaToggled());
		}
	}
	
	public String getKeyRGB() {
		return this.getKey() + "RGB";
	}
	
	public String getKeyChroma() {
		return chromaInGui ? this.getKey() + "Chroma" : null;
	}
	
	public boolean shouldBeShownAlphaInGui() {
		return alphaInGui;
	}
	
	public boolean shouldBeShownChromaInGui() {
		return chromaInGui;
	}
	
	public ModColor getColor() {
		return (ModColor) getValue();
	}
}
