package rubik;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;

public class DiscordRP {
	private boolean running = true;
	private long createdAt = 0;
	
	public void start() {
		this.createdAt = System.currentTimeMillis();
		
		ReadyCallback readyEventHandler = new ReadyCallback() {
			@Override
			public void apply(DiscordUser user) {
				System.out.println("Welcome " + user.username + "#" + user.discriminator);
				
				update("Launching Client...", "");
			}
		};
		DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(readyEventHandler).build();
		
		DiscordRPC.discordInitialize("1229162105270439946", handlers, true);
		
		new Thread("Discord RPC Callback") {
			@Override
			public void run() {
				while (running) {
					DiscordRPC.discordRunCallbacks();
				}
			}
		}.start();
	}
	
	public void shutdown() {
		this.running = false;
		
		DiscordRPC.discordShutdown();
	}
	
	public void update(String firstLine, String secondLine) {
		DiscordRichPresence.Builder richPresenceBuilder = new DiscordRichPresence.Builder(secondLine);
		
		richPresenceBuilder.setBigImage("large", "large");
		richPresenceBuilder.setDetails(firstLine);
		richPresenceBuilder.setStartTimestamps(createdAt);
		
		DiscordRPC.discordUpdatePresence(richPresenceBuilder.build());
	}
}
