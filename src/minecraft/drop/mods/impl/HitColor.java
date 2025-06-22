package drop.mods.impl;

import net.minecraft.entity.Entity;

import java.awt.Color;

import drop.ColorManager;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiHitColor;
import drop.mods.Mod;

public class HitColor extends Mod {
	private ColorManager hitColor = ColorManager.fromColor(Color.RED, false).setAlpha(76);
	
	public HitColor() {
		super(true);

		setHitColor(getIntFromFile("hitColor", hitColor.getRGB()));
		setHitColorChroma(getBooleanFromFile("hitColorChroma", hitColor.isChromaToggled()));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiHitColor(previousGuiScreen);
	}
	
	public void setHitColor(int rgb) {
		this.hitColor = ColorManager.fromRGB(rgb, hitColor.isChromaToggled());
		
		setToFile("hitColor", rgb);
	}
	
	public ColorManager getHitColor() {
		return hitColor;
	}
	
	public void setHitColorChroma(boolean toggled) {
		hitColor.setChromaToggled(toggled);
		
		setToFile("hitColorChroma", toggled);
	}
	
	public boolean isHitColorChromaToggled() {
		return hitColor.isChromaToggled();
	}
}
