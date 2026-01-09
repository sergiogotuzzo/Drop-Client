package drop.mod.impl;

import drop.gui.GuiSettings;
import drop.mod.Mod;
import drop.mod.ModOptions;
import drop.mod.option.ModOption;
import drop.mod.option.ParentOption;
import drop.mod.option.type.BooleanOption;
import drop.mod.option.type.IntOption;

public class Zoom extends Mod {	
	public Zoom() {
		super(true);
		
		this.options = new ModOptions(
				new BooleanOption(this, "scrollToZoom", true, new GuiSettings(1, "Scroll To Zoom")),
				new BooleanOption(this, "smoothCamera", true, new GuiSettings(2, "Cinematic Camera")),
				new IntOption(this, "zoomLevel", 2, 64, 4, new GuiSettings(3, "Zoom Level")),
				new IntOption(this, "zoomLevelMin", 2, 64, 4, new ParentOption("scrollToZoom"), new GuiSettings(4, "Zoom Level Min")),
				new IntOption(this, "zoomLevelMax", 2, 64, 16, new ParentOption("scrollToZoom"), new GuiSettings(5, "Zoom Level Max"))
				);
		
		saveOptions();
	}
}
