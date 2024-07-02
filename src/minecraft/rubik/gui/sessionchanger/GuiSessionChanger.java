package rubik.gui.sessionchanger;

import java.awt.Color;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import rubik.gui.GuiRubikClientScreen;

public class GuiSessionChanger extends GuiRubikClientScreen {
	private final GuiScreen previousGuiScreen;

	public GuiSessionChanger(GuiScreen previousGuiScreen) {
		this.previousGuiScreen = previousGuiScreen;
	}

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	this.drawDefaultBackground();
    	
        this.drawCenteredString(this.fontRendererObj, "Currently logged in as " + this.mc.session.getUsername(), this.width / 2, 40, Color.WHITE.getRGB());
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
    	switch (button.id) {
    		case 0:
    			this.mc.displayGuiScreen(previousGuiScreen);
    			break;
    		case 1:
    			this.mc.displayGuiScreen(new GuiLoginOffline(this));
    			break;
    		case 2:
    			this.mc.displayGuiScreen(new GuiLoginMicrosoft(this));
    			break;
    		case 3:
    			this.mc.displayGuiScreen(new GuiLoginMojang(this));
    			break;
    	}
    }

    @Override
    public void initGui() {
    	this.buttonList.clear();
        
        int i = -16;
        int j = 75;
        
        this.buttonList.add(new GuiButton(1, this.width / 2 - j, this.height / 4 + 24 + i, 150, 20, "Use Offline"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - j, this.height / 4 + 48 + i, 150, 20, "Use Microsoft"));
        this.buttonList.add(new GuiButton(3, this.width / 2 - j, this.height / 4 + 72 + i, 150, 20, "Use Mojang"));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.cancel", new Object[0])));
    }
}
