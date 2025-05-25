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
import drop.mods.impl.Bossbar;
import drop.mods.impl.Fullbright;

public class GuiMods extends GuiDropClientScreen {
    private final GuiScreen previousScreen;
    private int scrollOffset = 0;
    private int maxScroll = 0;

    public GuiMods(GuiScreen previousScreen) {
        this.previousScreen = previousScreen;
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
            mc.displayGuiScreen(previousScreen);
            
            return;
        }

        int id = button.id;
        List<Mod> mods = new ArrayList<>(ModInstances.getAllMods());
        
        if (id >= 1 && id <= 100) {
            Mod mod = mods.get(id - 1);	
            
            mod.setEnabled(!mod.isEnabled());
            
            this.initGui();
        }
        
        switch (button.id) {
	    	case 101:
	    		mc.displayGuiScreen(new GuiArmorStatus(this));
	    		break;
	    	case 102:
	    		mc.displayGuiScreen(new GuiBlockOverlay(this));
	    		break;
	    	case 103:
	    		break;
	    	case 104:
	    		mc.displayGuiScreen(new GuiChat(this));
	    		break;
	    	case 105:
	    		mc.displayGuiScreen(new GuiClock(this));
	    		break;
	    	case 106:
	    		mc.displayGuiScreen(new GuiCoordinatesDisplay(this));
	    		break;
	    	case 107:
	    		mc.displayGuiScreen(new GuiCPSDisplay(this));
	    		break;
	    	case 108:
	    		mc.displayGuiScreen(new GuiFPSDisplay(this));
	    		break;
	    	case 109:
	    		break;
	    	case 110:
	    		mc.displayGuiScreen(new GuiFreelook(this));
	    		break;
	    	case 111:
	    		mc.displayGuiScreen(new GuiKeystrokes(this));
	    		break;
	    	case 112:
	    		mc.displayGuiScreen(new GuiMemoryUsage(this));
	    		break;
	    	case 113:
	    		mc.displayGuiScreen(new GuiNametags(this));
	    		break;
	    	case 114:
	    		mc.displayGuiScreen(new GuiOldVisuals(this));
	    		break;
	    	case 115:
	    		mc.displayGuiScreen(new GuiPingDisplay(this));
	    		break;
	    	case 116:
	    		mc.displayGuiScreen(new GuiPotionEffects(this));
	    		break;
	    	case 117:
	    		mc.displayGuiScreen(new GuiPotsCounter(this));
	    		break;
	    	case 118:
	    		mc.displayGuiScreen(new GuiScoreboard(this));
	    		break;
	    	case 119:
	    		mc.displayGuiScreen(new GuiTabOverlay(this));
	    		break;
	    	case 120:
	    		mc.displayGuiScreen(new GuiTimeChanger(this));
	    		break;
	    	case 121:
	    		mc.displayGuiScreen(new GuiToggleSprintSneak(this));
	    		break;
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
                
                if (mod instanceof Bossbar || mod instanceof Fullbright) {
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
