package drop.gui.mod;

import java.awt.Color;
import java.io.IOException;

import drop.Client;
import drop.ColorManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import drop.gui.GuiButtonToggled;
import drop.gui.GuiDropClientScreen;
import drop.gui.GuiSlider;
import drop.mods.Mod;

public class GuiModColor extends GuiDropClientScreen {
	private final GuiScreen previousGuiScreen;
	protected final ColorManager color;
	protected final Mod mod;
	protected final String colorKey;
	protected final String colorChromaKey;
	protected final String title;
	protected final String subtitle;
	protected final boolean showAlphaSlider;
	
	private GuiSlider sliderRed;
    private GuiSlider sliderGreen;
    private GuiSlider sliderBlue;
    private GuiSlider sliderAlpha;
	
    public GuiModColor(GuiScreen previousGuiScreen, ColorManager color, Mod mod, String title) {
		this(previousGuiScreen, color,  mod, "textColor", "textChroma", title, "Text Color", false);
	}
    
    public GuiModColor(GuiScreen previousGuiScreen, ColorManager color, Mod mod, String title, String subtitle) {
		this(previousGuiScreen, color,  mod, "textColor", "textChroma", title, subtitle, false);
	}
    
    public GuiModColor(GuiScreen previousGuiScreen, ColorManager color, Mod mod, String colorKey, String colorChromaKey, String title, String subtitle) {
		this(previousGuiScreen, color, mod, colorKey, colorChromaKey, title, subtitle, false);
	}
	
	public GuiModColor(GuiScreen previousGuiScreen, ColorManager color, Mod mod, String colorKey, String colorChromaKey, String title, String subtitle, boolean showAlphaSlider) {
		this.previousGuiScreen = previousGuiScreen;
		this.color = color;
		this.colorChromaKey = colorChromaKey;
		this.mod = mod;
		this.colorKey = colorKey;
		this.title = title;
		this.subtitle = subtitle;
		this.showAlphaSlider = showAlphaSlider;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	this.drawDefaultBackground();
    	
    	drawRect((this.width - 300) / 2, (this.height - 200) / 2, (this.width - 300) / 2 + 300, (this.height - 200) / 2 + 200, new Color(0, 0, 0, 127).getRGB());
    	
    	String redText = String.valueOf(color.getRed());
    	String greenText = String.valueOf(color.getGreen());
    	String blueText = String.valueOf(color.getBlue());
        
        this.drawScaledText(title, (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 15, 2.0D, 0xFFFFFFFF, false, false);
        this.drawScaledText(subtitle, (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15 - 5, 1.3D, ColorManager.fromRGB(color.getRGB(), color.isChromaToggled()).setAlpha(255).getRGB(), false, false);
        this.drawText("Red", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 1 + 15, -1, false, false);
        this.drawText("Green", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 2 + 15, -1, false, false);
        this.drawText("Blue", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 3 + 15, -1, false, false);
        this.drawText(redText, (this.width + 300) / 2 - mc.fontRendererObj.getStringWidth(redText) - 15, (this.height - 200) / 2 + 30 + 15 * 1 + 15, -1, false, false);
        this.drawText(greenText, (this.width + 300) / 2 - mc.fontRendererObj.getStringWidth(greenText) - 15, (this.height - 200) / 2 + 30 + 15 * 2 + 15, -1, false, false);
        this.drawText(blueText, (this.width + 300) / 2 - mc.fontRendererObj.getStringWidth(blueText) - 15, (this.height - 200) / 2 + 30 + 15 * 3 + 15, -1, false, false);
        this.drawText("Chroma", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * (showAlphaSlider ? 5 : 4) + 15, -1, false, false);

        if (showAlphaSlider) {
        	String alphaText = String.valueOf(color.getAlpha());
        	
            this.drawText("Alpha", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 4 + 15, -1, false, false);
            this.drawText(alphaText, (this.width + 300) / 2 - mc.fontRendererObj.getStringWidth(alphaText) - 15, (this.height - 200) / 2 + 30 + 15 * 4 + 15, -1, false, false);
        }
        
        super.drawScreen(mouseX, mouseY, partialTicks);
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
        switch (button.id) {
            case 0:
            	this.mc.displayGuiScreen(this.previousGuiScreen);
            	break;
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
		
        this.buttonList.clear();
                
    	this.buttonList.add(sliderRed = new GuiSlider(1, (this.width - 300) / 2 + 100, (this.height - 200) / 2 + 30 + 15 * 1 + 15 + 1, 100, 5, 0.0F, 255.0F, color.getRed()));
    	this.buttonList.add(sliderGreen = new GuiSlider(2, (this.width - 300) / 2 + 100, (this.height - 200) / 2 + 30 + 15 * 2 + 15 + 1, 100, 5, 0.0F, 255.0F, color.getGreen()));
    	this.buttonList.add(sliderBlue = new GuiSlider(3, (this.width - 300) / 2 + 100, (this.height - 200) / 2 + 30 + 15 * 3 + 15 + 1, 100, 5, 0.0F, 255.0F, color.getBlue()));
    	this.buttonList.add(new GuiButtonToggled(5, color.isChromaToggled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * (showAlphaSlider ? 5 : 4) + 15 - 2));

    	if (showAlphaSlider) {
        	this.buttonList.add(sliderAlpha = new GuiSlider(4, (this.width - 300) / 2 + 100, (this.height - 200) / 2 + 30 + 15 * 4 + 15 + 1, 100, 5, 0.0F, 255.0F, color.getAlpha()));
    	}
    	
        this.buttonList.add(new GuiButton(0, (this.width + 300) / 2 - 50 - 15, (this.height - 200) / 2 + 15, 50, 20, I18n.format("gui.done", new Object[0])));
    }
}
