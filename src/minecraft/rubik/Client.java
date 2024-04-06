package rubik;

import rubik.event.EventTarget;
import rubik.event.impl.TickEvent;

public class Client {
	private static final Client client = new Client();
	
	public static final Client getInstance() {
		return client;
	}
	
	private DiscordRP discordRichPresence = new DiscordRP();
	
	public DiscordRP getDiscordRichPresence() {
		return discordRichPresence;
	}
	
	public void init() {
		discordRichPresence.start();
	}
	
	public void shutdown() {
		discordRichPresence.shutdown();
	}
	
	@EventTarget
	public void onTick(TickEvent event) {
		
	}
}
