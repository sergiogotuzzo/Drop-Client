package rubik.gui;

import java.awt.Color;
import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import rubik.ColorManager;
import rubik.mods.Mod;
import rubik.mods.ModInstances;
import rubik.mods.impl.PotionEffects;

public class GuiPotionEffectsDurationTextColor extends GuiModColor {
	private static final PotionEffects mod = ModInstances.getPotionEffectsMod();
	
	public GuiPotionEffectsDurationTextColor(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod.getDurationTextColor(), mod, "durationTextColor", "Potion Effects", "Duration Text Color");
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
        	mod.setDurationTextChroma(!mod.isDurationTextChromaEnabled());
        	this.initGui();
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();

    	this.buttonList.add(new GuiButtonToggled(5, mod.isDurationTextChromaEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 4 + 15 - 2));
    }
}
