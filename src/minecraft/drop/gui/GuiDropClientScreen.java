package drop.gui;

import java.awt.Color;

import drop.Drop;
import drop.mods.Mod;
import drop.mods.option.ModOption;
import drop.mods.option.type.FloatOption;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public abstract class GuiDropClientScreen extends GuiScreen {
	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawString(this.fontRendererObj, Drop.nameVersion, 2, this.height - 10, 0x808080);
        this.drawString(this.fontRendererObj, "Not affiliated with Mojang AB nor Microsoft", this.width - this.fontRendererObj.getStringWidth("Not affiliated with Mojang AB nor Microsoft") - 2, this.height - 10, 0x808080);
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
	
	@Override
	public void initGui() {
    	mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/menu_blur.json"));
    }
	
	@Override
	public void onGuiClosed() {
    	mc.entityRenderer.stopUseShader();
	}
    
    public void drawHollowRect(int x, int y, int width, int height, int color) {
		GuiDropClient.drawHollowRect(x, y, width, height, color);
	}
    
    public void drawText(String text, int x, int y, int color, boolean textShadow, boolean chroma) {
		if (chroma) {
			int textCharX = x;
			
	        for (char textChar : text.toCharArray()) {
	            long t = System.currentTimeMillis() - (textCharX * 10 - y * 10);
	            int c = Color.HSBtoRGB(t % (int) 2000.0F / 2000.0F, 0.8F, 0.8F);
	            
	            if (text.startsWith("�m")) {
	            	drawText(text, x, y, c, textShadow, false);
	            } else {
		            drawText(String.valueOf(textChar), textCharX, y, c, textShadow, false);
	            }
	            
	            textCharX += mc.fontRendererObj.getCharWidth(textChar);
	        }
		} else {
			mc.fontRendererObj.drawString(text, x, y, color, textShadow);
		}
	}
	
	public void drawScaledText(String text, int x, int y, double scale, int color, boolean textShadow, boolean chroma) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, 0);
		GlStateManager.scale(scale, scale, 1);
		GlStateManager.translate(-x, -y, 0);
		
		drawText(text, x, y, color, textShadow, chroma);
		
		GlStateManager.popMatrix();
	}
	
	protected GuiCheckBox createGuiCheckBox(int id, boolean toggled, int line) {
    	return new GuiCheckBox(id, (this.width + 350) / 2 - 15 - 13, (this.height - 250) / 2 + 30 + 15 - 2 * 2 + 15 * (line - 1), toggled);
	}
	
	protected GuiRect createGuiRect(int id, int color, int line) {
    	return new GuiRect(id, (this.width + 350) / 2 - 15 - 13, (this.height - 250) / 2 + 30 + 15 - 2 * 2 + 15 * (line - 1), color);
	}
	
	protected GuiSlider createGuiSlider(int id, float max, float defaultValue, int line) {
    	return new GuiSlider(id, (this.width - 350) / 2 + 15, (this.height - 250) / 2 + 30 + 15 + 1 + 15 * (line - 1), 350 - 15 * 2, 5, 0.0F, max, defaultValue);
	}
	
	protected GuiSliderOption createGuiSliderOption(int id, float max, float defaultValue, int line, ModOption option) {
    	return new GuiSliderOption(id, (this.width - 350) / 2 + 15, (this.height - 250) / 2 + 30 + 15 + 1 + 15 * (line - 1), 350 - 15 * 2, 5, 0.0F, max, defaultValue, option);
	}
	
	protected GuiText createGuiText(int id, String text, boolean right, int line) {
    	return new GuiText(id, right ? (this.width + 350) / 2 - 15 - mc.fontRendererObj.getStringWidth(text) : (this.width - 350) / 2 + 15, (this.height - 250) / 2 + 30 + 15 + 15 * (line - 1), text);
	}
	
	protected void writeOptionText(String text, int line) {
        this.drawText(text, (this.width - 350) / 2 + 15, (this.height - 250) / 2 + 30 + 15 + 15 * (line - 1), -1, true, false);
	}
	
	protected void writeOptionText(String text, int line, boolean chroma) {
        this.drawText(text, (this.width - 350) / 2 + 15, (this.height - 250) / 2 + 30 + 15 + 15 * (line - 1), -1, true, chroma);
	}
	
	protected void writeOptionText(String text, int line, int color) {
        this.drawText(text, (this.width - 350) / 2 + 15, (this.height - 250) / 2 + 30 + 15 + 15 * (line - 1), color, true, false);
	}
	
	protected void writeOptionValue(String text, int line) {
        this.drawText(text, (this.width + 350) / 2 - mc.fontRendererObj.getStringWidth(text) - 15, (this.height - 250) / 2 + 30 + 15 + 15 * (line - 1), -1, true, false);
	}
	
	protected void writeSelectedOptionValue(String text, int line) {
        this.drawText(text, (this.width + 350) / 2 - 15 - mc.fontRendererObj.getStringWidth(">") - 5 - mc.fontRendererObj.getStringWidth(text), (this.height - 250) / 2 + 30 + 15 + 15 * (line - 1), -1, true, false);
	}
	
	protected GuiText createGuiTextLeftArrow(int id, String text, int line) {
    	return new GuiText(id, (this.width + 350) / 2 - 15 - mc.fontRendererObj.getStringWidth(">") - 5 - mc.fontRendererObj.getStringWidth(text) - 5 - mc.fontRendererObj.getStringWidth("<"), (this.height - 250) / 2 + 30 + 15 + 15 * (line - 1), "<");
	}
	
	protected GuiText createGuiTextRightArrow(int id, int line) {
    	return new GuiText(id, (this.width + 350) / 2 - 15 - mc.fontRendererObj.getStringWidth(">"), (this.height - 250) / 2 + 30 + 15 + 15 * (line - 1), ">");
	}
}
