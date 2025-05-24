package rubik.mods;

import java.util.Arrays;
import java.util.Collection;

import rubik.gui.mod.hud.HUDManager;
import rubik.mods.impl.ArmorStatus;
import rubik.mods.impl.BlockOverlay;
import rubik.mods.impl.CPSDisplay;
import rubik.mods.impl.Chat;
import rubik.mods.impl.CoordinatesDisplay;
import rubik.mods.impl.FPSDisplay;
import rubik.mods.impl.Freelook;
import rubik.mods.impl.Fullbright;
import rubik.mods.impl.Keystrokes;
import rubik.mods.impl.MemoryUsage;
import rubik.mods.impl.OldVisuals;
import rubik.mods.impl.PingDisplay;
import rubik.mods.impl.PotionEffects;
import rubik.mods.impl.togglesprintsneak.ToggleSprintSneak;
import rubik.mods.impl.Scoreboard;
import rubik.mods.impl.TimeChanger;

public class ModInstances {
	private static FPSDisplay fpsDisplayMod;
	private static CPSDisplay cpsDisplayMod;
	private static PingDisplay pingDisplayMod;
	private static Keystrokes keystrokesMod;
	private static ArmorStatus armorStatusMod;
	private static PotionEffects potionEffectsMod;
	private static CoordinatesDisplay coordinatesDisplayMod;
	private static ToggleSprintSneak toggleSprintSneakMod;
	private static MemoryUsage memoryUsageMod;
	private static Freelook freelookMod = new Freelook();
	private static Fullbright fullbrightMod = new Fullbright();
	private static OldVisuals oldAnimationsMod = new OldVisuals();
	private static Scoreboard scoreboardMod = new Scoreboard();
	private static Chat chatMod = new Chat();
	private static BlockOverlay blockOverlayMod = new BlockOverlay();
	private static TimeChanger timeChangerMod = new TimeChanger();
	
	public static void register(HUDManager manager) {
		manager.register(fpsDisplayMod = new FPSDisplay());
		manager.register(cpsDisplayMod = new CPSDisplay());
		manager.register(pingDisplayMod = new PingDisplay());
		manager.register(keystrokesMod = new Keystrokes());
		manager.register(armorStatusMod = new ArmorStatus());
		manager.register(potionEffectsMod = new PotionEffects());
		manager.register(coordinatesDisplayMod = new CoordinatesDisplay());
		manager.register(toggleSprintSneakMod = new ToggleSprintSneak());
		manager.register(memoryUsageMod = new MemoryUsage());
	}
	
	public static FPSDisplay getFPSDisplayMod() {
		return fpsDisplayMod;
	}
	
	public static CPSDisplay getCPSDisplayMod() {
		return cpsDisplayMod;
	}
	
	public static PingDisplay getPingDisplayMod() {
		return pingDisplayMod;
	}
	
	public static Keystrokes getKeystrokesMod() {
		return keystrokesMod;
	}
	
	public static ArmorStatus getArmorStatusMod() {
		return armorStatusMod;
	}
	
	public static PotionEffects getPotionEffectsMod() {
		return potionEffectsMod;
	}
	
	public static CoordinatesDisplay getCoordinatesDisplayMod() {
		return coordinatesDisplayMod;
	}
	
	public static ToggleSprintSneak getToggleSprintSneakMod() {
		return toggleSprintSneakMod;
	}
	
	public static MemoryUsage getMemoryUsageMod() {
		return memoryUsageMod;
	}
	
	public static Freelook getFreelookMod() {
		return freelookMod;
	}
	
	public static Fullbright getFullbrightMod() {
		return fullbrightMod;
	}
	
	public static OldVisuals getOldAnimationsMod() {
		return oldAnimationsMod;
	}
	
	public static Scoreboard getScoreboardMod() {
		return scoreboardMod;
	}
	
	public static Chat getChatMod() {
		return chatMod;
	}
	
	public static BlockOverlay getBlockOverlayMod() {
		return blockOverlayMod;
	}
	
	public static TimeChanger getTimeChangerMod() {
		return timeChangerMod;
	}
	
	public static Collection<Mod> getAllMods() {
		return Arrays.asList(
			    getArmorStatusMod(),
			    getBlockOverlayMod(),
			    getChatMod(),
			    getCoordinatesDisplayMod(),
			    getCPSDisplayMod(),
			    getFPSDisplayMod(),
			    getFullbrightMod(),
			    getFreelookMod(),
			    getKeystrokesMod(),
			    getMemoryUsageMod(),
			    getOldAnimationsMod(),
			    getPingDisplayMod(),
			    getPotionEffectsMod(),
			    getScoreboardMod(),
			    getTimeChangerMod(),
			    getToggleSprintSneakMod()
			);

	}
}
