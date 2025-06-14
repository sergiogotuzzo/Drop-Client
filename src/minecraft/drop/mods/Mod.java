package drop.mods;

import org.apache.commons.lang3.StringUtils;

import drop.Drop;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import drop.events.EventManager;
import drop.gui.GuiDropClientScreen;

public abstract class Mod {
	protected boolean enabled;
	
	protected final Minecraft mc;
	protected final FontRenderer font;
	protected final Drop client;
		
	public Mod(boolean enabled) {
		mc = Minecraft.getMinecraft();
		font = mc.fontRendererObj;
		client = Drop.getInstance();
		
		setEnabled((boolean) getFromFile("enabled", enabled));
	}
	
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return null;
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
		Drop.getInstance().getModsFile().set(this.getClass().getSimpleName() + "." + key, value);
	}
	
	private Object getFromFile(String key, Object defaultValue) {
		if (!hasInFile(key)) {
			setToFile(key, defaultValue);
		}
		
		return Drop.getInstance().getModsFile().get(this.getClass().getSimpleName() + "." + key);
	}
	
	public boolean getBooleanFromFile(String key, boolean defaultValue) {
		return (boolean) getFromFile(key, defaultValue);
	}
	
	public double getDoubleFromFile(String key, double defaultValue) {
		return (double) getFromFile(key, defaultValue);
	}
	
	public float getFloatFromFile(String key, float defaultValue) {
		return (float) getDoubleFromFile(key, defaultValue);
	}
	
	public long getLongFromFile(String key, long defaultValue) {
		return (long) getFromFile(key, defaultValue);
	}
	
	public int getIntFromFile(String key, int defaultValue) {
		return (int) getLongFromFile(key, defaultValue);
	}
	
	public boolean hasInFile(String key) {
		return Drop.getInstance().getModsFile().has(this.getClass().getSimpleName() + "." + key);
	}
	
	public String getName() {
		return StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(this.getClass().getSimpleName().replace("Mod", "").replaceAll("\\d+", "")), " ");
	}
}
