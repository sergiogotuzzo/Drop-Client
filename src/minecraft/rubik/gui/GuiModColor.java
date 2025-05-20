package rubik.gui;

import java.awt.Color;
import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.Client;
import rubik.ColorManager;
import rubik.mods.Mod;

public class GuiModColor extends GuiDropClientScreen {
	private final GuiScreen previousGuiScreen;
	private final ColorManager color;
	private final Mod mod;
	private final String colorKey;
	private final String title;
	private final String subtitle;
	private final boolean showAlphaSlider;
	
	private GuiDropClientSlider sliderRed;
    private GuiDropClientSlider sliderGreen;
    private GuiDropClientSlider sliderBlue;
    private GuiDropClientSlider sliderAlpha;
	
    public GuiModColor(GuiScreen previousGuiScreen, ColorManager color, Mod mod, String title) {
		this(previousGuiScreen, color,  mod, "textColor", title, "Text Color", false);
	}
    
    public GuiModColor(GuiScreen previousGuiScreen, ColorManager color, Mod mod, String title, String subtitle) {
		this(previousGuiScreen, color,  mod, "textColor", title, subtitle, false);
	}
    
    public GuiModColor(GuiScreen previousGuiScreen, ColorManager color, Mod mod, String colorKey, String title, String subtitle) {
		this(previousGuiScreen, color, mod, colorKey, title, subtitle, false);
	}
	
	public GuiModColor(GuiScreen previousGuiScreen, ColorManager color, Mod mod, String colorKey, String title, String subtitle, boolean showAlphaSlider) {
		this.previousGuiScreen = previousGuiScreen;
		this.color = color;
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
        this.drawScaledText(subtitle, (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15 - 5, 1.3D, ColorManager.fromRGB(color.getRGB()).setAlpha(255).getRGB(), false, false);
        this.drawText("Red", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 1 + 15, -1, false, false);
        this.drawText("Green", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 2 + 15, -1, false, false);
        this.drawText("Blue", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 3 + 15, -1, false, false);
        this.drawText(redText, (this.width + 300) / 2 - mc.fontRendererObj.getStringWidth(redText) - 15, (this.height - 200) / 2 + 30 + 15 * 1 + 15, -1, false, false);
        this.drawText(greenText, (this.width + 300) / 2 - mc.fontRendererObj.getStringWidth(greenText) - 15, (this.height - 200) / 2 + 30 + 15 * 2 + 15, -1, false, false);
        this.drawText(blueText, (this.width + 300) / 2 - mc.fontRendererObj.getStringWidth(blueText) - 15, (this.height - 200) / 2 + 30 + 15 * 3 + 15, -1, false, false);
        
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
        }
    }
	
	@Override
    public void initGui() {
        this.buttonList.clear();
        
    	this.buttonList.add(sliderRed = new GuiDropClientSlider(1, (this.width - 300) / 2 + 100, (this.height - 200) / 2 + 30 + 15 * 1 + 15 + 1, 100, 5, 0, 255, color.getRed()));
    	this.buttonList.add(sliderGreen = new GuiDropClientSlider(2, (this.width - 300) / 2 + 100, (this.height - 200) / 2 + 30 + 15 * 2 + 15 + 1, 100, 5, 0, 255, color.getGreen()));
    	this.buttonList.add(sliderBlue = new GuiDropClientSlider(3, (this.width - 300) / 2 + 100, (this.height - 200) / 2 + 30 + 15 * 3 + 15 + 1, 100, 5, 0, 255, color.getBlue()));

    	if (showAlphaSlider) {
        	this.buttonList.add(sliderAlpha = new GuiDropClientSlider(4, (this.width - 300) / 2 + 100, (this.height - 200) / 2 + 30 + 15 * 4 + 15 + 1, 100, 5, 0, 255, color.getAlpha()));
    	}
    	
        this.buttonList.add(new GuiButton(0, (this.width + 300) / 2 - 50 - 15, (this.height - 200) / 2 + 15, 50, 20, I18n.format("gui.done", new Object[0])));
    }
}
