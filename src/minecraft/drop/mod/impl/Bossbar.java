package drop.mod.impl;

import java.awt.Color;

import drop.gui.mod.GuiSettings;
import drop.gui.mod.hud.ScreenPosition;
import drop.mod.ModDraggable;
import drop.mod.option.ModColor;
import drop.mod.option.ParentOption;
import drop.mod.option.type.BooleanOption;
import drop.mod.option.type.ColorOption;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.boss.BossStatus;

public class Bossbar extends ModDraggable {
	public Bossbar() {
		super(false, 0.5, 0.5);
		
		saveOptions(
				new BooleanOption(this, "hide", false, new GuiSettings(5, "Hide")),
				new BooleanOption(this, "showName", true, new ParentOption("hide", true), new GuiSettings(1, "Show Name")),
				new ColorOption(this, "textColor", ModColor.fromColor(Color.WHITE, false), new ParentOption("showName"), new GuiSettings(2, "Text Color", true, false)),
				new BooleanOption(this, "textShadow", true, new ParentOption("showName"), new GuiSettings(3, "Text Shadow")),
				new BooleanOption(this, "showHealth", true, new ParentOption("hide", true), new GuiSettings(4, "Show Health"))
				);
	}

	@Override
	public int getWidth() {
		int width = 0;
		
		if (!getBooleanOption("hide").isToggled()) {
			if (getBooleanOption("showName").isToggled()) {
				width = font.getStringWidth(BossStatus.bossName != null && BossStatus.statusBarTime > 0 ? BossStatus.bossName : "Ender Dragon");
			}
			
			if (getBooleanOption("showHealth").isToggled()) {
				width = 182;
			}
		}
		
		return width;
	}

	@Override
	public int getHeight() {
		int height = 0;
		
		if (!getBooleanOption("hide").isToggled()) {
			if (getBooleanOption("showName").isToggled()) {
				height += 5;
			}
			
			if (getBooleanOption("showHealth").isToggled()) {
				height += 1 + font.FONT_HEIGHT;
			}
		}
		
		return height;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (!getBooleanOption("hide").isToggled() && BossStatus.bossName != null && BossStatus.statusBarTime > 0) {
			--BossStatus.statusBarTime;
			
			this.renderBossbar(pos, BossStatus.healthScale, BossStatus.bossName);
		}
	}

	@Override
	public void renderDummy(ScreenPosition pos) {
		if (!getBooleanOption("hide").isToggled()) {
			float bossHealthScale = 1.0F;
			String bossName = "Ender Dragon";
			
			if (BossStatus.bossName != null && BossStatus.statusBarTime > 0) {
				bossHealthScale = BossStatus.healthScale;
				bossName = BossStatus.bossName;
			}
			
			this.renderBossbar(pos, bossHealthScale, bossName);
		}
	}
	
	private void renderBossbar(ScreenPosition pos, float bossHealthScale, String bossName) {
		if (getBooleanOption("showHealth").isToggled()) {
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			
			mc.getTextureManager().bindTexture(Gui.icons);
			
			int healthY = pos.getAbsoluteY() + (getBooleanOption("showName").isToggled() ? font.FONT_HEIGHT + 1 : 0);

            drawTexturedModalRect(pos.getAbsoluteX(), healthY, 0, 74, 182, 5);
            
            int healthWidth = (int) (bossHealthScale * (182.0F + 1.0F));
            
            if (healthWidth > 0) {
            	drawTexturedModalRect(pos.getAbsoluteX(), healthY, 0, 79, healthWidth, 5);
            }
		}
		
		if (getBooleanOption("showName").isToggled()) {
			drawText(bossName.replace("�r", ""), pos.getAbsoluteX() + getWidth() / 2 - font.getStringWidth(bossName) / 2, pos.getAbsoluteY(), getColorOption("textColor").getColor(), getBooleanOption("textShadow").isToggled());
		}
	}
}
