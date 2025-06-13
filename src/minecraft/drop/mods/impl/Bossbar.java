package drop.mods.impl;

import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiBossbar;
import drop.mods.ModDraggableText;
import drop.mods.ModInstances;
import drop.mods.hud.ScreenPosition;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.boss.BossStatus;

public class Bossbar extends ModDraggableText {
	private boolean showName = true;
	private boolean showHealth = true;
	
	public Bossbar() {
		super(true, 0.5, 0.5);
		
		setShowName(getBooleanFromFile("showName", showName));
		setShowHealth(getBooleanFromFile("showHealth", showHealth));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiBossbar(previousGuiScreen);
	}

	@Override
	public int getWidth() {
		int width = 0;
		
		if (showName) {
			width = font.getStringWidth(BossStatus.bossName != null && BossStatus.statusBarTime > 0 ? BossStatus.bossName : "Ender Dragon");
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
			
			this.renderBossbar(pos, BossStatus.healthScale, BossStatus.bossName);
		}
	}

	@Override
	public void renderDummy(ScreenPosition pos) {
		float bossHealthScale = 1.0F;
		String bossName = "Ender Dragon";
		
		if (BossStatus.bossName != null && BossStatus.statusBarTime > 0) {
			bossHealthScale = BossStatus.healthScale;
			bossName = BossStatus.bossName;
		}
		
		this.renderBossbar(pos, bossHealthScale, bossName);
	}
	
	private void renderBossbar(ScreenPosition pos, float bossHealthScale, String bossName) {
		if (showHealth) {
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			
			mc.getTextureManager().bindTexture(Gui.icons);
			
			int healthY = pos.getAbsoluteY() + (showName ? font.FONT_HEIGHT + 1 : 0);

            drawTexturedModalRect(pos.getAbsoluteX(), healthY, 0, 74, 182, 5);
            
            int healthWidth = (int) (bossHealthScale * (182.0F + 1.0F));
            
            if (healthWidth > 0) {
            	drawTexturedModalRect(pos.getAbsoluteX(), healthY, 0, 79, healthWidth, 5);
            }
		}
		
		if (showName) {
			drawText(bossName.replace("§r", ""), pos.getAbsoluteX() + getWidth() / 2 - font.getStringWidth(bossName) / 2, pos.getAbsoluteY(), textColor, textShadow);
		}
	}
	
	public void setShowName(boolean toggled) {
		showName = toggled;
		
		setToFile("showName", toggled);
	}
	
	public boolean isShowNameToggled() {
		return showName;
	}
	
	public void setShowHealth(boolean toggled) {
		showHealth = toggled;
		
		setToFile("showHealth", toggled);
	}
	
	public boolean isShowHealthToggled() {
		return showHealth;
	}
}
