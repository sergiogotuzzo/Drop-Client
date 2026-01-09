package drop.mod;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import drop.gui.hud.HUDManager;
import drop.mod.impl.ArmorStatus;
import drop.mod.impl.BlockOverlay;
import drop.mod.impl.Bossbar;
import drop.mod.impl.CPSDisplay;
import drop.mod.impl.Chat;
import drop.mod.impl.Clock;
import drop.mod.impl.ComboCounter;
import drop.mod.impl.CoordinatesDisplay;
import drop.mod.impl.DayCounter;
import drop.mod.impl.FPSDisplay;
import drop.mod.impl.Freelook;
import drop.mod.impl.Fullbright;
import drop.mod.impl.HitColor;
import drop.mod.impl.HurtCam;
import drop.mod.impl.ItemPhysics;
import drop.mod.impl.Keystrokes;
import drop.mod.impl.LeftHand;
import drop.mod.impl.MemoryUsage;
import drop.mod.impl.Nametags;
import drop.mod.impl.NoAchievementNotifications;
import drop.mod.impl.NoBobbing;
import drop.mod.impl.NoFireLayer;
import drop.mod.impl.NoHotbarScrolling;
import drop.mod.impl.NoPumpkinOverlay;
import drop.mod.impl.OldVisuals;
import drop.mod.impl.PackDisplay;
import drop.mod.impl.Particles;
import drop.mod.impl.PingDisplay;
import drop.mod.impl.PotionEffects;
import drop.mod.impl.PotsCounter;
import drop.mod.impl.ReachDisplay;
import drop.mod.impl.Scoreboard;
import drop.mod.impl.ServerAddress;
import drop.mod.impl.Speedometer;
import drop.mod.impl.TabOverlay;
import drop.mod.impl.TimeChanger;
import drop.mod.impl.Zoom;
import drop.mod.impl.togglesprintsneak.ToggleSprintSneak;

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