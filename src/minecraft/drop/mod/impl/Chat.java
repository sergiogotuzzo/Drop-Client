package drop.mod.impl;

import drop.gui.mod.GuiSettings;
import drop.mod.Mod;
import drop.mod.option.ModColor;
import drop.mod.option.ParentOption;
import drop.mod.option.type.BooleanOption;
import drop.mod.option.type.ColorOption;

public class Chat extends Mod {
	public Chat() {
		super(false);
		
		saveOptions(
				new BooleanOption(this, "chatHeightFix", true, new GuiSettings(1, "Chat Height Fix")),
				new BooleanOption(this, "textShadow", false, new GuiSettings(2, "Text Shadow")),
				new ColorOption(this, "backgroundColor", ModColor.fromRGB(0, 0, 0, 127, false), new GuiSettings(4, "Background Color", false, true))
				);
	}
}
