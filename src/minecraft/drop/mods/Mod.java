package drop.mods;

import drop.Client;
import drop.FileManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import drop.events.EventManager;

public abstract class Mod {
	private boolean enabled = true;
	
	protected final Minecraft mc;
	protected final FontRenderer font;
	protected final Client client;
	
	public Mod() {
		mc = Minecraft.getMinecraft();
		font = mc.fontRendererObj;
		client = Client.getInstance();
		
		setEnabled((boolean) getFromFile("enabled", enabled));
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		
		if (enabled) {
			EventManager.register(this);
		} else {
			EventManager.unregister(this);
		}
		
		setToFile("enabled", enabled);
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setToFile(String key, Object value) {
		FileManager.set(getName() + "." + key, value);
	}
	
	public Object getFromFile(String key, Object defaultValue) {
		if (!hasInFile(key)) {
			setToFile(key, defaultValue);
		}
		
		return FileManager.get(getName() + "." + key);
	}
	
	public boolean hasInFile(String key) {
		return FileManager.has(getName() + "." + key);
	}
	
	public String getName() {
		return this.getClass().getSimpleName();
	}
}
