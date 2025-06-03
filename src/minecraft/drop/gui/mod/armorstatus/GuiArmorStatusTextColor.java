package drop.gui.mod.armorstatus;

import java.io.IOException;

import drop.gui.GuiButtonToggled;
import drop.gui.mod.GuiModColor;
import drop.mods.ModDraggableText;
import drop.mods.ModInstances;
import drop.mods.impl.ArmorStatus;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiArmorStatusTextColor extends GuiModColor {
	private static final ArmorStatus mod = ModInstances.getArmorStatusMod();
	
	public GuiArmorStatusTextColor(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod.getTextColor(), mod, "Armor Status");
	}
	
	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        this.drawText("Dynamic Colors", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 5 + 15, -1, false, false);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        if (button.id == 6) {
        	mod.setDynamicColors(!mod.isDynamicColorsEnabled());
        	this.initGui();
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
		
    	this.buttonList.add(new GuiButtonToggled(6, mod.isDynamicColorsEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 5 + 15 - 2));
    }
}
