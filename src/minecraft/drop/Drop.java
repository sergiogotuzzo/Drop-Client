package drop;

import net.minecraft.client.Minecraft;

import java.io.IOException;

import drop.gui.mod.GuiModPositioning;
import drop.gui.mod.GuiModList;
import drop.event.EventManager;
import drop.event.EventTarget;
import drop.event.impl.TickEvent;
import drop.gui.hud.HUDManager;
import drop.mods.ModInstances;
import drop.mods.impl.Fullbright;

public class Drop {
	public static final String nameVersion = "Drop Client (1.8.9-67e6416/main)";
	
	private static final Drop instance = new Drop();
	
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
		
		ModInstances.register(hudManager);
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
			Fullbright mod = ModInstances.getFullbrightMod();

			mod.setEnabled(!mod.isEnabled());
		}
	}
	
	public static final Drop getInstance() {
		return instance;
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
