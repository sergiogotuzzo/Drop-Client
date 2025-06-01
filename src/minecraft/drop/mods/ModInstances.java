package drop.mods;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import drop.mods.hud.HUDManager;
import drop.mods.impl.ArmorStatus;
import drop.mods.impl.BlockOverlay;
import drop.mods.impl.Bobbing;
import drop.mods.impl.Bossbar;
import drop.mods.impl.CPSDisplay;
import drop.mods.impl.Chat;
import drop.mods.impl.Clock;
import drop.mods.impl.CoordinatesDisplay;
import drop.mods.impl.DayCounter;
import drop.mods.impl.FPSDisplay;
import drop.mods.impl.Freelook;
import drop.mods.impl.Fullbright;
import drop.mods.impl.HurtCam;
import drop.mods.impl.Keystrokes;
import drop.mods.impl.LeftHand;
import drop.mods.impl.MemoryUsage;
import drop.mods.impl.Nametags;
import drop.mods.impl.OldVisuals;
import drop.mods.impl.PackDisplay;
import drop.mods.impl.PingDisplay;
import drop.mods.impl.PotionEffects;
import drop.mods.impl.PotsCounter;
import drop.mods.impl.togglesprintsneak.ToggleSprintSneak;
import drop.mods.impl.Scoreboard;
import drop.mods.impl.ServerAddress;
import drop.mods.impl.TabOverlay;
import drop.mods.impl.TimeChanger;

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
	private static PotsCounter potsCounterMod;
	private static Clock clockMod;
	private static DayCounter dayCounterMod;
	private static PackDisplay packDisplayMod;
	private static ServerAddress serverAddressMod;
	private static Freelook freelookMod = new Freelook();
	private static Fullbright fullbrightMod = new Fullbright();
	private static OldVisuals oldVisualsMod = new OldVisuals();
	private static Scoreboard scoreboardMod = new Scoreboard();
	private static Chat chatMod = new Chat();
	private static BlockOverlay blockOverlayMod = new BlockOverlay();
	private static TimeChanger timeChangerMod = new TimeChanger();
	private static TabOverlay tabOverlayMod = new TabOverlay();
	private static Bossbar bossbarMod = new Bossbar();
	private static Nametags nametagsMod = new Nametags();
	private static Bobbing bobbingMod = new Bobbing();
	private static HurtCam hurtCamMod = new HurtCam();
	private static LeftHand leftHandMod = new LeftHand();
	
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
		manager.register(potsCounterMod = new PotsCounter());
		manager.register(clockMod = new Clock());
		manager.register(dayCounterMod = new DayCounter());
		manager.register(packDisplayMod = new PackDisplay());
		manager.register(serverAddressMod = new ServerAddress());
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
	
	public static PotsCounter getPotsCounterMod() {
		return potsCounterMod;
	}
	
	public static Clock getClockMod() {
		return clockMod;
	}
	
	public static DayCounter getdayCounterMod() {
		return dayCounterMod;
	}
	
	public static PackDisplay getPackDisplayMod() {
		return packDisplayMod;
	}
	
	public static ServerAddress getServerAddressMod() {
		return serverAddressMod;
	}
	
	public static Freelook getFreelookMod() {
		return freelookMod;
	}
	
	public static Fullbright getFullbrightMod() {
		return fullbrightMod;
	}
	
	public static OldVisuals getOldVisualsMod() {
		return oldVisualsMod;
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
	
	public static TabOverlay getTabOverlayMod() {
		return tabOverlayMod;
	}
	
	public static Bossbar getBossbarMod() {
		return bossbarMod;
	}
	
	public static Nametags getNametagsMod() {
		return nametagsMod;
	}
	
	public static Bobbing getBobbingMod() {
		return bobbingMod;
	}
	
	public static HurtCam getHurtCamMod() {
		return hurtCamMod;
	}
	
	public static LeftHand getLeftHandMod() {
		return leftHandMod;
	}
	
	public static List<Mod> getAllMods() {
		return Arrays.asList(
			    getArmorStatusMod(),
			    getBlockOverlayMod(),
			    getBobbingMod(),
			    getBossbarMod(),
			    getChatMod(),
			    getClockMod(),
			    getCoordinatesDisplayMod(),
			    getCPSDisplayMod(),
			    getdayCounterMod(),
			    getFPSDisplayMod(),
			    getFullbrightMod(),
			    getFreelookMod(),
			    getHurtCamMod(),
			    getKeystrokesMod(),
			    getLeftHandMod(),
			    getMemoryUsageMod(),
			    getNametagsMod(),
			    getOldVisualsMod(),
			    getPackDisplayMod(),
			    getPingDisplayMod(),
			    getPotionEffectsMod(),
			    getPotsCounterMod(),
			    getScoreboardMod(),
			    getServerAddressMod(),
			    getTabOverlayMod(),
			    getTimeChangerMod(),
			    getToggleSprintSneakMod()
			);

	}
}