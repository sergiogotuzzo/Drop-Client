package rubik.gui.sessionchanger;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumChatFormatting;
import rubik.SessionChanger;
import rubik.gui.GuiRubikClientScreen;

import java.awt.Color;
import java.io.IOException;

public class GuiLoginOffline extends GuiRubikClientScreen {
	private final GuiScreen previousGuiScreen;
	
	private GuiButton buttonLogin;
    private GuiTextField textFieldNickname;
    
    public GuiLoginOffline(GuiScreen previousGuiScreen) {
    	this.previousGuiScreen = previousGuiScreen;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	this.drawDefaultBackground();
    	
        this.drawCenteredString(this.fontRendererObj, "Login with an offline account", this.width / 2, 40, Color.WHITE.getRGB());
        
        super.drawScreen(mouseX, mouseY, partialTicks);
        
        this.textFieldNickname.drawTextBox();
    }

    @Override
    protected void keyTyped(char typedChar, int key) throws IOException {
        if (typedChar == '\t' && !this.textFieldNickname.isFocused()) {
            this.textFieldNickname.setFocused(true);
        }
        
        if (typedChar == '\r') {
            this.actionPerformed(this.buttonList.get(0));
        }
        
        this.textFieldNickname.textboxKeyTyped(typedChar, key);

        buttonLogin.enabled = !this.textFieldNickname.getText().isEmpty();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        
        this.textFieldNickname.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
    	switch (button.id) {
    		case 0:
    			this.mc.displayGuiScreen(previousGuiScreen);
    			break;
    		case 1:
    			SessionChanger.getInstance().setUserOffline(this.textFieldNickname.getText());
    			this.mc.displayGuiScreen(new GuiMainMenu());
    			break;
    	}
    }

    @Override
    public void initGui() {
    	this.buttonList.clear();
    	
    	int i = -16;
    	int j = -60;
    	
    	this.buttonList.add(buttonLogin = new GuiButton(1, this.width / 2 + j, this.height / 6 + 72 + i, 120, 20, "Login"));
    	this.buttonList.add(new GuiButton(0, this.width / 2 + j, this.height / 6 + 96 + i, 120, 20, I18n.format("gui.cancel", new Object[0])));
        
        textFieldNickname = new GuiTextField(2, this.fontRendererObj, this.width / 2 + j, this.height / 6 + 48 + i - 2, 120, 20);
        textFieldNickname.setText("Nickname");
        textFieldNickname.setMaxStringLength(16);
        textFieldNickname.setFocused(true);
    }

    @Override
    public void updateScreen() {
        this.textFieldNickname.updateCursorCounter();
    }

    @Override
    public void onGuiClosed() {
        mc.entityRenderer.loadEntityShader(null);
    }
}