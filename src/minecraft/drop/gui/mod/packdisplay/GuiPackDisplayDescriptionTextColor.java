package drop.gui.mod.packdisplay;

import java.awt.Color;
import java.io.IOException;

import drop.ColorManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.GuiButtonToggled;
import drop.gui.mod.GuiModColor;
import drop.mods.ModInstances;
import drop.mods.impl.PackDisplay;

public class GuiPackDisplayDescriptionTextColor extends GuiModColor {
	private static final PackDisplay mod = ModInstances.getPackDisplayMod();
	
	public GuiPackDisplayDescriptionTextColor(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod.getDescriptionTextColor(), mod, "descriptionTextColor", "Pack Display", "Description Text Color");
	}
	
	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        this.drawText("Chroma", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 4 + 15, -1, false, false);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        if (button.id == 5) {
        	mod.setDescriptionTextChroma(!mod.isDescriptionTextChromaEnabled());
        	this.initGui();
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
		
    	this.buttonList.add(new GuiButtonToggled(5, mod.isDescriptionTextChromaEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 4 + 15 - 2));
    }
}
