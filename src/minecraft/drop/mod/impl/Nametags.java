package drop.mod.impl;

import drop.gui.mod.GuiSettings;
import drop.mod.Mod;
import drop.mod.option.ModColor;
import drop.mod.option.type.BooleanOption;
import drop.mod.option.type.ColorOption;

public class Nametags extends Mod {
	private boolean showInThirdPerson = true;
	private boolean textShadow = false;
	private ModColor backgroundColor = ModColor.fromRGB(0, 0, 0, 64, false);
	
	public Nametags() {
		super(false);
		
		saveOptions(
				new BooleanOption(this, "showInThirdPerson", true, new GuiSettings(1, "Show In Third Person")),
				new BooleanOption(this, "textShadow", false, new GuiSettings(2, "Text Shadow")),
				new ColorOption(this, "backgroundColor", ModColor.fromRGB(0, 0, 0, 64, false), new GuiSettings(3, "Background Color", false, true))
				);
	}
}
