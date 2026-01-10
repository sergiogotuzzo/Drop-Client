package drop.mod;

import org.apache.commons.lang3.StringUtils;

import drop.Drop;
import drop.event.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import drop.gui.GuiScreenDC;
import drop.gui.mod.GuiModOptions;
import drop.mod.option.Brackets;
import drop.mod.option.ModOption;
import drop.mod.option.type.*;

public abstract class Mod {
	protected boolean enabled;
	protected ModOption[] options;
	
	protected final Minecraft mc;
	protected final FontRenderer font;
	protected final Drop client;
	
	public Mod(boolean enabled) {
		mc = Minecraft.getMinecraft();
		font = mc.fontRendererObj;
		client = Drop.getDropClient();
		
		setEnabled((boolean) getFromFile("enabled", enabled));				
	}
	
	public GuiScreenDC getGui(GuiScreenDC previousGuiScreen) {
		return new GuiModOptions(previousGuiScreen, this);
	}
	
	public String getName() {
		return StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(getClass().getSimpleName().replaceAll("\\d+", "")), " ");
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
	
	public void toggle() {
		setEnabled(!enabled);
	}
	
	public void saveOptions(ModOption... options) {
		this.options = options;
		
		for (ModOption option : this.options) {
			if (option instanceof BooleanOption) {
				option.saveValue((boolean) getFromFile(option.getKey(), ((BooleanOption) option).isToggled()));
			} else if (option instanceof ColorOption) {
				ColorOption colorOption = (ColorOption) option;

				int rgb = (int) ((long) getFromFile(colorOption.getKeyRGB(), colorOption.getColor().getRGB()));
				boolean chroma = colorOption.getGuiSettings().shouldBeChromaCheckBoxShown() ? (boolean) getFromFile(colorOption.getKeyChroma(), colorOption.getColor().isChromaToggled()) : false;
				
				colorOption.saveValue(ModColor.fromRGB(rgb, chroma));
			} else if (option instanceof FloatOption) {
				option.saveValue((float) ((double) getFromFile(option.getKey(), (float) option.getValue())));
			} else if (option instanceof IntOption || option instanceof EnumOption) {
				option.saveValue((int) ((long) getFromFile(option.getKey(), (int) option.getValue())));
			}
		}
	}
	
	public ModOption[] getOptions() {
		return options;
	}
	
	public ModOption getOption(String key) {		
		for (ModOption option : options) {
			if (option.getKey() == key) {
				return option;
			}
		}
		
		return null;
	}
	
	public BooleanOption getBooleanOption(String key) {		
		return (BooleanOption) getOption(key);
	}
	
	public ColorOption getColorOption(String key) {		
		return (ColorOption) getOption(key);
	}
	
	public IntOption getIntOption(String key) {
		return (IntOption) getOption(key);
	}
	
	public FloatOption getFloatOption(String key) {
		return (FloatOption) getOption(key);
	}
	
	public EnumOption getEnumOption(String key) {
		return (EnumOption) getOption(key);
	}
	
	public void setToFile(String key, Object value) {
		client.getModsFile().set(getClass().getSimpleName() + "." + key, value);
	}
	
	public Object getFromFile(String key, Object defaultValue) {
		if (!hasInFile(key)) {
			setToFile(key, defaultValue);
		}
		
		return client.getModsFile().get(getClass().getSimpleName() + "." + key);
	}
	
	public boolean hasInFile(String key) {
		return client.getModsFile().has(getClass().getSimpleName() + "." + key);
	}

	public Minecraft getMinecraft() {
		return mc;
	}
}
