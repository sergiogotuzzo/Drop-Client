package drop.gui.mod;

import java.awt.Color;
import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import drop.gui.GuiCheckBox;
import drop.gui.GuiDropClientScreen;
import drop.gui.GuiRect;
import drop.gui.GuiSlider;
import drop.gui.GuiSliderOption;
import drop.gui.GuiText;
import drop.mod.option.ModOption;

public class GuiModMenu extends GuiDropClientScreen {
	private final GuiScreen previousGuiScreen;
	private String title;
	
	public int rectWidth = 350;
	public int rectHeight = 250;
	
	public GuiModMenu(GuiScreen previousGuiScreen, String title) {
		this.previousGuiScreen = previousGuiScreen;
		this.title = title;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	this.drawDefaultBackground();
    	
    	this.drawRect((this.width - rectWidth) / 2, (this.height - rectHeight) / 2, (this.width - rectWidth) / 2 + rectWidth, (this.height - rectHeight) / 2 + rectHeight, new Color(0, 0, 0, 127).getRGB());
        
        this.drawScaledText(title, (this.width - rectWidth) / 2 + 15, (this.height - rectHeight) / 2 + 15, 2.0D, 0xFFFFFFFF, true, false);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) {
        	this.mc.displayGuiScreen(this.previousGuiScreen);
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();

        this.buttonList.clear();

        this.buttonList.add(new GuiButton(0, (this.width + rectWidth) / 2 - 50 - 15, (this.height - rectHeight) / 2 + 15 - 3, 50, 20, I18n.format("gui.done", new Object[0])));
    }
	
	protected GuiCheckBox createGuiCheckBox(int id, boolean toggled, int line) {
    	return new GuiCheckBox(id, (this.width + rectWidth) / 2 - 15 - 13, (this.height - rectHeight) / 2 + 30 + 15 - 2 * 2 + 15 * (line - 1), toggled);
	}
	
	protected GuiRect createGuiRect(int id, int color, boolean rainbow, int line) {
    	return new GuiRect(id, (this.width + rectWidth) / 2 - 15 - 13, (this.height - rectHeight) / 2 + 30 + 15 - 2 * 2 + 15 * (line - 1), 13, 13, color, Color.BLACK.getRGB(), rainbow);
	}
	
	protected GuiSlider createGuiSlider(int id, float max, float defaultValue, int line) {
    	return new GuiSlider(id, (this.width - rectWidth) / 2 + 15, (this.height - rectHeight) / 2 + 30 + 15 + 1 + 15 * (line - 1), rectWidth - 15 * 2, 5, 0.0F, max, defaultValue);
	}
	
	protected GuiSliderOption createGuiSliderOption(int id, float max, float defaultValue, int line, ModOption option) {
    	return new GuiSliderOption(id, (this.width - rectWidth) / 2 + 15, (this.height - rectHeight) / 2 + 30 + 15 + 1 + 15 * (line - 1), rectWidth - 15 * 2, 5, 0.0F, max, defaultValue, option);
	}
	
	protected GuiText createGuiText(int id, String text, boolean right, int line) {
    	return new GuiText(id, right ? (this.width + rectWidth) / 2 - 15 - mc.fontRendererObj.getStringWidth(text) : (this.width - rectWidth) / 2 + 15, (this.height - rectHeight) / 2 + 30 + 15 + 15 * (line - 1), text);
	}
	
	protected void writeOptionText(String text, int line) {
        this.drawText(text, (this.width - rectWidth) / 2 + 15, (this.height - rectHeight) / 2 + 30 + 15 + 15 * (line - 1), -1, true, false);
	}
	
	protected void writeOptionText(String text, int line, boolean chroma) {
        this.drawText(text, (this.width - rectWidth) / 2 + 15, (this.height - rectHeight) / 2 + 30 + 15 + 15 * (line - 1), -1, true, chroma);
	}
	
	protected void writeOptionText(String text, int line, int color) {
        this.drawText(text, (this.width - rectWidth) / 2 + 15, (this.height - rectHeight) / 2 + 30 + 15 + 15 * (line - 1), color, true, false);
	}
	
	protected void writeOptionValue(String text, int line) {
        this.drawText(text, (this.width + rectWidth) / 2 - mc.fontRendererObj.getStringWidth(text) - 15, (this.height - rectHeight) / 2 + 30 + 15 + 15 * (line - 1), -1, true, false);
	}
	
	protected void writeSelectedOptionValue(String text, int line) {
        this.drawText(text, (this.width + rectWidth) / 2 - 15 - mc.fontRendererObj.getStringWidth(">") - 5 - mc.fontRendererObj.getStringWidth(text), (this.height - rectHeight) / 2 + 30 + 15 + 15 * (line - 1), -1, true, false);
	}
	
	protected GuiText createGuiTextLeftArrow(int id, String text, int line) {
    	return new GuiText(id, (this.width + rectWidth) / 2 - 15 - mc.fontRendererObj.getStringWidth(">") - 5 - mc.fontRendererObj.getStringWidth(text) - 5 - mc.fontRendererObj.getStringWidth("<"), (this.height - rectHeight) / 2 + 30 + 15 + 15 * (line - 1), "<");
	}
	
	protected GuiText createGuiTextRightArrow(int id, int line) {
    	return new GuiText(id, (this.width + rectWidth) / 2 - 15 - mc.fontRendererObj.getStringWidth(">"), (this.height - rectHeight) / 2 + 30 + 15 + 15 * (line - 1), ">");
	}
}
