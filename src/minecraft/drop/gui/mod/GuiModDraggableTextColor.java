package drop.gui.mod;

import java.io.IOException;

import drop.ColorManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import drop.gui.GuiButtonToggled;
import drop.mods.ModDraggableText;

public class GuiModDraggableTextColor extends GuiModColor {
	private final ModDraggableText mod;
	
	public GuiModDraggableTextColor(GuiScreen previousGuiScreen, ModDraggableText mod, String title) {
		super(previousGuiScreen, mod.getTextColor(), mod, title);
		
		this.mod = mod;
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
        	mod.setTextChroma(!mod.isTextChromaEnabled());
        	this.initGui();
        }
    }
	
	@Override
    public void initGui() {
		super.initGui();
		
    	this.buttonList.add(new GuiButtonToggled(5, mod.isTextChromaEnabled(), (this.width + 300) / 2 - 20 - 15, (this.height - 200) / 2 + 30 + 15 * 4 + 15 - 2));
    }
}
