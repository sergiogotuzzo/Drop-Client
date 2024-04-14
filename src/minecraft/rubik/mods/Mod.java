package rubik.mods;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import rubik.Client;
import rubik.event.EventManager;

public abstract class Mod {
	private boolean enabled = true;
	
	protected final Minecraft mc;
	protected final FontRenderer font;
	protected final Client client;
	
	public Mod() {
		mc = Minecraft.getMinecraft();
		font = mc.fontRendererObj;
		client = Client.getInstance();
		
		setEnabled(enabled);
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		
		if (enabled) {
			EventManager.register(this);
		} else {
			EventManager.unregister(this);
		}
	}
	
	public boolean isEnabled() {
		return enabled;
	}
}
