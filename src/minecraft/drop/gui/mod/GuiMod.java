package drop.gui.mod;

import java.awt.Color;
import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import drop.gui.GuiButtonToggled;
import drop.gui.GuiDropClientScreen;
import drop.gui.GuiRect;
import drop.gui.GuiSlider;
import drop.gui.GuiText;
import drop.mods.Mod;

public class GuiMod extends GuiDropClientScreen {
	private final GuiScreen previousGuiScreen;
	protected final Mod mod;
	
	public GuiMod(GuiScreen previousGuiScreen, Mod mod) {
		this.previousGuiScreen = previousGuiScreen;
		this.mod = mod;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	this.drawDefaultBackground();
    	
    	this.drawRect((this.width - 300) / 2, (this.height - 200) / 2, (this.width - 300) / 2 + 300, (this.height - 200) / 2 + 200, new Color(0, 0, 0, 127).getRGB());
        
        this.drawScaledText(mod.getName(), (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 15, 2.0D, 0xFFFFFFFF, false, false);

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

        this.buttonList.add(new GuiButton(0, (this.width + 300) / 2 - 50 - 15, (this.height - 200) / 2 + 15 - 3, 50, 20, I18n.format("gui.done", new Object[0])));
    }
	
	protected void writeOptionText(String text, int line) {
        this.drawText(text, (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 + 15 * (line - 1), -1, false, false);
	}
	
	protected void writeOptionValue(String text, int line) {
        this.drawText(text, (this.width + 300) / 2 - mc.fontRendererObj.getStringWidth(text) - 15, (this.height - 200) / 2 + 30 + 15 + 15 * (line - 1), -1, false, false);
	}
	
	protected GuiButtonToggled createGuiButtonToggled(int id, boolean toggled, int line) {
    	return new GuiButtonToggled(id, toggled, (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 - 2 + 15 * (line - 1));
	}
	
	protected GuiRect createGuiRect(int id, int color, int line) {
    	return new GuiRect(id, (this.width + 300) / 2 - 15 - 13, (this.height - 200) / 2 + 30 + 15 - 2 * 2 + 15 * (line - 1), color);
	}
	
	protected GuiSlider createGuiSlider(int id, float min, float max, float defaultValue, int movedX, int line) {
    	return new GuiSlider(id, (this.width - 300) / 2 + 100 + movedX, (this.height - 200) / 2 + 30 + 15 + 1 + 15 * (line - 1), 100, 5, min, max, defaultValue);
	}
	
	protected GuiSlider createGuiSlider(int id, float min, float max, float defaultValue, int line) {
    	return this.createGuiSlider(id, min, max, defaultValue, 0, line);
	}
	
	protected GuiText createGuiText(int id, int x, String text, int line) {
    	return new GuiText(id, x, (this.height - 200) / 2 + 30 + 15 + 15 * (line - 1), text);
	}
	
	protected GuiText createGuiText(int id, String text, int line) {
    	return new GuiText(id, (this.width + 300) / 2 - 15 - mc.fontRendererObj.getStringWidth(text), (this.height - 200) / 2 + 30 + 15 + 15 * (line - 1), text);
	}
}
