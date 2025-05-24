package drop.gui.mod;

import java.awt.Color;
import java.io.IOException;

import drop.ColorManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import drop.gui.GuiButtonToggled;
import drop.mods.Mod;
import drop.mods.ModInstances;
import drop.mods.impl.OldVisuals;

public class GuiOldVisualsHitArmorColor extends GuiModColor {
	private static final OldVisuals mod = ModInstances.getOldVisualsMod();
	
	public GuiOldVisualsHitArmorColor(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod.getHitArmorColor(), mod, "hitArmorColor", "Old Visuals", "Hit Armor Color", true);
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
        	mod.setHitArmorChroma(!mod.isHitArmorChromaEnabled());
        	this.initGui();
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
		
    	this.buttonList.add(new GuiButtonToggled(5, mod.isHitArmorChromaEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 5 + 15 - 2));
    }
}
