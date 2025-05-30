package drop.gui.mod;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import drop.gui.GuiButtonToggled;
import drop.gui.GuiDropClientScreen;
import drop.gui.GuiText;
import drop.mods.Mod;
import drop.mods.ModInstances;

public class GuiMods extends GuiDropClientScreen {
    private final GuiScreen previousGuiScreen;
    
    private int scrollOffset = 0;
    private int maxScroll = 0;

    public GuiMods(GuiScreen previousGuiScreen) {
        this.previousGuiScreen = previousGuiScreen;
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        
        int dWheel = Mouse.getDWheel();
        
        if (dWheel != 0) {
            scrollOffset -= Integer.signum(dWheel) * 15;
            scrollOffset = Math.max(0, Math.min(scrollOffset, maxScroll));
            
            initGui();
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	this.drawDefaultBackground();
    	
        drawRect((this.width - 300) / 2, (this.height - 200) / 2, (this.width - 300) / 2 + 300, (this.height - 200) / 2 + 200, new Color(0, 0, 0, 127).getRGB());
        drawScaledText("Mods", (this.width - 300) / 2 + 15, (this.height - 200) / 2 + 15, 2.0D, 0xFFFFFFFF, false, false);

        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        
        scissorBox((this.width - 300) / 2, (this.height - 200) / 2 + 30 + 15 - 2, 300, 200 - 40 - 15 + 2);
        
        super.drawScreen(mouseX, mouseY, partialTicks);
        
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
        
        new GuiButton(0, (this.width - 300) / 2 + 300 - 50 - 15, (this.height - 200) / 2 + 15, 50, 20, I18n.format("gui.done", new Object[0])).drawButton(mc, mouseX, mouseY);
    }

    private void scissorBox(int x, int y, int width, int height) {
        int scale = mc.gameSettings.guiScale == 0 ? 1 : mc.gameSettings.guiScale;
        int factor = mc.displayHeight / this.height;
        
        GL11.glScissor(x * factor, (this.height - y - height) * factor, width * factor, height * factor);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) {
            mc.displayGuiScreen(previousGuiScreen);
            
            return;
        }

        int id = button.id;
        List<Mod> mods = new ArrayList<>(ModInstances.getAllMods());
        
        if (id >= 1 && id <= 100) {
            Mod mod = mods.get(id - 1);
            
            mod.setEnabled(!mod.isEnabled());
            
            this.initGui();
        }
        
        if (id >= 101) {
            Mod mod = mods.get(id - 101);
            
            if (mod.getGui(this) == null) {
            	return;
            }
            
        	mc.displayGuiScreen(mod.getGui(this));
        }
    }

    @Override
    public void initGui() {
        this.buttonList.clear();

        int i = 0;
        int baseY = (this.height - 200) / 2 + 30 - scrollOffset;

        for (Mod mod : ModInstances.getAllMods()) {
            int buttonY = baseY + 15 * i;

            if (buttonY >= (this.height - 200) / 2 + 30 && buttonY <= (this.height - 200) / 2 + 200 - 10) {
                String modName = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(mod.getClass().getSimpleName().replace("Mod", "").replaceAll("\\d+", "")), " ");
                
                boolean hovering = true;
                
                if (mod.getGui(this) == null) {
                	hovering = false;
                }
                
                this.buttonList.add(new GuiText(i + 101, (this.width - 300) / 2 + 15, buttonY + 15, modName, hovering));
                this.buttonList.add(new GuiButtonToggled(i + 1, mod.isEnabled(), (this.width - 300) / 2 + 300 - 20 - 15, buttonY - 2 + 15));
            }

            i++;
        }

        int totalHeight = 15 * ModInstances.getAllMods().size();
        maxScroll = Math.max(0, totalHeight - (200 - 50));

        this.buttonList.add(new GuiButton(0, (this.width - 300) / 2 + 300 - 50 - 15, (this.height - 200) / 2 + 15, 50, 20, I18n.format("gui.done", new Object[0])));
    }
}
