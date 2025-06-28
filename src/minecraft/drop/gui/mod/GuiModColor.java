package drop.gui.mod;

import java.awt.Color;
import java.io.IOException;

import drop.ColorManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import drop.gui.GuiDropClientScreen;
import drop.gui.GuiSlider;
import drop.mods.Mod;
import drop.mods.option.type.ColorOption;

public class GuiModColor extends GuiDropClientScreen {
	private final GuiScreen previousGuiScreen;
	private final Mod mod;
	
	protected final ColorOption option;

	private GuiSlider sliderRed;
    private GuiSlider sliderGreen;
    private GuiSlider sliderBlue;
    private GuiSlider sliderAlpha;
    
    private ColorManager color;
	
	public GuiModColor(GuiScreen previousGuiScreen, Mod mod, ColorOption option) {
		this.previousGuiScreen = previousGuiScreen;
		this.mod = mod;
		this.option = option;
		
		this.color = (ColorManager) option.getValue();
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	this.drawDefaultBackground();
    	
    	this.drawRect((this.width - 350) / 2, (this.height - 250) / 2, (this.width - 350) / 2 + 350, (this.height - 250) / 2 + 250, new Color(0, 0, 0, 127).getRGB());
        
        this.drawScaledText(mod.getName(), (this.width - 350) / 2 + 15, (this.height - 250) / 2 + 15, 2.0D, 0xFFFFFFFF, true, false);
        this.drawScaledText(option.getGuiSettings().getOptionName(), (this.width - 350) / 2 + 15, (this.height - 250) / 2 + 30 + 15 * 0 + 15 - 5, 1.3D, ColorManager.fromRGB(color.getRGB(), color.isChromaToggled()).setAlpha(255).getRGB(), true, color.isChromaToggled());
        
        this.writeOptionText("Red", 2, Color.RED.getRGB());
        this.writeOptionValue(String.valueOf(color.getRed()), 2);
        this.writeOptionText("Green", 4, Color.GREEN.getRGB());
        this.writeOptionValue(String.valueOf(color.getGreen()), 4);
        this.writeOptionText("Blue", 6, Color.BLUE.getRGB());
        this.writeOptionValue(String.valueOf(color.getBlue()), 6);

        if (option.getGuiSettings().shouldBeAlphaSliderShown()) {
            this.writeOptionText("Alpha", 8);
            this.writeOptionValue(String.valueOf(color.getAlpha()), 8);
        }
        
        if (option.getGuiSettings().shouldBeChromaCheckBoxShown()) {
        	this.writeOptionText("Chroma", option.getGuiSettings().shouldBeAlphaSliderShown() ? 10 : 8, true);
        }
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
    	color.setRed((int) (sliderRed.getSliderPosition() * 255.0F));
    	color.setGreen((int) (sliderGreen.getSliderPosition() * 255.0F));
    	color.setBlue((int) (sliderBlue.getSliderPosition() * 255.0F));
    	
    	if (option.getGuiSettings().shouldBeAlphaSliderShown()) {
        	color.setAlpha((int) (sliderAlpha.getSliderPosition() * 255.0F));
    	}
    	
    	option.saveValue(color);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {    	
        switch (button.id) {
	        case 0:
	        	this.mc.displayGuiScreen(this.previousGuiScreen);
	        	break;
            case 1:
            	color.setRed((int) (sliderRed.getSliderPosition() * 255.0F));
            	option.saveValue(color);
            	break;
            case 2:
            	color.setGreen((int) (sliderGreen.getSliderPosition() * 255.0F));
            	option.saveValue(color);
            	break;
            case 3:
            	color.setBlue((int) (sliderBlue.getSliderPosition() * 255.0F));
            	option.saveValue(color);
            	break;
            case 4:
            	color.setAlpha((int) (sliderAlpha.getSliderPosition() * 255.0F));
            	option.saveValue(color);
            	break;
            case 5:
            	color.setChromaToggled(!color.isChromaToggled());
            	option.saveValue(color);
            	this.initGui();
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();

        this.buttonList.clear();
        
        this.buttonList.add(new GuiButton(0, (this.width + 350) / 2 - 50 - 15, (this.height - 250) / 2 + 15 - 3, 50, 20, I18n.format("gui.done", new Object[0])));
                
    	this.buttonList.add(sliderRed = this.createGuiSlider(1, 255.0F, color.getRed(), 3));
    	this.buttonList.add(sliderGreen = this.createGuiSlider(2,255.0F, color.getGreen(), 5));
    	this.buttonList.add(sliderBlue = this.createGuiSlider(3, 255.0F, color.getBlue(), 7));

    	if (option.getGuiSettings().shouldBeAlphaSliderShown()) {
        	this.buttonList.add(sliderAlpha = this.createGuiSlider(4, 255.0F, color.getAlpha(), 9));
    	}
    	
    	if (option.getGuiSettings().shouldBeChromaCheckBoxShown()) {
        	this.buttonList.add(this.createGuiCheckBox(5, color.isChromaToggled(), option.getGuiSettings().shouldBeAlphaSliderShown() ? 10 : 8));
    	}
    }
}
