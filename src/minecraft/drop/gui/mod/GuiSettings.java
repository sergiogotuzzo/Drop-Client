package drop.gui.mod;

public class GuiSettings {
	private boolean visible;
	private int buttonId;
	private String optionName;
	private boolean shouldBeChromaCheckBoxShown;
	private boolean shouldBeAlphaSliderShown;
	private int decimals;
	
	public GuiSettings(boolean visible, int buttonId, String optionName, boolean shouldBeChromaCheckBoxShown, boolean shouldBeAlphaSliderShown, int decimals) {
		this.visible = visible;
		this.buttonId = buttonId;
		this.optionName = optionName;
		this.shouldBeChromaCheckBoxShown = shouldBeChromaCheckBoxShown;
		this.shouldBeAlphaSliderShown = shouldBeAlphaSliderShown;
		this.decimals = decimals;
	}
	
	public GuiSettings(int buttonId, String optionName, boolean shouldBeChromaCheckBoxShown, boolean shouldBeAlphaSliderShown, int decimals) {
		this(true, buttonId, optionName, shouldBeChromaCheckBoxShown, shouldBeAlphaSliderShown, decimals);
	}
	
	public GuiSettings(int buttonId, String optionName, boolean shouldBeChromaCheckBoxShown, boolean shouldBeAlphaSliderShown) {
		this(true, buttonId, optionName, shouldBeChromaCheckBoxShown, shouldBeAlphaSliderShown, 1);
	}
	
	public GuiSettings(boolean visible, int buttonId, String optionName, boolean shouldBeChromaCheckBoxShown, boolean shouldBeAlphaSliderShown) {
		this(visible, buttonId, optionName, shouldBeChromaCheckBoxShown, shouldBeAlphaSliderShown, 1);
	}
	
	public GuiSettings(int buttonId, String optionName, int decimals) {
		this(true, buttonId, optionName, false, false, decimals);
	}
	
	public GuiSettings(boolean visible, int buttonId, String optionName) {
		this(visible, buttonId, optionName, false, false, 1);
	}
	
	public GuiSettings(int buttonId, String optionName) {
		this(true, buttonId, optionName);
	}
	
	public GuiSettings(boolean visible) {		
		this(visible, 0, "", false, false, 0);
	}
	
	public int getButtonId() {
		return buttonId;
	}
	
	public String getOptionName() {
		return optionName;
	}
	
	public boolean shouldBeChromaCheckBoxShown() {
		return shouldBeChromaCheckBoxShown;
	}
	
	public boolean shouldBeAlphaSliderShown() {
		return shouldBeAlphaSliderShown;
	}
	
	public int getDecimals() {
		return decimals;
	}
	
	public boolean isVisible() {
		return visible;
	}
}
