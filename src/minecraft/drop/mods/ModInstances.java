package drop.mods;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import drop.mods.hud.HUDManager;
import drop.mods.impl.ArmorStatus;
import drop.mods.impl.BPSDisplay;
import drop.mods.impl.BlockOverlay;
import drop.mods.impl.NoBobbing;
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
import drop.mods.impl.HurtCam;
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
	private static BPSDisplay bpsDisplayMod;
	private static ComboCounter comboCounterMod;
	private static ReachDisplay reachDisplayMod;
	private static Freelook freelookMod = new Freelook();
	private static Fullbright fullbrightMod = new Fullbright();
	private static OldVisuals oldVisualsMod = new OldVisuals();
	private static Scoreboard scoreboardMod;
	private static Chat chatMod = new Chat();
	private static BlockOverlay blockOverlayMod = new BlockOverlay();
	private static TimeChanger timeChangerMod = new TimeChanger();
	private static TabOverlay tabOverlayMod = new TabOverlay();
	private static Bossbar bossbarMod;
	private static Nametags nametagsMod = new Nametags();
	private static NoBobbing noBobbingMod = new NoBobbing();
	private static HurtCam hurtCamMod = new HurtCam();
	private static LeftHand leftHandMod = new LeftHand();
	private static Zoom zoomMod = new Zoom();
	private static NoPumpkinOverlay noPumpkinOverlayMod = new NoPumpkinOverlay();
	private static NoHotbarScrolling noHotbarScrollingMod = new NoHotbarScrolling();
	private static Particles particlesMod = new Particles();
	private static NoAchievementNotifications noAchievementNotificationsMod = new NoAchievementNotifications();
	
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
		manager.register(bpsDisplayMod = new BPSDisplay());
		manager.register(comboCounterMod = new ComboCounter());
		manager.register(reachDisplayMod = new ReachDisplay());
		manager.register(bossbarMod = new Bossbar());
		manager.register(scoreboardMod = new Scoreboard());
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
	
	public static DayCounter getDayCounterMod() {
		return dayCounterMod;
	}
	
	public static PackDisplay getPackDisplayMod() {
		return packDisplayMod;
	}
	
	public static ServerAddress getServerAddressMod() {
		return serverAddressMod;
	}
	
	public static BPSDisplay getBPSDisplayMod() {
		return bpsDisplayMod;
	}
	
	public static ComboCounter getComboCounterMod() {
		return comboCounterMod;
	}
	
	public static ReachDisplay getReachDisplayMod() {
		return reachDisplayMod;
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
	
	public static NoBobbing getNoBobbingMod() {
		return noBobbingMod;
	}
	
	public static HurtCam getHurtCamMod() {
		return hurtCamMod;
	}
	
	public static LeftHand getLeftHandMod() {
		return leftHandMod;
	}
	
	public static Zoom getZoomMod() {
		return zoomMod;
	}
	
	public static NoPumpkinOverlay getNoPumpkinOverlayMod() {
		return noPumpkinOverlayMod;
	}
	
	public static NoHotbarScrolling getNoHotbarScrollingMod() {
		return noHotbarScrollingMod;
	}
	
	public static Particles getParticlesMod() {
		return particlesMod;
	}
	
	public static NoAchievementNotifications getNoAchievementNotificationsMod() {
		return noAchievementNotificationsMod;
	}
	
	public static List<Mod> getAllMods() {
		return Arrays.asList(
			    getArmorStatusMod(),
			    getBlockOverlayMod(),
			    getBossbarMod(),
			    getBPSDisplayMod(),
			    getChatMod(),
			    getClockMod(),
			    getComboCounterMod(),
			    getCoordinatesDisplayMod(),
			    getCPSDisplayMod(),
			    getDayCounterMod(),
			    getFPSDisplayMod(),
			    getFullbrightMod(),
			    getFreelookMod(),
			    getHurtCamMod(),
			    getKeystrokesMod(),
			    getLeftHandMod(),
			    getMemoryUsageMod(),
			    getNametagsMod(),
			    getNoAchievementNotificationsMod(),
			    getNoBobbingMod(),
			    getNoHotbarScrollingMod(),
			    getNoPumpkinOverlayMod(),
			    getOldVisualsMod(),
			    getPackDisplayMod(),
			    getParticlesMod(),
			    getPingDisplayMod(),
			    getPotionEffectsMod(),
			    getPotsCounterMod(),
			    getReachDisplayMod(),
			    getScoreboardMod(),
			    getServerAddressMod(),
			    getTabOverlayMod(),
			    getTimeChangerMod(),
			    getToggleSprintSneakMod(),
			    getZoomMod()
			);
	}
}