package rubik;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import rubik.events.EventManager;
import rubik.events.EventTarget;
import rubik.events.impl.RenderEvent;
import rubik.events.impl.TickEvent;
import rubik.gui.GuiModPositioning;
import rubik.gui.hud.HUDManager;
import rubik.mods.ModInstances;
import rubik.mods.impl.Fullbright;

public class Client {
	public static final String version = "1.8.9-2df00e1/main";
	
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
