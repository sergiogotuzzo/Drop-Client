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
import drop.mods.option.type.*;

public abstract class Mod {
	protected boolean enabled;
	
	protected final Minecraft mc;
	protected final FontRenderer font;
	protected final Drop client;
	
	protected ModOptions options = new ModOptions();
	
	public Mod(boolean enabled) {
		mc = Minecraft.getMinecraft();
		font = mc.fontRendererObj;
		client = Drop.getDropClient();
		
		toggle((boolean) getFromFile("enabled", enabled));				
	}
	
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiModSettings(previousGuiScreen, this);
	}

	public void toggle(boolean enabled) {
		this.enabled = enabled;
		
		if (enabled) {
			EventManager.register(this);
		} else {
			EventManager.unregister(this);
		}
		
		setToFile("enabled", enabled);
	}
	
	public void toggle() {
		toggle(!enabled);
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void saveOptions() {
		for (ModOption option : this.options.getOptions()) {
			if (option instanceof BooleanOption) {
				option.saveValue((boolean) getFromFile(option.getKey(), ((BooleanOption) option).isToggled()));
			} else if (option instanceof ColorOption) {
				ColorOption colorOption = (ColorOption) option;

				int rgb = (int) ((long) getFromFile(colorOption.getKeyRGB(), colorOption.getColor().getRGB()));
				boolean chroma = colorOption.getGuiSettings().shouldBeChromaCheckBoxShown() ? (boolean) getFromFile(colorOption.getKeyChroma(), colorOption.getColor().isChromaToggled()) : false;
				
				colorOption.saveValue(ModColor.fromRGB(rgb, chroma));
			} else if (option instanceof FloatOption) {
				option.saveValue((float) ((double) getFromFile(option.getKey(), (float) option.getValue())));
			} else if (option instanceof IntOption) {
				option.saveValue((int) ((long) getFromFile(option.getKey(), (int) option.getValue())));
			} else if (option instanceof EnumOption) {
				option.saveValue((int) ((long) getFromFile(option.getKey(), (int) option.getValue())));
			}
		}
	}
	
	public void setToFile(String key, Object value) {
		Drop.getDropClient().getModsFile().set(this.getClass().getSimpleName() + "." + key, value);
	}
	
	public Object getFromFile(String key, Object defaultValue) {
		if (!hasInFile(key)) {
			setToFile(key, defaultValue);
		}
		
		return Drop.getDropClient().getModsFile().get(this.getClass().getSimpleName() + "." + key);
	}
	
	public boolean hasInFile(String key) {
		return Drop.getDropClient().getModsFile().has(this.getClass().getSimpleName() + "." + key);
	}
	
	public String getName() {
		return StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(this.getClass().getSimpleName().replace("Mod", "").replaceAll("\\d+", "")), " ");
	}
	
	public ModOptions getOptions() {
		return options;
	}
	
	public Minecraft getMinecraft() {
		return mc;
	}
}
