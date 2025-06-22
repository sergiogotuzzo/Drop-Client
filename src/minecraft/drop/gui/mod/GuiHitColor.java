package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.mods.ModInstances;
import drop.mods.impl.HitColor;

public class GuiHitColor extends GuiMod {
	private static final HitColor mod = ModInstances.getHitColorMod();
	
	public GuiHitColor(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	super.drawScreen(mouseX, mouseY, partialTicks);

        this.writeOptionText("Hit Color", 1);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
            case 1:
            	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getHitColor(), "hitColor", "hitColor", "Hit Color", true));
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();

        this.buttonList.add(this.createGuiRect(1, mod.getHitColor().getRGB(), 1));
    }
}
