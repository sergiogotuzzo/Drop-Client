package drop.mods.impl;

import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiBossbar;
import drop.mods.ModDraggableText;
import drop.mods.ModInstances;
import drop.mods.hud.ScreenPosition;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;

public class Bossbar extends ModDraggableText {
	private boolean showName = true;
	private boolean showHealth = true;
	
	public Bossbar() {
		super(true, 0.5, 0.5);
		
		setShowName((boolean) getFromFile("showName", showName));
		setShowHealth((boolean) getFromFile("showHealth", showHealth));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiBossbar(previousGuiScreen);
	}

	@Override
	public int getWidth() {
		int width = 0;
		
		if (showName) {
			width = font.getStringWidth(BossStatus.bossName);
		}
		
		if (showHealth) {
			width = 182;
		}
		
		return width;
	}

	@Override
	public int getHeight() {
		int height = 0;
		
		if (showHealth) {
			height += 5;
		}
		
		if (showName) {
			height += 1 + font.FONT_HEIGHT;
		}
		
		return height;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (BossStatus.bossName != null && BossStatus.statusBarTime > 0) {
			--BossStatus.statusBarTime;
			
			this.renderBossbar(pos);
		}
	}

	@Override
	public void renderDummy(ScreenPosition pos) {
		if (BossStatus.bossName != null && BossStatus.statusBarTime > 0) {
			this.renderBossbar(pos);
		} else {
			if (showHealth) {
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				
				mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/icons.png"));
				
				int healthY = pos.getAbsoluteY() + (showName ? font.FONT_HEIGHT + 1 : 0);

	            drawTexturedModalRect(pos.getAbsoluteX(), healthY, 0, 74, 182, 5);
	            
	            drawTexturedModalRect(pos.getAbsoluteX(), healthY, 0, 79, 183, 5);
			}
			
			if (showName) {
				drawText("Ender Dragon", pos.getAbsoluteX() + getWidth() / 2 - font.getStringWidth("Ender Dragon") / 2, pos.getAbsoluteY(), textColor, textShadow);
			}
		}
	}
	
	private void renderBossbar(ScreenPosition pos) {
		if (showHealth) {
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			
			mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/icons.png"));
			
			int healthY = pos.getAbsoluteY() + (showName ? font.FONT_HEIGHT + 1 : 0);

            drawTexturedModalRect(pos.getAbsoluteX(), healthY, 0, 74, 182, 5);
            
            int health = (int) (BossStatus.healthScale * (182.0F + 1.0F));
            
            if (health > 0) {
            	drawTexturedModalRect(pos.getAbsoluteX(), healthY, 0, 79, health, 5);
            }
		}
		
		if (showName) {
			drawText(BossStatus.bossName.replace("§r", ""), pos.getAbsoluteX() + getWidth() / 2 - font.getStringWidth(BossStatus.bossName) / 2, pos.getAbsoluteY(), textColor, textShadow);
		}
	}
	
	public void setShowName(boolean toggled) {
		this.showName = toggled;
		
		setToFile("showName", toggled);
	}
	
	public boolean isShowNameToggled() {
		return showName;
	}
	
	public void setShowHealth(boolean toggled) {
		this.showHealth = toggled;
		
		setToFile("showHealth", toggled);
	}
	
	public boolean isShowHealthToggled() {
		return showHealth;
	}
}
