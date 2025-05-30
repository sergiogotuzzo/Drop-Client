package drop;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import drop.events.EventManager;
import drop.events.EventTarget;
import drop.events.impl.RenderEvent;
import drop.events.impl.TickEvent;
import drop.gui.mod.GuiModPositioning;
import drop.mods.hud.HUDManager;
import drop.mods.ModInstances;
import drop.mods.impl.Fullbright;

public class Client {
	public static final String nameVersion = "Drop Client (1.8.9-60931bd/main)";
	
	private static final Client instance = new Client();
	
	private DiscordRP discordRichPresence = new DiscordRP();
	private HUDManager hudManager;
	private Minecraft mc = Minecraft.getMinecraft();
	
	public static String lastServerIp;
	public static int lastServerPort;
	
	public void init() {
		FileManager.init();
		
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
		
		if (mc.gameSettings.keyBindFullbright.isPressed()) {
			Fullbright mod = ModInstances.getFullbrightMod();
			
			mod.setEnabled(!mod.isEnabled());
		}
	}
	
	public static final Client getInstance() {
		return instance;
	}
	
	public DiscordRP getDiscordRichPresence() {
		return discordRichPresence;
	}
	
	public HUDManager getHUDManager() {
		return hudManager;
	}
}
