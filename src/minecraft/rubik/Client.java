package rubik;

import net.minecraft.client.Minecraft;
import rubik.event.EventManager;
import rubik.event.EventTarget;
import rubik.event.impl.RenderEvent;
import rubik.event.impl.TickEvent;
import rubik.gui.GuiModPositioning;
import rubik.gui.hud.HUDManager;
import rubik.mods.ModInstances;
import rubik.mods.impl.Fullbright;

public class Client {
	private static final Client client = new Client();
	
	public static final Client getInstance() {
		return client;
	}
	
	private DiscordRP discordRichPresence = new DiscordRP();
	
	public DiscordRP getDiscordRichPresence() {
		return discordRichPresence;
	}
	
	private HUDManager hudManager;
	private Minecraft mc = Minecraft.getMinecraft();
	
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
}
