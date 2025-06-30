package drop.mods.impl;

import java.awt.Color;

import drop.FileManager;
import drop.gui.GuiSettings;
import drop.mods.Mod;
import drop.mods.ModColor;
import drop.mods.ModOptions;
import drop.mods.option.ParentOption;
import drop.mods.option.type.BooleanOption;
import drop.mods.option.type.ColorOption;

public class Chat extends Mod {
	public Chat() {
		super(false);
		
		this.options = new ModOptions(
				new BooleanOption(this, "chatHeightFix", true, new GuiSettings(1, "Chat Height Fix")),
				new BooleanOption(this, "textShadow", false, new GuiSettings(2, "Text Shadow")),
				new BooleanOption(this, "compactChat", true, new GuiSettings(3, "Compact Chat")),
				new ColorOption(this, "backgroundColor", ModColor.fromRGB(0, 0, 0, 127, false), new GuiSettings(4, "Background Color", false, true))
				);
		
		saveOptions();
	}
}
