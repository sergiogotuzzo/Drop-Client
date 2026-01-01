package drop.mods;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import drop.gui.hud.HUDManager;
import drop.mods.impl.ArmorStatus;
import drop.mods.impl.Speedometer;
import drop.mods.impl.BlockOverlay;
import drop.mods.impl.NoBobbing;
import drop.mods.impl.NoFireLayer;
import drop.mods.impl.NoHotbarScrolling;
import drop.mods.impl.NoPumpkinOverlay;
import drop.mods.impl.Bossbar;
import drop.mods.impl.CPSDisplay;
import drop.mods.impl.Chat;
import drop.mods.impl.Clock;
import drop.mods.impl.ComboCounter;
import drop.mods.impl.CoordinatesDisplay;
import drop.mods.impl.DayCounter;
import drop.mods.impl.FPSDisplay;
import drop.mods.impl.Freelook;
import drop.mods.impl.Fullbright;
import drop.mods.impl.HitColor;
import drop.mods.impl.HurtCam;
import drop.mods.impl.ItemPhysics;
import drop.mods.impl.Keystrokes;
import drop.mods.impl.LeftHand;
import drop.mods.impl.MemoryUsage;
import drop.mods.impl.Nametags;
import drop.mods.impl.NoAchievementNotifications;
import drop.mods.impl.OldVisuals;
import drop.mods.impl.PackDisplay;
import drop.mods.impl.Particles;
import drop.mods.impl.PingDisplay;
import drop.mods.impl.PotionEffects;
import drop.mods.impl.PotsCounter;
import drop.mods.impl.ReachDisplay;
import drop.mods.impl.togglesprintsneak.ToggleSprintSneak;
import drop.mods.impl.Scoreboard;
import drop.mods.impl.ServerAddress;
import drop.mods.impl.TabOverlay;
import drop.mods.impl.TimeChanger;
import drop.mods.impl.Zoom;

public class ModHandler {
	private static final List<Mod> MODS = Arrays.asList(
		    new ArmorStatus(),
		    new BlockOverlay(),
		    new Bossbar(),
		    new Chat(),
		    new Clock(),
		    new ComboCounter(),
		    new CoordinatesDisplay(),
		    new CPSDisplay(),
		    new DayCounter(),
		    new FPSDisplay(),
		    new Fullbright(),
		    new Freelook(),
		    new HitColor(),
		    new HurtCam(),
		    new ItemPhysics(),
		    new Keystrokes(),
		    new LeftHand(),
		    new MemoryUsage(),
		    new Nametags(),
		    new NoAchievementNotifications(),
		    new NoBobbing(),
		    new NoFireLayer(),
		    new NoHotbarScrolling(),
		    new NoPumpkinOverlay(),
		    new OldVisuals(),
		    new PackDisplay(),
		    new Particles(),
		    new PingDisplay(),
		    new PotionEffects(),
		    new PotsCounter(),
		    new ReachDisplay(),
		    new Scoreboard(),
		    new ServerAddress(),
		    new Speedometer(),
		    new TabOverlay(),
		    new TimeChanger(),
		    new ToggleSprintSneak(),
		    new Zoom()
		);
	private static final Map<Class<? extends Mod>, Mod> MODS_MAP = new HashMap<>();
	
	public static void init(HUDManager manager) {
		for (Mod mod : MODS) {
			register(manager, mod);
		}
	}
	
	public static void register(HUDManager manager, Mod mod) {
		if (mod instanceof ModDraggable) {
			manager.register((ModDraggable) mod);
		}
		
		MODS_MAP.put(mod.getClass(), mod);
	}
	
	public static <T extends Mod> T get(Class<T> clazz) {
		return clazz.cast(MODS_MAP.get(clazz));
	}
	
	public static List<Mod> getAll() {
		return MODS;
	}
}