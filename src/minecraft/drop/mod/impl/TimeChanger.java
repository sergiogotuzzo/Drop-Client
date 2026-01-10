package drop.mod.impl;

import java.time.LocalTime;

import drop.gui.GuiSettings;
import drop.mod.Mod;
import drop.mod.option.ParentOption;
import drop.mod.option.type.BooleanOption;
import drop.mod.option.type.FloatOption;

public class TimeChanger extends Mod {
	public TimeChanger() {
		super(false);
		
		saveOptions(
				new FloatOption(this, "time", 0.0F, 1.0F, 0.0F, new ParentOption("useRealCurrentTime", true), new GuiSettings(1, "Time", 2)),
				new BooleanOption(this, "useRealCurrentTime", false, new GuiSettings(2, "Use Real Current Time"))
				);
	}
	
	public static float getRealCurrentTimeInMinecraftTime() {
		LocalTime now = LocalTime.now();
	    float totalHours = now.getHour() + (now.getMinute() / 60f);
	    
	    return ((totalHours - 12f + 24f) % 24f) / 24f;
	}
}
