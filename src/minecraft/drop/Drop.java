package drop;

import net.minecraft.client.Minecraft;

import java.io.IOException;

import drop.gui.mod.GuiModPositioning;
import drop.mod.ModHandler;
import drop.mod.impl.Fullbright;
import drop.gui.mod.GuiModList;
import drop.event.EventManager;
import drop.event.EventTarget;
import drop.event.impl.TickEvent;
import drop.gui.hud.HUDManager;

public class Drop {
	private static final String NAME = "Drop Client";
	private static final String VERSION = "68b6198";
	private static final String BRANCH = "main";
	public static final String MENU_SUBTITLE = "Not affiliated with Mojang AB nor Microsoft";
	
	private static final Drop dropClient = new Drop();
	
	private DiscordRP discordRichPresence = new DiscordRP();
	private HUDManager hudManager;
	private Minecraft mc = Minecraft.getMinecraft();
	
	public static String lastServerIp;
	public static int lastServerPort;
	
	private FileManager modsFile;
	
	public void init() {
		modsFile = FileManager.init("mods.json");
		
		discordRichPresence.start();
		
		EventManager.register(this);
	}
	
	public void start() {
		hudManager = HUDManager.getInstance();
		
		ModHandler.init(hudManager);
	}
	
	public void shutdown() {
		discordRichPresence.shutdown();
	}
	
	@EventTarget
	public void onTick(TickEvent event) {
		if (mc.gameSettings.keyBindModPositioning.isPressed()) {
			mc.displayGuiScreen(new GuiModPositioning(hudManager));
		}
		
		if (mc.gameSettings.keyBindModList.isPressed()) {
			mc.displayGuiScreen(new GuiModList(null));
		}
		
		if (mc.gameSettings.keyBindFullbright.isPressed()) {
			ModHandler.get(Fullbright.class).toggle();
		}
	}
	
	public static String getNameVersion() {
		return NAME + " (" + VERSION + "/" + BRANCH + ")";
	}
	
	public static final Drop getDropClient() {
		return dropClient;
	}
	
	public DiscordRP getDiscordRichPresence() {
		return discordRichPresence;
	}
	
	public HUDManager getHUDManager() {
		return hudManager;
	}
	
	public FileManager getModsFile() {
		return modsFile;
	}
}
