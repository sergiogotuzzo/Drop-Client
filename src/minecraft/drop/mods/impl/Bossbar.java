package drop.mods.impl;

import java.awt.Color;

import drop.gui.GuiSettings;
import drop.mods.ModColor;
import drop.mods.ModDraggable;
import drop.mods.ModOptions;
import drop.gui.hud.ScreenPosition;
import drop.mods.option.ParentOption;
import drop.mods.option.type.BooleanOption;
import drop.mods.option.type.ColorOption;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.boss.BossStatus;

public class Bossbar extends ModDraggable {
	public Bossbar() {
		super(false, 0.5, 0.5);
		
		this.options = new ModOptions(
				new BooleanOption(this, "hide", false, new GuiSettings(5, "Hide")),
				new BooleanOption(this, "showName", true, new ParentOption("hide", true), new GuiSettings(1, "Show Name")),
				new ColorOption(this, "textColor", ModColor.fromColor(Color.WHITE, false), new ParentOption("showName"), new GuiSettings(2, "Text Color", true, false)),
				new BooleanOption(this, "textShadow", true, new ParentOption("showName"), new GuiSettings(3, "Text Shadow")),
				new BooleanOption(this, "showHealth", true, new ParentOption("hide", true), new GuiSettings(4, "Show Health"))
				);
		
		saveOptions();
	}

	@Override
	public int getWidth() {
		int width = 0;
		
		if (!options.getBooleanOption("hide").isToggled()) {
			if (options.getBooleanOption("showName").isToggled()) {
				width = font.getStringWidth(BossStatus.bossName != null && BossStatus.statusBarTime > 0 ? BossStatus.bossName : "Ender Dragon");
			}
			
			if (options.getBooleanOption("showHealth").isToggled()) {
				width = 182;
			}
		}
		
		return width;
	}

	@Override
	public int getHeight() {
		int height = 0;
		
		if (!options.getBooleanOption("hide").isToggled()) {
			if (options.getBooleanOption("showName").isToggled()) {
				height += 5;
			}
			
			if (options.getBooleanOption("showHealth").isToggled()) {
				height += 1 + font.FONT_HEIGHT;
			}
		}
		
		return height;
	}

	@Override
	public void render(ScreenPosition pos) {
		if (!options.getBooleanOption("hide").isToggled() && BossStatus.bossName != null && BossStatus.statusBarTime > 0) {
			--BossStatus.statusBarTime;
			
			this.renderBossbar(pos, BossStatus.healthScale, BossStatus.bossName);
		}
	}

	@Override
	public void renderDummy(ScreenPosition pos) {
		if (!options.getBooleanOption("hide").isToggled()) {
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
		if (options.getBooleanOption("showHealth").isToggled()) {
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			
			mc.getTextureManager().bindTexture(Gui.icons);
			
			int healthY = pos.getAbsoluteY() + (options.getBooleanOption("showName").isToggled() ? font.FONT_HEIGHT + 1 : 0);

            drawTexturedModalRect(pos.getAbsoluteX(), healthY, 0, 74, 182, 5);
            
            int healthWidth = (int) (bossHealthScale * (182.0F + 1.0F));
            
            if (healthWidth > 0) {
            	drawTexturedModalRect(pos.getAbsoluteX(), healthY, 0, 79, healthWidth, 5);
            }
		}
		
		if (options.getBooleanOption("showName").isToggled()) {
			drawText(bossName.replace("�r", ""), pos.getAbsoluteX() + getWidth() / 2 - font.getStringWidth(bossName) / 2, pos.getAbsoluteY(), options.getColorOption("textColor").getColor(), options.getBooleanOption("textShadow").isToggled());
		}
	}
}
