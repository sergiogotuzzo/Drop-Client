package drop.gui.mod;

import java.io.IOException;

import drop.ColorManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.GuiSlider;
import drop.mods.Mod;

public class GuiModColor extends GuiMod {
	protected final ColorManager color;
	protected final String colorKey;
	protected final String colorChromaKey;
	protected final String subtitle;
	protected final boolean showAlphaSlider;
	
	private GuiSlider sliderRed;
    private GuiSlider sliderGreen;
    private GuiSlider sliderBlue;
    private GuiSlider sliderAlpha;
	
    public GuiModColor(GuiScreen previousGuiScreen, Mod mod, ColorManager color) {
		this(previousGuiScreen, mod, color, "textColor", "textChroma", "Text Color", false);
	}
    
    public GuiModColor(GuiScreen previousGuiScreen, Mod mod, ColorManager color, String colorKey, String colorChromaKey, String subtitle) {
		this(previousGuiScreen, mod, color, colorKey, colorChromaKey, subtitle, false);
	}
	
	public GuiModColor(GuiScreen previousGuiScreen, Mod mod, ColorManager color, String colorKey, String colorChromaKey, String subtitle, boolean showAlphaSlider) {
		super(previousGuiScreen, mod);

		this.color = color;
		this.colorChromaKey = colorChromaKey;
		this.colorKey = colorKey;
		this.subtitle = subtitle;
		this.showAlphaSlider = showAlphaSlider;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	super.drawScreen(mouseX, mouseY, partialTicks);
    	
        this.drawScaledText(subtitle, (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15 - 5, 1.3D, ColorManager.fromRGB(color.getRGB(), color.isChromaToggled()).setAlpha(255).getRGB(), false, false);
        
        this.writeOptionText("Red", 2);
        this.writeOptionValue(String.valueOf(color.getRed()), 2);
        this.writeOptionText("Green", 4);
        this.writeOptionValue(String.valueOf(color.getGreen()), 4);
        this.writeOptionText("Blue", 6);
        this.writeOptionValue(String.valueOf(color.getBlue()), 6);

        if (showAlphaSlider) {
            this.writeOptionText("Alpha", 8);
            this.writeOptionValue(String.valueOf(color.getAlpha()), 8);
        }
        
        this.writeOptionText("Chroma", showAlphaSlider ? 10 : 8);
    }
    
    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
    	color.setRed((int) (sliderRed.getSliderPosition() * 255.0F));
    	color.setGreen((int) (sliderGreen.getSliderPosition() * 255.0F));
    	color.setBlue((int) (sliderBlue.getSliderPosition() * 255.0F));
    	
    	if (showAlphaSlider) {
        	color.setAlpha((int) (sliderAlpha.getSliderPosition() * 255.0F));
    	}
    	
    	mod.setToFile(colorKey, color.getRGB());
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
            case 1:
            	color.setRed((int) (sliderRed.getSliderPosition() * 255.0F));
            	mod.setToFile(colorKey, color.getRGB());
            	break;
            case 2:
            	color.setGreen((int) (sliderGreen.getSliderPosition() * 255.0F));
            	mod.setToFile(colorKey, color.getRGB());
            	break;
            case 3:
            	color.setBlue((int) (sliderBlue.getSliderPosition() * 255.0F));
            	mod.setToFile(colorKey, color.getRGB());
            	break;
            case 4:
            	color.setAlpha((int) (sliderAlpha.getSliderPosition() * 255.0F));
            	mod.setToFile(colorKey, color.getRGB());
            	break;
            case 5:
            	color.setChromaToggled(!color.isChromaToggled());
            	mod.setToFile(colorChromaKey, color.isChromaToggled());
            	this.initGui();
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
                
    	this.buttonList.add(sliderRed = this.createGuiSlider(1, 0.0F, 255.0F, color.getRed(), 3));
    	this.buttonList.add(sliderGreen = this.createGuiSlider(2, 0.0F, 255.0F, color.getGreen(), 5));
    	this.buttonList.add(sliderBlue = this.createGuiSlider(3, 0.0F, 255.0F, color.getBlue(), 7));

    	if (showAlphaSlider) {
        	this.buttonList.add(sliderAlpha = this.createGuiSlider(4, 0.0F, 255.0F, color.getAlpha(), 9));
    	}
    	
    	this.buttonList.add(this.createGuiButtonToggled(5, color.isChromaToggled(), showAlphaSlider ? 10 : 8));
    }
}
