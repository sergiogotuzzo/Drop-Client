package drop.mods;

import org.apache.commons.lang3.StringUtils;

import drop.Drop;
import drop.event.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiModSettings;
import drop.mods.option.Brackets;
import drop.mods.option.ModOption;
import drop.mods.option.type.BooleanOption;
import drop.mods.option.type.BracketsOption;
import drop.mods.option.type.ColorOption;
import drop.mods.option.type.FloatOption;
import drop.mods.option.type.IntOption;

public abstract class Mod {
	protected boolean enabled;
	
	protected final Minecraft mc;
	protected final FontRenderer font;
	protected final Drop client;
	
	protected ModOptions options = new ModOptions();
	
	public Mod(boolean enabled) {
		mc = Minecraft.getMinecraft();
		font = mc.fontRendererObj;
		client = Drop.getInstance();
		
		setEnabled(getBooleanFromFile("enabled", enabled));				
	}
	
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiModSettings(previousGuiScreen, this);
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
	
	public void saveOptions() {
		for (ModOption option : this.options.getOptions()) {
			if (option instanceof BooleanOption) {
				option.saveValue(getBooleanFromFile(option.getKey(), (boolean) option.getValue()));
			} else if (option instanceof ColorOption) {
				ColorOption colorOption = (ColorOption) option;

				int rgb = getIntFromFile(colorOption.getKeyRGB(), colorOption.getColor().getRGB());
				boolean chroma = colorOption.shouldBeShownChromaInGui() ? getBooleanFromFile(colorOption.getKeyChroma(), colorOption.getColor().isChromaToggled()) : false;
				
				colorOption.saveValue(ModColor.fromRGB(rgb, chroma));
			} else if (option instanceof FloatOption) {
				option.saveValue(getFloatFromFile(option.getKey(), (float) option.getValue()));
			} else if (option instanceof IntOption) {
				option.saveValue(getIntFromFile(option.getKey(), (int) option.getValue()));
			} else if (option instanceof BracketsOption) {
				((BracketsOption) option).saveValue(Brackets.fromId(getIntFromFile(option.getKey(), ((BracketsOption) option).getId())));
			}
		}
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
	
	public ModOptions getOptions() {
		return options;
	}
}
