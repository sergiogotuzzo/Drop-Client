package drop.gui.mod;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import drop.gui.GuiButtonToggled;
import drop.gui.GuiDropClientScreen;
import drop.gui.GuiText;
import drop.mods.Mod;
import drop.mods.ModInstances;

public class GuiModList extends GuiModMenu {    
    private GuiTextField textFieldSearchMod;
    private String textFieldText = "";
    
    private int scrollOffset = 0;
    private int maxScroll = 0;

    public GuiModList(GuiScreen previousGuiScreen) {
        super(previousGuiScreen, "Mods");
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        
        int dWheel = Mouse.getDWheel();
        
        if (dWheel != 0) {
            scrollOffset -= Integer.signum(dWheel) * 15;
            scrollOffset = Math.max(0, Math.min(scrollOffset, maxScroll));
            
            this.initGui();
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	super.drawScreen(mouseX, mouseY, partialTicks);

    	this.textFieldSearchMod.drawTextBox();
    }

    @Override
    protected void keyTyped(char typedChar, int key) throws IOException {
    	this.textFieldSearchMod.textboxKeyTyped(typedChar, key);
        
        this.textFieldText = textFieldSearchMod.getText();
        
        this.initGui();
        
        super.keyTyped(typedChar, key);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        
        this.textFieldSearchMod.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

        int id = button.id;
        List<Mod> mods = new ArrayList<>(ModInstances.getAllMods());
        
        if (id >= 1 && id <= 100) {
        	mods.get(id - 1).toggle();
            
            this.initGui();
        }
        
        if (id >= 101) {
            GuiDropClientScreen modGui = mods.get(id - 101).getGui(this);
            
            if (modGui == null) {
            	return;
            }
            
            this.mc.displayGuiScreen(modGui);
        }
    }

    @Override
    public void initGui() {
    	super.initGui();

        this.textFieldSearchMod = new GuiTextField(-1, this.fontRendererObj, (this.width - 120) / 2, (this.height - rectHeight) / 2 + 15 - 3, 120, 20);
        this.textFieldSearchMod.setText(textFieldText);
        this.textFieldSearchMod.setFocused(true);

        int totalHeight = 0;

        List<Mod> mods = ModInstances.getAllMods();
        
        for (int i = 1; i - 1 < mods.size(); i++) {
            Mod mod = mods.get(i - 1);
            
            if (!textFieldSearchMod.getText().isEmpty() && !mod.getName().toLowerCase().startsWith(textFieldSearchMod.getText().toLowerCase())) {
            	if (scrollOffset > 0) {
                	scrollOffset = 0;
            	}
            	
            	continue;
            }

            int buttonY = (this.height - rectHeight) / 2 + 30 + totalHeight - scrollOffset;

            if (buttonY >= (this.height - rectHeight) / 2 + 30 && buttonY + 15 <= (this.height - rectHeight) / 2 + rectHeight - 20) {
                this.buttonList.add(new GuiText(i + 100, (this.width - rectWidth) / 2 + 15, buttonY + 15, mod.getName(), mod.getGui(this) != null));
                this.buttonList.add(new GuiButtonToggled(i, mod.isEnabled(), (this.width - rectWidth) / 2 + rectWidth - 18 - 15, buttonY - 2 + 15));
            }

            totalHeight += 15;
        }

        this.maxScroll = Math.max(0, totalHeight - (rectHeight - 50));
    }

    @Override
    public void updateScreen() {
        this.textFieldSearchMod.updateCursorCounter();
    }
}
