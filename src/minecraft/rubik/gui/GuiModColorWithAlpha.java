package rubik.gui;

import java.awt.Color;
import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.Client;
import rubik.ColorManager;
import rubik.gui.GuiSlider;
import rubik.mods.Mod;

public class GuiModColorWithAlpha extends GuiRubikClientScreen {
	private final GuiScreen previousGuiScreen;
	private final ColorManager color;
	private final Mod mod;
    private final String key;
	
	private GuiSlider sliderRed;
    private GuiSlider sliderGreen;
    private GuiSlider sliderBlue;
    private GuiSlider sliderAlpha;
	
	public GuiModColorWithAlpha(GuiScreen previousGuiScreen, ColorManager color, Mod mod) {
		this(previousGuiScreen, color, mod, "backgroundColor");
	}
	
	public GuiModColorWithAlpha(GuiScreen previousGuiScreen, ColorManager color, Mod mod, String key) {
		this.previousGuiScreen = previousGuiScreen;
		this.color = color;
		this.mod = mod;
		this.key = key;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, I18n.format("Customize Mod Color", new Object[0]), this.width / 2, 20, 0xFFFFFFFF);
        this.drawString(this.fontRendererObj, "Rubik Client (" + Client.version + ")", 2, this.height - 10, 0x808080);
        this.drawString(this.fontRendererObj, "Minecraft 1.8.9", this.width - this.fontRendererObj.getStringWidth("Minecraft 1.8.9") - 2, this.height - 10, 0x808080);
        super.drawScreen(mouseX, mouseY, partialTicks);
        
        int rectLeft = this.width / 2 - 75;
        int rectTop = this.height / 4 + 80;
        int rectWidth = 150 - 1;
        int rectHeight = 20 - 1;
        
        drawRect(rectLeft, rectTop, rectLeft + rectWidth, rectTop + rectHeight, color.getRGB());
        drawHollowRect(rectLeft, rectTop, rectWidth, rectHeight, Color.BLACK.getRGB());
        this.fontRendererObj.drawStringWithShadow("Current Color", (rectLeft + (rectWidth - this.fontRendererObj.getStringWidth("Current Color")) / 2) + 1, (rectTop + (rectHeight - this.fontRendererObj.FONT_HEIGHT) / 2) + 1, Color.WHITE.getRGB());
    }
    
    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
    	color.setRed((int) (sliderRed.func_175217_d() * 255.0F));
    	color.setGreen((int) (sliderGreen.func_175217_d() * 255.0F));
    	color.setBlue((int) (sliderBlue.func_175217_d() * 255.0F));
    	color.setAlpha((int) (sliderAlpha.func_175217_d() * 255.0F));
    	
    	mod.setToFile(key, color.getRGB());
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
            	this.mc.displayGuiScreen(this.previousGuiScreen);
            	break;
            case 1:
            	color.setRed((int) (sliderRed.func_175217_d() * 255.0F));
            	mod.setToFile(key, color.getRGB());
            	break;
            case 2:
            	color.setGreen((int) (sliderGreen.func_175217_d() * 255.0F));
            	mod.setToFile(key, color.getRGB());
            	break;
            case 3:
            	color.setBlue((int) (sliderBlue.func_175217_d() * 255.0F));
            	mod.setToFile(key, color.getRGB());
            	break;
            case 4:
            	color.setAlpha((int) (sliderAlpha.func_175217_d() * 255.0F));
            	mod.setToFile(key, color.getRGB());
            	break;
        }
    }
	
	@Override
    public void initGui() {
        this.buttonList.clear();
        
        int i = -16;
        int j = 75;
        
		this.buttonList.add(sliderRed = new GuiSlider(1, this.width / 2 - j, this.height / 4 + i, 150, 20, "Red", 0, 255, color.getRed()));
        this.buttonList.add(sliderGreen = new GuiSlider(2, this.width / 2 - j, this.height / 4 + 24 + i, 150, 20, "Green", 0, 255, color.getGreen()));
        this.buttonList.add(sliderBlue = new GuiSlider(3, this.width / 2 - j, this.height / 4 + 48 + i, 150, 20, "Blue", 0, 255, color.getBlue()));
        this.buttonList.add(sliderAlpha = new GuiSlider(4, this.width / 2 - j, this.height / 4 + 72 + i, 150, 20, "Alpha", 0, 255, color.getAlpha()));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done", new Object[0])));
    }
}
