package drop.gui.mod;

import java.awt.Color;
import java.io.IOException;

import drop.Client;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import drop.events.EventTarget;
import drop.gui.GuiButtonToggled;
import drop.gui.GuiDropClientScreen;
import drop.gui.GuiRect;
import drop.gui.GuiText;
import drop.mods.ModInstances;
import drop.mods.ModDraggableText.Brackets;
import drop.mods.impl.PotsCounter;

public class GuiPotsCounter extends GuiDropClientScreen {
	private final GuiScreen previousGuiScreen;
	private final PotsCounter mod = ModInstances.getPotsCounterMod();
	
	public GuiPotsCounter(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	this.drawDefaultBackground();
    	
    	drawRect((this.width - 300) / 2, (this.height - 200) / 2, (this.width - 300) / 2 + 300, (this.height - 200) / 2 + 200, new Color(0, 0, 0, 127).getRGB());
        
        this.drawScaledText("Pots Counter", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 15, 2.0D, 0xFFFFFFFF, false, false);
        this.drawText("Show Background", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15, -1, false, false);
        this.drawText("Text Shadow", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 1 + 15, -1, false, false);
        this.drawText("Text Color", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 2 + 15, -1, false, false);
        this.drawText("Brackets", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 30 + 15 * 3 + 15, -1, false, false);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
            	this.mc.displayGuiScreen(this.previousGuiScreen);
            	break;
            case 1:
            	mod.setShowBackground(!mod.isShowBackgroundEnabled());
            	this.initGui();
            	break;
            case 2:
            	mod.setTextShadow(!mod.isTextShadowEnabled());
            	this.initGui();
            	break;
            case 3:
            	mc.displayGuiScreen(new GuiModDraggableTextColor(this, mod, "Pots Counter"));
            	break;
            case 4:
            	mod.setBrackets(Brackets.fromId(mod.getBrackets() == Brackets.CURLY ? Brackets.NONE.getId() : mod.getBrackets().getId() + 1));
            	this.initGui();
            	break;
        }
    }
	
	@Override
    public void initGui() {
        this.buttonList.clear();
        
    	this.buttonList.add(new GuiButtonToggled(1, mod.isShowBackgroundEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 0 + 15 - 2));
    	this.buttonList.add(new GuiButtonToggled(2, mod.isTextShadowEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 1 + 15 - 2));
        this.buttonList.add(new GuiRect(3, (this.width + 300) / 2 - 15 - 13, (this.height - 200) / 2 + 30 + 15 * 2 + 15 - 2 * 2, mod.getTextColor().getRGB()));
        this.buttonList.add(new GuiText(4, (this.width + 300) / 2 - 15 - mc.fontRendererObj.getStringWidth(mod.getBrackets().getName()), (this.height - 200) / 2 + 30 + 15 * 3 + 15, mod.getBrackets().getName()));
        this.buttonList.add(new GuiButton(0, (this.width + 300) / 2 - 50 - 15, (this.height - 200) / 2 + 15, 50, 20, I18n.format("gui.done", new Object[0])));
    }
}
