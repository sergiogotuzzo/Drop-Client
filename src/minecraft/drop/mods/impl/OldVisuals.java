package drop.mods.impl;

import net.minecraft.entity.Entity;

import java.awt.Color;

import drop.ColorManager;
import drop.gui.GuiDropClientScreen;
import drop.gui.mod.GuiOldVisuals;
import drop.mods.Mod;

public class OldVisuals extends Mod {
	private boolean fishingRod = true;
	private boolean bow = true;
	private boolean blockHitting = true;
	private boolean armorHitAnimation = true;
	private ColorManager hitArmorColor = ColorManager.fromColor(Color.RED, false).setAlpha(76);
	
	public OldVisuals() {
		super(true);
		
		setFishingRod(getBooleanFromFile("fishingRod", fishingRod));
		setBow(getBooleanFromFile("bow", bow));
		setBlockHitting(getBooleanFromFile("blockHitting", blockHitting));
		setArmorHitAnimation(getBooleanFromFile("armorHitAnimation", armorHitAnimation));
		setHitArmorColor(getIntFromFile("hitArmorColor", hitArmorColor.getRGB()));
		setHitArmorChroma(getBooleanFromFile("hitArmorChroma", hitArmorColor.isChromaToggled()));
	}
	
	@Override
	public GuiDropClientScreen getGui(GuiDropClientScreen previousGuiScreen) {
		return new GuiOldVisuals(previousGuiScreen);
	}
	
	public void setFishingRod(boolean toggled) {
		this.fishingRod = toggled;
		
		setToFile("fishingRod", toggled);
	}
	
	public boolean isFishingRodToggled() {
		return fishingRod;
	}
	
	public void setBow(boolean toggled) {
		this.bow = toggled;
		
		setToFile("bow", toggled);
	}
	
	public boolean isBowToggled() {
		return bow;
	}
	
	public void setBlockHitting(boolean toggled) {
		this.blockHitting = toggled;
		
		setToFile("blockHitting", toggled);
	}
	
	public boolean isBlockHittingToggled() {
		return blockHitting;
	}
	
	public void setArmorHitAnimation(boolean toggled) {
		this.armorHitAnimation = toggled;
		
		setToFile("armorHitAnimation", toggled);
	}
	
	public boolean isArmorHitAnimationToggled() {
		return armorHitAnimation;
	}
	
	public void setHitArmorColor(int rgb) {
		this.hitArmorColor = ColorManager.fromRGB(rgb, hitArmorColor.isChromaToggled());
		
		setToFile("hitArmorColor", rgb);
	}
	
	public ColorManager getHitArmorColor() {
		return hitArmorColor;
	}
	
	public void setHitArmorChroma(boolean toggled) {
		hitArmorColor.setChromaToggled(toggled);
		
		setToFile("hitArmorChroma", toggled);
	}
	
	public boolean isHitArmorChromaToggled() {
		return hitArmorColor.isChromaToggled();
	}
}
