package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import drop.ColorManager;
import drop.gui.GuiBlurredScreen;
import drop.gui.GuiSlider;
import drop.mod.Mod;
import drop.mod.ModColor;
import drop.mod.option.type.ColorOption;

public class GuiModColor extends GuiModMenu {	
	protected final ColorOption option;

	private GuiSlider sliderRed;
    private GuiSlider sliderGreen;
    private GuiSlider sliderBlue;
    private GuiSlider sliderAlpha;
    
    private ModColor color;
	
	public GuiModColor(GuiScreen previousGuiScreen, Mod mod, ColorOption option) {
		super(previousGuiScreen, mod.getName());

		this.option = option;
		
		this.color = option.getColor();
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        this.drawScaledText(option.getGuiSettings().getOptionName(), (this.width - rectWidth) / 2 + 15, (this.height - rectHeight) / 2 + 30 + 15 * 0 + 15 - 5, 1.3D, new ColorManager(color.getRGB()).setAlpha(255).getRGB(), true, color.isChromaToggled());
        
        this.writeOptionText("Red", 2, new ColorManager(255, 0, 0).getRGB());
        this.writeOptionValue(String.valueOf(color.getRed()), 2);
        this.writeOptionText("Green", 4, new ColorManager(0, 255, 0).getRGB());
        this.writeOptionValue(String.valueOf(color.getGreen()), 4);
        this.writeOptionText("Blue", 6, new ColorManager(0, 0, 255).getRGB());
        this.writeOptionValue(String.valueOf(color.getBlue()), 6);

        if (option.getGuiSettings().shouldBeAlphaSliderShown()) {
            this.writeOptionText("Alpha", 8);
            this.writeOptionValue(String.valueOf(color.getAlpha()), 8);
        }
        
        if (option.getGuiSettings().shouldBeChromaCheckBoxShown()) {
        	this.writeOptionText("Chroma", option.getGuiSettings().shouldBeAlphaSliderShown() ? 10 : 8, true);
        }
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
    	super.actionPerformed(button);
    	
        switch (button.id) {
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
