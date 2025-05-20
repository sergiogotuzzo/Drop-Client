package rubik.gui;

import java.awt.Color;
import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.ColorManager;
import rubik.mods.Mod;
import rubik.mods.ModInstances;
import rubik.mods.impl.BlockOverlay;

public class GuiBlockOverlayOverlayColor extends GuiModColor {
	private static final BlockOverlay mod = ModInstances.getBlockOverlayMod();
	
	public GuiBlockOverlayOverlayColor(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod.getOverlayColor(), mod, "overlayColor", "Block Overlay", "Overlay Color", true);
	}
	
	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        this.drawText("Chroma", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 5 + 15, -1, false, false);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        if (button.id == 5) {
        	mod.setOverlayChroma(!mod.isOverlayChromaEnabled());
        	this.initGui();
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
		
    	this.buttonList.add(new GuiButtonToggled(5, mod.isOverlayChromaEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 5 + 15 - 2));
    }
}
