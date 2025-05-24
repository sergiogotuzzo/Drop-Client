package drop.mods.impl;

import net.minecraft.entity.Entity;

import java.awt.Color;

import drop.ColorManager;
import drop.mods.Mod;

public class OldVisuals extends Mod {
	private boolean fishingRod = true;
	private boolean bow = true;
	private boolean blockHitting = true;
	private boolean armorHitAnimation = true;
	private ColorManager hitArmorColor = ColorManager.fromColor(Color.RED).setAlpha(76);
	private boolean hitArmorChroma = false;

	public OldVisuals() {
		setFishingRod((boolean) getFromFile("fishingRod", fishingRod));
		setBow((boolean) getFromFile("bow", bow));
		setBlockHitting((boolean) getFromFile("blockHitting", blockHitting));
		setArmorHitAnimation((boolean) getFromFile("armorHitAnimation", armorHitAnimation));
		setHitArmorColor((int) ((long) getFromFile("hitArmorColor", hitArmorColor.getRGB())));
		setHitArmorChroma((boolean) getFromFile("hitArmorChroma", hitArmorChroma));
	}
	
	public void setFishingRod(boolean enabled) {
		this.fishingRod = enabled;
		
		setToFile("fishingRod", enabled);
	}
	
	public boolean isFishingRodEnabled() {
		return fishingRod;
	}
	
	public void setBow(boolean enabled) {
		this.bow = enabled;
		
		setToFile("bow", enabled);
	}
	
	public boolean isBowEnabled() {
		return bow;
	}
	
	public void setBlockHitting(boolean enabled) {
		this.blockHitting = enabled;
		
		setToFile("blockHitting", enabled);
	}
	
	public boolean isBlockHittingEnabled() {
		return blockHitting;
	}
	
	public void setArmorHitAnimation(boolean enabled) {
		this.armorHitAnimation = enabled;
		
		setToFile("armorHitAnimation", enabled);
	}
	
	public boolean isArmorHitAnimationEnabled() {
		return armorHitAnimation;
	}
	
	public void setHitArmorColor(int rgb) {
		this.hitArmorColor = ColorManager.fromRGB(rgb);
		
		setToFile("hitArmorColor", rgb);
	}
	
	public ColorManager getHitArmorColor() {
		return hitArmorColor;
	}
	
	public void setHitArmorChroma(boolean enabled) {
		this.hitArmorChroma = enabled;
		
		setToFile("hitArmorChroma", enabled);
	}
	
	public boolean isHitArmorChromaEnabled() {
		return hitArmorChroma;
	}
}
