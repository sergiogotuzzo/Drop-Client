package drop.gui;

import java.awt.Color;

import drop.Drop;
import drop.mod.Mod;
import drop.mod.option.ModOption;
import drop.mod.option.type.FloatOption;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public abstract class GuiDropClientScreen extends GuiScreen {
	@Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawString(this.fontRendererObj, Drop.getNameVersion(), 2, this.height - 10, 0x808080);
        this.drawString(this.fontRendererObj, Drop.MENU_SUBTITLE, this.width - this.fontRendererObj.getStringWidth(Drop.MENU_SUBTITLE) - 2, this.height - 10, 0x808080);
        
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
	            
	            drawText(String.valueOf(textChar), textCharX, y, c, textShadow, false);
	            
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
}
