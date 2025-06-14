package drop.gui.mod;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.mods.ModInstances;
import drop.mods.impl.Scoreboard;

public class GuiScoreboard extends GuiMod {
	private static final Scoreboard mod = ModInstances.getScoreboardMod();
	
	public GuiScoreboard(GuiScreen previousGuiScreen) {
		super(previousGuiScreen, mod);
	}

	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
        
        this.writeOptionText("Hide Numbers", 1);
        this.writeOptionText("Text Shadow", 2);
        this.writeOptionText("Background Color", 3);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
    	super.actionPerformed(button);
    	
        switch (button.id) {
            case 1:
            	mod.setHideNumbers(!mod.isHideNumbersToggled());
            	this.initGui();
            	break;
            case 2:
            	mod.setTextShadow(!mod.isTextShadowToggled());
            	this.initGui();
            	break;
            case 3:
            	mc.displayGuiScreen(new GuiModColor(this, mod, mod.getBackgroundColor(), "backgroundColor", "Background Color", true));
            	break;
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
        
    	this.buttonList.add(this.createGuiCheckBox(1, mod.isHideNumbersToggled(), 1));
    	this.buttonList.add(this.createGuiCheckBox(2, mod.isTextShadowToggled(), 2));
		this.buttonList.add(this.createGuiRect(3, mod.getBackgroundColor().getRGB(), 3));
    }
}
